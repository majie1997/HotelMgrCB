package ClanBoom.entity;

public class BusinessCB {
	private String Name;//会员姓名
	private String Food;//每份菜单所点菜品
	private double Money;//每份菜单价钱
	private String Desk;//每个生意的所在桌子编号
	private int State;//0大厅 ，1包间
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
		System.out.println(this.Name+"\t"+"包间"+this.Desk+"\t"+this.Food+"\t"+this.Money+"元");
	}
		if(this.State==2){
			System.out.println("酒席"+"\n"+"餐桌是："+this.Desk+"\n"+"菜品"+this.Food+"\n"+"钱数："+this.Money);
		}
		else{
		System.out.println(this.Name+"\t"+"大厅"+this.Desk+"\t"+this.Food+"\t"+this.Money+"元");
	}
	}
}
