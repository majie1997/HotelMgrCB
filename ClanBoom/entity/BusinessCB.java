package ClanBoom.entity;

public class BusinessCB {
	private String Name;//��Ա����
	private String Food;//ÿ�ݲ˵������Ʒ
	private double Money;//ÿ�ݲ˵���Ǯ
	private String Desk;//ÿ��������������ӱ��
	private int State;//0���� ��1����
	public BusinessCB(){}
	public BusinessCB(String Name,String Food,double Money,String Desk,int State){
		this.Food=Food;
		this.Money=Money;
		this.Desk=Desk;
		this.State=State;
		this.Name=Name;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getFood() {
		return Food;
	}
	public void setFood(String food) {
		Food = food;
	}
	public double getMoney() {
		return Money;
	}
	public void setMoney(double money) {
		Money = money;
	}
	public String getDesk() {
		return Desk;
	}
	public void setDesk(String desk) {
		Desk = desk;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}
	public void show(){
		if(this.State==1){
		System.out.println(this.Name+"\t"+"����"+this.Desk+"\t"+this.Food+"\t"+this.Money+"Ԫ");
	}
		if(this.State==2){
			System.out.println("��ϯ"+"\n"+"�����ǣ�"+this.Desk+"\n"+"��Ʒ"+this.Food+"\n"+"Ǯ����"+this.Money);
		}
		else{
		System.out.println(this.Name+"\t"+"����"+this.Desk+"\t"+this.Food+"\t"+this.Money+"Ԫ");
	}
	}
}
