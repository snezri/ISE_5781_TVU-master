package unittests.primitives;

import Primitives.Vector;
import org.junit.Test;

import static Primitives.Util.isZero;
import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void testAdd() {
        Vector v1=new Vector (2,4,6);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector (1, 2, 3);
        Vector vr=v1.add(v2);
        Vector expected= new Vector(3,6,9);
        assertEquals("addVector() wrong result ",expected,vr );
        // =============== Boundary Values Tests ==================
        // TC11: test zero vector
        Vector v3 = new Vector(-2, -4, -6);
        try {
             v1.add(v3);
             fail("Add for vector 0 does not throw an exception");
         } catch (Exception e) {}
    }



    @Test
    public void testSubstract() {
        Vector v1=new Vector (2,4,6);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector (1, 2, 3);
        Vector vr=v1.substract(v2);
        Vector expected= new Vector(1,2,3);
        assertEquals("substractVector() wrong result ",expected,vr );
    }

    @Test
    public void testScale() {
        Vector v1=new Vector (2,4,6);
        // ============ Equivalence Partitions Tests ==============
        double x=2;
        Vector vr=v1.scale(x);
        Vector expected= new Vector(4,8,12);
        assertEquals("substractVector() wrong result ",expected,vr );
    }

    @Test
    public void testDotProduct() {
        Vector v1=new Vector (2,4,6);
        // ============ Equivalence Partitions Tests ==============
        Vector v2=new Vector (1, 2, 3);
        double vr=v1.dotProduct(v2);
        double expected= 28;
        assertEquals("dotProductVector() wrong result ",expected,vr,0.000001);
    }

    /**
     * Test method for {@link Primitives.Vector#crossProduct(Primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);

        // ============ Equivalence Partitions Tests ==============
        Vector v2 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals("crossProduct() wrong result length", v1.length() * v2.length(), vr.length(), 0.00001);

        // TC02: Test cross-product result orthogonality to its operands
        assertTrue("crossProduct() result is not orthogonal to 1st operand", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 2nd operand", isZero(vr.dotProduct(v2)));

        // =============== Boundary Values Tests ==================
        // TC11: test zero vector from cross-product of co-lined vectors
         try {
             v1.crossProduct(v2);
             fail("crossProduct() for parallel vectors does not throw an exception");
          } catch (Exception e) {}
    }



    @Test
    public void testLengthSquared() {
        Vector v1=new Vector (0,3,4);
        // ============ Equivalence Partitions Tests ==============
        double length=v1.lengthSquared();
        double expected= 25;
        assertEquals("lengthSquaredVector() wrong result ",expected,length,0.00001 );
    }

    @Test
    public void testLength() {
        Vector v1=new Vector (0,3,4);
        // ============ Equivalence Partitions Tests ==============
        double length=v1.length();
        double expected= 5;
        assertEquals("LengthVector() wrong result ",expected,length,0.00001 );
    }

    @Test
    public void testNormalize() {
        Vector v1=new Vector (5,15,20);
        // ============ Equivalence Partitions Tests ==============
        Vector vr= v1.normalize();
        assertTrue("NormalizeVector() wrong result ",v1==vr );
    }

    @Test
    public void testNormalized() {
        Vector v1=new Vector (5,15,20);
        // ============ Equivalence Partitions Tests ==============
        Vector vr= v1.normalized();
        assertTrue("NormalizedVector() wrong result ",v1!=vr );
    }
}