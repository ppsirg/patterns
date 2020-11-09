package mundo.Factory;

import mundo.Armas.Arma;
import mundo.Personajes.Boss;
import mundo.Personajes.Enemigo;
import mundo.Personajes.Zombie;

public class BossFactory extends AbstractFactory {
    @Override
    public Enemigo getBoss(String type, byte Salud) {
        Boss Boss = null;
        if (type.equals("Boss")) {
            if(Salud == 0){
                Boss = new Boss();
            }else
                Boss = new Boss(Salud);
        }
        return Boss;
    }

    @Override
    public Enemigo getEnemy(String type, short level, Zombie zombNodoLejano) {
        return null;
    }

    @Override
    public Arma getWeapon(String type) {
        return null;
    }

}
