package ClanBoom.entity;

public class AdminCB extends HumanCB {
	private String Limits;//ְ��Ȩ�ޣ�
	public String getLimits() {
		return Limits;
	}
	public void setLimits(String limits) {
		Limits = limits;
	}
	public AdminCB(){}
	public AdminCB (String ManName ,String Num,String Password,String Limits) {
		super(ManName,Num,Password);
		this.Limits=Limits;
	}
	public void show(){
		System.out.println(this.getNum()+"\t"+this.getManName()+"\t"+this.getLimits());
	}
}
