package ctrl.state.extended;


public class StoppedState extends GuiState {
	
	private static StoppedState instance = null;

	public static StoppedState getInstance() {
		if(instance == null)
			instance = new StoppedState();
		return instance;
	}
	
	public StoppedState() {
		loadWorldActive = true;
		mouseVisible = true;
		playButtonVisible = true;
		debugButtonVisible = true;
		stopButtonVisible = false;
		resetButtonVisible = true;
		ddButtonsVisible = true;
		ddMouseButtonVisible = false;
		score = 2;
		arrowsVisible = true;
		setMouseButtonVisible = false;	
		viewFieldVisible = true;
		recordButtonVisible = true;
	}
	
}
