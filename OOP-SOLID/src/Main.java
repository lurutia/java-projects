import java.util.ArrayList;
import java.util.List;

import shape.AreaCalculator;
import shape.Circle;
import shape.Square;
import shape.SumCalculatorOutputter;
import shape.VolumeCalculator;
import shape.interfaces.ShapeInterface;

public class Main {

	public static void main(String[] args) {
		List<ShapeInterface> shapes = new ArrayList<ShapeInterface>();
		
		shapes.add(new Circle(2));
		shapes.add(new Square(5));
		shapes.add(new Square(6));
		
		AreaCalculator areaCalculator = new AreaCalculator(shapes);
		VolumeCalculator volumeCalculator = new VolumeCalculator(shapes);
		SumCalculatorOutputter sumCalculatorOutputter = new SumCalculatorOutputter(areaCalculator);
		SumCalculatorOutputter sumCalculatorOutputter2 = new SumCalculatorOutputter(volumeCalculator);

		System.out.println(areaCalculator.output());
		System.out.println(sumCalculatorOutputter.JSON());
		System.out.println(sumCalculatorOutputter.HTML());
		System.out.println(sumCalculatorOutputter2.JSON());
		System.out.println(sumCalculatorOutputter2.HTML());
		
	}
}
