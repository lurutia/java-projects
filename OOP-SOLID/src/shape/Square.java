package shape;

import shape.interfaces.ShapeInterface;

public class Square implements ShapeInterface {
	public int length;
	
	public Square(int length) {
		this.length = length;
	}
	
	public double area() {
		return Math.pow(this.length, 2);
	}
}
