package spielbrettview.customviews.groups;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import spielbrettview.customviews.GeneralView;
import spielbrettview.customviews.WorldView;
import util.Constants;
import ctrl.Controller;

public class ArrowControlsGroup {
	
	private Button forwardButton;
	private Button leftButton;
	private Button rightButton;
	private Button backButton;
	private WorldView canvas;
	
	private Text text1;
	private Text text2;
	private Combo ifCondition;
	private Combo thenCondition;
	
	private Button okButton;
	
	private String[] ifItems = {Constants.Ifs.defaultStep,Constants.Ifs.treeAbove,
			Constants.Ifs.treeRight,
			Constants.Ifs.treeBelow,
			Constants.Ifs.treeLeft,
			Constants.Ifs.mushroomAbove,
			Constants.Ifs.mushroomRight,
			Constants.Ifs.mushroomBelow,
			Constants.Ifs.mushroomLeft,
			Constants.Ifs.leafAbove,
			Constants.Ifs.leafRight,
			Constants.Ifs.leafBelow,
			Constants.Ifs.leafLeft,
			Constants.Ifs.topBorder,
			Constants.Ifs.rightBorder,
			Constants.Ifs.downBorder,
			Constants.Ifs.leftBorder, Constants.Ifs.goalAchiewed};
	
	private String[] thenItems = {Constants.Thens.up,
			Constants.Thens.right, 
			Constants.Thens.down,
			Constants.Thens.left,
			Constants.Thens.horizontal,
			Constants.Thens.vertical,
			Constants.Thens.random, 
			Constants.Thens.stop};
	
	public void paintArrowControlsGroup(Group parent, WorldView canvas) {
		if(!Controller.isTournamentModus()) {
			this.canvas = canvas;
			
			if(Controller.isSimpleModus()) {
				paintEditors(parent);
			}
			else {
				paintArrows(parent);
			}
		}
	}
	
	private void paintEditors(Group parent) {
		Group editorGroup = new Group(parent, SWT.NONE);
		
		
		editorGroup.setLayout(new GridLayout(2, false));
		GridData gd3 = new GridData(GridData.FILL, GridData.FILL, true, false);
		
		editorGroup.setLayoutData(gd3);
		
		paintArrows(editorGroup);
		
		paintConditions(editorGroup);
		
		paintViews(editorGroup);
		
	}
	
