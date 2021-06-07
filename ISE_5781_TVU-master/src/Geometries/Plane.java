package Geometries;

import Primitives.*;
import java.util.List;


public class Plane extends Geometry {
    private Point3D p0;
    private Vector normal;

    /**
     * Plane constructor to create a plane with 3 points
     *
     * @param p1
     * @param p2
     * @param p3
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3){
        this.p0=p1;
        Vector v1= p2.substract(p1);
        Vector v2=p3.substract(p2);
        normal=(v1.crossProduct(v2)).normalize();
    }
    /**
     * Plane constructor to create a plane with a point and a normal
     *
     * @param p
     * @param v
     */
    public Plane(Point3D p, Vector v){
        this.p0=p;
        this.normal=v;
    }

    public Point3D getPoint3D(){ return p0;}
    public Vector getVector(){ return normal;}
    public void setPoint3d(Point3D point3d) {
        this.p0= point3d;
    }
    public void setVector(Vector normal) {
        this.normal = normal;
    }

    @Override
    public String toString() { return "Plane{" +
                "p0=" + p0 +
                ", normal=" + normal +
                '}'; }

    @Override
    public Vector getNormal(Point3D p) {
        return normal;
    }

    public Vector getNormal() { return null;}

    /**
     * @param ray
     * @return
     */
    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if(ray.getPoint3D().equals(p0))
            return null;

        //denominator == 0
        if(Util.isZero(normal.dotProduct(ray.getDir()))) 		//if the plane is parallel to the ray
            return null;

        //numerator == 0
        if (Util.isZero(normal.dotProduct(ray.getPoint3D().substract(p0))))
            return null;

        else
        {
            double t=Util.alignZero((normal.dotProduct(ray.getPoint3D().substract(p0)))/(normal.dotProduct(ray.getDir())));
            if (t<0)
                return null;
            else
            {
                return List.of(new GeoPoint(this, ray.getPoint(t)));
            }
        }

    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plane other = (Plane) obj;
        if (p0 == null) {
            if (other.p0 != null)
                return false;
        } else if (!p0.equals(other.p0))
            return false;
        if (normal == null) {
            if (other.normal != null)
                return false;
        } else if (!normal.equals(other.normal))
            return false;
        return true;
    }
}