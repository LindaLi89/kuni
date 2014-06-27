package ctrl.state.extended;


public class RecordState extends GuiState {
	
	private static RecordState instance = null;

	public static RecordState getInstance() {
		if(instance == null)
			instance = new RecordState();
		return instance;
	}
	
	public RecordState() {
		loadWorldActive = true;
		mouseVisible = true;
		playButtonVisible = false;
		debugButtonVisible = false;
		stopButtonVisible = false;
		resetButtonVisible = false;
		ddButtonsVisible = true;
		ddMouseButtonVisible = false;
		score = 1;
		arrowsVisible = true;
		setMouseButtonVisible = false;	
		viewFieldVisible = true;
		recordButtonVisible = false;
	}
	
}
