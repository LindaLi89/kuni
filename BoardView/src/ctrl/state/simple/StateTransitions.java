package ctrl.state.simple;

import ctrl.state.IGuiState;
import ctrl.state.IStateTransition;
import util.Constants;

public class StateTransitions implements IStateTransition {
	
	IGuiState currentState;
	
	
	
	public StateTransitions() {
		super();
		currentState = InitialState.getInstance();
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#nextState(java.lang.String)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performArrowAction()
	 */
	@Override
	public IGuiState performArrowAction() {
		return currentState;
	}
	
	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performOkButtonAction()
	 */
	@Override
	public IGuiState performOkButtonAction() {
		return currentState;
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performLoadNewWorldAction()
	 */
	@Override
	public GuiState performLoadNewWorldAction() {
		return StartStateWithWorld.getInstance();
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performPlayAction()
	 */
	@Override
	public GuiState performPlayAction() {
		return StartedState.getInstance();
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performRecordAction()
	 */
	@Override
	public GuiState performRecordAction() {
		return RecordState.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performStopRecordAction()
	 */
	@Override
	public GuiState performStopRecordAction() {
		return ActiveState.getInstance();
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performResetAction()
	 */
	@Override
	public GuiState performResetAction() {
		return StartStateWithWorld.getInstance();
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performSetMouseAction()
	 */
	@Override
	public GuiState performSetMouseAction() {
		return ActiveState.getInstance();
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performSetObstacleAction()
	 */
	@Override
	public IGuiState performSetObstacleAction() {
		return currentState;
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#performStopAction()
	 */
	@Override
	public IGuiState performStopAction() {
		return StoppedState.getInstance();
	}
	
	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#getCurrentState()
	 */
	@Override
	public IGuiState getCurrentState() {
		return currentState;
	}

	/* (non-Javadoc)
	 * @see model.state.simple.IStateTransition#setCurrentState(model.state.simple.GuiState)
	 */
	@Override
	public void setCurrentState(IGuiState currentState) {
		this.currentState = currentState;
	}

}
