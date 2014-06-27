package ctrl.state.extended;


public class StartedState extends GuiState {
	
	private static StartedState instance = null;

	public static StartedState getInstance() {
		if(instance == null)
			instance = new StartedState();
		return instance;
	}
	
	public StartedState() {
		loadWorldActive = true;
		mouseVisible = true;
		playButtonVisible = false;
		debugButtonVisible = false;
		stopButtonVisible = true;
		resetButtonVisible = false;
		ddButtonsVisible = true;
		ddMouseButtonVisible = false;
		score = 1;
		arrowsVisible = false;
		setMouseButtonVisible = false;	
		viewFieldVisible = true;
		recordButtonVisible = false;
	}
	
}
