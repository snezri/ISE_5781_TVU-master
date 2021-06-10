package elements;

import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;

/**
 *Interface LightSource
 * @author Dina Hayoun and Sarah Nezri
 */
public interface LightSource {

    /**
     *
     * @param p : the point
     * @return its color
     */
    public Color getIntensity(Point3D p);

    /**
     *
     * @param p : a point
     * @return the direction of the light source
     */
    public Vector getL(Point3D p);
    /**
     *
     * @param point
     * @return the distance between the LightSoyrce and the point
     */
    double getDistance(Point3D point);
}
