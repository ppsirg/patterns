package puj.patterns.mundo.GameDataLogs;

public interface DataSource {
    void writeData(String data);
    String readData();
}