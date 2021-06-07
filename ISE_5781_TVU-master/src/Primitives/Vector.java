package Primitives;

public class Vector {
    Point3D head;

    public Vector(double x, double  y, double z){
       Point3D p=new Point3D(x,y,z);
       if(p.equals(Point3D.ZERO)){
           throw new IllegalArgumentException("Vector cannot be 0");
       }
        this.head=p;
    }

    public Vector(Point3D p){
        if(p.equals(Point3D.ZERO)){
            throw new IllegalArgumentException("Vector cannot be 0");
        }
        head = new Point3D(p);

    }

   public Point3D getHead(){
        return head;
   }

   @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector)obj;
        return head.equals(other.head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "head=" + head +
                '}';
    }

    public Vector add(Vector v2){
        return new Vector(this.head.x.coord+v2.head.x.coord,this.head.y.coord+v2.head.y.coord,this.head.z.coord+v2.head.z.coord);
    }

    public Vector substract(Vector v2){
        return new Vector(this.head.x.coord-v2.head.x.coord,this.head.y.coord-v2.head.y.coord,this.head.z.coord-v2.head.z.coord);
    }

    public Vector scale( double num){
        return new Vector((this.head.x.coord)*num,(this.head.y.coord)*num,(this.head.z.coord)*num);
    }

    public double dotProduct(Vector v1){
        return this.head.x.coord*v1.head.x.coord+this.head.y.coord*v1.head.y.coord+this.head.z.coord*v1.head.z.coord;
    }

    public Vector crossProduct(Vector v){
        return new Vector((this.head.y.coord)*(v.head.z.coord)-(this.head.z.coord)*(v.head.y.coord),(this.head.z.coord)*(v.head.x.coord)-(this.head.x.coord)*(v.head.z.coord), (this.head.x.coord)*(v.head.y.coord)-(this.head.y.coord)*(v.head.x.coord));
    }

    public double lengthSquared(){
        return (this.head.x.coord*this.head.x.coord+this.head.y.coord*this.head.y.coord+this.head.z.coord*this.head.z.coord);
    }
    public double length(){
        return Math.sqrt(this.lengthSquared());
    }

    public Vector normalize(){
        double length=this.length();
        this.head=new Point3D(this.head.x.coord/length,this.head.y.coord/length,this.head.z.coord/length);
        return this;
    }

    public Vector normalized(){
        Vector v=new Vector(this.head);
        return v.normalize();
    }
}
