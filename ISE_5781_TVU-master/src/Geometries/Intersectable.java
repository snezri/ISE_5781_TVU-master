package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * return list for Cutting points between a Ray and various bodies
 */
public interface Intersectable {

    /**
     * A PDS class for relating a geometry
     * to a point.
     */
    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point3D;

        /**
         * Constructor for the GeoPoint class
         * @param geo The geometry to be set
         * @param point The point to be set
         */
        public GeoPoint(Geometry geo, Point3D point) {
            geometry = geo;
            point3D = point;
        }

        public boolean equals(Object o) {
            if (o == this)
                return true;

            if (!(o instanceof GeoPoint))
                return false;

            GeoPoint gp = (GeoPoint)o;
            return this.geometry.equals(gp.geometry) && this.point3D.equals(gp.point3D);
        }
    }
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null :
                geoList.stream().map(gp -> gp.point3D).collect(Collectors.toList());
    }

    /**
     * Instead of returning a list of points, this
     * method shall return a list of points related to
     * geometries where they are located.
     * @param ray The ray to check for intersections.
     * @return A list of GeoPoint objects, containing
     * intersections points with the given ray, where each point
     * comes with the geometry where it is located.
     */
    public List<GeoPoint> findGeoIntersections(Ray ray);
}
