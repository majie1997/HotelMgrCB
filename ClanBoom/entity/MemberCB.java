package ClanBoom.entity;

import java.security.Provider.Service;
import java.util.LinkedList;
import java.util.List;
/**
 * ��Ա
 * @author zhp
 *
 */
public class MemberCB extends HumanCB {
	private String Birth;//����
	private double RestMoney;//�˻����
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
	 * ��ʾ����Ϣ
	 */
	public void showMeg(){
		System.out.println("�˻���"+this.getNum()+" �û�����"+this.getManName()+" ��ǰ��"+this.RestMoney+"Ԫ��"+"����"+this.Birth);
	}	
}
