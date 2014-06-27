package model;



import model.exceptions.EatableObjectOnPlaceException;

import org.apache.xmlrpc.XmlRpcException;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

import rpc.RPCClient;
import ctrl.Controller;

public class AdvancedMouse extends Mouse{
	
	public AdvancedMouse(String name, String image) {
		super(name, image);
	}
	
	@Override
	public boolean defineStartPosition() {
		int x;
		int y;
		
		RPCClient rpc = RPCClient.getInstance();
		if(rpc != null) {
			try {
				rpc.callSetMouse();
				int[] newPosition = rpc.callDefineStartPosition(Controller.getWorldCtrl().getWorldWidth(), Controller.getWorldCtrl().getWorldHeight());
				x = newPosition[0];
				y = newPosition[1];
				
				setPosition(x, y);
			} catch (XmlRpcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//TODO �ndern: die Methode darf keinen booleschen Wert zur�ckliefern
		return false;
	}
	
	@Override
	public boolean setPosition(int x, int y) {
		boolean result = super.setPosition(x, y);
		if(result) {
			RPCClient rpc = RPCClient.getInstance();
			if(rpc != null) {
				try {
					rpc.callSetMouse(x, y);
				} catch (XmlRpcException e) {
					// TODO D�rsam: Verhalten definieren, wenn die remote-Connection nicht funktioniert
					//e.printStackTrace();
				}
			}	
		}
		return result;
	}
	

	@Override
	public AbstractObject doNextStep()  {
		Point point = getPosition();
		RPCClient rpc = RPCClient.getInstance();
		if(rpc != null) {
			try {
				String move = rpc.callDoNextStep(this, null);

				//TODO Hard kodierte Strings vermeiden
				switch(move) {
				case "oben":
					try {
						if (moveUp()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					} catch (EatableObjectOnPlaceException e) {
						Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
						return e.getObstacle();
					}
					break;
				case "rechts":
					try {
						if (moveRight()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					} catch (EatableObjectOnPlaceException e) {
						Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
						return e.getObstacle();
					}
					break;
				case "unten":
					try {
						if (moveDown()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					} catch (EatableObjectOnPlaceException e) {
						Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
						return e.getObstacle();
					}
					break;
				case "links":
					try {
						if (moveLeft()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					} catch (EatableObjectOnPlaceException e) {
						Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
						return e.getObstacle();
					}
					break;
				case "stop":
					Controller.getWorldCtrl().stopAutomaticGame();
					break;
				case "waagerecht":
				try {
					double rnd = Math.random();
					if(rnd < 0.5) {
						if (moveLeft()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					}
					else {
						if (moveRight()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					}
				} catch (EatableObjectOnPlaceException e) {
					Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
					return e.getObstacle();
				}
				break;
				case "senkrecht":
				try {
					double rnd = Math.random();
					if(rnd < 0.5) {
						if (moveUp()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					}
					else {
						if (moveDown()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					}
				} catch (EatableObjectOnPlaceException e) {
					Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
					return e.getObstacle();
				}
				break;
				case "zufall":
				try {
					double rnd = Math.random();
					if(rnd < 0.25) {
						if (moveUp()) {
							Controller.getWorldCtrl().updateWorldAndMouse(point, this);
							return null;
						}
					}
					else {
						if(rnd < 0.5) {
							if (moveLeft()) {
								Controller.getWorldCtrl().updateWorldAndMouse(point, this);
								return null;
							}
						}
						else {
							if(rnd < 0.75) {
								if (moveDown()) {
									Controller.getWorldCtrl().updateWorldAndMouse(point, this);
									return null;
								}
							}
							else {
								if (moveRight()) {
									Controller.getWorldCtrl().updateWorldAndMouse(point, this);
									return null;
								}
							}
						}
					}
				} catch (EatableObjectOnPlaceException e) {
					Controller.getWorldCtrl().updateWorldAndMouseWithPoints(point, this, e.getObstacle());
					return e.getObstacle();
				}
				break;
				case "machNichts":
					break;
				}
			} catch (XmlRpcException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * starts the game thread, used for updating the GUI
	 */
	public void startGame() {
		GameThread gt = new GameThread();
		gt.go();
	}
	
	public void doOneStep() {
		GameThread gt = new GameThread();
		gt.doSteps();
	}
	
	/**
	 * @author D�rsam
	 * Thread for updating the GUI
	 */
	protected class GameThread implements Runnable {
		// The timer interval in milliseconds
		private static final int TIMER_INTERVAL = 100;
		
		protected void go() {
			Thread t = new Thread(this);
			active = true;
			t.start();
		}
			
		public void run() {
			while (active) {
				try {
					doSteps();
					Thread.sleep(TIMER_INTERVAL);
				} catch (Exception e) {
					Display.getDefault().syncExec(new Runnable() {
						public void run() {
							doSteps();
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
		
		public void doSteps() {
			try {		
				Point previous = new Point(positionX,positionY);
				AbstractObject result = doNextStep();						
							
				if (result == null) {
					Controller.getWorldCtrl().updateWorldAndMouse(previous, getMouse());	
				}
				else {
					Controller.getWorldCtrl().updateWorldAndMouseWithPoints(previous, getMouse(), result);
				}
				Controller.getWorldCtrl().updateCanvas();
				    Thread.sleep(TIMER_INTERVAL);
				} 
				catch (Exception e) {
				    Display.getDefault().syncExec(new Runnable() {
				       public void run() {
				    	   Controller.getWorldCtrl().updateCanvas();
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
