package ctrl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import model.AbstractObject;
import model.AdvancedMouse;
import model.Mouse;
import model.RegisteredMouses;
import model.Score;
import model.SimpleMouse;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;

import record.Record;
import record.Rule;
import util.Constants;

public class MouseController {
	private Label labelName1;
	private Label labelName2;
	private String name1;
	private String name2;
	
	
	//TODO macht ein Controller f�r beide M�use Sinn?
	//es w�re eher besser, ihn aufzuteilen, dann wird auch der Turniermodus einfacher
	private Mouse mouse1;
	private Mouse mouse2;
	
	private boolean firstMouseOK = false;


	private Iterator<String> iter;
	
	private Record record = new Record();

	//nur für den einfachen Modus
	public Record getRecord() {
		return record;
	}

	//nur für den einfachen Modus
	public void resetRecord() {
		this.record = new Record();
	}


	public MouseController(Label name1, Label name2) {
		super();
		this.labelName1 = name1;
		this.labelName2 = name2;
		try {
			record.readStrategy(Constants.FileNames.strategy);
			record.readRules(Constants.FileNames.rules);
		} catch (IOException e) {
			//do nothing (Strategie wird nicht gebraucht, wenn das File nicht existiert
		}
		

	}

	public MouseController(Label name1) {
		super();
		this.labelName1 = name1;
		
		try {
			record.readStrategy(Constants.FileNames.strategy);
			record.readRules(Constants.FileNames.rules);
		} catch (IOException e) {
			//do nothing (Strategie wird nicht gebraucht, wenn das File nicht existiert
		}

	}

	public void registerMouseForCurrentWorld() {
		if (Controller.isTournamentModus()) {
			Controller.registerMouseForWorld(RegisteredMouses.getInstance()
					.getFirstMouse());
			Controller.registerMouseForWorld(RegisteredMouses.getInstance()
					.getMouse(1));
		} else {
			Controller.registerMouseForWorld(mouse1);
		}
	}

	public void registerMouse(String name, String img) {
		//TODO D�rsam: Momentane Annahme: Turniermodus nur f�r den fortgeschrittenen Part
		
		if(!Controller.isTournamentModus()) {
			if (Controller.isSimpleModus()) {
				mouse1 = new SimpleMouse(name, img);	
			} else if (Controller.isAdvancedModus()) {
				mouse1 = new AdvancedMouse(name, img);
			} 
			registerMouseAndScore(mouse1);
		}
		else {
			if(!firstMouseOK) {
				mouse1 = new AdvancedMouse(name, img);
				registerMouseAndScore(mouse1);
				firstMouseOK = true;
			}
			else {
				mouse2 = new AdvancedMouse(name, img);
				registerMouseAndScore(mouse2);
			}
		}
	}

	protected void registerMouseAndScore(Mouse mouse) {
		RegisteredMouses.getInstance().replaceMouse(mouse);
		
		ScoreController scoreCtrl = Controller.getScoreCtrl();
		Score mouseScore = mouse.getScore();
		if(!firstMouseOK)
			scoreCtrl.setScoreMouse1(mouseScore);
		else
			scoreCtrl.setScoreMouse2(mouseScore);
		mouseScore.updateName();
	}
	
	public void updateName() {
		labelName1.setText("Name: " + name1);
		Controller.getCurrentView().getGrpScores().layout();
		if (Controller.isTournamentModus()) {
			labelName2.setText("Name: " + name2);
			Controller.getCurrentView().getGrpScores().layout();
		}
	}

	//wird nur ben�tigt, wenn einmal angelegte Figuren gespeichert werden sollen, um sie 
	//beim Moduswechsel noch mal benutzen zu k�nnen
	public void readSpielfigurtxt() throws IOException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
	    IWorkspaceRoot root = workspace.getRoot();
		FileReader fr = new FileReader("spielfigur.txt");
		BufferedReader br = new BufferedReader(fr);
		String name = br.readLine();
		String image = br.readLine();
		fr.close();
		this.registerMouse(name, image);
	}

	public void initIterator() {
		iter = record.getSequenz().iterator();
	}

	//nur für den einfachen Modus
	public String getNextStep() {
		if(iter == null) {
			initIterator();
		}
		if (iter.hasNext()) {
			return iter.next();
		} else {
			return null;
		}
	}

	//nur für den einfachen Modus
	public Iterator<Rule> getRuleIter() {
		return record.getRules().iterator();
	}
	
	//nur für den einfachen Modus
	public String getDefaultAction() {
		return getActionForCondition(Constants.Ifs.defaultStep);
	}
	
	//nur für den einfachen Modus
	public String getGoalAction() {
		return getActionForCondition(Constants.Ifs.goalAchiewed);
	}

	//nur für den einfachen Modus
	public String getActionForCondition(String cond) {
		Iterator<Rule> iter = getRuleIter();
		String result = null;
		while(result == null && iter.hasNext()) {
			Rule rule = iter.next();
			if(rule.getIfCond().equalsIgnoreCase(cond)) {
				result = rule.getThen();
			}
		}
		return result;
	}
	
	public Mouse getMouse1() {
		return mouse1;
	}

	//nur für den Turniermodus
	public Mouse getMouse2() {
		return mouse2;
	}
	
	//TODO f�r beide M�use erweitern
	public boolean setMouseOnPosition(int x, int y) {
		AbstractObject objectAtPostion = mouse1.defineStartPosition(x, y);
		if(objectAtPostion == null) {
			//Position nicht erlaubt
			return false;
		}
		else {
			WorldController wCtrl = Controller.getWorldCtrl();
			if(objectAtPostion instanceof Mouse) {
				wCtrl.updateWorldAndMouse(null, mouse1);
			}
			else {
				//Position erlaubt und Objekt gegessen
				wCtrl.updateWorldAndMouseWithPoints(null, mouse1, objectAtPostion);			
			}
			registerMouseForCurrentWorld();
			return true;
		}
	}
	
	
	public Point getMouseStartPosition() {
		return Controller.getWorldCtrl().getMouseStartPosition();
	}

}
