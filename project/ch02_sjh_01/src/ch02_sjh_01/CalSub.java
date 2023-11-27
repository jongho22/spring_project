package ch02_sjh_01;

public class CalSub implements ICalculator {
	@Override
	public int doOperation(int firstNum, int secondNum) {
		return firstNum - secondNum;
	}

}
