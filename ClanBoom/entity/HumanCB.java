package ClanBoom.entity;

public abstract class HumanCB {
	private String ManName;//»À√˚
	private String Num;//’Àªß
	private String Password;//√‹¬Î
	public String getManName() {
		return ManName;
	}
	public void setManName(String manName) {
		ManName = manName;
	}
	public String getNum() {
		return Num;
	}
	public void setNum(String num) {
		Num = num;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public HumanCB(){}
	public HumanCB(String ManName,String Num,String Password){
		this.ManName=ManName;
		this.Num=Num;
		this.Password=Password;
	}
		
	
}
