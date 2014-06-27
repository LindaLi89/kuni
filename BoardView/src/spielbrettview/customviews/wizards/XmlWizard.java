package spielbrettview.customviews.wizards;

import org.eclipse.jface.wizard.Wizard;

public class XmlWizard extends Wizard{
	protected XWizardPageOne one;

	public XmlWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public void addPages(){
		one = new XWizardPageOne();
		addPage(one);
	}
	
	public boolean performFinish(){
		
		 return true;
	}
}
