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
	 * 初始化所有菜品
	 */
	public void initFood(){
		FoodCB food1=new FoodCB(1,"蚝皇阿一鲍 ",498);
		FoodCB food2=new FoodCB(2,"鹿筋烧辽参 ",468);
		FoodCB food3=new FoodCB(3,"鱼翅捞饭 ",218);
		FoodCB food4=new FoodCB(4,"烤全羊 ",1588);
		FoodCB food5=new FoodCB(5,"过桥牛排 ",268);
		FoodCB food6=new FoodCB(6,"精品佛跳墙 ",420);
		FoodCB food7=new FoodCB(7,"碟鱼头 ",128);
		FoodCB food8=new FoodCB(8,"考王爷肉 ",98);
		FoodCB food9=new FoodCB(9,"梅菜扣生排 ",68);
		
		
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
	 * 初始化所有会员
	 * @param numBer
	 * @param passWord
	 * @return
	 */
	public void initMem(){
		MemberCB mem1=new MemberCB("张沛","111111","zhangpei",500,"07-17");
		MemberCB mem2=new MemberCB("马洁","222222","majie",5000,"07-17");
		MemberCB mem3=new MemberCB("单晓辉","333333","shanxiaohui",5000,"07-17");
		MemberCB mem4=new MemberCB("李嘉馨","444444","lijiaxin",5000,"07-17");
		Memmap.put(mem1.getNum(),mem1);
		Memmap.put(mem2.getNum(),mem2);
		Memmap.put(mem3.getNum(),mem3);
		Memmap.put(mem4.getNum(),mem4);
	}
	//初始化所有餐桌
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
	//初始化所有管理员
	public void initAdmin(){
		AdminCB admin1=new AdminCB("马老师","000000","123456","经理");
		AdminCB admin2=new AdminCB("吕梦天","000001","123456","店员");
		AdminCB admin3=new AdminCB("熊良玉","000002","123456","店员");
		Adminmap.put(admin1.getNum(),admin1);
		Adminmap.put(admin2.getNum(),admin2);
		Adminmap.put(admin3.getNum(),admin3);
	}
	//判断管理员名和密码是否匹配
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
	//判断用户名和密码是否配对
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
	//点菜
	public double order(String number,String Desk,boolean isDesk){
		boolean isExit=true;
		double money=0;
		String Food="";
		int num=0;
		List<MenuCB> ord=new ArrayList<MenuCB>();
		do{
			System.out.println("请输入你要点的菜品序号(输入0结束!)");
			int name=input.nextInt();
			for(int i=0;i<Foodmap.size();i++){
				if(name==Foodmap.get(i).getNum()){
					System.out.println("请输入份数");
					num=input.nextInt();
					MenuCB menuCB=new MenuCB(1,Foodmap.get(i).getFood(),Foodmap.get(i).getPrice(),num);
					Food=Food+Foodmap.get(i).getFood()+"  "+num+"份";
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
		System.out.println("您的菜单为：");
		for(int i=0;i<ord.size();i++){
			System.out.println(ord.get(i).getFood()+"\t"+ord.get(i).getNumber()+"份");
		}
		return money;
	}
	//充值
	public void Recharge(String number){
		System.out.println("请输入充值金额充值：");
		double money=input.nextInt();
		money=money+Memmap.get(number).getRestMoney();
		Memmap.get(number).setRestMoney(money);
	}
	//查看所有会员
	public void Memshow(){
		Set<String> numbers = Memmap.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			Memmap.get(it.next()).showMeg();
		}
	}
	//增加会员
	public void MemAdd(){
		System.out.println("请输入要增加的会员姓名");
		String name =input.next();
		name.trim();
		System.out.println("请输入密码");
		String password =input.next();
		password.trim();
		System.out.println("请输入生日（MM-DD）");
		String birth=input.next();
		birth.trim();
		System.out.println("请输入存入的钱数：");
		double money=input.nextDouble();
		String number=createNumber();
		MemberCB memberCB=new MemberCB(name,number,password,money,birth);
		Memmap.put(number, memberCB);
		System.out.println("新会员增添成功！");
		memberCB.showMeg();
	}
	//删除会员
	public void MemRemove(String number) {
		boolean isE=true;
		Set<String> numbers = Memmap.keySet();
		Iterator<String> it = numbers.iterator();
		while (it.hasNext()) {
			String searchNum = it.next();
			if (searchNum.equals(number)){
				Memmap.remove(searchNum);
				System.out.println("删除会员成功！");
				isE=false;
				break;
			}
		}
		if(isE){
			System.out.println("您输入的信息有误！");
		}
	}
	//查看所有菜品
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
	//清空餐桌
	public void DeskClear(String number){
		for(int i=0;i<DeskList.size();i++){
			if(DeskList.get(i).getDeskRecord().equals(number)&&DeskList.get(i).getDeskUse()==1){
					// add code here 1
					MySQLUtil.isDeskUsed_ClanBoom(DeskList.get(i));
					DeskList.get(i).setDeskUse(0); 
				}
			}

	}
	//查看所有包间和大厅
	public void DeskShow(){
		for(int i=0;i<DeskList.size();i++){
			DeskList.get(i).show();
		}
	}
	//增添菜品
	public void FoodAdd() {
		System.out.println("请输入要增加的菜品名");
		String name =input.next();
		name.trim();
		System.out.println("请输入菜品单价");
		double money=input.nextDouble();
		int num=Foodmap.get(Foodmap.size()-1).getNum()+1;
		FoodCB foodCB=new FoodCB(num,name,money);
		Foodmap.add(foodCB);
		System.out.println("新菜品增添成功！");
		foodCB.show();
	}
	//删除菜品
	public void FoodRemove(int number){
		boolean isEx=false;
		for(int i=0;i<Foodmap.size();i++){
			if(Foodmap.get(i).getNum()==number){
				Foodmap.remove(i);
				System.out.println("删除菜品成功！");
				isEx=true;
				for(int q=i;q<Foodmap.size();q++)
				{
					Foodmap.get(q).setNum(Foodmap.get(q).getNum()-1);
				}
			}
		}
		if(!isEx){
			System.out.println("您输入信息有误");
		}
	}
	//承办酒席
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
				System.out.println("请输入你要选择的餐桌");
				String record=input.next();
				for(int w=0;w<DeskList.size();w++)
				{
					if(DeskList.get(w).getDeskRecord().equals(record)&&DeskList.get(w).getDeskUse()==0){
						isE=false;
						Desk+=record+" ";
					}
				}
				if(isE){
					System.out.println("您输入有误！");
				}
				}while(isE);
			}
			System.out.println("酒席餐桌预订成功！");
			isE=true;
			for(int r=0;r<Foodmap.size();r++){
				Foodmap.get(r).show();;
			}
			do{
				System.out.println("请输入要订的菜品序号：(输入其他数字结束)");
				int figure=input.nextInt();
				if(figure>0&&figure<Foodmap.size()){
					System.out.println("请输入份数：");
					fenshu=input.nextInt();
					Food=Food+Foodmap.get(figure).getFood()+fenshu+"份 ";
					money=money+Foodmap.get(figure).getPrice()*fenshu;
				}
				else{
					isE=false;
				}
			}while(isE);
			System.out.println("菜品预订成功！");
			BusinessCB businessCB=new BusinessCB("",Food,money,Desk,2);
			BusinessList.add(businessCB);
		}else{
			System.out.println("餐桌数量不够，不能预订！");
		}
		
	}
	//查看营业状况
	public void BusinessShow(){
		for(int i=0;i<BusinessList.size();i++){
			BusinessList.get(i).show();
		}
	}
	/**
	 * 随机生成号码
	 */
	public String createNumber() {
		Random random = new Random();
		boolean isExist = false; // 记录现有用户中是否存在此卡号用户 是：true 否：false
		String number = "";
		int temp = 0;
		do {
			isExist = false; // 标志位重置为false,用于控制外重循环，当生成了
			do{
			temp= random.nextInt(1000000);
			}while(temp<100000);
			// 和现有用户的卡号比较，不能是重复
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
