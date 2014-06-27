package spielbrettview.customviews;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import ctrl.Controller;



public class WorldView extends Canvas {

	private String[] imagePaths = {"..\\..\\icons\\pilz01.png", "..\\..\\icons\\pilz02.png", "..\\..\\icons\\baum.png"};
	private Image[] images = new Image[3];
	private int distance;
	private GC gc;
	private int width;
	private int height;
	List <Rectangle> pathToFill = new ArrayList <Rectangle>();
	List <Rectangle> viewToFill = new ArrayList <Rectangle>();
	private boolean pov = false;
	
	private int picX;
	private int picY;
	private int offset; 
	private Image i;
	
	public boolean isPov() {
		return pov;
	}



	public void setPov(boolean pov) {
		this.pov = pov;
	}

	private List<List<Point>> objects = new ArrayList<List<Point>>();
	
	//Liste der beweglichen Objekte besteht aus den Punkten und zugeh�rigen Icons
	Map<Point, Image> moveableObjects = new HashMap<Point, Image>();
	

	public Map<Point, Image> getMoveableObjects() {
		return moveableObjects;
	}



	public WorldView(Composite parent, int style) {
		super(parent, style);
		
		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent event) {
				onPaint(event);
			}
		});	
		
		addKeyListener(new KeyAdapter() {
			

			@Override
			public void keyPressed(KeyEvent e) {
				if(Controller.getWorldCtrl() != null)
					Controller.getWorldCtrl().performActionOnKeyPress(e);		
			}
		});		
	}
	
	

	public void setObjects(List<List<Point>> objects) {
		this.objects = objects;
	}



	public void setMoveableObjects(Map<Point, Image> moveableObjects) {
		this.moveableObjects = moveableObjects;
	}



	protected void onPaint(PaintEvent event) {
		gc = event.gc;			
		paintGrid(gc);
		paintObjects(gc);
		paintMoveableObjects(gc);
		paintPath(gc);
		paintSkala(gc);
		paintPointOfView(gc);
		

	}
	
	private void paintPointOfView(GC gc){
		
		
	}
	
	private void paintObjects(GC gc) {
		if(objects.size() > 0) {
			Iterator<List<Point>> iter = objects.iterator();
			int i=0;
			while(iter.hasNext()) {
				List<Point> list = iter.next();
				if(list.size() > 0) {
					Iterator<Point> iter2 = list.iterator();
					while(iter2.hasNext()) {
						Point c = iter2.next();
						if(images[i] == null) {
							images[i] = paintObject(gc, c.x, c.y, imagePaths[i]);
						}
						else {
							paintObject(gc, c.x, c.y, images[i]);
						}
						
					}
				}
				i++;
			}
		}
		
	}
	
	protected void paintMoveableObjects(GC gc) {
		if(moveableObjects != null) {
			Iterator<Point> iter2 = moveableObjects.keySet().iterator();
			while(iter2.hasNext()) {
				Point c = iter2.next();
				paintObject(gc, c.x, c.y, moveableObjects.get(c));
				Rectangle r = new Rectangle((c.x+1)*distance, (c.y+1)*distance, distance/4, distance/4);
				pathToFill.add(r);
				
				
				org.eclipse.swt.graphics.Color color = new org.eclipse.swt.graphics.Color(getDisplay(), 55, 255, 105);
				gc.setBackground(color);
				gc.setAlpha(200);
				
				if(pov==true){
					gc.fillRectangle((c.x+2)*distance+(distance/3), (c.y+1)*distance+(distance/3), distance/3, distance/3);
					gc.fillRectangle((c.x+1)*distance+(distance/3), (c.y+2)*distance+(distance/3), distance/3, distance/3);
					gc.fillRectangle((c.x)*distance+(distance/3), (c.y+1)*distance+(distance/3), distance/3, distance/3);
					gc.fillRectangle((c.x+1)*distance+(distance/3), (c.y)*distance+(distance/3), distance/3, distance/3);
				}
				
			}
		}
	}
	
	public void paintPath(GC gc){
		org.eclipse.swt.graphics.Color color = new org.eclipse.swt.graphics.Color(getDisplay(), 163, 3, 6);
		gc.setBackground(color);
		gc.setAlpha(150);
		
		Iterator<Rectangle> iter = pathToFill.iterator();
		
		while (iter.hasNext()){
			Rectangle r = iter.next();
			int w = r.width;
			int h = r.height;
			int x = r.x;
			int y = r.y;
			
			gc.fillRectangle(x, y, w, h);
		}
		
		
	}
	public void reset(){
		pathToFill = new ArrayList <Rectangle>();
	}

	private Image paintObject(GC gc, int x, int y, String imagePath) {
		
		defineMouseImage(imagePath); 


		gc.drawImage(i, distance*(x+1) +offset, distance*(y+1)+offset );
		
		return i;
	}
	
	
	private void paintObject(GC gc, int x, int y, Image image) {
		
		defineMouseImage(image); 


		gc.drawImage(i, distance*(x+1) +offset, distance*(y+1)+offset );
	}



	protected void defineMouseImage(String imagePath) {
		i = generateImageForWorld(imagePath);
	}
	
	protected void defineMouseImage(Image image) {
		picX = (int) (distance*0.8);
		picY = (int) (distance*0.8);
		offset = Math.abs((distance/2) - (picX/2));
		
		ImageData data = image.getImageData();
		data = data.scaledTo(picX,picY);
		i = new Image(Display.getDefault(),data);
	}
	
	public Image generateImageForWorld(String imagePath) {
		picX = (int) (distance*0.8);
		picY = (int) (distance*0.8);
		offset = Math.abs((distance/2) - (picX/2));
		
		Image image = new Image(getDisplay(), getClass().getResourceAsStream("..\\..\\..\\icons\\spielfigurIcons\\" + imagePath));
		ImageData data = image.getImageData();
		data = data.scaledTo(picX,picY);
		i = new Image(Display.getDefault(),data);
		
		return i;
	}
	
	public void setSpaces(int w, int h) {
		width = w;
		height = h;
	}

	public void paintGrid(GC gc) {
		int x = this.getSize().x;
		int y = this.getSize().y;
		if(width != 0 && height != 0) {		
			if(width > height) {
				distance = x/(width + 1) - 1;
			}
			else {
				distance = y/(height + 1) - 1;
			}
			
			int numberLinesX = width + 1;
			int numberLinesY = height + 1;
		
			gc.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK));
			paintParallelLines(gc, numberLinesX, numberLinesY, true);
			paintParallelLines(gc, numberLinesX, numberLinesY, false);
			
		}
	}

	private void paintParallelLines(GC gc, int numberLinesX, int numberLinesY, boolean vertical) {
		//vertical lines
		if(vertical) {
			int i=1; 
			while(i <= numberLinesX) {
				int xCoord = i*distance;
				int j = 1;
				while(j <= numberLinesY) {
					int yCoord = j*distance;
					gc.drawLine(xCoord, yCoord, xCoord, distance*(numberLinesY));
					j++;
				}
				i++;
			}
		}
		else {
			int i=1; 
			while(i <= numberLinesY) {
				int yCoord = i*distance;
				int j = 1;
				while(j <= numberLinesX) {
					int xCoord = j*distance;
					gc.drawLine(xCoord, yCoord, distance*(numberLinesX), yCoord);
					j++;
				}
				i++;
			}
		}
	}



	public int getDistance() {
		return distance;
	}
	
	public void paintSkala(GC gc){
		
		

		org.eclipse.swt.graphics.Color color = new org.eclipse.swt.graphics.Color(getDisplay(), 163, 3	,6);
		gc.setForeground(color);
		gc.setAlpha(255);
		
		int y=0;
		for(int row = 0; row < height; ++row) {
			
			y = (row+1)*distance+(distance/2);
			gc.drawText(Integer.toString(row), distance/2, y, true);
		}
		
		int x=0;
		for(int col = 0; col < width; ++col) {
			
			x = (col+1)*distance+(distance/2);
			gc.drawText(Integer.toString(col), x, distance/2, true);
		}
		
	}
	
	

}
