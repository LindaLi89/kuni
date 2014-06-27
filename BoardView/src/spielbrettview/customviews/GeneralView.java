package spielbrettview.customviews;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import model.Mouse;
import model.RegisteredMouses;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

import spielbrettview.customviews.groups.ArrowControlsGroup;
import spielbrettview.customviews.groups.FigureGroup;
import spielbrettview.customviews.groups.PlayControlsGroup;
import spielbrettview.customviews.groups.ScoreGroup;
import spielbrettview.customviews.groups.WorldAndStrategyGroup;
import spielbrettview.customviews.wizards.MouseWizard;
import util.Constants;
import ctrl.Controller;
import ctrl.MouseController;
import ctrl.StrategyController;
import ctrl.state.IGuiState;

public class GeneralView extends ViewPart {

	public static final String ID = "SpielbrettView.generalView";

	private WorldView canvas;
	private Label name;

	private ScoreGroup scoreGroup;
	private FigureGroup figureGroup;
	private PlayControlsGroup playControlsGroup;
	private Group grpSpielen;
	private ArrowControlsGroup arrowControlsGroup;
	private Group grpControls;
	private WorldAndStrategyGroup strategyGroup;


	private Group grpMain;
	private Group grpScores;
	private Group grpCanvas;
	
	private String selectedIcon;

	protected void createScoreBoard() {
		scoreGroup = new ScoreGroup();
		scoreGroup.paintScoreBoard(grpMain);
		
		//TODO D�rsam: warum wird der MouseController ausgerechnet hier initialisiert?
		MouseController mouseCtrl1 = new MouseController(name);
		Controller.registerMouseCtrl(mouseCtrl1);
		if(Controller.isSimpleModus()) {
			Controller.getStrategyCtrl().fillTextView();
		}
	}
	
	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public Group getGrpScores() {
		return grpScores;
	}

	protected void createFigures() {
		figureGroup = new FigureGroup();
		figureGroup.paintFigureGroup(grpSpielen, canvas);
	}
	
	protected void createPlayControls() {
		playControlsGroup = new PlayControlsGroup();
		playControlsGroup.paintPlayControlsGroup(grpSpielen, Controller.isAdvancedModus(), Controller.isSimpleModus());
	}
	
	protected void createArrowControls() {
		arrowControlsGroup = new ArrowControlsGroup();
		arrowControlsGroup.paintArrowControlsGroup(grpControls, canvas);
		Controller.registerStrategyCtrl(new StrategyController(arrowControlsGroup));
	}
	
	protected void createWorldAndStrategyControls() {
		strategyGroup = new WorldAndStrategyGroup();
		strategyGroup.paintWorldAndStrategyGroup(grpControls, canvas, Controller.isAdvancedModus(), Controller.isSimpleModus());
	}
	

	public void createPartControl(Composite parent) {

		grpMain = new Group(parent, SWT.NONE);
		grpMain.setLayout(new GridLayout(2, false));

		createControlGroups(grpMain);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();

	    
	    int w = (d.width/100)*90;
	    int h = (d.height/100)*80;
		
		Display.getCurrent().getActiveShell().setMinimumSize(w, h);

		setState(Controller.getCurrentState());
	}
	

