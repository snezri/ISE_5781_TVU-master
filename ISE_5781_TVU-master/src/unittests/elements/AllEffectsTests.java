package unittests.elements;


import org.junit.Test;
import elements.*;
import Geometries.Plane;
import Geometries.Sphere;
import Geometries.Triangle;
import Primitives.*;
import renderer.*;
import scene.Scene;


public class AllEffectsTests {

    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void newImage() {

        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setViewPlaneSize(200, 200).setDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(60)),
                new Sphere(30, new Point3D(60, 50, -50)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setnShininess(30).setkT(0.6)),
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setnShininess(30)), //
                new Plane(new Point3D(-70, 70, -140), new Vector(1,-0.3,0)).setMaterial(new Material().setkR(0.5)));//plane miror
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-80, -50, 20), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));


        ImageWriter imageWriter = new ImageWriter("AllEffectsTests", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracerBase (new RayTracerBasic(scene));

        render.renderImage();
        render.writeToImage();
    }

}
