package ctrl.state.simple;


public class StartStateWithWorld extends GuiState {
	
	private static StartStateWithWorld instance = null;

	public static StartStateWithWorld getInstance() {
		if(instance == null)
			instance = new StartStateWithWorld();
		return instance;
	}
	
	public StartStateWithWorld() {
		loadWorldActive = true;
		mouseVisible = false;
		playButtonVisible = false;
		stopButtonVisible = false;
		resetButtonVisible = false;
		ddButtonsVisible = true;
		ddMouseButtonVisible = true;
		score = 0;
		arrowsVisible = false;
		setMouseButtonVisible = true;	
		viewFieldVisible = false;
		recordButtonVisible = false;
	}
	
}
