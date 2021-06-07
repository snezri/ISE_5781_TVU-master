package unittests.geometries;

import Geometries.Plane;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlaneTests {
    /**
     * Test method for {@link Geometries.Plane#getNormal(Primitives.Point3D)}.
     */
    @Test
    public void testGetNormalPlane() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p = new Plane(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("It is not the sphere's normal",new Vector(sqrt3, sqrt3, sqrt3), p.getNormal(new Point3D(0, 0, 1)));
    }

    @Test
    public void testConstructor(){
        // ============ Equivalence Partitions Tests ==============
        Plane P=new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        // =============== Boundary Values Tests ==================
        //TC01: The first and second point are the same
        Point3D p1=new Point3D(1,0,0);
        Point3D p2=new Point3D(2,0,0);
        Point3D p3=new Point3D(4,0,0);
        try{
            Plane Pl=new Plane(p1,p1,p3);
            fail("can't create a plane with only two points");

        } catch (IllegalArgumentException e) {}
        //TC10: The three points are on the same line
        try{
            Plane Pl=new Plane(p1,p2,p3);
            fail("can't create a plane because the three points are on the same line");

        } catch (IllegalArgumentException e) {}

    }


    /**
     * Test method for {@link Geometries.Plane#findIntersections(Primitives.Ray)}.
     */
    @Test
    public void testfindIntersectionsRay() {
        Plane pl = new Plane(new Point3D(0, 0, 1), new Vector(1, 1, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray into plane
        assertEquals(List.of(new Point3D(1, 0, 0)),
                pl.findIntersections(new Ray(new Point3D(0.5, 0, 0), new Vector(1, 0, 0))));

        // TC02: Ray out of plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))));

        // =============== Boundary Values Tests ==================
        // TC11: Ray parallel to plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(0, 1, -1))));

        // TC12: Ray in plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(0, 0.5, .5), new Vector(0, 1, -1))));


        // TC13: Orthogonal ray into plane
        assertEquals(List.of(new Point3D(1d / 3, 1d / 3, 1d / 3)),
                pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(-1, -1, -1))));

        // TC14: Orthogonal ray out of plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));

        // TC15: Orthogonal ray out of plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 1, 1))));

        // TC16: Orthogonal ray from plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 1))));

        // TC17: Ray from plane
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(0, 0.5, 0.5), new Vector(1, 1, 0))));

        // TC18: Ray from plane's Q point
        assertNull("Must not be plane intersection",pl.findIntersections(new Ray(new Point3D(0, 0, 1), new Vector(1, 1, 0))));

    }
}