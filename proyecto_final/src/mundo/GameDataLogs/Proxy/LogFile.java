package mundo.GameDataLogs.Proxy;

import java.io.FileInputStream;

public abstract class LogFile {
    public abstract String getNameWithPath();
    public abstract FileInputStream getFileContents() throws java.io.FileNotFoundException;
    public static LogFile getLogFileInstance(String fileNameWithPath){
        return new LogFileProxy(fileNameWithPath);
    }
}