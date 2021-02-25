package shape;

public class SumCalculatorOutputter {
	
	protected AreaCalculator calculator;
	
	public SumCalculatorOutputter(AreaCalculator calculator) {
		this.calculator = calculator;
	}
	
	public String JSON() {
		return "{'data': ['sum': " + calculator.sum() + "]}";
	}
	
	public String HTML() {
		return "Sum of the areas of provided shapes : " + calculator.sum();
	}
}
