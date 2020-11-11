package mundo.Memento;

import mundo.Personajes.Boss;
import mundo.Personajes.Caminante;
import mundo.Personajes.Personaje;
import mundo.Personajes.Zombie;
import mundo.SurvivorCamp;


import java.io.*;
import java.util.Stack;

public class MementoCareTaker {

    /*
      * Stack is a subclass of Vector that implements a standard last-in, first-out stack.
     */
    private Stack <Personaje> PersonajeStack = new Stack<>();
    /**
     * guarda la partida actual
     *
     * @throws IOException
     *             en caso de que el jugador abra el ejecutable desde una
     *             carpeta inválida
     */
    public void pushMemento (Personaje memento, Boss jefe, Zombie zombNodoCercano ) throws IOException {
        PersonajeStack.push(memento);
        File carpeta = new File(System.getProperty("user.dir") + "/PartidasGuardadas");
        File archivoPersonaje = new File(carpeta.getAbsolutePath() + "/personaje.txt");
        if (!carpeta.exists())
            carpeta.mkdirs();
        ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivoPersonaje));
        escritor.writeObject(memento);
        escritor.close();
        SurvivorCamp.events.notify("Write", archivoPersonaje);
        try {
            guardarDatosCampo(carpeta, jefe , zombNodoCercano);
        } catch (IOException e) {
            throw new IOException(
                    "Error al guardar el archivo, es posible que haya abierto el juego desde \"Acceso rápido\"");
        }
    }
    public Personaje popMemento() throws Exception {
        Personaje Personaje = null;
        if (PersonajeStack.size() == 0){
            Personaje = ReadFile();
        }else{
          Personaje = PersonajeStack.pop();
        }
        return Personaje;
    }

    private Personaje ReadFile () throws Exception{
        File carpeta = new File(System.getProperty("user.dir") + "/PartidasGuardadas");
        File archivoPersonaje = new File(carpeta.getAbsolutePath() + "/personaje.txt");

        Personaje p;
        try {
            ObjectInputStream oIS = new ObjectInputStream(new FileInputStream(archivoPersonaje));
            Personaje personaje = (Personaje) oIS.readObject();
            oIS.close();
            SurvivorCamp.events.notify("Read",archivoPersonaje);
            p = personaje;
        } catch (IOException e) {
            throw new Exception(
                    "No se ha encontrado una partida guardada o es posible que haya abierto el juego desde \"Acceso rápido\"");
        }
        return p;
    }

    /**
     * escribe los datos de los enemigos
     *
     * @param carpeta
     *            carpeta en la que se va a guardar el archivo
     * @throws IOException
     *             en caso de que ocurra un error inesperado
     */
    private void guardarDatosCampo(File carpeta, Boss jefe, Zombie zombNodoCercano) throws IOException {
        File datosZombie = new File(carpeta.getAbsolutePath() + "/zombies.txt");
        BufferedWriter bW = new BufferedWriter(new FileWriter(datosZombie));
        String texto = "/salud/posX/posY/estado/frame/dirX/dirY";
        if (jefe != null)
            texto += "\n" + jefe.getSalud();
        else
            texto = escribirDatosZombie(texto, zombNodoCercano.getAtras());

        bW.write(texto);
        bW.close();
        SurvivorCamp.events.notify("Write",datosZombie);
    }

    /**
     * escribe los datos de los zombies de manera recursiva
     *
     * @param datos
     * @param actual
     * @return el texto con la información de los zombies
     */
    private String escribirDatosZombie(String datos, Zombie actual) {
        if (actual.getEstadoActual().equals(Zombie.NODO))
            return datos;
        datos += "\n" + actual.getSalud() + "_" + actual.getPosX() + "_" + actual.getPosY() + "_"
                + actual.getEstadoActual() + "_" + actual.getFrameActual();
        if (actual instanceof Caminante) {
            datos += "_" + ((Caminante) actual).getDireccionX();
            datos += "_" + ((Caminante) actual).getDireccionY();
        }
        return escribirDatosZombie(datos, actual.getAtras());
    }
}
