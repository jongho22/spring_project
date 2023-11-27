package ch03_sjh_03;

public class CalDiv implements ICalculator {
	public int doOperation(int firstNum, int secondNum) {
		return firstNum != 0 ? (firstNum / secondNum) : 0;
	}
}
