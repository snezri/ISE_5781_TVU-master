package Primitives;

public class Point3D {

    Coordinate x;
    Coordinate y;
    Coordinate z;
    public final static Point3D ZERO = new Point3D(0,0,0);

    public double getX() {
        return this.x.coord;
    }

    public double getY(){
        return this.y.coord;
    }
    public double getZ(){
        return this.z.coord;
    }

    public Point3D(double x, double y, double z)
    {
        this.x=new Coordinate(x);
        this.y=new Coordinate(y);
        this.z=new Coordinate(z);
    }

    public Point3D(Point3D p)
    {
        this.x=p.x;
        this.y=p.y;
        this.z=p.z;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D)obj;
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }

    @Override
    public String toString() {
        return "Point3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Vector substract(Point3D A){
        return new Vector(this.x.coord-A.x.coord, this.y.coord-A.y.coord, this.z.coord-A.z.coord);
        }

    public Point3D add(Vector V){
        return new Point3D(this.x.coord+V.head.x.coord,this.y.coord+V.head.y.coord,this.z.coord+V.head.z.coord);
    }

    public double distanceSquared(Point3D a){
        return ((this.x.coord-a.x.coord)*(this.x.coord-a.x.coord)+(this.y.coord-a.y.coord)*(this.y.coord-a.y.coord)+(this.z.coord-a.z.coord)*(this.z.coord-a.z.coord));
    }

    public double distance(Point3D a){
        return Math.sqrt(distanceSquared(a));
    }


}