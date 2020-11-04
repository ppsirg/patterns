package mundo;

public class ZombieFactory {
    public static Zombie getZombie (String type, short level, Zombie zombNodoLejano){
        Zombie ZombieType = null;
        if (type.equals("Caminante"))
            ZombieType = new Caminante(level, zombNodoLejano);
        else if (type.equals("Rastrero"))
            ZombieType = new Rastrero(level, zombNodoLejano);
       return ZombieType;
    }
}
