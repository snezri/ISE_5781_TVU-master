package unittests.primitives;

import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RayTest {

    @Test
    public void findClosestPoint() {


        // ============ Equivalence Partitions Tests ==============
        //TC01: The middle point is the closest point to the ray
        Ray ray = new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 50));

        List<Point3D> list1 = new LinkedList<Point3D>();
        list1.add(new Point3D(0, 0, 200));
        list1.add(new Point3D(1, 0, 3));
        list1.add(new Point3D(0, 2, -200));
        list1.add(new Point3D(0, -40, -10));
        assertEquals("the middle is not the closest",list1.get(1), ray.findClosestPoint(list1));

        // =============== Boundary Values Tests ==================
        //TC02: The list is null
        List<Point3D> list2 = null;
        assertNull("try again",ray.findClosestPoint(list2));

        //TC03: The first point is the closest point to the ray
        List<Point3D> list3 = new LinkedList<Point3D>();
        list3.add(new Point3D(1, 0, 3));
        list3.add(new Point3D(0, 0, 200));
        list3.add(new Point3D(0, 2, -200));
        list3.add(new Point3D(0, -40, -10));
        assertEquals("the first point is not the closest",list3.get(0), ray.findClosestPoint(list3));

        //TC04: The last point of the list is the closest point to the ray
        List<Point3D> list4 = new LinkedList<Point3D>();
        list4.add(new Point3D(0, 0, 200));
        list4.add(new Point3D(0, 2, -200));
        list4.add(new Point3D(0, -40, -10));
        list4.add(new Point3D(1, 0, 3));
        assertEquals("the last point is not the closest",list4.get(3), ray.findClosestPoint(list4));
    }

}