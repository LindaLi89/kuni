package spielbrettview.customviews.wizards;

import model.Leaf;
import model.Mushroom;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

public class PointWizard extends Wizard{
	
	protected PWizardPageOne one;
	
	public PointWizard(){
		super();
		setNeedsProgressMonitor(true);
	}
	
	public void addPages(){
		one = new PWizardPageOne();
		addPage(one);
	}
	
	public boolean performFinish() {
		try{
		Leaf.setPoints(Integer.parseInt(one.getText1()));
		Mushroom.setPoints(Integer.parseInt(one.getText2()));
		return true;
		}catch(NumberFormatException e){
			MessageDialog.openInformation(getShell(), "Hier ist etwas schief gelaufen...", "Bitte nur Zahlen eingeben!");
			return false;
		}
	}
	
	

}
