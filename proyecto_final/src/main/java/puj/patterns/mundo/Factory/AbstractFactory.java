package puj.patterns.mundo.Factory;

import puj.patterns.mundo.Armas.Arma;
import puj.patterns.mundo.Personajes.Enemigo;
import puj.patterns.mundo.Personajes.Zombie;

public abstract class AbstractFactory {
    public abstract Enemigo getBoss(String type, byte Salud);
    public abstract Enemigo getZombie(String type);
    public abstract Enemigo getZombie(String type, short level, Zombie zombNodoLejano);
    public abstract Enemigo getZombie(String type, int posX, int posY, int direccionX, int direccionY, String estadoActual, byte frameActual, byte salud, int ronda);
    public abstract Enemigo getZombie(String type, int posX, int posY, String estadoActual, byte frameActual, byte salud, int ronda);
    public abstract Arma getWeapon(String type);

}