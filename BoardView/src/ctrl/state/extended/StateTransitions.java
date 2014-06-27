package ctrl.state.extended;

import ctrl.state.IGuiState;
import ctrl.state.IStateTransition;
import util.Constants;

public class StateTransitions implements IStateTransition{
	
	IGuiState currentState;
	
	
	
	public StateTransitions() {
		super();
		currentState = InitialState.getInstance();
	}

	public void nextState(String action) {
		switch(action) {
			case Constants.Actions.arrow: 
				currentState = performArrowAction();
				break;
			case Constants.Actions.loadNewWorld: 
				currentState = performLoadNewWorldAction();
				break;
			case Constants.Actions.play: 
				currentState = performPlayAction();
				break;
			case Constants.Actions.record: 
				currentState = performRecordAction();
				break;
			case Constants.Actions.reset: 
				currentState = performResetAction();
				break;
			case Constants.Actions.setMouse: 
				currentState = performSetMouseAction();
				break;
			case Constants.Actions.unsetMouse: 
				currentState = performResetAction();
				break;
			case Constants.Actions.setObstacle: 
				currentState = performSetObstacleAction();
				break;
			case Constants.Actions.stop: 
				currentState = performStopAction();
				break;
			case Constants.Actions.okButton:
				currentState = performOkButtonAction();
				break;
		}
	}
	
	public IGuiState performArrowAction() {
		return currentState;
	}
	
	public IGuiState performOkButtonAction() {
		return currentState;
	}

	public IGuiState performLoadNewWorldAction() {
		return StartStateWithWorld.getInstance();
	}

	public IGuiState performPlayAction() {
		return StartedState.getInstance();
	}

	public IGuiState performRecordAction() {
		return RecordState.getInstance();
	}
	
	public IGuiState performStopRecordAction() {
		return ActiveState.getInstance();
	}

	public IGuiState performResetAction() {
		return StartStateWithWorld.getInstance();
	}

	public IGuiState performSetMouseAction() {
		return ActiveState.getInstance();
	}

	public IGuiState performSetObstacleAction() {
		return currentState;
	}

	public IGuiState performStopAction() {
		return StoppedState.getInstance();
	}
	
	public IGuiState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IGuiState currentState) {
		this.currentState = currentState;
	}

}
