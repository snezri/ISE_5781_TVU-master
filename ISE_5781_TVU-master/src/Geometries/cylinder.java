package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.List;

public class cylinder extends Tube {

    double height;

    /* Constructor */

    public cylinder(double height, Ray r, double radius) {
        super(radius, r);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return super.findIntersections(ray);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", _axisRay=" + _axisRay +
                ", _radius=" + _radius +
                '}';
    }
}
