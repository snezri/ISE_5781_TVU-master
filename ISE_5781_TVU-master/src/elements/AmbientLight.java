package elements;

import Primitives.Color;


public class AmbientLight extends Light {

    /**
     * Class AmbientLight extends Light
     */


    /**
     * Constructor AmbientLight
     *
     * @param Ia the original fill light (light intensity according to RGB components)
     * @param kA the filling light attenuation coefficient
     */
    public AmbientLight(Color Ia, double kA) {
        super(Ia.scale(kA));
    }

    /**
     * Default constructor for AmbientLight
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

}
