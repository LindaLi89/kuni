package spielbrettview.customviews.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class WizardPageOne extends WizardPage{
	  private Text text1;
	  private Composite container;

	  public WizardPageOne() {
	    super("First Page");
	    setTitle("Erstelle deine eigene Spielfigur");
	    setDescription("Schritt 1: Eingabe des Namens");
	  }

	  @Override
	  public void createControl(Composite parent) {
	    container = new Composite(parent, SWT.NULL);
	    GridLayout layout = new GridLayout();
	    container.setLayout(layout);
	    layout.numColumns = 2;
	    Label label1 = new Label(container, SWT.NULL);
	    label1.setText("Gib deinen Namen ein:");

	    text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
	    text1.setText("");
	    text1.addKeyListener(new KeyListener() {

	      @Override
	      public void keyPressed(KeyEvent e) {
	      }

	      @Override
	      public void keyReleased(KeyEvent e) {
	        if (!text1.getText().isEmpty()) {
	          setPageComplete(true);

	        }
	      }

	    });
	    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
	    text1.setLayoutData(gd);
	    // Required to avoid an error in the system
	    setControl(container);
	    setPageComplete(false);

	  }

	  public String getText1() {
	    return text1.getText();
	  }
}
