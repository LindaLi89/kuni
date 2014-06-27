package spielbrettview.customviews.groups;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import ctrl.Controller;

import spielbrettview.customviews.CustomDragSourceListener;
import spielbrettview.customviews.CustomDropTargetListener;
import spielbrettview.customviews.GeneralView;
import spielbrettview.customviews.WorldView;
import util.Constants;

public class FigureGroup {
	
	private Button treeButton;
	private Button leafButton;
	private Button mushroomButton;
	private static Button mouseButton;
	private CustomDropTargetListener customDropTargetListener;
	private WorldView canvas;
	
	public Button getTreeButton() {
		return treeButton;
	}

	public Button getLeafButton() {
		return leafButton;
	}

	public Button getMushroomButton() {
		return mushroomButton;
	}

	public static Button getMouseButton() {
		return mouseButton;
	}

	public void paintFigureGroup(Group grpSpielen, WorldView canvas) {
		this.canvas = canvas;
		Group grpFiguren = new Group(grpSpielen, SWT.NONE);
		grpFiguren.setText("Figuren");
		grpFiguren.setLayout(new GridLayout(4, true));
		GridData gd3 = new GridData(GridData.FILL, GridData.FILL, false, false);
		gd3.verticalSpan = 3;
		grpFiguren.setLayoutData(gd3);
		createDragDropBar(grpFiguren);
	}
	
	protected Button createFigureButton(Group figures, String icon) {
		Button button = new Button(figures, SWT.BORDER_SOLID);

		Image image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(
						Constants.Paths.iconPath + icon));		
		ImageData imageData = image.getImageData();
		imageData = imageData.scaledTo(32, 32);

		Image i = new Image(Display.getDefault(),imageData); 
		button.setImage(i);
		button.setEnabled(false);
		
		return button;
	}
	
	protected void createDragDropBar(Group figures) {
		treeButton = createFigureButton(figures, "baum.png");
		leafButton = createFigureButton(figures, "pilz02.png");
		mushroomButton = createFigureButton(figures, "pilz01.png");		
		FigureGroup.mouseButton = new Button(figures, SWT.BORDER_SOLID);						

		// DnD
		DragSource source = new DragSource(treeButton, DND.DROP_NONE);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		// add a drag listener

		source.addDragListener(new CustomDragSourceListener(treeButton
				.getParent(), source));

		source = new DragSource(leafButton, DND.DROP_NONE);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		// add a drag listener
		source.addDragListener(new CustomDragSourceListener(leafButton
				.getParent(), source));

		source = new DragSource(mushroomButton, DND.DROP_NONE);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		// add a drag listener
		source.addDragListener(new CustomDragSourceListener(mushroomButton
				.getParent(), source));
		
		source = new DragSource(FigureGroup.mouseButton, DND.DROP_NONE);
		source.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		// add a drag listener
		source.addDragListener(new CustomDragSourceListener(FigureGroup.mouseButton
				.getParent(), source));

		DropTarget target = new DropTarget(canvas, DND.DROP_NONE);
		target.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		// add a drop listener
		customDropTargetListener = new CustomDropTargetListener();
		target.addDropListener(customDropTargetListener);

		mushroomButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				customDropTargetListener.setSource(Constants.Objects.mushroom);
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});

		leafButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				customDropTargetListener.setSource(Constants.Objects.leaf);
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});

		treeButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				customDropTargetListener.setSource(Constants.Objects.tree);
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
			}
		});
		

			FigureGroup.mouseButton.addMouseListener(new MouseListener() {

				@Override
				public void mouseUp(MouseEvent arg0) {
					Controller.getCurrentView().getPlaceMouseButton().setEnabled(false);
				}

				@Override
				public void mouseDown(MouseEvent arg0) {
					customDropTargetListener.setSource(Constants.Objects.mouse);
					Controller.actionPerformed(Constants.Actions.setMouse);
				}

				@Override
				public void mouseDoubleClick(MouseEvent arg0) {

				}
			});


	}

	public CustomDropTargetListener getCustomDropTargetListener() {
		return customDropTargetListener;
	}

	public void setObstaclesActive() {
		treeButton.setEnabled(true);
		leafButton.setEnabled(true);
		mushroomButton.setEnabled(true);
	}
	
	public void setObstaclesInactive() {
		treeButton.setEnabled(false);
		leafButton.setEnabled(false);
		mushroomButton.setEnabled(false);
	}
	
	public void setMouseActive() {
		mouseButton.setEnabled(true);
	}
	
	public void setMouseInactive() {
		mouseButton.setEnabled(false);
	}

}
