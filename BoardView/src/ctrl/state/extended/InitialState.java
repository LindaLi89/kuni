package ctrl.state.extended;


public class InitialState extends GuiState {
	
	private static InitialState instance = null;

	public static InitialState getInstance() {
		if(instance == null)
			instance = new InitialState();
		return instance;
	}
	
	public InitialState() {
		loadWorldActive = true;
		mouseVisible = false;
		playButtonVisible = false;
		debugButtonVisible = false;
		stopButtonVisible = false;
		resetButtonVisible = false;
		ddButtonsVisible = false;
		ddMouseButtonVisible = false;
		score = 0;
		arrowsVisible = false;
		setMouseButtonVisible = false;
		viewFieldVisible = false;
		recordButtonVisible = false;
	}
	
}
