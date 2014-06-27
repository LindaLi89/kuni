package spielbrettview.customviews.groups;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.World;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import rpc.RPCClient;
import spielbrettview.customviews.WorldView;
import spielbrettview.customviews.wizards.XWizardPageOne;
import util.Constants;
import ctrl.Controller;
import ctrl.WorldController;

public class WorldAndStrategyGroup {
	private Group grpSelectCanvas;
	private Button drawCanvasButton;
	private Combo select;
	private List<String> dateien;
	private String currentWorldName;
	private Label currentWorldNameLabel;
	private Label currentStrategyLabel;
	private Button buttonPointOfView;
	private Combo strategyCombo;
	private String[] folderData;

	//TODO Dï¿½rsam: static nur temporï¿½r - wied spï¿½ter noch durch di epassende GRoup-Klasse ersetzt
	private static Button placeMouseButton;

	public Button getDrawCanvasButton() {
		return drawCanvasButton;
	}

	public Combo getSelect() {
		return select;
	}

	public void paintWorldAndStrategyGroup(Group parent, WorldView c, boolean isAdvancedModus, boolean isSimpleModus) {
		final WorldView canvas;
		canvas = c;
		grpSelectCanvas = new Group(parent, SWT.NONE);
		if (Controller.isTournamentModus()) {
			grpSelectCanvas.setLayout(new GridLayout(3, true));
		} else {
			grpSelectCanvas.setLayout(new GridLayout(2, true));
		}
		grpSelectCanvas.setLayoutData(new GridData(GridData.FILL,
				GridData.BEGINNING, true, false));

		drawCanvasButton = createPushButton(grpSelectCanvas,
				"Spielfeld zeichnen");
		
		select = new Combo(grpSelectCanvas, SWT.NONE);
		select.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		
		currentWorldNameLabel = new Label(grpSelectCanvas, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		currentWorldNameLabel.setLayoutData(gd);

		if(isAdvancedModus) {
			createStrategySelectButton(grpSelectCanvas);
			createStrategySelect(grpSelectCanvas);
		}
		
		buttonPointOfView = new Button(grpSelectCanvas, SWT.CHECK);
		
		buttonPointOfView.setText("Sichtfeld");
		
		
		buttonPointOfView.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {
			}
			
			@Override
			public void mouseDown(MouseEvent e) {
				if(buttonPointOfView.getSelection()){
					canvas.setPov(false);					
				}else{
					canvas.setPov(true);					
				}
	
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
			}
		});
		
		drawCanvasButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				canvas.reset();
				Controller.deregisterAllMiceFromWorld();

				String pfad = Constants.GUI.emptyString;
				String grundPfad = XWizardPageOne.getXmlPfad();

				String fileName;
				if (select.getSelectionIndex() != -1) {
					fileName = dateien.get(select.getSelectionIndex());

				} else {
					fileName =  dateien.get(0);
				}

				pfad = grundPfad + "\\" + fileName;
				World world = new World(pfad);
				
				setCurrentWorldName(fileName);

				// TODO hier noch die Fehlermeldung einbauen, die bisher als
				// Swing-Komponente benutzt wurde
				WorldController ctrl = new WorldController(world, canvas);
				Controller.registerWorldCtrl(ctrl);
				canvas.redraw();

				PlayControlsGroup.getPlayButton().setEnabled(false);

				currentWorldNameLabel.setText("aktueller Garten: "
						+ currentWorldName);

				currentWorldNameLabel.pack();
				
