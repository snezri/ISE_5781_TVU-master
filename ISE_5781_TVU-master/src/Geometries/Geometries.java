package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Dina Hayoun and Sarah Nezri
 *
 */
public class Geometries implements Intersectable {

    List<Intersectable> Intersectable;

    public Geometries() {
        Intersectable = new LinkedList<>();
    }

    public void add(Intersectable... intersectables) {
        for (Intersectable Item : intersectables) {
            this.Intersectable.add(Item);
        }
    }

    public Geometries(Intersectable... Intersectables) {
        for (Intersectable Item : Intersectables) {
            this.Intersectable = new LinkedList<>();
            add(Intersectables);

        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> result = null;
        for (Intersectable element : Intersectable) {
            List<Point3D> elemList = element.findIntersections(ray);
            if (elemList != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }

                result.addAll(elemList);
            }
        }

        return result;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> result = null;
        for (Intersectable element : Intersectable) {
            List<Point3D> elemList = element.findIntersections(ray);
            if (elemList != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }

                for (Point3D p : elemList) {
                    if (element instanceof Geometry) {
                        result.add(new GeoPoint((Geometry) element, p));
                    }
                }
            }
        }
        return result;
    }
}