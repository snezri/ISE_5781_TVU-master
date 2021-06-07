package Geometries;

import Primitives.*;

public class Cylinder extends Tube{
    double height;

    public Cylinder(Ray r , double rad, double height) {
        super(r,rad);
        height = height;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() { return "Cylinder{" + "height=" + height + '}'; }

    @Override
    public Vector getNormal(Point3D p0) {
        return null;
    }
}
