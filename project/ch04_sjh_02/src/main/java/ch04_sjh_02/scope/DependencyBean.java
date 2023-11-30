package ch04_sjh_02.scope;

public class DependencyBean {
	
	InjectionBean injectionBean;
	
	public DependencyBean(InjectionBean injectionBean) {
		this.injectionBean = injectionBean;
	}
}
