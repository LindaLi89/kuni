package spielbrettview.customviews.wizards;

import model.Leaf;
import model.Mushroom;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PWizardPageOne extends WizardPage{
	
		private Composite container;
		private Text text1;
		private Text text2;
	
		public PWizardPageOne(){
			super("First Page");
			setTitle("Einstellungen");
			setDescription("Hier können die zu vergebenden Punkte angepasst werden.");
	}
		
		public void createControl(Composite parent){
			container = new Composite(parent, SWT.NULL);
			GridLayout layout = new GridLayout();
			container.setLayout(layout);
			layout.numColumns = 2;
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			
			Label label1 = new Label(container, SWT.NULL);
			label1.setText("Punkte pro Blatt: ");
			text1 = new Text(container, SWT.BORDER | SWT.SINGLE);
			text1.setLayoutData(gd);
			text1.setText(String.valueOf(Leaf.getPoints()));
			text1.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent e) {					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					if(!text1.getText().isEmpty() && !text2.getText().isEmpty()){
						setPageComplete(true);
					}
				}	
			});
		    
		    Label label2 = new Label(container, SWT.NULL);
		    label2.setText("Minuspunkte pro Pilz: ");
		    text2 = new Text(container, SWT.BORDER | SWT.SINGLE);
		    text2.setLayoutData(gd);
		    text2.setText(String.valueOf(Mushroom.getPoints()));
			text2.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent e) {					
				}

				@Override
				public void keyReleased(KeyEvent e) {
					if(!text1.getText().isEmpty() && !text2.getText().isEmpty()){
						setPageComplete(true);
					}
				}	
			});			
		    
		    setControl(container);
		    setPageComplete(false);	
		}

		public String getText1() {
			return text1.getText();
		}

		public String getText2() {
			return text2.getText();
		}
}
