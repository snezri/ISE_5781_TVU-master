package unittests.geometries;

import java.util.List;
import Geometries.*;
import Geometries.Geometries;
import Primitives.*;
import org.junit.Test;

import java.awt.*;
import java.io.LineNumberInputStream;

import static org.junit.Assert.*;

public class GeometriesTests {

    @Test
    public void testFindIntersections(){
        Geometries geo=new Geometries();
        Geometries emptyList=new Geometries();
        Plane Pl=new Plane(new Point3D(-4, 0, 0),new Vector(4,0,0));
        Triangle Tri=new Triangle(new Point3D(3, 2, 0), new Point3D(3, -2, 0), new Point3D(6, 0, 6));
        Sphere Sph=new Sphere(new Point3D(1,0,0),1);
        geo.add(Pl);
        geo.add(Tri);
        geo.add(Sph);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Some forms are intersected with the ray
        assertEquals("Some forms are intersected with the ray",
                2,geo.findIntersections(new Ray((new Point3D(4,0,0)),new Vector(1,0,0))).size());
        // =============== Boundary Values Tests ==================
        //TC02: The geometrie's list is empty
        assertNull("The geometrie's list is empty",emptyList.findIntersections(new Ray((new Point3D(4,0,0)),new Vector(1,0,0))));
        //TC02: There is no intersection
        assertNull("There is no intersection",geo.findIntersections(new Ray(new Point3D(20,20,30),new Vector(40,50,50))));
        //TC02: There is only one intersection
        assertEquals("There is only one intersection",
                1,geo.findIntersections(new Ray((new Point3D(2,1,0)),new Vector(4,1,0))).size());
        //TC02: Every forms are intersected
        assertEquals("Every forms are intersected",
                3,geo.findIntersections(new Ray((new Point3D(6,0,0)),new Vector(-1,0,0))).size());
    }

}

