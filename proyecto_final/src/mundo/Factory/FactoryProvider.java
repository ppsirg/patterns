package mundo.Factory;

public class FactoryProvider {
    public static AbstractFactory getFactory(String type){
        AbstractFactory FactoryType=null;
        if (type.equals("BossFactory"))
            FactoryType = new BossFactory();
        else if (type.equals("ZombieFactory"))
            FactoryType = new ZombieFactory();
        else if (type.equals("WeaponFactory"))
            FactoryType = new WeaponFactory();
        return FactoryType;
    }
}
