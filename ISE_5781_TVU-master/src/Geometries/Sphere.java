package Geometries;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.List;

public class Sphere extends Geometry{
    private Point3D center;
    private double radius;

    public Sphere(Point3D p, double r){
        this.center=p;
        this.radius=r;
    }

    public Point3D getCenter(){
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String toString() { return "Sphere{" + "center=" + center + ", radius=" + radius + '}'; }

    @Override
    public Vector getNormal(Point3D p0) {return getCenter().substract(p0);}

    /**
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if (ray.getPoint3D().equals(center)) {
            return List.of(new GeoPoint (this, ray.getPoint(radius)));
        }
        else {
            Vector u = ray.getPoint3D().substract(center);
            double tm = u.dotProduct(ray.getDir());
            double d = Math.sqrt(u.lengthSquared() - (tm * tm));
            if (d >= radius)
                return null;
            double th = Math.sqrt((radius * radius) - (d * d));
            double t1 = th + tm;
            double t2 = tm - th;
            if (t1 <= 0 && t2 <= 0)
                return null;
            else {
                if (t1 > 0 && t2 > 0)
                    return List.of(new GeoPoint (this, ray.getPoint(t1)),(new GeoPoint (this, ray.getPoint(t2))));
                else if (t1 > 0 )
                    return List.of(new GeoPoint (this, ray.getPoint(t1)));
                else if (t2 > 0)
                    return List.of(new GeoPoint (this, ray.getPoint(t2)));
            }
            return null;
        }

    }
}
