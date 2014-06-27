package spielbrettview.customviews;

import model.Leaf;
import model.Mushroom;
import model.Tree;

import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.graphics.Point;

import util.Constants;
import ctrl.Controller;
import ctrl.WorldController;

public class CustomDropTargetListener implements DropTargetListener {

	private String source;

	public void setSource(String source) {
		this.source = source;
	}

	public void dragEnter(DropTargetEvent event) {

	}

	public void dragOver(DropTargetEvent event) {

	}

	public void dragLeave(DropTargetEvent event) {
	}

	public void dropAccept(DropTargetEvent event) {
	}

	public void dragOperationChanged(DropTargetEvent event) {
	}

	/**
	 * This method moves the dragged picture to the new position and shifts the
	 * old picture to the right or left.
	 */

	public void drop(DropTargetEvent event) {

		WorldController wController = ctrl.Controller.getWorldCtrl();
		WorldView canvas = wController.getCanvas();

		Point relativePoint = canvas.toControl(event.x, event.y);

		// Anzahl Kästchen in x Richtung
		int xBoxes = wController.getWorldWidth() + 1;

		// Anzahl Kästchen in y Richtung
		int yBoxes = wController.getWorldHeight() + 1;

		int boxWidth = canvas.getDistance();
		int boxHeight = boxWidth;

		int xBox = 0;
		int yBox = 0;

		int oldBoxWidth = 0;
		int oldBoxHeight = 0;

		int y = 1;
		int x = 1;

		boolean found = false;
		boolean xfound = false;

		while (x <= xBoxes && !xfound) {

			if (relativePoint.x <= x * boxWidth
					&& relativePoint.x >= oldBoxWidth) {

				xfound = true;
				xBox = x - 1;

			}

			oldBoxWidth = x * boxWidth;

			x++;

		}

		y = 1;

		while (y <= yBoxes && !found) {

			if (relativePoint.y <= y * boxWidth
					&& relativePoint.y >= oldBoxHeight) {

				found = true;

				yBox = y - 1;

			}
			oldBoxHeight = y * boxHeight;

			y++;

		}

		// && canvasY <= j *boxHeight && canvasY >= oldBoxHeight
		Point p = new Point(xBox - 1, yBox - 1);


		if (found == true && xfound == true && p.x >= 0 && p.y>=0 ) {

			switch (source) {

			case Constants.Objects.mushroom:
				wController.addObstacle(p, new Mushroom());
				break;

			case Constants.Objects.tree:
				wController.addObstacle(p, new Tree());
				break;
			case Constants.Objects.leaf:
				wController.addObstacle(p, new Leaf());
				break;
			case Constants.Objects.mouse:
				boolean mouseSet = Controller.getMouseCtrl().setMouseOnPosition(p.x, p.y);
				if(mouseSet) { //nur, wenn die Maus gesetzt werden konnte
					Controller.actionPerformed(Constants.Actions.setMouse);
				}
				else {
					Controller.actionPerformed(Constants.Actions.unsetMouse);
				}
				break;
			}

		}
		// TODO der Controller sollte hier selber updaten,
		// die nächsten beiden Aufrufe sollten an dieser Stelle nicht notwendig
		// sein
		wController.updateObstacles();
		wController.updateCanvas();

	}

}
