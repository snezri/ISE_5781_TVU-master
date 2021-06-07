package scene;

import Geometries.Geometries;
import Primitives.Color;
import elements.AmbientLight;
import elements.LightSource;

import java.util.LinkedList;
import java.util.List;


/**
 * The Scene class is a PDS (Passive Data Structure) means it is here only to keep the elements.
 * That's why all the attributes are public. There are no getter functions.
 */
public class Scene {
    public String name;
    public Color background=Color.BLACK;
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<LightSource>();

    @Override
    public String toString() {
        return "Scene [name=" + name + ", background=" + background + ", ambientLight=" + ambientLight + ", geometries="
                + geometries + "]";
    }


    /**
     * Scene constructor- Receives the scene's name and create an empty group of forms 3D models
     * @param name
     */
    public Scene(String name) {
        this.name = name;
        geometries=new Geometries();
    }

    /**
     *
     * @param background
     * @return
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     *
     * @param ambientLight
     * @return
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     *
     * @param geometries
     * @return
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     *
     * @param lights
     * @return
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}
