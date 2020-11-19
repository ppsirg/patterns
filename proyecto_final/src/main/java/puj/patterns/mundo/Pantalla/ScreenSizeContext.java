package puj.patterns.mundo.Pantalla;

public class ScreenSizeContext {
    private AdjustScreenSize strategy;

    public ScreenSizeContext(AdjustScreenSize strategy){
        this.strategy=strategy;
    }

    public int executeAdjustScreenSize(){
        return strategy.UpdateSizeVariables();
    }
}
