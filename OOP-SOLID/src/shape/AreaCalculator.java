package shape;
import java.util.ArrayList;
import java.util.List;

import shape.interfaces.ShapeInterface;

public class AreaCalculator {
	protected List<ShapeInterface> shapes;
	
	public AreaCalculator(List<ShapeInterface> shapes) {
		this.shapes = shapes;
	}
	
	public double sum() {
		List<Double> areas = new ArrayList<Double>();
		
		for(ShapeInterface shape : shapes) {
			areas.add(shape.area());
//			if(shape instanceof Square) {
//				areas.add(Math.pow(((Square)shape).length, 2));
//			} else if(shape instanceof Circle) {
//				areas.add(Math.PI * Math.pow(((Circle)shape).radius, 2));
//			}
		}
		
		return arraySum(areas);
	}
	
	protected double arraySum(List<Double> areas) {
		double sum = 0;
		for(Double value : areas) {
			sum += value;
		}
		
		return sum;
	}
	
	public String output() {
		return "Sum of the areas of provided shapes : " + this.sum();
	}
}
