package mundo.Personajes;

import java.util.HashMap;
import java.util.Map;

public class EnemigoFactoria {
    public static Map<String, EnemigoContexto> cache = new HashMap<String, EnemigoContexto>();

    public static EnemigoContexto getEnemigo(String type){
        return createEnemigo(type, (byte)16);
    }

    public static EnemigoContexto getEnemigo(String type, byte salud){
        return createEnemigo(type, salud);
    }

    private static EnemigoContexto createEnemigo(String type, byte salud){
        //veificar si ya ha sido creado
        EnemigoContexto enemigo = new EnemigoContexto();
        if (cache.containsKey(type)){
            enemigo = cache.get(type);
        }
        else {
            if (type.equalsIgnoreCase("zombie")){
                enemigo.setLentitud((short)30, (short)40, (short)45, (short)50);
                enemigo.setsalud((byte)3, (byte)5, (byte)6, (byte)8);
                cache.put("zombie", enemigo);
            }
            else {
                enemigo.setLentitud((short)14);
                enemigo.setsalud(salud);
                cache.put("boss", enemigo);
            }
        }
        return enemigo;
    }

}
