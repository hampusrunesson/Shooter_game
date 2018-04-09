import java.awt.Graphics;
import java.util.LinkedList;

/**
 * This class adds and deletes all the GameObject to a LinkedList
 */
public class Driver {

    LinkedList<GameObject> object = new LinkedList<GameObject>();


    /**
     * This method runs all the tick methods of the GameObjects
     */
    public void tick()
    {
        for(int i = 0; i <object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.tick();

        }
    }

    /**
     * Runs all the render methods of the GameObjects
     * @param g is the graphics
     */
    public void render(Graphics g)
    {
        for(int i = 0; i <object.size(); i++)
        {
            GameObject tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    /**
     * Adds the GameObject to the list
     * @param tempObject is the GameObject to be added
     */
    public void addObject(GameObject tempObject)
    {
        object.add(tempObject);

    }

    /**
     * Removes the GameObject from the list
     * @param tempObject is the GameObject to be removed
     */
    public void removeObject(GameObject tempObject)
    {
        object.remove(tempObject);
    }

    public LinkedList<GameObject> getObject() {
        return object;
    }

    public void setObject(LinkedList<GameObject> object) {
        this.object = object;
    }
}
