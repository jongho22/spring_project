package ch02_sjh_02;

public class MainClass {

	public static void main(String[] args) {
		
		MyCalculator calculator = new MyCalculator();
//		calculator.calAdd(10, 5, new CalAdd());
//		calculator.calSub(10, 5, new CalSub());
//		calculator.calMul(10, 5, new CalMul());
//		calculator.calDiv(10, 5, new CalDiv());
		
		calculator.calculate(10, 5, new CalAdd());
		calculator.calculate(10, 5, new CalSub());
		calculator.calculate(10, 5, new CalMul());
		calculator.calculate(10, 5, new CalDiv());

	}
}
