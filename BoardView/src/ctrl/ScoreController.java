package ctrl;

import model.RegisteredMouses;
import model.Score;

import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import spielbrettview.customviews.groups.ScoreGroup;

public class ScoreController {
	private Group scores1;
	private Label scoreLabel1;
	private Group scores2;
	private Label scoreLabel2;
	
	private String name;
	private String points;
	private String time;
	
	private ScoreGroup grpScores;
	
	private Score scoreMouse1;
	private Score scoreMouse2;
	


	public ScoreController(ScoreGroup sg, Group score, Label scoreLabel) {
		super();
		grpScores = sg;
		this.scores1 = score;
		this.scoreLabel1 = scoreLabel;
		Controller.registerScoreCtrl(this);
	}
	
	public ScoreController(Group score1, Group score2, Label scoreLabel1, Label scoreLabel2){
		super();
		this.scores1 = score1;
		this.scores2 = score2;
		this.scoreLabel1 = scoreLabel1;
		this.scoreLabel2 = scoreLabel2;
		Controller.registerScoreCtrl(this);
	}
	
	public void resetScore (){
		RegisteredMouses.getInstance().getFirstMouse().resetScorePoints();
		updateScoreView();
	}
	
	public void setScore (int punkte){
		RegisteredMouses.getInstance().getFirstMouse().addScorePoints(punkte);
		updateScoreView();
	}
	
	public void setScoreMouse2(int punkte){
		RegisteredMouses.getInstance().getMouse(1).addScorePoints(punkte);
		updateScoreView();
	}
	
	public void updateNameView() {
		name = scoreMouse1.getName();
		grpScores.updateName(name);
	}
	
	public void updateScoreView() {
		scoreLabel1.setText("Punkte: " + RegisteredMouses.getInstance().getFirstMouse().getScore());
		scores1.layout();
		if(Controller.isTournamentModus()){
			scoreLabel2.setText("Punkte: " + RegisteredMouses.getInstance().getMouse(1).getScore());
			scores2.layout();
		}
	}

	public void setScoreMouse1(Score scoreMouse1) {
		this.scoreMouse1 = scoreMouse1;
	}

	public void setScoreMouse2(Score scoreMouse2) {
		this.scoreMouse2 = scoreMouse2;
	}
	
	public void updateText() {
		grpScores.updateText();
	}
	
	public void setStartTime(long startTime) {
		grpScores.setStartTime(startTime);
	}
	
	public void enableResetButton() {
		grpScores.enableResetButton();
	}
	
}
