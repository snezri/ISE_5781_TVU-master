package Geometries;

import Primitives.*;

import java.util.ArrayList;
import java.util.List;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;

/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class Plane extends Geometry {
    Point3D p;
    Vector _normal;

    @Override
    public String toString() {
        return "Plane{" +
                "p=" + p +
                ", normal=" + _normal +
                '}';
    }

    /**
     * @param p1 point 1
     * @param p2 point 2
     * @param p3 point 3
     */
    /*public Plane(Point3D p1, Point3D p2, Point3D p3) {
        Vector U = new Vector (p1, p2);
        Vector V = new Vector (p1, p3);
        Vector N = U.crossProduct(V);

        N.normalize();

        this.normal=N.scale(-1);
        this.p=p1;
    }*/
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        super();
        p = p1;

        Vector u = p2.substract(p1);
        Vector v = p3.substract(p1);

        Vector n = u.crossProduct(v);

        _normal = n.normalized();
    }

    public Plane(Point3D p1, Vector normal) {
        super();
        this.p = p1;
        this._normal = normal.normalized();
    }

    /**
     * @param point of Point3D in plane
     * @return Normal for plane
     */
    @Override
    public Vector getNormal(Point3D point) {
        return _normal;
    }

    /*because polygon
     * @return vector normal in a plane
     */
    public Vector getNormal() {
        return getNormal(new Point3D(0, 0, 0));
    }

    /********getter********/
    public Point3D getP() {
        return p;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector p0Q;
        try {
            p0Q = p.substract(ray.getPoint3D());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from point Q - no intersections
        }

        double nv = _normal.dotProduct(ray.getDir());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(p0Q) / nv);

        return t <= 0 ? null : List.of(ray.getPoint(t));
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<Point3D> intersections = findIntersections(ray);
        if (intersections == null) return null;
        List<GeoPoint> result = new ArrayList<>();
        for (Point3D p : intersections) {
            result.add(new GeoPoint(this, p));
        }
        return result;
    }
}
