package com.company;

public class Main {

    public static void main(String[] args) {
        Vector v = new Vector(2,1,2);
        System.out.println(v.Dlina());
        Vector v2 = new Vector(3,3,2);
        System.out.println(v.Scal(v2));
        Vector v3 = v.proiz(v2);
    }
}

class Vector{

    int x, y, z;

    Vector(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double Dlina(){
        double d = Math.sqrt(x^2 + y^2 + z^2);
        return d;
    }

    public double Scal(Vector v){
        double s = x*v.x + y*v.y + z*v.z;
        return s;
    }

    public Vector proiz(Vector v){
        Vector p = new Vector(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
        return p;
    }

    public double cos(Vector v){
        double c = Scal(this) / (this.Dlina() * v.Dlina());
        return c;
    }


    public Vector sum(Vector v){
        Vector s = new Vector(x + v.x, y + v.y, z + v.z);
        return s;
    }

    public Vector raz(Vector v){
        Vector r = new Vector(x - v.x, y - v.y, z - v.z);
        return r;
    }

    public Vector[] N(int n){
        Vector[] v = new Vector[n];
        for (int i=0; i<n; i++){
            v[i] = new Vector((int)Math.random(), (int)Math.random(), (int)Math.random());
        }
        return v;
    }
}