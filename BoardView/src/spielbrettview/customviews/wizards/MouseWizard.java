package spielbrettview.customviews.wizards;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import model.Mouse;
import model.RegisteredMouses;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import spielbrettview.customviews.GeneralView;
import spielbrettview.customviews.WorldView;
import spielbrettview.customviews.groups.FigureGroup;
import util.Constants;
import ctrl.Controller;
import ctrl.MouseController;

public class MouseWizard extends Wizard {

	// TODO Dörsam: Bedeutung der "id"-Werte kommentieren
	protected WizardPageOne one;
	protected WizardPageTwo two;
	protected WorldView canvas;
	protected Group grpScores;
	protected Label name;
	protected int id = 0;

	public MouseWizard() {
		super();
		setNeedsProgressMonitor(true);
	}

	public MouseWizard(int id) {
		super();
		this.id = id;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		one = new WizardPageOne();
		two = new WizardPageTwo();
		addPage(one);
		addPage(two);
	}

	@Override
	public boolean performFinish() {
		try {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
		    IWorkspaceRoot root = workspace.getRoot();

			FileWriter fw = new FileWriter("spielfigur.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(one.getText1());
			pw.println(WizardPageTwo.getImg());
			pw.flush();
			pw.close();
			MouseController mouseCtrl = Controller.getMouseCtrl();
			if(mouseCtrl!=null){
				mouseCtrl.registerMouse(one.getText1(), WizardPageTwo.getImg());
				setImage();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean performCancel() {
		MouseController mouseCtrl = Controller.getMouseCtrl();
		if(mouseCtrl != null){
			mouseCtrl.registerMouse("", two.getDefaultImg());
		}
		return true;
	}
	
	public void setImage(){
		Mouse mouse = RegisteredMouses.getInstance().getFirstMouse();
		String img = mouse.getImg();

		Image image = new Image(Display.getDefault(), GeneralView.class
				.getClassLoader().getResourceAsStream(Constants.Paths.mouseIconPath + img));	

		ImageData imageData = image.getImageData();
		imageData = imageData.scaledTo(32, 32);

		Image i = new Image(Display.getDefault(),imageData); 
		FigureGroup.getMouseButton().setImage(i);
	}

}
