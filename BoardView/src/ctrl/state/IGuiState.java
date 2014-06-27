package ctrl.state;

public interface IGuiState {

	public abstract boolean isLoadWorldActive();

	public abstract boolean isMouseVisible();

	public abstract boolean isPlayButtonVisible();
	
	public abstract boolean isDebugButtonVisible();

	public abstract boolean isStopButtonVisible();

	public abstract boolean isResetButtonVisible();

	public abstract boolean isDdButtonsVisible();

	public abstract boolean isDdMouseButtonVisible();

	public abstract int getScore();

	public abstract boolean isArrowsVisible();

	public abstract boolean isSetMouseButtonVisible();

	public abstract boolean isViewFieldVisible();

	public abstract boolean isRecordButtonVisible();
	

}