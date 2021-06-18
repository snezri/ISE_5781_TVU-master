package Primitives;
/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */

import Geometries.Intersectable;

import java.util.List;

import static Primitives.Util.isZero;


public class Ray {
    private static final double DELTA = 0.1;
    Point3D p0;
    Vector dir;

    public Ray(Point3D p, Vector v){
        this.p0=p;
        this.dir=v.normalized();
    }

    /**
     * New constructor
     * @param head
     * @param direction
     * @param normal
     */
    public Ray(Point3D head, Vector direction, Vector normal) {
        if (direction.dotProduct(normal) == 0) {
            p0 = head;
            dir = direction;
        } else {
            int sign = 1;
            // if (direction.dotProduct(normal)>0)
            // sign=1;
            if (direction.dotProduct(normal) < 0)
                sign = -1;
            p0 = head.add(normal.scale(sign * DELTA));
            dir = direction;
        }
    }

    public Point3D getPoint3D(){
        return p0;
    }

    public Vector getDir(){
        return dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return p0.equals(other.p0) && dir.equals(other.dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + p0 +
                ", dir=" + dir +
                '}';
    }

    public Point3D getPoint(double delta ){
        if (isZero(delta)){
            return  p0;
        }
        return p0.add(dir.scale(delta));
    }

    /**
     * find the closest Point to Ray origin
     * @param pointsList intersections point List
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> pointsList){
        Point3D result =null;
        double closestDistance = Double.MAX_VALUE;

        if(pointsList== null){
            return null;
        }

        for (Point3D p: pointsList) {
            double temp = p.distance(p0);
            if(temp < closestDistance){
                closestDistance =temp;
                result =p;
            }
        }

        return  result;}

    /**
     * get the closest GeoPoint in the list of points
     * @param points list of intersection points
     * @return the closest point
     */
    public Intersectable.GeoPoint getClosestGeoPoint(List<Intersectable.GeoPoint> points) {
        if (points == null)
            return null;

        Intersectable.GeoPoint myPoint = points.get(0);

        for (var point : points
        ) {
            if (p0.distance(myPoint.point3D) > p0.distance(point.point3D)) {
                myPoint = point;
            }
        }

        return myPoint;
    }
}
