public class Phoenix extends Enemy {

    public Phoenix(double x, double y) {
        super(x, y, 3, 100, 10, 1, SpriteLoader.getSprite("phoenix"));
        activeItem = new FireballShooter(this, x, y);
    }

    @Override
    public void interact() {
    }

}