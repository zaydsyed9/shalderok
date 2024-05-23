abstract class Weapon extends GameObject {
    double angle;

    public Weapon(double x, double y, int w, int h) {
        super(x, y, w, h);
    }
    
    abstract void shoot();
}