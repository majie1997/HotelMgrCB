package ClanBoom.entity;
//�˵���
public class MenuCB extends FoodCB {
	private int Number;//����
	
	public int getNumber() {
		return Number;
	}

	public void setNumber(int number) {
		Number = number;
	}
	public MenuCB(){}
	public MenuCB(int Num,String Food,Double Price,int Number){
		super(Num,Food,Price);
		this.Number=Number;
	}

	public void show(){
		System.out.println(this.getFood()+"\t"+this.getPrice()+"\t"+this.getNumber());
	}
}
