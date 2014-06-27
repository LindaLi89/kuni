package model.exceptions;

import model.AbstractObject;

public class EatableObjectOnPlaceException extends Exception {
	private static final long serialVersionUID = -5385125943440316715L;
	private AbstractObject obstacle;

	public AbstractObject getObstacle() {
		return obstacle;
	}

	public EatableObjectOnPlaceException(AbstractObject obstacle) {
		super();
		this.obstacle = obstacle;
	}	
}
