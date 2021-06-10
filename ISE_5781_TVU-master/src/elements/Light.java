package elements;

import Primitives.Color;

/**
 * Abstract Class Light
 * @author Dina Hayoun and Sarah Nezri
 */
abstract class Light {

    protected Color intensity;

    /**
     * Light constructor
     * @param intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * @return the intensity
     */
    public Color getIntensity() {
        return intensity;
    }


}
