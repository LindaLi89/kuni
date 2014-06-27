package spielbrettview.customviews;

import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import spielbrettview.customviews.wizards.MouseWizard;

public class TournamentView extends GeneralView {
	
	public static final String ID = "SpielbrettView.tournamentView";
	
	private Label name1;
	private Label name2;
	
	//TODO Dörsam umschreiben auf den ScoreController
//	protected void createScoreBoard() {
//
//		grpScores.setText("Score ");
//		grpScores.setLayout(new GridLayout(2,true));
//		
//		
//		Group grpPlayer1 = new Group(grpScores, SWT.NONE);
//		grpPlayer1.setLayout(new GridLayout(1, true));
//		grpPlayer1.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
//		grpPlayer1.setText("Player 1");
//		
//		name1 = new Label (grpPlayer1, SWT.NONE);
//		name1.setText("Name: ");
//		score1 = new Label (grpPlayer1, SWT.NONE);
//		score1.setText("Punkte: ");
//		stoppuhr1 = new Label (grpPlayer1, SWT.NULL);
//		stoppuhr1.setText("00:00");
//		
//		
//		Group grpPlayer2 = new Group(grpScores, SWT.NONE);
//		grpPlayer2.setLayout(new GridLayout(1, true));
//		grpPlayer2.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
//		grpPlayer2.setText("Player 2");
//		
//		name2 = new Label (grpPlayer2, SWT.NONE);
//		name2.setText("Name: ");
//		score2 = new Label (grpPlayer2, SWT.NONE);
//		score2.setText("Punkte: ");
//		stoppuhr2 = new Label (grpPlayer2, SWT.NULL);
//		stoppuhr2.setText("00:00");
//		
//		scoreCtrl1 = new ScoreController(grpPlayer1, grpPlayer2, score1, score2);
//		mouseCtrl1 = new MouseController(grpScores, name1, name2);
//		Controller.registerMouseCtrl(mouseCtrl1);
//		
//	}
	
//	
//
//	@Override
//	protected void createStrategySelectButton(Group grpSelectCanvas) {
//		Button p1Strategy = createPushButton(grpSelectCanvas, "Strategie wählen");
//		Button p2Strategy = createPushButton(grpSelectCanvas, "Strategie wählen");
//		
//	}
//
//	@Override
//	protected void createStrategySelect(Group grpSelectCanvas) {
//		Combo strategyCombo1 = new Combo(grpSelectCanvas, SWT.NONE);
//		Combo strategyCombo2 = new Combo(grpSelectCanvas, SWT.NONE);
//		
//	}
}
