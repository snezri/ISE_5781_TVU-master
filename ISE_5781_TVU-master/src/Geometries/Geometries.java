package Geometries;

import Primitives.Point3D;
import Primitives.Ray;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {

    private List<Intersectable> listGeo;

    public Geometries() {
        listGeo =new LinkedList<Intersectable>();
    }

    public Geometries(Intersectable... intersectables) {
        listGeo = List.of(intersectables);
    }

    public void add(Intersectable... geometries) {
        for (Intersectable i:geometries)
            listGeo.add(i);
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        LinkedList<GeoPoint> intersections = new LinkedList<GeoPoint>();
        for (Intersectable i : listGeo) {
            if (i.findGeoIntersections(ray) != null)
                for (GeoPoint p : i.findGeoIntersections(ray))
                    intersections.add(p);
        }
        if (intersections.isEmpty())
            return null;
        return intersections;
    }

}
