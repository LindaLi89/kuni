package ctrl;

import org.eclipse.ui.PlatformUI;

import ctrl.state.IGuiState;
import ctrl.state.IStateTransition;
import model.Mouse;
import model.MouseList;
import spielbrettview.customviews.GeneralView;
import util.Constants;

public class Controller {
	
	private static WorldController worldCtrl = null;
	private static MouseController mouseCtrl;
	private static ScoreController scoreCtrl;
	private static StrategyController strategyCtrl;
	
	public static StrategyController getStrategyCtrl() {
		return strategyCtrl;
	}

	private static MouseList mouseList = new MouseList();
	private static IStateTransition stateTransitions;
	
	private static String currentModus;
	public static WorldController getWorldCtrl() {
		return worldCtrl;
	}
	
	public static MouseController getMouseCtrl() {
		return mouseCtrl;
	}
	
	public static ScoreController getScoreCtrl() {
		return scoreCtrl;
	}


	public static void registerWorldCtrl(WorldController newCtrl) {
		worldCtrl = newCtrl;
	}
	
	public static void registerMouseCtrl(MouseController ctrl) {
			mouseCtrl = ctrl;
	}
	
	public static void registerStrategyCtrl(StrategyController ctrl) {
		strategyCtrl = ctrl;
}
	
	public static void registerScoreCtrl(ScoreController ctrl) {
		scoreCtrl = ctrl;
}

	public static MouseList getMouseList() {
		return mouseList;
	}
	
	public static void setCurrentModus(String modus) {
		currentModus = modus;
		if(isSimpleModus())
			stateTransitions = new ctrl.state.simple.StateTransitions();
		else 
			if(isAdvancedModus())
				stateTransitions = new ctrl.state.extended.StateTransitions();
	}

	public static String getCurrentModus() {
		return currentModus;
	}

	public static boolean isSimpleModus() {
		if(currentModus != null)
			return currentModus.equalsIgnoreCase(Constants.Modi.simple);
		return false;
	}
	
	public static boolean isAdvancedModus() {
		return currentModus.equalsIgnoreCase(Constants.Modi.advanced);
	}
	
	public static boolean isTournamentModus(){
		if(currentModus != null)
			return currentModus.equalsIgnoreCase(Constants.Modi.tournament);
		return false;
	}

	public static void registerMouseForWorld(Mouse mouse) {
		mouseList.addMouse(mouse);
		worldCtrl.updateMovableObjects();
	}
	
	public static void deregisterAllMiceFromWorld() {
		mouseList.removeAll();
		if(worldCtrl != null) {
			worldCtrl.updateMovableObjects();
			worldCtrl.updateCanvas();
		}
	}
	
	public static GeneralView getCurrentView() {
		return (GeneralView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
	}
	
	public static void setGuiStates() {
		((GeneralView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart())
			.setState(stateTransitions.getCurrentState());
	}
	
	public static IGuiState getCurrentState() {
		return stateTransitions.getCurrentState();
	}
	
	public static void actionPerformed(String action) {
		stateTransitions.nextState(action);
		setGuiStates();
	}
	
	public static void updateScore() {
		scoreCtrl.resetScore();	
	}
	
	public static void updateActionList(String action) {
		strategyCtrl.updateTextView(action);
	}
	
	public static void updateRuleList(String cond, String then) {
		strategyCtrl.updateTextView(cond, then);
	}
	
	public static void emptyRuleList() {
		strategyCtrl.resetTextViewRules();
	}
	
	public static void resetActionList() {
		strategyCtrl.resetTextViewActions();
	}
	
}
