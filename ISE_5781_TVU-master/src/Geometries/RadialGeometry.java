package Geometries;

import Primitives.*;

import java.util.List;
/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public abstract class RadialGeometry extends Geometry {
    protected final double _radius;

    public RadialGeometry(double radius) {
        if(radius <= 0){
            throw new IllegalArgumentException("radious can not be <= 0");
        }
        _radius = radius;
    }

    private double getRadius() {
        return _radius;
    }
    public abstract Vector getNormal(Point3D point);

    public abstract List<Point3D> findIntersections(Ray ray);
}
