package shape;

import shape.interfaces.ShapeInterface;
import shape.interfaces.ThreeDimentionalShapeInterface;

public class Cuboid implements ShapeInterface, ThreeDimentionalShapeInterface {
	int length;
	
	public Cuboid(int length) {
		this.length = length;
	}
	
	@Override
	public double area() {
		return Math.pow(this.length, 2);
	}
	
	@Override
	public double volume() {
		return this.area() * this.length;
	}
}
