package elements;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;

/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class Camera {

    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;

    //physical dimensions of the "screen"
    private double _width;
    private double _height;
    private double _distance;       // distance between camera and the view plane
    private double _depthOfField;   // distance between view plane and the depth of field plane
    private double _dOFRadius;

    private Point3D getP0() {
        return _p0;
    }

    private Vector getvTo() {
        return _vTo;
    }

    private Vector getvUp() {
        return _vUp;
    }

    private Vector getvRight() {
        return _vRight;
    }

    public double getWidth() {
        return _width;
    }

    public double getHeight() {
        return _height;
    }

    private double getDistance() {
        return _distance;
    }

    public double get_depthOfField() {
        return _depthOfField;
    }

    public Camera set_depthOfField(double _depthOfField) {
        this._depthOfField = _depthOfField;
        return this;
    }

    public double get_dOFRadius() {
        return _dOFRadius;
    }

    public Camera set_dOFRadius(int _dOFRadius) {
        this._dOFRadius = _dOFRadius;
        return this;
    }

    /**
     * @param p0  eye of camera
     * @param vTo vector opposite to Z axis
     * @param vUp vector pointing towards Y axis
     */
    public Camera(Point3D p0, Vector vTo, Vector vUp) {

        if (!isZero(vTo.dotProduct(vUp))) {
            throw new IllegalArgumentException(" vto and vup are not orthogonal");
        }

        _p0 = new Point3D(p0.getX(), p0.getY(), p0.getZ());

        _vTo = vTo.normalized();
        _vUp = vUp.normalized();

        _vRight = _vTo.crossProduct(_vUp).normalize();
    }

    /**
     * @param width
     * @param height
     * @return
     */
    public Camera setViewPlaneSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * @param distance-field
     * @return the camera-builder pattern(chaining)
     */
    public Camera setDistance(double distance) {
        try {
            if (isZero(distance)) {
                throw new IllegalArgumentException("can not be 0");
            }
            _distance = distance;
        } catch (Exception e) {
            System.out.println(" distans can not be 0");
        }
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {

        Point3D pc = _p0.add(_vTo.scale(_distance));

        if (isZero(nX) || isZero(nY)) {
            throw new IllegalArgumentException("can not be 0");
        }
        double ry = _height / nY;
        double rx = _width / nX;
       /* double xj = (i - nX / 2.0) * rx + rx / 2.0;
        double yi = (j - nY / 2.0) * ry + ry / 2.0;*/

        double yi = -(((i - ((nY - 1) / 2d)))*ry);
        double xj = ((j - ((nX - 1) / 2d)))*rx;

        Point3D Pij = pc;
        if (!isZero(xj)) {
            Pij=Pij.add(_vRight.scale(xj));
        }
        if (!isZero(yi)) {
            Pij=Pij.add(_vUp.scale(yi));
        }

        Vector v = Pij.substract(_p0);
        return new Ray(_p0, v);

    }
    /**
     * @param ray the ray from the center of the camera
     * @param n the number of squares in height and width (of the grid)
     *          this function construct a grid around the circle of the camera
     * @return it return the list of all the rays from each pixel in the camera
*/
    public List<Ray> constructRaysGridFromCamera(int n,  Ray ray) { // we construct a square around the circle of the camera, the size n=2*radius(aperture)
                                                                    // we launch a ray (we choose a random point in the pixel)  from each pixel of the grid and
                                                                    // we select only the ray IN the circle of the camera

        List<Ray> myRays = new LinkedList<>(); //the list of all the rays

        double t0 = _depthOfField + _distance; // distance from the central point of the camera to the focus point
        double t = t0/(_vTo.dotProduct(ray.getDir())); // distance from the point on the aperture grid to the focus point ( calcul with the cosinus)
        Point3D point = ray.getPoint(t); // we found the focus point

        double pixelSize = alignZero((_dOFRadius * 2) / n); // the size of each pixel

        // we construct a ray from each pixel of the grid and we select only the rays in the circle
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Ray tmpRay = constructRayFromPixel(n, n, j, i, pixelSize, point);
                // we check if each ray is in the circle of the camera
                if (tmpRay.getPoint3D().equals(_p0)){// if the ray is from the camera center
                    myRays.add(tmpRay); // we add the ray to the list myRays
                }
                else if (tmpRay.getPoint3D().substract(_p0).dotProduct(tmpRay.getPoint3D().substract(_p0)) <= _dOFRadius * _dOFRadius){
                    // if the distance with the center au carre is <= au carre of the radius -> the ray is in the circle of the camera
                    myRays.add(tmpRay); // we add the ray to the list myRays
                }
            }
        }

        return myRays; // we return  the list of all my rays in the circle
    }

    /**
     *
     * @param nX grid's width
     * @param nY grid's height
     * @param j y emplacement of the point
     * @param i x emplacement of the point
     * @param pixelSize size of the pixel
     * @param point point on the focus plane
     * @return a ray from the pixel to the focus point
     */

    private Ray constructRayFromPixel(int nX, int nY, double j, double i, double pixelSize, Point3D point) {

        Point3D pIJ = new Point3D(_p0);

        Random r = new Random(); // we want a random point for each pixel for more precisison

        double xJ = ((j + r.nextDouble() / (r.nextBoolean() ? 2 : -2)) - ((nX - 1) / 2d)) * pixelSize;
        double yI = -((i + r.nextDouble() / (r.nextBoolean() ? 2 : -2)) - ((nY - 1) / 2d)) * pixelSize;

        if (xJ != 0) {
            pIJ = pIJ.add(_vRight.scale(xJ));
        }
        if (yI != 0) {
            pIJ = pIJ.add(_vUp.scale(yI));
        }

        Vector vIJ = point.substract(pIJ);

        return new Ray(pIJ, vIJ); // return a new ray from a pixel
    }
}
