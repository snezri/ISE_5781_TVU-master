package elements;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import static Primitives.Util.isZero;

public class Camera {
    Point3D p0;
    private Vector vUp, vRight, vTo;
    double width, heigth, distance;

    /**
     * Constructor - Construct the camera and normalize the vectors and create the normal vector
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        this.p0 = p0;
        if(vUp.dotProduct(vTo)!=0)
            throw new IllegalArgumentException("vUp and vTo are not orthogonals");
        this.vUp=vUp.normalized();
        this.vTo=vTo.normalized();
        this.vRight=(vUp.crossProduct(vTo)).normalized();
    }

    /**
     * Vector getter
     * @return vUp
     */
    public Vector getvUp() { return vUp; }

    /**
     * Vector getter
     * @return vTo
     */
    public Vector getvTo() { return vTo; }

    /**
     * Vector getter
     * @return vRight
     */
    public Vector getvRight() { return vRight; }

    /**
     * Vector getter
     * @return P0
     */
    public Point3D getP0() { return p0;}

    /**
     * Vector getter
     * @return Height
     */
    public double getHeight(){return heigth;}

    /**
     * Vector getter
     * @return Width
     */
    public double getWidth(){return width;}

    /**
     * Vector getter
     * @return Distance
     */
    public double getDistance(){return distance;}


    /**
     * Vector setter
     * @param height , width
     */
    public Camera setViewPlaneSize(double width, double height){
        this.width=width;
        this.heigth=height;
        return this;
    }

    /**
     * Vector setter
     * @param distance
     */
    public Camera setDistance(double distance){
        this.distance=distance;
        return this;
    }

    /**
     *constructing a ray passing through a pixel
     */
    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        Point3D Pc = p0.add(vTo.scale(distance));

        double Rx = width / nX;
        double Ry = heigth / nY;

        Point3D Pij = Pc;

        double Xj = (j - (nX - 1) / 2d) * Rx;
        double Yi = -(i - (nY - 1) / 2d) * Ry;

        if (isZero(Xj) && isZero(Yi)) {
            return new Ray(p0, Pij.substract(p0));
        }
        if (isZero(Xj)) {
            Pij = Pij.add(vUp.scale(Yi));
            return new Ray(p0, Pij.substract(p0));
        }
        if (isZero(Yi)) {
            Pij = Pij.add(vRight.scale(Xj));
            return new Ray(p0, Pij.substract(p0));
        }

        Pij = Pij.add(vRight.scale(Xj).add(vUp.scale(Yi)));
        return new Ray(p0, Pij.substract(p0));

    }
}
