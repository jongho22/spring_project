package ch04_sjh_02.scope;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		DependencyBean dependencyBean_01 = ctx.getBean("dependencyBean",DependencyBean.class);
		
		DependencyBean dependencyBean_02 = ctx.getBean("dependencyBean",DependencyBean.class);
		
		if(dependencyBean_01.equals(dependencyBean_02)) {
			System.out.println("두 객체가 같습니다.");
		} else {
			System.out.println("두 객체가 다름니다.");
		}
		
		ctx.close();
	}

}
