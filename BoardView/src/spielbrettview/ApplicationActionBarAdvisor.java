package spielbrettview;



import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {  
	
	
	//Actions unter "Menü"  
	private CustomAction simple;
	private CustomAction advanced;
	private CustomAction tournement;
	private CustomAction best;
	
	//Actions unter "Einstellungen" 
	private CustomAction punkt;
	private CustomAction pfad;
	private CustomAction symbol;
	  
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {  
	super(configurer);  
	}  
	  
	protected void makeActions(IWorkbenchWindow window) {  

	simple = new CustomAction("E");  
	simple.setText("Einfacher Modus");  
	//simple.setAccelerator(SWT.CTRL + 'e');  <--Shortcut muss erts noch eingerichtet werden
	register(simple);  
	
	advanced = new CustomAction("A");  
	advanced.setText("Erweiterter Modus");   
	register(advanced);
		
	tournement = new CustomAction("T");  
	tournement.setText("Turnier Modus");   
	register(tournement);
	
	
	best = new CustomAction("B");  
	best.setText("Bestenliste");   
	register(best);
	
	punkt = new CustomAction("punkt");  
	punkt.setText("Punkte");    
	register(punkt);
	
	pfad = new CustomAction("pfad");  
	pfad.setText("Pfade konfigurieren");   
	register(pfad);
	
	symbol = new CustomAction("symbol");  
	symbol.setText("Spielfigur erstellen");   
	register(symbol);
	}  
	  
	protected void fillMenuBar(IMenuManager menuBar) {  
	  
	MenuManager fileMenu = new MenuManager("&Menü", "menü"); 
	fileMenu.add(simple); 
	fileMenu.add(advanced); 
	fileMenu.add(tournement); 
	fileMenu.add(best); 
	menuBar.add(fileMenu); 
	
	MenuManager preferencesMenu = new MenuManager("&Einstellungen", "einstellungen"); 
	preferencesMenu.add(punkt);
	preferencesMenu.add(pfad);
	preferencesMenu.add(symbol);
	menuBar.add(preferencesMenu);
	}  
	  
	}  