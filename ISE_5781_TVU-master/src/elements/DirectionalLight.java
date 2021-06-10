package elements;

import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;
/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class DirectionalLight extends Light implements LightSource{


    private Vector direction;

    /**
     * Directional ctor
     *
     * @param intensity
     * @param direction
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }
    /**
     * @param p
     * @return the intensity of the object on a specific point which is reduced by the distance of the point
     */

    @Override
    public Color getIntensity(Point3D p) {
        return 	intensity;
    }
    /**
     * @param p
     * @return the vector direction
     */
    @Override
    public Vector getL(Point3D p) {
        return direction.normalize();
    }
    /**
     *@param point
     * @return the distance between the light and the point
     */
    @Override
    public double getDistance(Point3D point) {

        return Double.POSITIVE_INFINITY;
    }

}
