package puj.patterns.mundo.Factory;

import puj.patterns.mundo.Armas.Arma;
import puj.patterns.mundo.Personajes.Boss;
import puj.patterns.mundo.Personajes.Enemigo;
import puj.patterns.mundo.Personajes.Zombie;

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
    public Enemigo getZombie(String type) {
        return null;
    }

    @Override
    public Enemigo getZombie(String type, short level, Zombie zombNodoLejano) {
        return null;
    }

    @Override
    public Enemigo getZombie(String type, int posX, int posY, int direccionX, int direccionY, String estadoActual, byte frameActual, byte salud, int ronda) {
        return null;
    }

    @Override
    public Enemigo getZombie(String type, int posX, int posY, String estadoActual, byte frameActual, byte salud, int ronda) {
        return null;
    }

    @Override
    public Arma getWeapon(String type) {
        return null;
    }

}
