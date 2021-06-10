package renderer;
/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
import Primitives.Color;
import Primitives.Ray;
import scene.Scene;

/**
 * RayTracerBase class holds the scene
 */
public abstract class RayTracerBase {
    protected Scene scene;

    /**
     * RayTracerBase constructor receives a scene and apply it to the scene parameter
     * @param scene
     * @return scene
     */
    public RayTracerBase(Scene scene) {
        this.scene = scene;
    }

    @Override
    public String toString() {
        return "RayTracerBase [myScene=" + scene + "]";
    }

    /**
     * This abstract function receives a ray and return a color
     * @param ray
     * @return  Color
     */
    public abstract Color traceRay (Ray ray);
}
