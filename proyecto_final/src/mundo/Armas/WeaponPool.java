package mundo.Armas;

import mundo.Factory.AbstractFactory;
import mundo.Factory.FactoryProvider;
import mundo.Factory.WeaponFactory;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WeaponPool implements Serializable {

    private int size;

    private boolean shutdown;

    /**
     * Instancia la fábrica de Boss
     */
    AbstractFactory WeaponFactory = FactoryProvider.getFactory("WeaponFactory");

    /*
    * To keep all the objects will use a BlockingQueue object.
    * This will ensure that the object will be delivered only if is accessible,
    * otherwise will wait until an object will become accessible.
     */

    private BlockingQueue objects;

    public WeaponPool(int size) {
        this.size = size;
        shutdown = false;
        init();
    }

    /*
     * initiate the pool with fix size
     */
    private void init() {
        objects = new LinkedBlockingQueue();
        for (int i = 0; i < size; i++) {
            objects.add(WeaponFactory.getWeapon("Cuchillo"));
            objects.add(WeaponFactory.getWeapon("Granada"));
            objects.add(WeaponFactory.getWeapon("M1911"));
            objects.add(WeaponFactory.getWeapon("Remington"));
        }
    }


    public Arma get() {
        if (!shutdown) {
            Arma t = null;

            try {
                t = (Arma) objects.take();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return t;
        }

        throw new IllegalStateException("Object pool is already shutdown.");
    }


    public void release(Arma t) {
        try {
            objects.offer(t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        objects.clear();
    }

    public int size() {
        return objects.size();
    }

}
