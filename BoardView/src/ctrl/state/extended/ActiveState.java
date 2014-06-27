package ctrl.state.extended;


public class ActiveState extends GuiState {
	
	private static ActiveState instance = null;

	public static ActiveState getInstance() {
		if(instance == null)
			instance = new ActiveState();
		return instance;
	}
	
	public ActiveState() {
		loadWorldActive = true;
		mouseVisible = true;
		playButtonVisible = true;
		debugButtonVisible = true;
		stopButtonVisible = false;
		resetButtonVisible = false;
		ddButtonsVisible = true;
		ddMouseButtonVisible = false;
		score = 1;
		arrowsVisible = true;
		setMouseButtonVisible = false;	
		viewFieldVisible = true;
		recordButtonVisible = true;
	}
	
}
