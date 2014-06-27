package spielbrettview.customviews;


import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import spielbrettview.CustomAction;
import util.Constants;

import ctrl.Controller;




/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class View extends ViewPart {
	
	public static final String ID = "SpielbrettView.view";
	
	
	private static View instance = null;
	
	
	private IWorkbenchWindow workbenchWindow; 
	private Shell shell;
	
	

	
	
	public View() {
	}
	
	public IWorkbenchWindow getWorkbenchWindow() {
		return workbenchWindow;
	}

	public void setWorkbenchWindow(IWorkbenchWindow workbenchWindow) {
		this.workbenchWindow = workbenchWindow;
	}

	public void createPartControl(Composite parent) {
		
		
	
	
		workbenchWindow = getSite().getWorkbenchWindow();
		
		parent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		shell = 	parent.getShell();
		shell.setBounds(10, 10, 210, 180);
		
		
		Monitor primary = Display.getCurrent().getPrimaryMonitor();
	    Rectangle bounds = primary.getBounds();
	    Rectangle rect = Display.getCurrent().getActiveShell().getBounds();
	    
	    int x = bounds.x + (bounds.width - rect.width) / 2;
	    int y = bounds.y + (bounds.height - rect.height) / 2;

	    Display.getCurrent().getActiveShell().setLocation(x, y);
	    
		parent.setLayout(new GridLayout(1, true));
//		parent.setLayoutData(new GridData(GridData.CENTER, GridData.CENTER, true, true));
		
		GridData centerLayout = new GridData(GridData.CENTER, GridData.BEGINNING, true, true);
		Button btnBestenliste = new Button(parent, SWT.NULL);
		btnBestenliste.setLayoutData(centerLayout);

		btnBestenliste.setText("Bestenliste");
		
		btnBestenliste.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				//in neue View starten
				try {
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(BestListView.ID);
					shell.setBounds(10, 10, 500, 500);
					CustomAction.centerToScreen();
				} catch (PartInitException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Menüview schließen
				getViewSite().getPage().hideView(View.this); 
				
			}
		});
		
		Button tournament_button = new Button(parent, SWT.CENTER);
		tournament_button.setLayoutData(centerLayout);
		tournament_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				//in neue View starten
				try {
					Controller.setCurrentModus(Constants.Modi.tournament);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(TournamentView.ID);
					shell.setBounds(10, 10, 820, 800);
					CustomAction.centerToScreen();
				} catch (PartInitException e1) {
					Controller.setCurrentModus(Constants.Modi.none);	
				}
				
				//Menüview schließen
				getViewSite().getPage().hideView(View.this); 
				
			}
		});
		tournament_button.setText("Turniermodus");
		
		Button advanced_button = new Button(parent, SWT.CENTER);
		advanced_button.setLayoutData(centerLayout);
		advanced_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				//in neue View starten
				try {
					Controller.setCurrentModus(Constants.Modi.advanced);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(GeneralView.ID);
					shell.setBounds(10, 10, 850, 850);
					CustomAction.centerToScreen();
				} catch (PartInitException e1) {
					Controller.setCurrentModus(Constants.Modi.none);	
				}
				
				//Menüview schließen
				getViewSite().getPage().hideView(View.this); 
			}
		});
		advanced_button.setText("Erweiterter Modus");
		
		
		Button simple_button = new Button(parent, SWT.CENTER);
		simple_button.setLayoutData(centerLayout);
		simple_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				//in neue View starten
				try {
					Controller.setCurrentModus(Constants.Modi.simple);
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(GeneralView.ID);
					shell.setBounds(10, 10, 1000, 850);
					CustomAction.centerToScreen();
				} catch (PartInitException e1) {
					Controller.setCurrentModus(Constants.Modi.none);	
				}
				
				//Menüview schließen
				getViewSite().getPage().hideView(View.this); 
				
			}
		});
		simple_button.setText("Einfacher Modus");
		

		// TODO Auto-generated method stub
		
	}
	
	public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}
}
