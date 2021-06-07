package Geometries;

import Primitives.*;

import java.util.List;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;

public class Tube extends Geometry{
    Ray axisRay;
    double radius;
    /**
     * Tube constructor to create a tube with a axis ray and a radius
     * @param R
     * @param rad
     */

    public Tube(Ray R, double rad){
        this.axisRay=R;
        this.radius=rad;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() {
        return "Tube{" + "axisRay=" + axisRay + ", radius=" + radius + '}'; }


    @Override
    public Vector getNormal(Point3D p) {
        Point3D P0 = axisRay.getPoint3D();
        Vector v = axisRay.getDir();
        Vector P0_P = p.substract(P0);
        double t = alignZero(v.dotProduct(P0_P));
        if (isZero(t)) {
            return P0_P.normalize();
        }
        Point3D o = P0.add(v.scale(t));
        if (p.equals(o)) {
            throw new IllegalArgumentException("point can't be on the tube axis");
        }
        Vector n = p.substract(o).normalize();
        return n;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
