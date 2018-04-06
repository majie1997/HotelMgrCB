package ClanBoom.entity;

import java.security.Provider.Service;
import java.util.LinkedList;
import java.util.List;
/**
 * 会员
 * @author zhp
 *
 */
public class MemberCB extends HumanCB {
	private String Birth;//生日
	private double RestMoney;//账户余额
	public String getBirth() {
		return Birth;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
	public double getRestMoney() {
		return RestMoney;
	}
	public void setRestMoney(double restMoney) {
		RestMoney = restMoney;
	}
	public MemberCB(){}
	public MemberCB(String ManName,String Num,String Password,double RestMoney,String Birth){
		super(ManName,Num,Password);
		this.Birth=Birth;
		this.RestMoney=RestMoney;
	}
	/**
	 * 显示卡信息
	 */
	public void showMeg(){
		System.out.println("账户："+this.getNum()+" 用户名："+this.getManName()+" 当前余额："+this.RestMoney+"元。"+"生日"+this.Birth);
	}	
}
