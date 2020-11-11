package mundo.Puntajes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperationListener implements EventListener {
    private File log;

    public FileOperationListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {

        File carpeta = new File(System.getProperty("user.dir") + "/PartidasGuardadas");
        File logfile = new File(carpeta.getAbsolutePath() + "/logfile.txt");
        try {
            FileWriter myWriter = new FileWriter(logfile,true);
            myWriter.write(java.time.Clock.systemUTC().instant()+ " ZombieKillerGame has performed " + eventType + " operation with the following file: " + file.getName()+"\n");
            myWriter.close();
            System.out.println("Save to log " + log + ": ZombieKillerGame has performed " + eventType + " operation with the following file: " + file.getName());
        } catch (IOException e) {
            System.out.println("An error occurred writing to file /PartidasGuardadas/logfile.txt");
            e.printStackTrace();
        }
    }
}