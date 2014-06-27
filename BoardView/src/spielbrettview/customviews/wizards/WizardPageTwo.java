package spielbrettview.customviews.wizards;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;


public class WizardPageTwo extends WizardPage {
	private Composite container;
	private static String[] icons;
	private static String selectedIcon="";
	private Button[] button;

	public WizardPageTwo() {
		super("Second Page");
		setTitle("Erstelle deine eigene Spielfigur");
		setDescription("Schritt 2: Wähle ein Icon");
	}

	@Override
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 5;
		
		String klasse = ResourcesPlugin.getWorkspace().getRoot()
				.getLocation().toFile().toString();
		klasse = klasse.substring(0, klasse.lastIndexOf(System
				.getProperty("file.separator")));
		klasse += "\\kinderuni\\BoardView\\icons\\spielfigurIcons\\";
		File f = new File(klasse);
		icons = f.list();
		
		button = new Button[icons.length];
		for(int i = 0; i < icons.length; i++){
		Image image = new Image(null,
				WizardPageTwo.class.getResourceAsStream("..\\..\\..\\icons\\spielfigurIcons\\" + icons[i]));
		button[i] = new Button(container, SWT.RADIO);
		
	
		
		ImageData imageData = image.getImageData();
		imageData = imageData.scaledTo(60, 60);

		Image x = new Image(Display.getDefault(),imageData); 

		button[i].setImage(x);
		
		button[i].addSelectionListener(new SelectionListener() {
		
			
			public void widgetSelected(SelectionEvent e) {
				if (e.getSource() == button[0]) {
					selectedIcon = icons[0];
				} else if (e.getSource() == button[1]) {
					selectedIcon = icons[1];
				} else if (e.getSource() == button[2]) {
					selectedIcon = icons[2];
				} else if (e.getSource() == button[3]) {
					selectedIcon = icons[3];
				} else if (e.getSource() == button[4]) {
					selectedIcon = icons[4];
				} else if (e.getSource() == button[5]) {
					selectedIcon = icons[5];
				} else if (e.getSource() == button[6]) {
					selectedIcon = icons[6];
				} else if (e.getSource() == button[7]) {
					selectedIcon = icons[7];
				} else if (e.getSource() == button[8]) {
					selectedIcon = icons[8];
				} else if (e.getSource() == button[9]) {
					selectedIcon = icons[9];
				}else if (e.getSource() == button[10]) {
					selectedIcon = icons[10];
				}else if (e.getSource() == button[11]) {
					selectedIcon = icons[11];
				}else if (e.getSource() == button[12]) {
					selectedIcon = icons[12];
				}else if (e.getSource() == button[13]) {
					selectedIcon = icons[13];
				}else if (e.getSource() == button[14]) {
					selectedIcon = icons[14];
				}else if (e.getSource() == button[15]) {
					selectedIcon = icons[15];
				}
				setPageComplete(true);
			}
			
			
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		}

		setControl(container);
		setPageComplete(false);
	}
	
	public static String getImg(){
		return selectedIcon;
	}
	
	public String getDefaultImg() {
		return icons[0];
	}
}
