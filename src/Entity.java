import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public abstract class Entity extends GameObject {
    
    private final int WEAPON_RADIUS = 64;

    public Room r;

    public double vx, vy;
    public double spd;
    public double knockbackX, knockbackY;
    public int hp;
    public int maxHp;
    public int damage;
    public double evasiveness;
    public Item activeItem;
    public double angle;

    public Entity(double x, double y, double spd, int hp, int damage, double evasiveness, Sprite s){
        super(x, y, s.getWidth(), s.getHeight(), s);
        this.spd = spd;
        this.vx = 0;
        this.vy = 0;
        this.hp = hp;
        this.maxHp = hp;
        this.damage = damage;
        this.evasiveness = evasiveness;
    }

    public void enterNewFloor(Floor f, Room r) {
        
        if(activeItem instanceof Weapon){
            f.weapons.add((Weapon)activeItem);
        }
        this.r = r;
    } 

    public void takeHit(Projectile projectile){
        if(!(Math.random() < evasiveness)){
            this.hp -= projectile.damage;
            Point2D.Double knockbackVector = this.getUnitVectorTo(projectile);
            this.knockbackX = -10*knockbackVector.x;
            this.knockbackY = -10*knockbackVector.y;
        }
    }

    public void equip(Item item){
        this.activeItem = item;
    }

    public void useActiveItem(){
        if(activeItem != null){
            activeItem.use();
        }
    }

    protected abstract void updateAngle();
    protected abstract void updateVelocity();
    protected abstract void updateDirection();

    protected void updateActiveItem(){
        if(activeItem != null){
            double centerX = drawCenterX() + WEAPON_RADIUS * Math.cos(angle);
            double centerY = drawCenterY() - WEAPON_RADIUS * Math.sin(angle);
            activeItem.x = activeItem.drawXFromCenter(centerX);
            activeItem.y = activeItem.drawYFromCenter(centerY);
        }
        if(activeItem instanceof Weapon){
            ((Weapon)(activeItem)).angle = angle;
            ((Weapon)activeItem).update();
        }
    }
    protected void move(){
        x += vx;
        y += vy;
    }
    
    private void updateSprite(){
        frameIndex++;
        if(frameIndex >= sprite.getNumFrames(state)){
            frameIndex = 0;
        }
    }

    public void update(){
        updateSprite();
        updateDirection();
        updateVelocity();
        move();
        updateAngle();
        updateActiveItem();
    }

    public void paint(Graphics2D g2d){
        super.paint(g2d);

        // draw weapon
        if(activeItem != null){
            activeItem.paint(g2d);
        }
    }

    @Override
    public boolean isSolid(){
        return true;
    }

}
