package ClanBoom.entity;

import java.util.HashMap;
import java.util.Map;

public class FoodCB {
	private int Num;//���
	private String Food;//����
	private double Price;//�˵ļ۸�
	public int getNum() {
		return Num;
	}
	public void setNum(int num) {
		Num = num;
	}
	
	public String getFood() {
		return Food;
	}
	public void setFood(String food) {
		Food = food;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public FoodCB() {
		
	}
	public FoodCB (int Num,String Food,double Price) {
		this.Food=Food;
		this.Price=Price;
		this.Num=Num;
	}
	/**
	 * ��ʾ��Ʒ��Ϣ
	 */
	public void show(){
		System.out.println(this.Num+"��"+this.Food+"\t"+this.Price+"Ԫ��");
	}
}
