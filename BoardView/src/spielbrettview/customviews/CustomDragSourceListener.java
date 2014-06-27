package spielbrettview.customviews;

import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.widgets.Composite;

public class CustomDragSourceListener implements DragSourceListener {

	  private Composite parentComposite;
	  private DragSource source;
	  
	  private String sourceName;

	  
	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	/**
	   * @param parentComposite
	   *            - the composite that holds all pictures
	   * @param source
	   *            - the drag source
	   * 
	   */

	  public CustomDragSourceListener(Composite parentComposite, DragSource source) {
	    this.parentComposite = parentComposite;
	    this.source = source;
	   
	  }

	  public void dragStart(DragSourceEvent event) {
		  
		  
	  }

	  public void dragFinished(DragSourceEvent event) {
		  
	  }

	  
	/**
	   * The method computes the position / index of the source control (label) in
	   * the children array of the parent composite. This index is passed to the
	   * drop target using the data field of the drag source event.
	   */

	  public void dragSetData(DragSourceEvent event) {
		  
	
	    for (int i = 0; i < parentComposite.getChildren().length; i++) {
	      if (parentComposite.getChildren()[i].equals(source.getControl())) {
	        event.data = Integer.valueOf(i).toString();
	        break;
	      }
	    }
	  }

	} 