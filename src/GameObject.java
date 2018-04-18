import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * This class decides what things a GameObject needs to contain
 * and sets that as a standard
 */
public abstract class GameObject {

    //kanske ska vara private?
    public int x,y, coolDown = 0;
    public float velx = 0, vely = 0, tempvely = 0, tempvelx = 0;


    public ID id;
    private String shooterName;
    public SpriteSheet spriteS;


    /**
     * The constructor
     *
     * @param x horizontal location
     * @param y vertical location
     * @param id is the way to know what type a certain GameObject is
     * @param spriteS is a picture that contains the images of the different components
     * @param shooterName is the name of the player
     */
    public GameObject (int x, int y, ID id, SpriteSheet spriteS, String shooterName, int coolDown)
    {
        this.x = x;
        this.y = y;
        this.coolDown = coolDown;
        this.id = id;
        this.spriteS = spriteS;
        this.shooterName = shooterName;
    }

    public abstract void tick();
    public abstract void render (Graphics g);
    public abstract Rectangle getBounds();

    public ID getID() {
        return id;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getVelx() {
        return velx;
    }

    public void setVelx(float velx) {
        this.velx = velx;
    }

    public float getVely() {
        return vely;
    }

    public void setVely(float vely) {
        this.vely = vely;
    }

    public float getTempvely() {
        return tempvely;
    }

    public void setTempvely(float tempvely) {
        this.tempvely = tempvely;
    }

    public float getTempvelx() {
        return tempvelx;
    }

    public void setTempvelx(float tempvelx) {
        this.tempvelx = tempvelx;
    }

    public String getShooterName() {
        return shooterName;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
    public void incCoolDown()
    {
        this.coolDown++;
    }

    public void setShooterName(String shooterName) {
        this.shooterName = shooterName;
    }
}
