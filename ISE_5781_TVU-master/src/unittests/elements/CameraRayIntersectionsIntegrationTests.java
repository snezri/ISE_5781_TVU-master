package unittests.elements;

import Geometries.Intersectable;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import elements.Camera;
import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CameraRayIntersectionsIntegrationTests {

    public ArrayList<Ray> RayConstruction(Camera cam){
        ArrayList<Ray> rays=new ArrayList<Ray>();
        double width=cam.getWidth();
        double height=cam.getHeight();
        for(int i=0;i<width;i++){
            for(int j=0; j<height;j++){
                rays.add(cam.constructRayThroughPixel((int)width,(int) height,j,i));
            }
        }
        return rays;
    }

    public int countIntersections(ArrayList<Ray> ray, Intersectable element){
        int count=0;
        for(int i=0; i<ray.size(); i++){
            count+=element.findIntersections(ray.get(i)).size();
        }
        return count;
    }

    private void assertCountIntersections(Camera cam, Intersectable geo, int expected) {
        int count = 0;

        List<Point3D> allpoints = null;

        cam.setViewPlaneSize(3, 3);
        cam.setDistance(1);
        int nX =3;
        int nY =3;
        //view plane 3X3 (WxH 3X3 & nx,ny =3 => Rx,Ry =1)
        for (int i = 0; i < nY; ++i) {
            for (int j = 0; j < nX; ++j) {
                var intersections = geo.findIntersections(cam.constructRayThroughPixel(nX, nY, j, i));
                if (intersections != null) {
                    if (allpoints == null) {
                        allpoints = new LinkedList<>();
                    }
                    allpoints.addAll(intersections);
                }
                count += intersections == null ? 0 : intersections.size();
            }
        }

        System.out.format("there is %d points:%n", count);
        if (allpoints != null) {
            for (var item : allpoints) {
                System.out.println(item);
            }
        }
        System.out.println();

        assertEquals("Wrong amount of intersections",expected, count);
    }

    @Test
    public void cameraRaySphereIntegration() {
        Camera cam1=new Camera(new Point3D(0,0,0),new Vector(0,1,0),new Vector(-1,0,0));
        Camera cam2=new Camera(new Point3D(0,0,0.5),new Vector(0,1,0),new Vector(-1,0,0));
        cam1.setViewPlaneSize(3,3);
        cam1.setDistance(1);
        cam2.setViewPlaneSize(3,3);
        cam2.setDistance(1);

        //TC01: 2 intersections
        ArrayList<Ray> ray1= RayConstruction(cam1);
        assertEquals(" Was supposed to be 2 intersections",
                2,countIntersections(ray1,new Sphere(new Point3D(0,0,-3),1)));

        // TC02: 18 intersections points
        ArrayList<Ray> ray2= RayConstruction(cam2);
        assertEquals(" Was supposed to be 18 intersections",
                18,countIntersections(ray2,new Sphere(new Point3D(0,0,-2.5),2.5)));

        // TC03: 10 intersections points
        assertEquals(" Was supposed to be 10 intersections",
                10,countIntersections(ray2,new Sphere(new Point3D(0,0,-2),2)));

        // TC04: 9 intersections points
        assertEquals(" Was supposed to be 9 intersections",
                10,countIntersections(ray1,new Sphere(new Point3D(0,0,-1),4)));

        // TC05: No intersections points
        assertEquals(" Was supposed to be 0 intersections",
                0,countIntersections(ray1,new Sphere(new Point3D(0,0,1),0.5)));
    }

    @Test
    public void cameraRayPlaneIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Plane against camera 9 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 0, 1)), 9);

        // TC02: Plane with small angle 9 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 2)), 9);

        // TC03: Plane parallel to lower rays 6 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);

        // TC04: Beyond Plane 0 points
        assertCountIntersections(cam, new Plane(new Point3D(0, 0, -5), new Vector(0, 1, 1)), 6);
    }

    @Test
    public void cameraRayTriangleIntegration() {
        Camera cam = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0));

        // TC01: Small triangle 1 point
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -1, -2)), 1);

        // TC02: Medium triangle 2 points
        assertCountIntersections(cam, new Triangle(new Point3D(1, 1, -2), new Point3D(-1, 1, -2), new Point3D(0, -20, -2)), 2);
    }

}
