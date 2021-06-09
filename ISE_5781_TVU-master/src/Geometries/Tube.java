package Geometries;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static Primitives.Util.alignZero;
import static Primitives.Util.isZero;



public class Tube extends RadialGeometry {

    protected Ray _axisRay;


    public Tube( double radius, Ray axisRay) {
        super(radius);
        _axisRay = axisRay;
    }

    private Ray getAxisRay() {
        return _axisRay;
    }

    private double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "_axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
    /* normal vector of a Tube
     *  */
    public Vector getNormal(Point3D point){
        Point3D p0=_axisRay.getPoint3D();

        Vector p1 = point.substract(p0);

        Vector p2=_axisRay.getDir();

        double x =alignZero( p2.dotProduct(p1));
        if(isZero(x)){
            return p1;
        }
        Point3D O = _axisRay.getPoint3D().add(p1.scale(x));
        if(O.equals(point)){
            throw new IllegalArgumentException("point can not be reference point of tube");
        }

        Vector N = point.substract(O);

        return N.normalize();
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
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
