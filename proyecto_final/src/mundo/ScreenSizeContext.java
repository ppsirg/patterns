package mundo;

public class ScreenSizeContext {
    private AdjustScreenSize strategy;

    public ScreenSizeContext(AdjustScreenSize strategy){
        this.strategy=strategy;
    }

    public int executeAdjustScreenSize(int width){
        return strategy.UpdateSizeVariables(width);
    }
}
