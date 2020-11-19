package puj.patterns.mundo.Factory;

import puj.patterns.mundo.Armas.Arma;
import puj.patterns.mundo.Personajes.Caminante;
import puj.patterns.mundo.Personajes.Enemigo;
import puj.patterns.mundo.Personajes.Rastrero;
import puj.patterns.mundo.Personajes.Zombie;

public class ZombieFactory extends AbstractFactory {

    @Override
    public Enemigo getBoss(String type, byte Salud) {
        return null;
    }

    @Override
    public Enemigo getZombie(String type) {
        Zombie ZombieType = null;
        if (type.equals("Caminante"))
            ZombieType = new Caminante();
        return ZombieType;
    }

    @Override
    public Enemigo getZombie(String type, short level, Zombie zombNodoLejano) {
        Zombie ZombieType = null;
        if (type.equals("Caminante"))
            ZombieType = new Caminante(level, zombNodoLejano);
        else if (type.equals("Rastrero"))
            ZombieType = new Rastrero(level, zombNodoLejano);
        return ZombieType;
    }

    @Override
    public Enemigo getZombie(String type, int posX, int posY, int direccionX, int direccionY, String estadoActual, byte frameActual, byte salud, int ronda) {
        Zombie ZombieType = null;
        if (type.equals("Caminante"))
            ZombieType = new Caminante(posX, posY, direccionX, direccionY, estadoActual, frameActual, salud, ronda);
        return ZombieType;
    }

    @Override
    public Enemigo getZombie(String type, int posX, int posY, String estadoActual, byte frameActual, byte salud, int ronda) {
        Zombie ZombieType = null;
        if (type.equals("Rastrero"))
            ZombieType = new Rastrero(posX, posY, estadoActual, frameActual, salud, ronda);
        return ZombieType;
    }

    @Override
    public Arma getWeapon(String type) {
        return null;
    }

}