	protected void createControlGroups(Group grpMain) {
		
		grpCanvas = new Group(grpMain, SWT.NONE);
		grpCanvas.setLayout(new GridLayout(2, false));
		grpCanvas.setText(Constants.GUI.canvas);
		GridData gd1 = new GridData(GridData.FILL, GridData.FILL, true, true);
		grpCanvas.setLayoutData(gd1);
		createCanvas(grpCanvas);
		
		grpControls = new Group(grpMain, SWT.NONE);
		grpControls.setLayout(new GridLayout(1, true));
		grpControls.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 1, 1));
		
		createArrowControls();
		
		createWorldAndStrategyControls();

		grpSpielen = new Group(grpMain, SWT.NONE);
		grpSpielen.setText("Spielen");
		grpSpielen.setLayout(new GridLayout(2, true));
		grpSpielen.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true));
		
		
		
		createPlayControls();

		createScoreBoard();

		createFigures();

		createMouses();
	}

	protected void createCanvas(Group grpCanvas) {
		
		canvas = new WorldView(grpCanvas,  SWT.DOUBLE_BUFFERED);
		canvas.setBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		GridData gd1 = new GridData(SWT.LEFT, SWT.CENTER, true, true);
		gd1.grabExcessHorizontalSpace = true;
		gd1.grabExcessVerticalSpace = true;
		gd1.horizontalSpan = 1;

		gd1.verticalSpan = 1;
		gd1.minimumHeight = Constants.WorldSize.minHeight;
		gd1.minimumWidth = Constants.WorldSize.minWidth;
		
	
		canvas.setLayoutData(gd1);	
	}


	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	protected void callMouseWizard() {
		try {
			Controller.getMouseCtrl().readSpielfigurtxt();
			drawMouseButton();
		} catch (IOException e) {
			MouseWizard wizard = new MouseWizard();
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(),
					wizard);
			wizardDialog.open();
		}
	}


	protected void createMouses() {
		if(Controller.isSimpleModus() || Controller.isAdvancedModus()) {
			callMouseWizard();
		}
		else {
			MouseWizard wizard1 = new MouseWizard(1);
			WizardDialog wizardDialog1 = new WizardDialog(Display.getDefault().getActiveShell(),
					wizard1);
			wizardDialog1.open();
			
			MouseWizard wizard2 = new MouseWizard(2);
			WizardDialog wizardDialog2 = new WizardDialog(Display.getDefault().getActiveShell(),
					wizard2);
			wizardDialog2.open();
		}
	}

	public void setCurrentWorldName(String name) {
		strategyGroup.setCurrentWorldName(name);
	}
	
	private void drawMouseButton() {
		Mouse mouse = RegisteredMouses.getInstance().getFirstMouse();
		if(mouse != null) {
			String img = mouse.getImg();
			selectedIcon = img;

			Image image = new Image(Display.getDefault(), GeneralView.class
					.getClassLoader().getResourceAsStream(Constants.Paths.mouseIconPath + selectedIcon));	

			ImageData imageData = image.getImageData();
			imageData = imageData.scaledTo(32, 32);
	
			Image i = new Image(Display.getDefault(),imageData); 

			FigureGroup.getMouseButton().setImage(i);
		}
	}
	
	protected void changeButtonEnable(Button b, boolean t) {
		//TODO D�rsam: wieso kann b hier �berhaupt null werden?
		//Initialisierung �berpr�fen
		if(b != null)
			b.setEnabled(t);
	}

	public void enableRecordButton() {
		playControlsGroup.enableRecordButton();
	}
	
	public void fillSelectionBoxWithFilesFromUserDirectory() {
		strategyGroup.fillSelectionBoxWithFilesFromUserDirectory();
	}
	
	public Button getPlaceMouseButton() {
		return strategyGroup.getPlaceMouseButton();
	}
	
	public Button getDrawCanvasButton() {
		return strategyGroup.getDrawCanvasButton();
	}
	
	public Combo getSelect() {
		return strategyGroup.getSelect();
	}
	
	public void setState(IGuiState currentState) {
		boolean state = false;
		
		state = currentState.isArrowsVisible();
		if(state)
			arrowControlsGroup.activateArrows();
		else
			arrowControlsGroup.deActivateArrows();
		
		state = currentState.isDdButtonsVisible();
		if(state)
			figureGroup.setObstaclesActive();
		else
			figureGroup.setObstaclesInactive();
		
		
		state = currentState.isDdMouseButtonVisible();
		if(state)
			figureGroup.setMouseActive();
		else
			figureGroup.setMouseInactive();
		
		
		state = currentState.isLoadWorldActive();
		if(state)
			strategyGroup.setWorldButtonActive();
		else
			strategyGroup.setWorldButtonInactive();
		
		
		state = currentState.isMouseVisible();
		//TODO D�rsam �berpr�fen, wann genau das Canvas aktualisiert wird!

		state = currentState.isPlayButtonVisible();
		if(state)
			playControlsGroup.enablePlayButton();
		else
			playControlsGroup.disablePlayButton();
		
		
		if(Controller.isAdvancedModus()) {
			state = currentState.isDebugButtonVisible();
			if(state)
				playControlsGroup.enableDebugButton();
			else
				playControlsGroup.disableDebugButton();
		}
		
		state = currentState.isStopButtonVisible();
		if(state)
			playControlsGroup.enableStopButton();
		else
			playControlsGroup.disableStopButton();
		
		
		if(Controller.isSimpleModus()) {
			state = currentState.isRecordButtonVisible();
			if(state)
				playControlsGroup.enableRecordButton();
			else
				playControlsGroup.disableRecordButton();
		}
		
		
		state = currentState.isResetButtonVisible();
		if(state)
			scoreGroup.enableResetButton();
		else
			scoreGroup.disableResetButton();
		
		
		//TODO D�rsam: im SimpleView eigentlich unn�tig
		state = currentState.isSetMouseButtonVisible();
		
		
		state = currentState.isViewFieldVisible();
		if(state)
			strategyGroup.enableViewButton();
		else
			strategyGroup.disableViewButton();
		
		int score = currentState.getScore();
		if(score == 0) {
			Controller.updateScore();
			//TODO die Stoppuhr muss im Controller laufen, nicht in der GUI
			scoreGroup.resetTime();
			playControlsGroup.stopTimerThread();
		}
		
	}
	
}
