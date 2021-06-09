package Geometries;
import Primitives.*;

import java.util.ArrayList;
import java.util.List;

import static Primitives.Util.alignZero;

public class Sphere extends RadialGeometry {
    protected Point3D _center;

    /**
     *
     * @param radius OF SPHERE
     * @param _center OF SPHERE
     */
    /************constractors****/
    /**
     * Constructor for Sphere class, gets a radius and a center point3D, and creates a new sphere
     * @param radius radius of a sphere
     * @param center a point3D, the center point of a sphere
     */
    public Sphere(double radius, Point3D center) {
        super(radius);
        this._center = center;
    }

    /**********getter**********/
    public Point3D get_center() {
        return _center;
    }
    /*****************to string  ***********/

    @Override
    public String toString() {
        return "Sphere{" +
                "_center=" + _center +
                '}';
    }
    @Override
    public Vector getNormal(Point3D point) {
        if (point.equals(_center)) {
            throw new IllegalArgumentException("point isnt ok to be 0");
        }
        Vector n = point.substract(_center);
        return n.normalized();
    }

    /**
     * find intersections point3D with sphere
     * @param ray ray for casting
     * @return list of intersections point3D or null if there were not found
     */
    public List<Point3D> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint3D();
        Vector v = ray.getDir();
        Vector u;
        // 𝑢 = 𝑂 − 𝑃0 distance from the center and the p0
        try {
            u = _center.substract(p0);
        } catch (IllegalArgumentException e) {
            return List.of(ray.getPoint(_radius));
        }
        //tm=v*u the distance between p0 and the point with makes 90 degrees with the center
        double tm = alignZero(v.dotProduct(u));
        //d=u^2+tm^2 distance between the center and the point that  makes 90 degrees with the center
        double dsquared;
        if (tm == 0)
            dsquared = u.lengthSquared();
        else {
            dsquared = u.lengthSquared() - tm * tm;
        }
        double thsquared = alignZero(_radius * _radius - dsquared);

        if (thsquared <= 0) return null;
        //th=radius*radius-d*d between p1 and center
        double th = alignZero(Math.sqrt(thsquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;

        if (t1 > 0 && t2 > 0)
            return List.of(ray.getPoint(t1), ray.getPoint(t2)); //P1 , P2
        if (t1 > 0)
            return List.of(ray.getPoint(t1)); //just one point
        if (t2 > 0)
            return List.of(ray.getPoint(t2));
        return null;
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

