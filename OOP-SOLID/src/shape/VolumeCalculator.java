package shape;
import java.util.ArrayList;
import java.util.List;

import shape.interfaces.ShapeInterface;

public class VolumeCalculator extends AreaCalculator {
	public VolumeCalculator(List<ShapeInterface> shapes) {
		super(shapes);
	}
	
	@Override
	public double sum() {
		List<Double> areas = new ArrayList<Double>();
		
		for(ShapeInterface shape : shapes) {
			areas.add(shape.area());
		}
		return this.arraySum(areas);
	}
}
