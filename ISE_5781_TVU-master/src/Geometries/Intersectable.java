package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Intersectable  for all the geometries to find their intersections
 */

public interface Intersectable {

    /**
     * GeoPoint is a Static Internal Auxiliary Department (as a completely passive data structure - PDS)
     * it represents a point on a geometry
     */
    public static class GeoPoint
    {

        public Geometry geometry;
        public Point3D point;

        /**
         * Constructor of GeoPoint with 2 parameters:
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        /**
         * Equals to compare the parameters of two objects of type GeoPoints
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            GeoPoint other = (GeoPoint) obj;
            if (geometry == null) {
                if (other.geometry != null)
                    return false;
            } else if (geometry!=other.geometry)
                return false;
            if (point == null) {
                if (other.point != null)
                    return false;
            } else if (!point.equals(other.point))
                return false;
            return true;
        }

    }

    /**
     *function to find intersections with a ray
     *@param ray which intersect
     *@return a list of intersections points
     *
     */
    default List<Point3D> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }


    List<GeoPoint> findGeoIntersections(Ray ray);
}
