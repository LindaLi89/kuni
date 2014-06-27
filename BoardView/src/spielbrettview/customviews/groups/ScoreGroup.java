package spielbrettview.customviews.groups;


import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import spielbrettview.customviews.GeneralView;
import spielbrettview.customviews.Stoppuhr;
import util.Constants;
import ctrl.Controller;
import ctrl.ScoreController;

public class ScoreGroup {
	
	private Label name;
	private Label stoppuhr1;
	private Label stoppuhr2;
	private Button resetButton;
	private Group grpScores;
	private Stoppuhr time;
	private Label score1;
	private Label score2;
	private long sek = 00;
	private long min = 00;
	private long startTime;
	

	
	public void paintScoreBoard(Group parent) {
		
		grpScores = new Group(parent, SWT.NONE);
		grpScores.setLayout(new GridLayout(1, true));
		grpScores.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 1, 1));
		
		grpScores.setText(Constants.GUI.scoreGrp);
		name = new Label(grpScores, SWT.NONE);



		name.setText(Constants.GUI.mouseNameLabel);

		score1 = new Label(grpScores, SWT.NONE);
		
		score1.setFont( new Font(Display.getCurrent(),"Arial", 14, SWT.BOLD ) );

		stoppuhr1 = new Label(grpScores, SWT.NULL);
		
		stoppuhr1.setFont( new Font(Display.getCurrent(),"Arial", 14, SWT.BOLD ) );

		Controller.registerScoreCtrl(new ScoreController(this, grpScores, score1));
		score1.setText(Constants.GUI.pointsLabel);
		
		resetButton = new Button(grpScores, SWT.NONE);
		Image image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(Constants.Paths.iconPath + Constants.Icons.resetIcon));
		resetButton.setImage(image);
		resetButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				
				min = 00;
				sek = 00;
				stoppuhr1.setText(min + " : " + sek);
				Controller.getScoreCtrl().resetScore();
				Controller.actionPerformed(Constants.Actions.reset);
				GeneralView view = Controller.getCurrentView();
				
				Controller.getWorldCtrl().resetWorld();
				
				//TODO Dörsam: überprüfen, ob es nicht eine geschicktere Stelle für das 
				//Zurücksetzen der Mäuse gibt
				Controller.deregisterAllMiceFromWorld();			
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}
	
	public void updateName(String n) {
		name.setText(Constants.GUI.mouseNameLabel+ n);
		name.pack();
		updateGroup();
	}

	//TODO Dörsam: zur allgemeinen Benutzung zur Verfügung stellen
	protected void updateGroup() {
		grpScores.layout();
		grpScores.pack();
		grpScores.update();
	}
	
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void updateText() {

		Date currentTime = new Date();
		long duration = (currentTime.getTime());

		long act = (duration - startTime) / Constants.Times.milli;

		// TODO stimmt die Abfrage? sollte es nicht < 60 heissen?
		if (act < (Constants.Times.sec - 1)) {

			sek = act;

		} else {

			min = act / Constants.Times.sec;

			sek = act % Constants.Times.sec;

		}

		stoppuhr1.setText(min + " : " + sek);
		if (Controller.isTournamentModus()) {
			stoppuhr2.setText(min + " : " + sek);
			stoppuhr2.pack();
		}
		stoppuhr1.pack();

	}
	
	public void enableResetButton() {
		resetButton.setEnabled(true);
	}
	
	public void disableResetButton() {
		resetButton.setEnabled(false);
	}
	
	public void resetTime() {
		startTime = (new Date()).getTime();
		
	}

}
