package unittests.geometries;

import Geometries.Cylinder;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.Test;

import static org.junit.Assert.*;

public class CylinderTest {

    @Test
    public void testGetNormal() {
        Cylinder Cyl=new Cylinder(new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)), 2, 6);
        Vector v1=new Vector(0, 1, 0);
        Vector v2=new Vector(0, 0, 6);
        Vector v3=new Vector(0, 0, -1);
        // ============ Equivalence Partitions Tests ==============
        //TC01: test on the cylinder's side
        assertEquals("It is not the cylinder's normal",v1,Cyl.getNormal(new Point3D(0,2,3)));
        //TC02: test on the cylinder's bottom base
        assertEquals("It is not the bottom base's normal",v3,Cyl.getNormal(new Point3D(1,1,0)));
        //TC03: test on the cylinder's up base
        assertEquals("It is not the up base's normal",v2.normalized(),Cyl.getNormal(new Point3D(1,0,6)));
        // ============ Boundary Values Tests ==============
        //TC04: test on the cylinder's bottom base center
        assertEquals("It is not the bottom base center's normal",v3,Cyl.getNormal(new Point3D(0,0,0)));
        //TC05: test on the cylinder's up base center
        assertEquals("It is not the up base center's normal",v2.normalized(),Cyl.getNormal(new Point3D(0,0,6)));
    }
}