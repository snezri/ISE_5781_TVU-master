package Geometries;

import Primitives.*;

/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public abstract class Geometry implements Intersectable {

    /**
     * The emission light of this geometry
     */
    protected Color emission = Color.BLACK;

    private Material material = new Material();

    /**
     * Get normal function.
     * @param point of geometry shape.
     * @return the normal of each geometry shape.
     */
    public abstract Vector getNormal(Point3D point);


    /**
     * Setter method for the emission field
     * of this geometry.
     * The method is in a form af builder pattern.
     * @param color The color to be set for the emission
     * @return The object instance.
     */
    public Geometry setEmission(Color color) {
        emission = new Color(color);
        return this;
    }

    public Geometry setMaterial(Material m) {
        material = m;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    /**
     * Getter for the emission field of this geometry
     * @return The emission of this geometry
     */
    public Color getEmission() {
        return new Color(emission);
    }
}