				grpSelectCanvas.layout();
				grpSelectCanvas.pack();
				grpSelectCanvas.update();
				
				
				Controller.actionPerformed(Constants.Actions.loadNewWorld);
			}

			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		
		findWorldFiles();

		
		if(isAdvancedModus) {
			currentStrategyLabel = new Label(grpSelectCanvas, SWT.NONE);
			createPlaceSetButton(parent, canvas);
		}
	}

	public static Button getPlaceMouseButton() {
		return placeMouseButton;
	}

	public void setCurrentWorldName(String currentWorldName) {
		this.currentWorldName = currentWorldName;
	}

	protected void createPlaceSetButton(Group parent, final WorldView canvas) {
		placeMouseButton = createPushButton(parent, Constants.GUI.setMouse);

		placeMouseButton.setEnabled(false);

		placeMouseButton.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {

//				 neue Maus nur wenn noch keine auf dem Spielfeld ist
				//TODO Dï¿½rsam: das sollte eigentlich immer der Fall sein, 
				//der Button darf nur aktiv sein, wenn die Maus noch nicht in der Welt vorhanden ist
				 if (Controller.getMouseList().getMice().size() == 0) {					 
					 //TODO Dï¿½rsam: was ist zu tun im Turniermodus?
					 if (Controller.isAdvancedModus() || Controller.isSimpleModus()) {
						 Controller.getWorldCtrl().setMouseAtStartPosition();
						 canvas.redraw();
						 
						 
					 }					
					 Controller.getMouseCtrl().registerMouseForCurrentWorld();

				 }
				 Controller.actionPerformed(Constants.Actions.setMouse);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	protected void findWorldFiles() {
		dateien = new ArrayList<String>();

		if (XWizardPageOne.getXmlPfad() != null) {
			fillSelectionBoxWithFilesFromUserDirectory();
		} else {
			// OS-Abfrage
			Properties prop = System.getProperties();
			String osName = prop.getProperty("os.name");
			osName = osName.substring(0, 3);

			String grundPfad = Constants.GUI.emptyString;

			if (osName.equals("Mac")) {
				grundPfad = new File("").getAbsolutePath()
						+ "/src/xml/XML_Beispiel_Daten/";
			} else if (osName.equals("Win")) {
				grundPfad = ResourcesPlugin.getWorkspace().getRoot()
						.getLocation().toFile().toString();

				grundPfad = grundPfad.substring(0, grundPfad.lastIndexOf(System
						.getProperty("file.separator")));

				//Einigt euch mal auf einen Namen für den Workspace
				grundPfad += "\\kinderuni\\BoardView\\src\\xml\\XML_Beispiel_Daten\\";
			}

			XWizardPageOne.setXmlPfad(grundPfad);
			fillSelectionBoxWithFilesFromUserDirectory();
		}
	}
	
	public void fillSelectionBoxWithFilesFromUserDirectory() {
		File ordner = new File(XWizardPageOne.getXmlPfad());

		File[] dateienAusOrdner = ordner.listFiles();
		dateien = new ArrayList<String>();
		int j = 0;
		for (int i = 0; i < dateienAusOrdner.length; i++) {
			if (dateienAusOrdner[i].getName().endsWith(".xml")) {
				dateien.add(j, dateienAusOrdner[i].getName());
				j++;
			}
		}

		if(select != null) {
			fillFileArray();
			select.pack(true);
			select.redraw();
		}
	}
	
	private void fillFileArray() {
		select.removeAll();

		for (int i = 0; i < dateien.size(); i++) {
			int length = dateien.get(i).length()-4;
			select.add(dateien.get(i).substring(0, length), i);
		}
	}
	protected Button createPushButton(Group figures, String name) {
		Button button = new Button(figures, SWT.PUSH);
		button.setText(name);
		return button;
	}

	protected void createStrategySelectButton(Group grpSelectCanvas) {
		Button strategieWahl = createPushButton(grpSelectCanvas,
				"Strategie wählen");
		strategieWahl.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (strategyCombo.getSelectionIndex() != -1) {
					RPCClient.getInstance().setClassName("figur." +
							folderData[strategyCombo.getSelectionIndex()]);
							currentStrategyLabel.setText("aktuelle Strategie: " + folderData[strategyCombo.getSelectionIndex()]);
							currentStrategyLabel.pack();
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}

	protected void createStrategySelect(Group grpSelectCanvas) {
		strategyCombo = new Combo(grpSelectCanvas, SWT.NONE);
		Object[] strategies = RPCClient.getInstance().callGetAllStrategies();

		if (strategies != null) {
			folderData = new String[strategies.length];
			for (int i = 0; i < strategies.length; i++) {
				folderData[i] = (String) strategies[i];
			}
			for (int i = 0; i < folderData.length; i++) {
				strategyCombo.add(folderData[i]);
			}

			strategyCombo.pack(true);
			strategyCombo.redraw();
		}
	}
	
	public void setWorldButtonActive() {
		drawCanvasButton.setEnabled(true);
	}
	
	public void setWorldButtonInactive() {
		drawCanvasButton.setEnabled(false);
	}
	
	public void enableViewButton() {
		buttonPointOfView.setEnabled(true);
	}
	
	public void disableViewButton() {
		buttonPointOfView.setEnabled(false);
	}
	
	
}
