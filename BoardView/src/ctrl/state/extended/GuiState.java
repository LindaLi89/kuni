package ctrl.state.extended;

import ctrl.state.IGuiState;

public class GuiState implements IGuiState{

	protected boolean loadWorldActive = true;
	protected  boolean mouseVisible = false;
	protected  boolean playButtonVisible = false;
	protected  boolean debugButtonVisible = false;
	protected  boolean stopButtonVisible = false;
	protected  boolean resetButtonVisible = false;
	protected  boolean ddButtonsVisible = false;
	protected  boolean ddMouseButtonVisible = false;
	
	
	//0 resetiert, 1 aktiv, 2 gestezt, aber nicht mehr aktiv
	protected int score = 0;
	protected boolean arrowsVisible = false;
	protected boolean setMouseButtonVisible = false;
	protected boolean viewFieldVisible = false;
	protected boolean recordButtonVisible = false;
	
	public boolean isLoadWorldActive() {
		return loadWorldActive;
	}
	public boolean isMouseVisible() {
		return mouseVisible;
	}
	public boolean isPlayButtonVisible() {
		return playButtonVisible;
	}
	
	public boolean isDebugButtonVisible() {
		return debugButtonVisible;
	}
	public boolean isStopButtonVisible() {
		return stopButtonVisible;
	}
	public boolean isResetButtonVisible() {
		return resetButtonVisible;
	}
	public boolean isDdButtonsVisible() {
		return ddButtonsVisible;
	}
	public boolean isDdMouseButtonVisible() {
		return ddMouseButtonVisible;
	}
	public int getScore() {
		return score;
	}
	public boolean isArrowsVisible() {
		return arrowsVisible;
	}
	public boolean isSetMouseButtonVisible() {
		return setMouseButtonVisible;
	}
	public boolean isViewFieldVisible() {
		return viewFieldVisible;
	}
	public boolean isRecordButtonVisible() {
		return recordButtonVisible;
	}

}
