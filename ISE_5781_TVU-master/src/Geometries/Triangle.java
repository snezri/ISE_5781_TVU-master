package Geometries;

import Primitives.*;

import java.util.List;

/**
 * Triangle class extends Polygon
 */
public class Triangle extends Polygon {

    /**
     * Triangle constructor to create a triangle with 3 points
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle [p1=" + vertices.get(0) + ", p2=" + vertices.get(1) + ", p3=" + vertices.get(2) + "]";
    }


    /**
     *The function findGeoIntersections find all the intersections of a triangle by a ray
     *
     * @return list of GeoPoints
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        // First check the intersections with the plane
        Point3D point3d = vertices.get(0);
        Vector v = vertices.get(0).substract(vertices.get(1));
        Vector w = vertices.get(1).substract(vertices.get(2));
        Vector normal = (v.crossProduct(w)).normalize();
        Plane plane = new Plane(point3d, normal);
        if (plane.findIntersections(ray) == null)
            return null;

        // After check if they're in the triangle
        Vector v1 = (ray.getPoint3D().substract(vertices.get(0)));
        Vector v2 = (ray.getPoint3D().substract(vertices.get(1)));
        Vector v3 = (ray.getPoint3D().substract(vertices.get(2)));
        Vector N1 = (v1.crossProduct(v2)).normalize();
        Vector N2 = (v2.crossProduct(v3)).normalize();
        Vector N3 = (v3.crossProduct(v1)).normalize();
        if (Util.alignZero(ray.getDir().dotProduct(N1)) == 0 || Util.alignZero(ray.getDir().dotProduct(N2)) == 0
                || Util.alignZero(ray.getDir().dotProduct(N3)) == 0)
            return null;

        if ((ray.getDir().dotProduct(N1) > 0 && ray.getDir().dotProduct(N2) > 0 && ray.getDir().dotProduct(N3) > 0)
                || (ray.getDir().dotProduct(N1) < 0 && ray.getDir().dotProduct(N2) < 0
                && ray.getDir().dotProduct(N3) < 0)) {
            return List.of(new GeoPoint (this, plane.findIntersections(ray).get(0)));
        }

        else
            return null;

    }



}