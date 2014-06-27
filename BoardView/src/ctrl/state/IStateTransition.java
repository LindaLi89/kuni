package ctrl.state;


public interface IStateTransition {

	public abstract void nextState(String action);

	public abstract IGuiState performArrowAction();

	public abstract IGuiState performOkButtonAction();

	public abstract IGuiState performLoadNewWorldAction();

	public abstract IGuiState performPlayAction();

	public abstract IGuiState performRecordAction();

	public abstract IGuiState performStopRecordAction();

	public abstract IGuiState performResetAction();

	public abstract IGuiState performSetMouseAction();

	public abstract IGuiState performSetObstacleAction();

	public abstract IGuiState performStopAction();

	public abstract IGuiState getCurrentState();

	public abstract void setCurrentState(IGuiState currentState);

}