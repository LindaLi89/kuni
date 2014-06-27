package ctrl.state.simple;

import ctrl.state.IGuiState;

public class GuiState implements IGuiState {

	protected boolean loadWorldActive = true;
	protected  boolean mouseVisible = false;
	protected  boolean playButtonVisible = false;
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
	
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isLoadWorldActive()
	 */
	@Override
	public boolean isLoadWorldActive() {
		return loadWorldActive;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isMouseVisible()
	 */
	@Override
	public boolean isMouseVisible() {
		return mouseVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isPlayButtonVisible()
	 */
	@Override
	public boolean isPlayButtonVisible() {
		return playButtonVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isStopButtonVisible()
	 */
	@Override
	public boolean isStopButtonVisible() {
		return stopButtonVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isResetButtonVisible()
	 */
	@Override
	public boolean isResetButtonVisible() {
		return resetButtonVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isDdButtonsVisible()
	 */
	@Override
	public boolean isDdButtonsVisible() {
		return ddButtonsVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isDdMouseButtonVisible()
	 */
	@Override
	public boolean isDdMouseButtonVisible() {
		return ddMouseButtonVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#getScore()
	 */
	@Override
	public int getScore() {
		return score;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isArrowsVisible()
	 */
	@Override
	public boolean isArrowsVisible() {
		return arrowsVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isSetMouseButtonVisible()
	 */
	@Override
	public boolean isSetMouseButtonVisible() {
		return setMouseButtonVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isViewFieldVisible()
	 */
	@Override
	public boolean isViewFieldVisible() {
		return viewFieldVisible;
	}
	/* (non-Javadoc)
	 * @see model.state.simple.IGuiState#isRecordButtonVisible()
	 */
	@Override
	public boolean isRecordButtonVisible() {
		return recordButtonVisible;
	}
	@Override
	public boolean isDebugButtonVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
