package ch02_sjh_03;

public class CalAssembler {
	MyCalculator calculator;
	CalAdd calAdd;
	CalSub calSub;
	CalMul calMul;
	CalDiv calDiv;
	
	public CalAssembler() {
		calculator = new MyCalculator();
		calAdd 	   = new CalAdd();
		calSub     = new CalSub();
		calMul     = new CalMul();
		calDiv     = new CalDiv();
		
		assemble();
	}
	
	public void assemble() {
		calculator.calculate(10, 5, calAdd);
		calculator.calculate(10, 5, calSub);
		calculator.calculate(10, 5, calMul);
		calculator.calculate(10, 5, calDiv);
	}
}
