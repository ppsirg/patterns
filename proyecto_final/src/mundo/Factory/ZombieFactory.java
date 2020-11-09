package mundo.Factory;

import mundo.Armas.Arma;
import mundo.Personajes.Caminante;
import mundo.Personajes.Enemigo;
import mundo.Personajes.Rastrero;
import mundo.Personajes.Zombie;

public class ZombieFactory extends AbstractFactory {

    @Override
    public Enemigo getBoss(String type, byte Salud) {
        return null;
    }

    @Override
    public Enemigo getEnemy(String type, short level, Zombie zombNodoLejano) {
        Zombie ZombieType = null;
        if (type.equals("Caminante"))
            ZombieType = new Caminante(level, zombNodoLejano);
        else if (type.equals("Rastrero"))
            ZombieType = new Rastrero(level, zombNodoLejano);
        return ZombieType;
    }

    @Override
    public Arma getWeapon(String type) {
        return null;
    }

}
