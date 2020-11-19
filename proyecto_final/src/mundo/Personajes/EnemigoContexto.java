package mundo.Personajes;

import java.util.Arrays;

public class EnemigoContexto {
    private short[] lentitud;
    private byte[] salud;

    public EnemigoContexto(){}

    public void setLentitud(short ... lens){
        this.lentitud = new short[lens.length];
        for (int i=0; i < lens.length; i++ ){
            this.lentitud[i] = lens[i];
        }
    }

    public void setsalud(byte ... sals){
        this.salud = new byte[sals.length];
        for (int i=0; i < sals.length; i++ ){
            this.salud[i] = sals[i];
        }
    }

    public short getLentitud(int i) {
        return lentitud[i];
    }

    public byte getSalud(int i) {
        return salud[i];
    }
}
