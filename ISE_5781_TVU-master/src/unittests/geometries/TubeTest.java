package unittests.geometries;

import Geometries.Tube;
import Primitives.Point3D;
import Primitives.Ray;
import Primitives.Vector;
import org.junit.Test;

import static org.junit.Assert.*;

public class TubeTest {

    /**
     * Test method for {@link Geometries.Tube#getNormal(Primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Tube tube = new Tube(new Ray(new Point3D(0, 0, 1), new Vector(0, -1, 0)), 1.0);
        Vector normal = tube.getNormal(new Point3D(0, 0.5, 2)).normalize();
        assertEquals("normal is not orthogonal to the tube", new Vector(0,0,1),normal);
    }

}