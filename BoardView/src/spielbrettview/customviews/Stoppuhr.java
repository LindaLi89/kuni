package spielbrettview.customviews;

import org.eclipse.swt.widgets.Display;

import ctrl.Controller;

public class Stoppuhr implements Runnable {
	private static final int TIMER_INTERVAL = 100;
	private boolean active = false;

	public void startThread() {

		Thread t = new Thread(this);
		t.start();
		active = true;
	}

	public void stopThread() {
		active = false;
	}

	public void run() {
		while (active) {
			try {

				Controller.getScoreCtrl().updateText();
				Thread.sleep(TIMER_INTERVAL);
			} catch (Exception e) {
				Display.getDefault().syncExec(new Runnable() {
					public void run() {
						Controller.getScoreCtrl().updateText();
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
			}
		}
	}

}

