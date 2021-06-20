package unittests.renderer;

import Geometries.Sphere;
import Primitives.Color;
import Primitives.Material;
import Primitives.Point3D;
import Primitives.Vector;
import elements.Camera;
import elements.DirectionalLight;
import org.junit.Test;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;
import scene.Scene;

public class DepthOfFieldTest {
    @Test
    public void depthOfFieldTest() {

        Scene scene = new Scene("basic depth of field test");

        Camera camera = new Camera(new Point3D(0,10,500),new Vector(0,0,-1),new Vector(0,1,0))
                .setDistance(450)
                .setViewPlaneSize(30,30)
                .set_depthOfField(50)
                .set_aperture(3);

        Material mat = new Material().setKd(0.5).setkT(0.1).setkR(0.8).setKs(0.5).setnShininess(500);

        scene.lights.add(new DirectionalLight(new Color(300,300,300), new Vector(-1,-1,-1)));

        scene.geometries.add(
                new Sphere(3, new Point3D(6,12,-100)).setEmission(new Color(180,0,0)).setMaterial(mat),
                new Sphere(3, new Point3D(3,9,-50)).setEmission(new Color(180,200,0)).setMaterial(mat),
                new Sphere(3, new Point3D(0,6,0)).setEmission(new Color(0,0,180)).setMaterial(mat),
                new Sphere(3, new Point3D(-3,3,50)).setEmission(new Color(0,180,0)).setMaterial(mat),
                new Sphere(3, new Point3D(-6,0,100)).setEmission(new Color(0,180,180)).setMaterial(mat)
        );

        Render render = new Render() //
                .setImageWriter(new ImageWriter("depth", 500, 500)) //
                .setCamera(camera) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .setGridParams(8);
        render.renderImageWithDepthOfField();
        render.writeToImage();

    }
}
