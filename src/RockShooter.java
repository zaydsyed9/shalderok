public class RockShooter extends Weapon {
    
    public RockShooter(Entity owner, double x, double y){
        super(owner, x, y, null);
    }

    public void shoot(){
        if(canShoot()){
            queuedProjectiles.add(new Rock(x, y, angle, owner.damage));
            cooldownTimer = 400;
        }
    }

    @Override
    public void interact() {
        
    }

}