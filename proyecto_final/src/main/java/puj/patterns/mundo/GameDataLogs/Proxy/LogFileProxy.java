package puj.patterns.mundo.GameDataLogs.Proxy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LogFileProxy extends LogFile {
    private String fileNameWithPath;
    RealLogFile realLogFile = null;
    public LogFileProxy(String fileNameWithPath){
        this.fileNameWithPath=fileNameWithPath;
    }
    public String getNameWithPath(){
        return this.fileNameWithPath;
    }
    public FileInputStream getFileContents() throws FileNotFoundException {
        this.realLogFile = new RealLogFile(this.fileNameWithPath);
        return realLogFile.getFileContents();
    }
}
