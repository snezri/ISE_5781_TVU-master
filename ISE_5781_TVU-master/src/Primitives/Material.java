package Primitives;

public class Material {

    public double kD = 0;
    public double kS = 0;
    public int nShininess =0;

    /**
     * Transparency coefficient
     */
    public double kT=0.0;

    /**
     * Reflection coefficient
     */
    public double kR=0.0;

    /**
     * @param kD the kD to set
     */
    public void setkD(double kD) {
        this.kD = kD;
    }

    /**
     * @param kS the kS to set
     */
    public void setkS(double kS) {
        this.kS = kS;
    }

    /**
     * @param kT the kT to set
     */
    public Material setkT(double kT) {
        this.kT = kT;
        return this;
    }

    /**
     * @param kR the kR to set
     */
    public Material setkR(double kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Material constructor
     *
     * @param kD
     * @param kS
     * @param nShininess
     */
    public Material(double kD, double kS, int nShininess) {
        this.kD = kD;
        this.kS = kS;
        this.nShininess = nShininess;
    }

    /**
     * Material default constructor
     *
     */
    public Material() {
        this.kD = 0;
        this.kS = 0;
        this.nShininess = 0;
    }

    /**
     * @param kD the kD to set
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    /**
     * @param kS the kS to set
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    /**
     * @param nShininess the nShininess to set
     */
    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}
