package mundo.Factory;

import mundo.Armas.Arma;
import mundo.Personajes.Enemigo;
import mundo.Personajes.Zombie;

public abstract class AbstractFactory {
    public abstract Enemigo getBoss(String type, byte Salud);
    public abstract Enemigo getZombie(String type);
    public abstract Enemigo getZombie(String type, short level, Zombie zombNodoLejano);
    public abstract Enemigo getZombie(String type, int posX, int posY, int direccionX, int direccionY, String estadoActual, byte frameActual, byte salud, int ronda);
    public abstract Enemigo getZombie(String type, int posX, int posY, String estadoActual, byte frameActual, byte salud, int ronda);
    public abstract Arma getWeapon(String type);

}