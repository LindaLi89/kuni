package spielbrettview.customviews.wizards;


import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import spielbrettview.customviews.GeneralView;

public class XWizardPageOne extends WizardPage{
	private Composite container;
	private String path;
	private DirectoryDialog dd2;
	private Text text2;
	private Text text3;
	private Text text4;

	private static String xmlPfad;


	public XWizardPageOne() {
		super("First Page");
		setTitle("Pfade konfigurieren");
		setDescription("Hier k�nnen die Pfade zu den notwendigen Dateien konfiguriert werden!");
	}
	
	public void createControl(Composite parent){

		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		
		Label label2 = new Label(container, SWT.NULL);
		label2.setText("Pfad zu den XML-Dateien: ");
		
		text2 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text2.setLayoutData(gd);
		
		Button button2 = new Button(container, SWT.NULL);
		button2.setText("Verzeichnis w�hlen");
		
		new Label(container, SWT.NULL);
		
		dd2 = new DirectoryDialog(parent.getShell());

		button2.addSelectionListener(new SelectionListener(){

			@Override
			public void widgetSelected(SelectionEvent e) {
				path = dd2.open();
				System.out.println(path);
				text2.setText(path);
				setXmlPfad(path);
				GeneralView view = (GeneralView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
				view.fillSelectionBoxWithFilesFromUserDirectory();					
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {				
			}	
		});
		
		Label label3 = new Label(container, SWT.NULL);
		label3.setText("IP-Adresse Spieler 1: ");

		text3 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text3.setLayoutData(gd);
		
		Label label4 = new Label(container, SWT.NULL);
		label4.setText("IP-Adresse Spieler 2: ");

		text4 = new Text(container, SWT.BORDER | SWT.SINGLE);
		text4.setLayoutData(gd);
		
		
		setControl(container);
		setPageComplete(true);
	}

	public static String getXmlPfad(){
		return xmlPfad;
	}

	public static void setXmlPfad(String s){
		xmlPfad = s;
	}
}


