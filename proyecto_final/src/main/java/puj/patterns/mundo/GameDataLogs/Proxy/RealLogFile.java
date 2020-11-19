package puj.patterns.mundo.GameDataLogs.Proxy;

import java.io.*;

public class RealLogFile extends LogFile {
    private String fileNameWithPath;
    public RealLogFile(String fileNameWithPath){
        this.fileNameWithPath=fileNameWithPath;
    }
    public String getNameWithPath(){
        return this.fileNameWithPath;
    }

    public FileInputStream getFileContents() throws FileNotFoundException {
        File file=new File(this.fileNameWithPath);
        return new FileInputStream(file);
    }
}
