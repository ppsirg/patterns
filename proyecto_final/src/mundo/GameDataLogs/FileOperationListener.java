package mundo.GameDataLogs;

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
        String plainlogfile = carpeta.getAbsolutePath() + "/logfile.txt";
        String encodedlogfile = carpeta.getAbsolutePath() + "/encodedlogfile.txt";
        try {

            FileWriter myWriter = new FileWriter(logfile,true);
            String logrecord =java.time.Clock.systemUTC().instant()+ " ZombieKillerGame has performed " + eventType + " operation with the following file: " + file.getName()+"\n";
            myWriter.write(logrecord);
            myWriter.close();
            System.out.println("Save to log " + log + ": ZombieKillerGame has performed " + eventType + " operation with the following file: " + file.getName());

            /**
             * Decoradores Compression Y Encryption para la clase
             * que escribe el encodedlogfile.txt
             */

            DataSourceDecorator encoded = new CompressionDecorator(
                                                new EncryptionDecorator(
                                                        new FileDataSource(encodedlogfile)));

            DataSource plain = new FileDataSource(plainlogfile);
            String data = plain.readData();
            encoded.writeData(data);
           /*
            * Código para probar la codificación y decodificación del logfile
            System.out.println("- Input ----------------");
            System.out.println(data);
            System.out.println("- Encoded --------------");
            System.out.println(encodedplain.readData());
            System.out.println("- Decoded --------------");
            System.out.println(encoded.readData());
            */



        } catch (IOException e) {
            System.out.println("An error occurred writing to file /PartidasGuardadas/logfile.txt");
            e.printStackTrace();
        }
    }
}