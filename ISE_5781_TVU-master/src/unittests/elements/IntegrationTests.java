package unittests.elements;


import Geometries.Geometries;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Vector;
import elements.Camera;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntegrationTests {

    /**
     *
     * when the view plane is 3 x 3
     * @param geometrie
     * @return numbers of intersections of the rays of the camera, with the geometries, through the 3x3 view plane.
     */
    public int numbersIntersections(Camera camera, Geometries geometrie) {
        int intersections = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (geometrie.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)) != null) //if there are intersections
                    intersections += geometrie.findIntersections(camera.constructRayThroughPixel(3, 3, i, j)).size(); //add to intersections, the num of intersections of the geometries by the ray
        return intersections;
    }

    /**
     * Test method for {@link elements.Camera} intersections with a sphere
     */
    @Test
    public void SphereTests() {
        Camera camera1 = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setViewPlaneSize(3, 3);
        Camera camera2 = new Camera(new Point3D(0, 0, 0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setViewPlaneSize(3, 3);
        // TC01: sphere after the camera 2 intersections
        assertEquals("Mistake in the calcul of intersections points with a sphere", 2,
                numbersIntersections(camera1, new Geometries(new Sphere(1, new Point3D(0, 0, -3)))));
        // TC02: view plane in the sphere 18 intersections
        assertEquals("Mistake in the calcul of intersections points when the wiew plane is in the sphere", 18,
                numbersIntersections(camera2, new Geometries(new Sphere(2.5, new Point3D(0, 0, -2.5)))));
        // TC03: view plane in the sphere 10 intersections
        assertEquals("Mistake in the calcul of intersections points when the wiew plane is in the sphere ", 10,
                numbersIntersections(camera2, new Geometries(new Sphere(2, new Point3D(0, 0, -2)))));
        // TC04: camera in the sphere 9 intersections
        assertEquals("Mistake in the calcul of intersections points when the camera is in the sphere", 9,
                numbersIntersections(camera1, new Geometries(new Sphere(4, new Point3D(0, 0, 0)))));
        // TC05: sphere behind the camera, no intersections
        assertEquals("Mistake in the calcul of intersections points with a sphere behind the camera ", 0,
                numbersIntersections(camera1, new Geometries(new Sphere(0.5, new Point3D(0, 0, 1)))));
    }

    /**
     * Test method for {@link elements.Camera} intersections with a plane
     */
    @Test
    public void PlaneTests() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setViewPlaneSize(3, 3);
        // TC06: plane parallel to the view plane 9 intersections
        assertEquals("Mistake in the calcul of intersections points with a plane parallel to the view plane ", 9,
                numbersIntersections(camera, new Geometries(new Plane(new Point3D(0, 0, -3), new Vector(0, 0, 1)))));
        // TC07: plane 9 intersections
        assertEquals("Mistake in the calcul of intersections points with a plane", 9,
                numbersIntersections(camera, new Geometries(new Plane(new Point3D(0, 0, -1), new Vector(0, -0.5, 1)))));
        // TC08: plane 6 intersections
        assertEquals("Mistake in the calcul of intersections points with a plane", 6,
                numbersIntersections(camera, new Geometries(new Plane(new Point3D(0, 0, -5), new Vector(0, -2, 1)))));
    }

    /**
     * Test method for {@link elements.Camera} intersections with a triangle
     */
    @Test
    public void TriangleTests() {
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1)
                .setViewPlaneSize(3, 3);
        // TC09: triangle 1 intersection
        assertEquals("Mistake in the calcul of intersections points with a small triangle ", 1,
                numbersIntersections(camera, new Geometries(
                        new Triangle(new Point3D(0, 1, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2)))));
        // TC10: triangle 2 intersections
        assertEquals("Mistake in the calcul of intersections points with a big triangle ", 2,
                numbersIntersections(camera, new Geometries(
                        new Triangle(new Point3D(0, 20, -2), new Point3D(1, -1, -2), new Point3D(-1, -1, -2)))));
    }

}
