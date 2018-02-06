import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    //kanske ska vara protected???
    public int x,y;
    public float velx = 0, vely = 0, tempvely = 0, tempvelx = 0;


    public ID id;
    private String shooterName;
    public SpriteSheet spriteS;

    //

    public GameObject (int x, int y, ID id, SpriteSheet spriteS, String shooterName)
    {
        this.x = x;
        this.y = y;
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

    public void setShooterName(String shooterName) {
        this.shooterName = shooterName;
    }
}
