package puj.patterns.mundo.Armas;

import puj.patterns.mundo.Factory.AbstractFactory;
import puj.patterns.mundo.Factory.FactoryProvider;

import java.io.Serializable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class WeaponPool implements Serializable {

    private int size;

    private boolean shutdown;

    /*
     * Singleton Pattern of type Initialization-on-demand holder idiom
     * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
     */
    private static class LazyHolder {
        static final WeaponPool INSTANCE = new WeaponPool(1);
    }
    public static WeaponPool getInstance() {
        return LazyHolder.INSTANCE;
    }



    /**
     * Instancia la f�brica de Armas
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
        //System.out.println("Init: Armas disponibles en el pool: "+objects.size());
    }


    public Arma get() {
        if (!shutdown) {
            Arma t = null;

            try {
                t = (Arma) objects.take();
                //checkammo(t);
                //System.out.println("Get: Armas disponibles en el pool: "+objects.size());
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
            reloadammo(t);
            objects.offer(t);
            //System.out.println("Release: Armas disponibles en el pool: "+objects.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadammo(Arma t){
        if (t instanceof Granada) {
            //System.out.println("R:Cantidad de granadas: " + ((Granada) t).getMunicion());
            ((Granada) t).setMunicion((byte) 2);
        }else if(t instanceof M1911) {
            //System.out.println("R:Cantidad de balas en M1911: " + ((M1911) t).getMunicion());
            ((M1911) t).setMunicion(((M1911) t).getLimBalas());
        }else if(t instanceof Remington) {
            //System.out.println("R:Cantidad de balas en Remington: " + ((Remington) t).getMunicion());
            ((Remington) t).setMunicion(((Remington) t).getLimBalas());
        }
    }

    public void checkammo(Arma t){
        if (t instanceof Granada) {
            System.out.println("C:Cantidad de granadas: " + ((Granada) t).getMunicion());
        }else if(t instanceof M1911) {
            System.out.println("C:Cantidad de balas en M1911: " + ((M1911) t).getMunicion());
        }else if(t instanceof Remington) {
            System.out.println("C:Cantidad de balas en Remington: " + ((Remington) t).getMunicion());
        }
    }
    public void shutdown() {
        objects.clear();
    }

    public int size() {
        return objects.size();
    }

}
