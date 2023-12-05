package ch06_sjh_01.ems.member;

public class Student {
	
	private String sNum;
	private String sId;
	private String sPw;
	private String sName;
	private int sAge;
	private char sGender;
	private String sMajor;
	
	public Student(String sNum, String sId, String sPw, String sName, int sAge,
			char sGender, String sMajor) {
		this.sNum = sNum;
		this.sId = sId;
		this.sPw = sPw;
		this.sName = sName;
		this.sAge = sAge;
		this.sGender = sGender;
		this.sMajor = sMajor;
	}
	
	public String getsNum() {return sNum;}
	public void setsNum(String sNum) {this.sNum = sNum;}
	
	public String getsId() {return sId;}
	public void setsId(String sId) {this.sId = sId;}
	
	public String getsPw() {return sPw;}
	public void setsPw(String sPw) {this.sPw = sPw;}
	
	public String getsName() {return sName;}
	public void setsName(String sName) {this.sName = sName;}
	
	public int getsAge() {return sAge;}
	public void setsAge(int sAge) {this.sAge = sAge;}
	
	public char getsGender() {return sGender;}
	public void setsGender(char sGender) {this.sGender = sGender;}
	
	public String getsMajor() {return sMajor;}
	public void setsGender(String sMajor) {this.sMajor = sMajor;}
	
}
