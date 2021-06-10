package elements;

import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;
/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class SpotLight extends PointLight {

    private Vector direction;

    /**
     * SpotLight Ctor
     *
     * @param intensity
     * @param position
     * @param direction
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }
    /**
     * @param p
     * @return the intensity of the object on a specific point which is reduced by the distance of the point
     * Takes into account the direction also of a spot light
     */
    @Override
    public Color getIntensity(Point3D p)
    {
        return super.getIntensity(p).scale(Math.max(direction.normalize().dotProduct(getL(p)),0)) ;
    }

}
