package Primitives;

/**
 * Representing material for objects
 * @author Dina Hayoun and Sarah Nezri
 */
public class Material {
    public double kD;
    public double kS;
    public int nShininess;
    public double kT=0.0;//refraction both fields between 0 to 1
    public double kR=0.0;//reflection both fields between 0 to
    public Material setKd(double _kd) {
        kD = _kd;
        return this;
    }
    public Material setKs(double _ks) {
        kS = _ks;
        return this;
    }
    public Material setkT(double kt) {
        this.kT = kt;
        return this;
    }

    public Material setkR(double kr) {
        this.kR = kr;
        return this;
    }
    public Material setnShininess(int ns) {
        nShininess = ns;
        return this;
    }
    public Material() {
        kD = kS = nShininess = 0;
    }
}
