package spielbrettview;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

import ctrl.Controller;

import spielbrettview.customviews.*;
import spielbrettview.customviews.wizards.*;
import util.Constants;


public class CustomAction extends Action implements IWorkbenchAction {

	private static final String ID = "spielbrettview.CustomAction";

	private String action;

	public CustomAction(String a) {
		setId(ID);

		this.action = a;
	}

	public void run() {
		
		//je nachdem welche view geöffnet wurde wird der entsprechende Zweig aufgerufen

		switch (action) {
		case "E":
			openViewhideOld(GeneralView.ID);
			Controller.setCurrentModus(Constants.Modi.advanced);
			//Fenstergröße wird angepasst
			Display.getDefault().getActiveShell().setBounds(10, 10, 850, 850);
			
			centerToScreen();

			break;

		case "A":

			openViewhideOld(GeneralView.ID);
			Controller.setCurrentModus(Constants.Modi.simple);
			//Fenstergröße wird angepasst
			Display.getDefault().getActiveShell().setBounds(10, 10, 850, 850);
			
			centerToScreen();

			break;

		case "T":

			openViewhideOld(TournamentView.ID);
			Controller.setCurrentModus(Constants.Modi.tournament);
			//Fenstergröße wird angepasst
			Display.getDefault().getActiveShell().setBounds(10, 10, 850, 850);
			
			centerToScreen();

			break;

		case "B":

			openViewhideOld(BestListView.ID);
			Controller.setCurrentModus(Constants.Modi.none);
			//Fenstergröße wird angepasst
			Display.getDefault().getActiveShell().setBounds(10, 10, 500, 500);
			
			centerToScreen();

			break;

		case "punkt":

			PointWizard punkteWizard = new PointWizard();
			WizardDialog punkteDialog = new WizardDialog(Display.getDefault()
					.getActiveShell(), punkteWizard);
			punkteDialog.open();
			
			centerToScreen();

			break;
			
		case "pfad":
			XmlWizard xwizard = new XmlWizard();
			WizardDialog xmlDialog = new WizardDialog(Display.getDefault().getActiveShell(), xwizard);
			xmlDialog.open();
			
			centerToScreen();
			
			break;
			
		case "symbol":
			MouseWizard wizard = new MouseWizard();
			WizardDialog wizardDialog = new WizardDialog(Display.getDefault().getActiveShell(),
					wizard);
			wizardDialog.open();
			
			centerToScreen();
			
			break;
		}

	}

	private void openViewhideOld(String key) {
		
		
		//Verzeichnis aller views (außer View.ID) 
		String[] views = new String[4];

		views[0] = GeneralView.ID;
		views[1] = GeneralView.ID;
		views[2] = TournamentView.ID;
		views[3] = BestListView.ID;
		
		//die aufgerufene View wird auf "null" gesetzt

		for (int i = 0; i < views.length; i++) {

			if (key.equals(views[i])) {

				views[i] = null;

			}

		}

		IWorkbench wb = PlatformUI.getWorkbench();
		IWorkbenchWindow workbenchWindow = wb.getActiveWorkbenchWindow();

		IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();

		try {
			//aufgerufene View wird geöffnet
			workbenchPage.showView(key);
		} catch (PartInitException e1) {
			e1.printStackTrace();
		}
		
		
		//Hautpmenü View wird geschlossen
		IViewReference viewReference = workbenchPage.findViewReference(View.ID);

		workbenchPage.hideView(viewReference);
		
		//Alle anderen nicht geöffneten Views werden geschlossen
		for (String view : views) {
			if (view != null) {
				
				IViewReference viewRef = workbenchPage.findViewReference(view);
				workbenchPage.hideView(viewRef);

			}

		}
	}
	
	public static void centerToScreen(){
		
		Monitor primary = Display.getCurrent().getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = Display.getCurrent().getActiveShell().getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;
	    
	    Display.getCurrent().getActiveShell().setLocation(x, y);
	    

	}

	public void dispose() {
	}

}