package elements;

import Primitives.Color;
import Primitives.Point3D;
import Primitives.Vector;

/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class PointLight extends Light implements LightSource {


    private Point3D position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;


    /**do
     * PointLight ctor
     *
     * @param intensity
     * @param position
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
    }

    /**
     * @param kC the kC to set
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * @param kL the kL to set
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * @param kQ the kQ to set
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }
    /**
     * @param p
     * @return the intensity of the object on a specific point which is reduced by the distance of the point
     */
    @Override
    public Color getIntensity(Point3D p)
    {
        double distance=position.substract(p).length();
        return intensity.reduce(kC+kL*distance+kQ*distance*distance) ;
    }
    /**
     * @param p
     * @return the vector direction from the light to the point (normalized)
     */
    @Override
    public Vector getL(Point3D p) {
        return p.substract(position).normalize();
    }
    /**
     * @param point
     * @return the distance between the light and the point
     */
    @Override
    public double getDistance(Point3D point)
    {
        return position.distance(point);
    }

}
