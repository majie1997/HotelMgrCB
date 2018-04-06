package ClanBoom.utils;
import ClanBoom.MySQL.MySQLUtil;
import ClanBoom.entity.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.print.DocFlavor.INPUT_STREAM;
import javax.rmi.CORBA.Util;

import ClanBoom.entity.FoodCB;
import ClanBoom.entity.MemberCB;

public class ManUtil {
	Scanner input = new Scanner(System.in);
	public List<FoodCB> Foodmap=new ArrayList<FoodCB>();
	public Map<String,MemberCB> Memmap=new HashMap<String,MemberCB>();
	public List<DeskCB>DeskList=new ArrayList<DeskCB>(); 
	public Map<String,AdminCB> Adminmap =new HashMap<String,AdminCB>();
	public List<BusinessCB> BusinessList=new ArrayList<BusinessCB>(); 
	/**
	 * ��ʼ�����в�Ʒ
	 */
	public void initFood(){
		FoodCB food1=new FoodCB(1,"�ʰ�һ�� ",498);
		FoodCB food2=new FoodCB(2,"¹�����ɲ� ",468);
		FoodCB food3=new FoodCB(3,"����̷� ",218);
		FoodCB food4=new FoodCB(4,"��ȫ�� ",1588);
		FoodCB food5=new FoodCB(5,"����ţ�� ",268);
		FoodCB food6=new FoodCB(6,"��Ʒ����ǽ ",420);
		FoodCB food7=new FoodCB(7,"����ͷ ",128);
		FoodCB food8=new FoodCB(8,"����ү�� ",98);
		FoodCB food9=new FoodCB(9,"÷�˿����� ",68);
		
		
		Foodmap.add(food1);
		Foodmap.add(food2);
		Foodmap.add(food3);
		Foodmap.add(food4);	
		Foodmap.add(food5);
		Foodmap.add(food6);
		Foodmap.add(food7);
		Foodmap.add(food8);
		Foodmap.add(food9);
	}
	/**
	 * ��ʼ�����л�Ա
	 * @param numBer
	 * @param passWord
	 * @return
	 */
	public void initMem(){
		MemberCB mem1=new MemberCB("����","111111","zhangpei",500,"07-17");
		MemberCB mem2=new MemberCB("���","222222","majie",5000,"07-17");
		MemberCB mem3=new MemberCB("������","333333","shanxiaohui",5000,"07-17");
		MemberCB mem4=new MemberCB("���ܰ","444444","lijiaxin",5000,"07-17");
		Memmap.put(mem1.getNum(),mem1);
		Memmap.put(mem2.getNum(),mem2);
		Memmap.put(mem3.getNum(),mem3);
		Memmap.put(mem4.getNum(),mem4);
	}
	//��ʼ�����в���
	public void initCompart(){
		DeskCB desk1=new DeskCB(0,"001",1,0);
		DeskCB desk2=new DeskCB(0,"002",1,0);
		DeskCB desk3=new DeskCB(0,"003",1,0);
		DeskCB desk4=new DeskCB(0,"004",1,0);
		DeskCB desk5=new DeskCB(0,"005",1,0);
		DeskCB desk6=new DeskCB(0,"006",1,0);
		DeskCB desk7=new DeskCB(0,"007",1,0);
		
		DeskCB desk8=new DeskCB(1,"111",2,1);
		DeskCB desk9=new DeskCB(1,"222",2,0);
		DeskCB desk10=new DeskCB(1,"333",2,0);
		DeskCB desk11=new DeskCB(1,"444",2,0);
		DeskCB desk12=new DeskCB(1,"555",2,0);
		DeskCB desk13=new DeskCB(1,"666",2,0);
		DeskList.add(desk1);
		DeskList.add(desk2);
		DeskList.add(desk3);
		DeskList.add(desk4);
		DeskList.add(desk5);
		DeskList.add(desk6);
		DeskList.add(desk7);
		DeskList.add(desk8);
		DeskList.add(desk9);
		DeskList.add(desk10);
		DeskList.add(desk11);
		DeskList.add(desk12);
		DeskList.add(desk13);
		
	}
	//��ʼ�����й���Ա
	public void initAdmin(){
		AdminCB admin1=new AdminCB("����ʦ","000000","123456","����");
		AdminCB admin2=new AdminCB("������","000001","123456","��Ա");
		AdminCB admin3=new AdminCB("������","000002","123456","��Ա");
		Adminmap.put(admin1.getNum(),admin1);
		Adminmap.put(admin2.getNum(),admin2);
		Adminmap.put(admin3.getNum(),admin3);
	}
	//�жϹ���Ա���������Ƿ�ƥ��
	public boolean isExistAdmin(String number,String password){
		//initMem();
		Set<String> numbers = Adminmap.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String searchNum = it.next();
			if (searchNum.equals(number)
					&& (Adminmap.get(searchNum)).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	//�ж��û����������Ƿ����
	public boolean isExistCard(String number,String password){
		Set<String> numbers = Memmap.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String searchNum = it.next();
			if (searchNum.equals(number)
					&& (Memmap.get(searchNum)).getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	//���
	public double order(String number,String Desk,boolean isDesk){
		boolean isExit=true;
		double money=0;
		String Food="";
		int num=0;
		List<MenuCB> ord=new ArrayList<MenuCB>();
		do{
			System.out.println("��������Ҫ��Ĳ�Ʒ���(����0����!)");
			int name=input.nextInt();
			for(int i=0;i<Foodmap.size();i++){
				if(name==Foodmap.get(i).getNum()){
					System.out.println("���������");
					num=input.nextInt();
					MenuCB menuCB=new MenuCB(1,Foodmap.get(i).getFood(),Foodmap.get(i).getPrice(),num);
					Food=Food+Foodmap.get(i).getFood()+"  "+num+"��";
					ord.add(menuCB);
					money+=num*menuCB.getPrice();
				}
			}
			
			if(name==0){
				isExit=false;
			}
		}while(isExit);
		
		BusinessCB businessCB = null;
		if(isDesk){
		businessCB=new BusinessCB(Memmap.get(number).getManName(),Food,money,Desk,1);
		}else{
		businessCB=new BusinessCB(Memmap.get(number).getManName(),Food,money,Desk,0);
		}
		if(businessCB!=null){
		BusinessList.add(businessCB);
		}
		System.out.println("���Ĳ˵�Ϊ��");
		for(int i=0;i<ord.size();i++){
			System.out.println(ord.get(i).getFood()+"\t"+ord.get(i).getNumber()+"��");
		}
		return money;
	}
	//��ֵ
	public void Recharge(String number){
		System.out.println("�������ֵ����ֵ��");
		double money=input.nextInt();
		money=money+Memmap.get(number).getRestMoney();
		Memmap.get(number).setRestMoney(money);
	}
	//�鿴���л�Ա
	public void Memshow(){
		Set<String> numbers = Memmap.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			Memmap.get(it.next()).showMeg();
		}
	}
	//���ӻ�Ա
	public void MemAdd(){
		System.out.println("������Ҫ���ӵĻ�Ա����");
		String name =input.next();
		name.trim();
		System.out.println("����������");
		String password =input.next();
		password.trim();
		System.out.println("���������գ�MM-DD��");
		String birth=input.next();
		birth.trim();
		System.out.println("����������Ǯ����");
		double money=input.nextDouble();
		String number=createNumber();
		MemberCB memberCB=new MemberCB(name,number,password,money,birth);
		Memmap.put(number, memberCB);
		System.out.println("�»�Ա����ɹ���");
		memberCB.showMeg();
	}
	//ɾ����Ա
	public void MemRemove(String number) {
		boolean isE=true;
		Set<String> numbers = Memmap.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String searchNum = it.next();
			if (searchNum.equals(number)){
				Memmap.remove(searchNum);
				System.out.println("ɾ����Ա�ɹ���");
				isE=false;
				break;
			}
		}
		if(isE){
			System.out.println("���������Ϣ����");
		}
	}
	//�鿴���в�Ʒ
	public void FoodShow(){
		for(int i=0;i<Foodmap.size();i++){
			Foodmap.get(i).show();
		}
	}
	//
	public int DeskPd(String number){
		int flag=1;
		for(int i=0;i<DeskList.size();i++){
			if(DeskList.get(i).getDeskRecord().equals(number)){
				flag=0;
				if(DeskList.get(i).getDeskUse()==1)	{
					return 0;
				}else{
					return 1;
				}
			}	
		}
		if(flag==1){
				return 2;
			}
		return 0;
	}
	//��ղ���
	public void DeskClear(String number){
		for(int i=0;i<DeskList.size();i++){
			if(DeskList.get(i).getDeskRecord().equals(number)&&DeskList.get(i).getDeskUse()==1){
					// add code here 1
					MySQLUtil.isDeskUsed_ClanBoom(DeskList.get(i));
					DeskList.get(i).setDeskUse(0); 
				}
			}

	}
	//�鿴���а���ʹ���
	public void DeskShow(){
		for(int i=0;i<DeskList.size();i++){
			DeskList.get(i).show();
		}
	}
	//�����Ʒ
	public void FoodAdd() {
		System.out.println("������Ҫ���ӵĲ�Ʒ��");
		String name =input.next();
		name.trim();
		System.out.println("�������Ʒ����");
		double money=input.nextDouble();
		int num=Foodmap.get(Foodmap.size()-1).getNum()+1;
		FoodCB foodCB=new FoodCB(num,name,money);
		Foodmap.add(foodCB);
		System.out.println("�²�Ʒ����ɹ���");
		foodCB.show();
	}
	//ɾ����Ʒ
	public void FoodRemove(int number){
		boolean isEx=false;
		for(int i=0;i<Foodmap.size();i++){
			if(Foodmap.get(i).getNum()==number){
				Foodmap.remove(i);
				System.out.println("ɾ����Ʒ�ɹ���");
				isEx=true;
				for(int q=i;q<Foodmap.size();q++)
				{
					Foodmap.get(q).setNum(Foodmap.get(q).getNum()-1);
				}
			}
		}
		if(!isEx){
			System.out.println("��������Ϣ����");
		}
	}
	//�а��ϯ
	public void Feast(int number){
		double money=0;
		String Food="";
		String Desk="";
		int num=0;
		int fenshu;
		boolean isE=true;

		for(int i=0;i<DeskList.size();i++){
			DeskList.get(i).show();
			if(DeskList.get(i).getDeskUse()==0){
				num++;
			}
		}
		if(num>number||num==number){
			for(int q=0;q<number;q++){
				isE=true;
				do{
				System.out.println("��������Ҫѡ��Ĳ���");
				String record=input.next();
				for(int w=0;w<DeskList.size();w++)
				{
					if(DeskList.get(w).getDeskRecord().equals(record)&&DeskList.get(w).getDeskUse()==0){
						isE=false;
						Desk+=record+" ";
					}
				}
				if(isE){
					System.out.println("����������");
				}
				}while(isE);
			}
			System.out.println("��ϯ����Ԥ���ɹ���");
			isE=true;
			for(int r=0;r<Foodmap.size();r++){
				Foodmap.get(r).show();;
			}
			do{
				System.out.println("������Ҫ���Ĳ�Ʒ��ţ�(�����������ֽ���)");
				int figure=input.nextInt();
				if(figure>0&&figure<Foodmap.size()){
					System.out.println("�����������");
					fenshu=input.nextInt();
					Food=Food+Foodmap.get(figure).getFood()+fenshu+"�� ";
					money=money+Foodmap.get(figure).getPrice()*fenshu;
				}
				else{
					isE=false;
				}
			}while(isE);
			System.out.println("��ƷԤ���ɹ���");
			BusinessCB businessCB=new BusinessCB("",Food,money,Desk,2);
			BusinessList.add(businessCB);
		}else{
			System.out.println("������������������Ԥ����");
		}
		
	}
	//�鿴Ӫҵ״��
	public void BusinessShow(){
		for(int i=0;i<BusinessList.size();i++){
			BusinessList.get(i).show();
		}
	}
	/**
	 * ������ɺ���
	 */
	public String createNumber() {
		Random random = new Random();
		boolean isExist = false; // ��¼�����û����Ƿ���ڴ˿����û� �ǣ�true ��false
		String number = "";
		int temp = 0;
		do {
			isExist = false; // ��־λ����Ϊfalse,���ڿ�������ѭ������������
			do{
			temp= random.nextInt(1000000);
			}while(temp<100000);
			// �������û��Ŀ��űȽϣ��������ظ�
			number=temp+number;
			Set<String> cardNumbers = Memmap.keySet();
			for (String cardNumber : cardNumbers) {
				if (number.equals(cardNumber)) {
					isExist = true;
					break;
				}
			}
		} while (isExist);
		return number;
	}
	}
