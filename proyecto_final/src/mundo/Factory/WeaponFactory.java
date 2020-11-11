package mundo.Factory;

import mundo.Armas.*;
import mundo.Personajes.Enemigo;
import mundo.Personajes.Zombie;

import java.io.Serializable;

public class WeaponFactory extends AbstractFactory implements Serializable {
    @Override
    public Enemigo getBoss(String type, byte Salud) {
        return null;
    }

    @Override
    public Enemigo getEnemy(String type, short level, Zombie zombNodoLejano) {
        return null;
    }

    @Override
    public Arma getWeapon(String type) {

        Arma WeaponType = null;
        if (type.equals("Cuchillo"))
            WeaponType = new Cuchillo();
        else if (type.equals("Granada"))
            WeaponType = new Granada();
        else if (type.equals("Remington"))
            WeaponType = new Remington();
        else if (type.equals("M1911"))
            WeaponType = new M1911();
        return WeaponType;

    }
}
