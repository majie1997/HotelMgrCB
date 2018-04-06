package ClanBoom.entity;

import java.util.HashMap;
import java.util.Map;

public class FoodCB {
	private int Num;//标号
	private String Food;//菜名
	private double Price;//菜的价格
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
	 * 显示菜品信息
	 */
	public void show(){
		System.out.println(this.Num+"，"+this.Food+"\t"+this.Price+"元。");
	}
}
