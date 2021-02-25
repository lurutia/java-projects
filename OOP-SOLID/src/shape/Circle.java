package shape;

import shape.interfaces.ShapeInterface;

public class Circle implements ShapeInterface {
	public int radius;
	
	public Circle(int radius) {
		this.radius = radius;
	}
	
	public double area() {
		return Math.PI * Math.pow(this.radius, 2);
	}
}