	private void paintConditions(Group parent) {
		
		Group boxes = new Group(parent, SWT.NONE);
		boxes.setLayout(new GridLayout(2, true));
		boxes.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 1, 1));
		
		ifCondition = new Combo(boxes, SWT.DROP_DOWN | SWT.READ_ONLY);
		fillIfs();
		
		thenCondition = new Combo(boxes, SWT.DROP_DOWN | SWT.READ_ONLY);
		fillThens();
		
		paintOKButton(boxes);
	}
	
	private void paintOKButton(Group parent) {
			
			Label label = new Label(parent, SWT.NONE);
			label.setText("");
			
			okButton = new Button(parent, SWT.PUSH);
			okButton.setText("OK");
			
			okButton.addSelectionListener(new SelectionListener() {		
				@Override
				public void widgetSelected(SelectionEvent e) {
					//TODO diese AKtion hat mit der Welt eigentlich nichts zu tun
					int ifIndex = ifCondition.getSelectionIndex();
					int thenIndex = thenCondition.getSelectionIndex();
					
					Controller.getWorldCtrl().okButtonPressed(ifItems[ifIndex], thenItems[thenIndex]);
					Controller.actionPerformed(Constants.Actions.okButton);
				}		
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {	
				}
			});
			
		}
	
	private void fillIfs() {
		
		
		ifCondition.setItems(ifItems);
	}
	
	private void fillThens() {
		
		
		thenCondition.setItems(thenItems);
	}
	
	private void paintViews(Group parent) {

		
		ScrolledComposite scrollComposite1 = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		scrollComposite1.setAlwaysShowScrollBars(true);
		scrollComposite1.setExpandHorizontal(true);
		scrollComposite1.setMinSize(80, 80);
		
		text1 = new Text(scrollComposite1, SWT.MULTI);
		scrollComposite1.setContent(text1);
		text1.setSize(parent.getSize());
		text1.setEnabled(false);
		
		Group ruleGroup = new Group(parent, SWT.NONE);
		GridLayout gl = new GridLayout(2, false);
		ruleGroup.setLayout(gl);
		ScrolledComposite scrollComposite2 = new ScrolledComposite(ruleGroup, SWT.V_SCROLL | SWT.BORDER);
		scrollComposite2.setAlwaysShowScrollBars(true);
		scrollComposite2.setExpandHorizontal(true);
		scrollComposite2.setMinSize(80, 80);
		
		text2 = new Text(scrollComposite2, SWT.MULTI);
		scrollComposite2.setContent(text2);
		text2.setEnabled(false);
		
		Button deleteButton = new Button(ruleGroup, SWT.PUSH);
		deleteButton.setText("Löschen");
		deleteButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				Controller.getMouseCtrl().getRecord().deleteRules();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void paintArrows(Group parent) {
		Group moves = new Group(parent, SWT.NONE);
		moves.setLayout(new GridLayout(3, true));
		moves.setLayoutData(new GridData(GridData.FILL, GridData.FILL,
				true, true, 1, 1));

		createForwardButton(moves);
		
		createLeftButton(moves);
		
		createRightButton(moves);
		
		createDownButton(moves);
	}
	
	private void createDownButton(Group moves) {
		Label label4 = 	new Label(moves, SWT.NONE);
		label4.setText("");
		backButton = createArrowButton(moves, "runter.png");
		backButton.addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				Controller.getWorldCtrl().moveDown();
				Controller.actionPerformed(Constants.Actions.arrow);
				//TODO Dörsam: das Redraw müßte auch aufgrund des Zustandsübergangs 
				//erfolgen
				canvas.redraw();			
			}		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {	
			}
		});
		
		Label label5 = 	new Label(moves, SWT.NONE);
		label5.setText("");
	}

	private void createRightButton(Group moves) {
		Label label3 = 	new Label(moves, SWT.NONE);
		label3.setText("");
		rightButton = createArrowButton(moves, "rechts.png");
		rightButton.addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				Controller.getWorldCtrl().moveRight();
				Controller.actionPerformed(Constants.Actions.arrow);
				canvas.redraw();			
			}		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {	
			}
		});
	}

	private void createLeftButton(Group moves) {
		Label label2 = 	new Label(moves, SWT.NONE);
		label2.setText("");
		leftButton = createArrowButton(moves, "links.png");
		leftButton.addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				Controller.getWorldCtrl().moveLeft();
				Controller.actionPerformed(Constants.Actions.arrow);
				canvas.redraw();			
			}		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}

	private void createForwardButton(Group moves) {
		Label label1 = 	new Label(moves, SWT.NONE);
		label1.setText("");
		forwardButton = createArrowButton(moves, "hoch.png");
		forwardButton.addSelectionListener(new SelectionListener() {		
			@Override
			public void widgetSelected(SelectionEvent e) {
				Controller.getWorldCtrl().moveUp();
				Controller.actionPerformed(Constants.Actions.arrow);
				canvas.redraw();			
			}		
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {	
			}
		});
	}

	protected Button createArrowButton(Group moves, String icon) {
		Button button = new Button(moves, SWT.PUSH);
		Image image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + icon));
		button.setImage(image);
		return button;
	}
	
	public void activateArrows() {
		forwardButton.setEnabled(true);
		leftButton.setEnabled(true);
		rightButton.setEnabled(true);
		backButton.setEnabled(true);

		if(Controller.isSimpleModus()) {
			ifCondition.setEnabled(true);
			thenCondition.setEnabled(true);
			okButton.setEnabled(true);
		}
	}
	
	public void deActivateArrows() {
		forwardButton.setEnabled(false);
		leftButton.setEnabled(false);
		rightButton.setEnabled(false);
		backButton.setEnabled(false);
		
		if(Controller.isSimpleModus()) {
			ifCondition.setEnabled(false);
			thenCondition.setEnabled(false);
			okButton.setEnabled(false);
		}
	}
	
	public void fillTextWithContents(String text) {
		text1.setText(text);
		text1.pack();
		text1.redraw();
	}
	
	public void appendTextWithContents(String text) {
		text1.insert(text);
		text1.pack();
		text1.redraw();
	}
	
	public void resetText1() {
		text1.setText("");
		text1.pack();
		text1.redraw();
	}
	
	public void fillTextWithContents(String cond, String then) {
		if(cond != null && cond.length() > 0 && then != null && then.length() > 0) {
			text2.setText("Wenn " + cond + ", dann " + then);
			text2.pack();
			text2.redraw();
		}
	}
	
	public void appendTextWithContents(String cond, String then) {
		if(cond != null && cond.length() > 0 && then != null && then.length() > 0) {
			text2.insert("Wenn " + cond + ", dann " + then);
			text2.pack();
			text2.redraw();
		}
	}
	
	public void resetText2() {
		text2.setText("");
		text2.pack();
		text2.redraw();
	}
	
	public void resetText() {
		resetText1();
		resetText2();
	}
}
