package spielbrettview.customviews.groups;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import spielbrettview.customviews.GeneralView;
import spielbrettview.customviews.Stoppuhr;
import util.Constants;
import ctrl.Controller;

public class PlayControlsGroup {
	private Group grpIcons;
	private static Button playButton;
	private static Button debugButton;
	private Button recordButton;
	private Button stopButton;
	private Image image_inactive;
	private Image image;
	private boolean isSimpleModus;
	
	protected Stoppuhr time;
	
	public void paintPlayControlsGroup(Group grpSpielen, boolean isAdvancedModus, boolean isSimpleModus) {
		this.isSimpleModus = isSimpleModus;
		
		grpIcons = new Group(grpSpielen, SWT.NONE);
		grpIcons.setLayout(new GridLayout(5, true));
		grpIcons.setText("Icons");

		PlayControlsGroup.playButton = new Button(grpIcons, SWT.PUSH);
		PlayControlsGroup.playButton.setEnabled(false);

		createPlayButton();

		createPauseButton();

		createStopButton();

		if(isSimpleModus) {
			createRecordButton();
		}
		
		if(isAdvancedModus) {
			createDebugButton();
		}
	}
	
	public static Button getPlayButton() {
		return playButton;
	}

	public static Button getDebugButton() {
		return debugButton;
	}

	protected void createPauseButton() {
		Image image;
		Button pauseButton = new Button(grpIcons, SWT.PUSH);
		image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + "Pause.png"));
		pauseButton.setImage(image);
		pauseButton.setEnabled(false);
	}

	protected void createRecordButton() {
		recordButton = new Button(grpIcons, SWT.PUSH);
		image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + Constants.Icons.recordIcon));
		image_inactive = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + Constants.Icons.recordInactiveIcon));
		recordButton.setImage(image);
		recordButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				if (Controller.getWorldCtrl().isRecButton()) {
					Controller.getMouseCtrl().initIterator();
					recordButton.setImage(image);

				} else {
					Controller.getMouseCtrl().resetRecord();
					recordButton.setImage(image_inactive);
					Controller.resetActionList();
				}

				//TODO Dörsam auf stateTransitions übertragen!
				Controller.getWorldCtrl().setRecButton(
						!Controller.getWorldCtrl().isRecButton());
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
	}

	protected  void createPlayButton() {
		Image image = createImage(Constants.Icons.playIcon);
		PlayControlsGroup.playButton.setImage(image);
		
		PlayControlsGroup.playButton.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (Controller.getMouseList().getMice().size() == 0) {
					Controller.getMouseCtrl().registerMouseForCurrentWorld();
				}
				Controller.getScoreCtrl().setStartTime(new Date().getTime());
				time = new Stoppuhr(); // neuer Thread wird erzeugt
				time.startThread();
				Controller.getWorldCtrl().startAutomaticGame();
				Controller.actionPerformed(Constants.Actions.play);
			}
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	protected void createDebugButton() {
		PlayControlsGroup.debugButton = new Button (grpIcons, SWT.PUSH);
		Image image = createImage(Constants.Icons.debugIcon);
		PlayControlsGroup.debugButton.setImage(image);
		PlayControlsGroup.debugButton.setEnabled(false);
		
		PlayControlsGroup.debugButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				Controller.getWorldCtrl().startOneStep();
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				// do nothing
			}
		});
		
	}



	protected void createStopButton() {
		Image image;
		stopButton = new Button(grpIcons, SWT.PUSH);
		image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + "Stop.png"));
		stopButton.setImage(image);
		
	
		stopButton.addSelectionListener(new SelectionListener() {
	
			public void widgetSelected(SelectionEvent e) {
	
				time.stopThread();
				Controller.getWorldCtrl().stopAutomaticGame();
				Controller.actionPerformed(Constants.Actions.stop);
			}
	
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
	}
	
	protected Image createImage(String icon) {
		return new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + icon));
	}
	
	public void enableRecordButton() {
		recordButton.setEnabled(true);
	}
	
	public void disableRecordButton() {
		recordButton.setEnabled(false);
	}
	
	public void enablePlayButton() {
		playButton.setEnabled(true);
	}
	
	public void disablePlayButton() {
		playButton.setEnabled(false);
	}
	
	public void enableDebugButton() {
		debugButton.setEnabled(true);
	}
	
	public void disableDebugButton() {
		debugButton.setEnabled(false);
	}
	
	public void enableStopButton() {
		stopButton.setEnabled(true);
	}
	
	public void disableStopButton() {
		stopButton.setEnabled(false);
	}
	
	public void stopTimerThread() {
		if(time != null)
			time.stopThread();
	}
}
