package unittests.primitives;

import Primitives.Point3D;
import Primitives.Vector;
import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTests {

    @Test
    public void testSubstract() {
        Point3D p1=new Point3D(1,2,6);
        // ============ Equivalence Partitions Tests ==============
        Point3D p2=new Point3D(0,2,4);
        Vector vr=p1.substract(p2);
        Vector expected=new Vector (1, 0, 2);
        assertEquals("substractPoint3D() wrong result ",expected,vr);
        // =============== Boundary Values Tests ==================
        // TC11: test Point zero
        Point3D p3 = new Point3D(1, 2, 6);
        try {
            p1.substract(p3);
            fail("Add for vector 0 does not throw an exception");
        } catch (Exception e) {}
    }

    @Test
    public void testAdd() {
        Point3D p1=new Point3D(1,2,6);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector(0,2,4);
        Point3D vr=p1.add(v2);
        Point3D expected=new Point3D (1, 4, 10);
        assertEquals("addPoint3D() wrong result ",expected,vr);
    }

    @Test
    public void testDistanceSquared() {
        Point3D p1=new Point3D(1,5,6);
        // ============ Equivalence Partitions Tests ==============
        Point3D p2=new Point3D(1,2,2);
        double length= p1.distanceSquared(p2);
        double expected= 25;
        assertEquals("distanceSquaredPoint3D() wrong result ",expected,length,0.00001 );
    }

    @Test
    public void distance() {
        Point3D p1=new Point3D(1,5,6);
        // ============ Equivalence Partitions Tests ==============
        Point3D p2=new Point3D(1,2,2);
        double length= p1.distance(p2);
        double expected= 5;
        assertEquals("distancePoint3D() wrong result ",expected,length,0.00001 );
    }
}