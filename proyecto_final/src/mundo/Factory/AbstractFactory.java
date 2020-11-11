package mundo.Factory;

import mundo.Armas.Arma;
import mundo.Personajes.Enemigo;
import mundo.Personajes.Zombie;

public abstract class AbstractFactory {
    public abstract Enemigo getBoss(String type, byte Salud);
    public abstract Enemigo getEnemy(String type, short level, Zombie zombNodoLejano);
    public abstract Arma getWeapon(String type);

}