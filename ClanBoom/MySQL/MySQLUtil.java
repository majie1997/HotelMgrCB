package ClanBoom.MySQL;
/**
 * 2017-07-14
 * mysql数据库函数封装 
 * **************说明********************
 * 所有函数都是public static方法，不用实例化类，直接使用 类名字.函数名
 * 所有函数命名结尾都是_ ClanBoom
 * 在运行之前需要先开启MySQL服务，并运行MySQL_shell\\createtable.sql文件的命令，在控制台或windowsshell
 */
import java.sql.Connection;
import ClanBoom.entity.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import com.mysql.jdbc.Driver;

import ClanBoom.utils.ManUtil;
public class MySQLUtil {
	private static String url = "jdbc:mysql://localhost:3306/clanboom?autoReconnect=true&useSSL=false";
           // + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
	private static String driver = "com.mysql.jdbc.Driver";
	private static String user = "root";
	private static String password = "123456";
	/*
	 * 执行SQL语句   String sql
	 * 返回受影响的行数
	 */
	public static int update(String sql){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		int cow = 0;
		try {//建立连接
			conn=getConnection(); 
			stmt=conn.createStatement();  
			cow = stmt.executeUpdate(sql);
			/*if(cow>0){
				System.out.println("成功执行语句"+sql);
			}else{
				System.out.println("没有成功执行");
			}*/
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
		return cow;
	}
	/*
	 * 获取数据库连接对象。
	 * 返回链接对象的引用
	 */
	public static Connection getConnection() {
		Connection conn=null;
		if(conn==null){
			// 获取连接并捕获异常
			try {
				Class.forName(driver);
				//System.out.println("加载驱动");
				conn = DriverManager.getConnection(url,user,password );
				//System.out.println("建立连接");
			} catch (Exception e) {
				e.printStackTrace();// 异常处理
			}
		}	
		return conn;// 返回连接对象
	}
	/*
	 * 关闭数据库连接。
	 */
	public static void closeAll(Connection conn, Statement stmt,ResultSet rs){
		// 若结果集对象不为空，则关闭
		if (rs != null) {
			try {
				rs.close();
				//System.out.println("关闭rs");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		// 若Statement对象不为空，则关闭
		if (stmt != null) {
			try {
				stmt.close();
				//System.out.println("关闭stmt");
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		// 若数据库连接对象不为空，则关闭
		if (conn != null) {
			try {
				conn.close();
				//System.out.println("关闭conn");
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
	}
	
	/*
	 * 加载所有数据库信息
	 * 2017-07-12
	 * // 使用rs（resultset对象），将相应的数据库加载到泛型集合
	 */
	public static void addAll_ClanBoom(ManUtil manUtil){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		//PreparedStatement psmt=null; 
		 
		//2 建立连接
		try{
			conn=getConnection(); 
			stmt=conn.createStatement(); 
			//加载食品信息  List<FoodCB> Foodmap
			
			rs=stmt.executeQuery("select * from foodmapsql"); 
			while(rs.next()){
				FoodCB foodCB = new FoodCB(rs.getInt(1),rs.getString(2),rs.getDouble(3));
				manUtil.Foodmap.add(foodCB);
			}
			// 加载会员信息 Map<String,MemberCB> Memmap
			rs=stmt.executeQuery("select * from MemmapSQL"); 
			while(rs.next()){
				MemberCB memberCB = new MemberCB(rs.getString(2), rs.getString(1),
						      rs.getString(3), rs.getDouble(4), rs.getString(5));  
				manUtil.Memmap.put(memberCB.getNum(), memberCB);
			}
			//加载餐桌信息List<DeskCB>DeskList
			rs=stmt.executeQuery("select * from DeskListSQL"); 
			while(rs.next()){
				DeskCB deskCB = new DeskCB(rs.getInt(2), rs.getString(1),
						rs.getInt(3), rs.getInt(4));
				manUtil.DeskList.add(deskCB);
			}
			//加载管理员信息  Map<String,AdminCB> Adminmap
			rs=stmt.executeQuery("select * from AdminmapSQL"); 
			while(rs.next()){
				AdminCB adminCB = new AdminCB(rs.getString(2),
						rs.getString(1), rs.getString(3), rs.getString(4));
				manUtil.Adminmap.put(adminCB.getNum(), adminCB);
			}
			//加载经理信息  List<BusinessCB> BusinessList
			rs= stmt.executeQuery("select * from BusinessListSQL");
			while(rs.next()){
				BusinessCB businessCB = new BusinessCB(rs.getString(2),
						rs.getString(3), rs.getDouble(4),
						rs.getString(5), rs.getInt(6));
				manUtil.BusinessList.add(businessCB);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
}// addAll_ClanBoom
	/*
	 * 新增会员
	 * 2017-07-12	
	 */
	public static void addMember_ClanBoom(MemberCB memberCB){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement psmt=null; 
		try {
			conn=getConnection(); //链接
			stmt=conn.createStatement(); 
			String sql="insert into MemmapSQL(Num,ManName,Password,RestMoney,Birth)values(?,?,?,?,?) ";
			psmt=conn.prepareStatement(sql);// 使用格式化构成SQL语句
			// 以下5行对应上两行的5个？
			psmt.setString(1,memberCB.getNum());
			psmt.setString(2, memberCB.getManName());
			psmt.setString(3, memberCB.getPassword());
			psmt.setDouble(4, memberCB.getRestMoney());
			psmt.setString(5, memberCB.getBirth());
		    psmt.execute();
		   // System.out.println("成功插入会员数据在数据库");
		   // System.out.println("成功执行语句"+sql);
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
		
	}//addMember_ClanBoom
	/*
	 * 删除会员
	 * 2017-07-12	
	 */
	public static void deleteMember_ClanBoom(MemberCB memberCB){		    
		//String sql="delete from MemmapSQL where Num = "+"'"+memberCB.getNum()+"'";
		// 先定义SQL语句，再用update方法执行
		String sql="delete from MemmapSQL where Num = " +memberCB.getNum() ;
		update(sql); 
		
	}

	/*
	 * 修改会员
	 * 2017-07-12	
	 * search 1=修改密码 2=修改生日,3余额
	 * 选择对应的search 
	 * 要先在泛型集合中修改后的memberCB 作为参数；也就是将改好的memberCB再在数据库中做调整
	 */
	public static void changeMember_ClanBoom(int search,MemberCB memberCB){
		String sql = null;
		switch (search) {
		case 1:
			sql = "update MemmapSQL set Password = "+"'"+memberCB.getPassword()
			      +"'"+" where Num="+memberCB.getNum();
			break;
		case 2:
			sql = "update MemmapSQL set Birth = "+"'"+memberCB.getBirth()
			     +"'"+" where Num="+memberCB.getNum();
			break;
		case 3:
			sql = "update MemmapSQL set RestMoney = "+memberCB.getRestMoney()
		         +" where Num="+memberCB.getNum();
			break;
		default:
			System.out.println("程序出错啦 search的值不对");
			break;
		}//switch
		update(sql);
		
	}//changeMember_ClanBoom

	/*
	 * 新建菜品
	 * 要先在泛型集合中修改后的foodCB 作为参数；也就是将改好的foodCB再在数据库中做调整
	 */
	
	public static void addFood_ClanBoom(FoodCB foodCB) {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement psmt=null; 
		try {
			conn=getConnection(); 
			stmt=conn.createStatement(); 
			String sql="insert into FoodmapSQL(Num,Food,Price)values(?,?,?) ";
			psmt=conn.prepareStatement(sql);// 使用格式化构成SQL语句
			// 以下3行对应上两行的3个？
			psmt.setInt(1, foodCB.getNum());
			psmt.setString(2, foodCB.getFood());
			psmt.setDouble(3, foodCB.getPrice());
		    psmt.execute();
		    //System.out.println("成功执行语句"+sql);
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
		
	}//addFood_ClanBoom
	/*
	 删除菜品
	 要在泛型集合删除之前的foodCB作为参数
	 并且把后面的菜品号码减一
	 */
	public static void deleteFood_ClanBoom(FoodCB foodCB){ 
		String sql = "delete from FoodmapSQL where Num = "+foodCB.getNum();
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null; 
		try {
			conn=getConnection();  
			stmt=conn.createStatement();  
			stmt.executeUpdate(sql);
			// 删除对应的food
		    rs=stmt.executeQuery("select * from FoodmapSQL");
		    int max = foodCB.getNum();
		    while(rs.next()){
		    	if(rs.getInt(1) > max){
		    		max = rs.getInt(1);
		    	}
		    }// while
		    // for循环 依次减一
		    	for(int i=(foodCB.getNum()+1);i<=max;i++ ){
		    		sql = "update FoodmapSQL set Num = "+(i-1)+" where Num = "+i;
		    		stmt.executeUpdate(sql);
		    	//	System.out.println("成功执行语句"+sql);
		    	} 
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
 	 
	}//deleteFood_ClanBoom
	/*
	 * 添加生意，默认未完成
	 */
    public static void addBusiness_ClanBoom(BusinessCB businessCB){
    	Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement psmt=null; 
		try {
			conn=getConnection(); 
			stmt=conn.createStatement(); 
			String sql="insert into BusinessListSQL(Name,Food,Money,Desk)values(?,?,?,?)";
			psmt=conn.prepareStatement(sql);
			psmt.setString(1,businessCB.getName());
			psmt.setString(2, businessCB.getFood() );
			psmt.setDouble(3, businessCB.getMoney());
			psmt.setString(4, businessCB.getDesk()); 
		    psmt.execute();
		   // System.out.println("成功插入在数据库"+sql);
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
 	 
    }
    /*
     * 待到付款完成，将标志位设为1
     */
    public static void isOver_ClanBoom(MemberCB memberCB){
    	String sql = "update BusinessListSQL set isOver = 1 where name = "
    			+"'"+memberCB.getManName()+"'";
    	update(sql);
    }
    /*
     * 修改餐桌是否被占用信息，对DeskUse取反
     * 要将在泛型集合中修改之前的deskcb作为参数
     */
    public static void isDeskUsed_ClanBoom(DeskCB deskCB){
    	Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement psmt=null;
		int change = (deskCB.getDeskUse()+1)%2;
    	
		try {
			conn=getConnection(); 
			stmt=conn.createStatement(); 
			String sql = "update DeskListSQL set DeskUse = ? where DeskRecord = ?";
	    	psmt=conn.prepareStatement(sql);
			psmt.setInt(1, change);
			psmt.setString(2, deskCB.getDeskRecord());
		    psmt.execute();
		  //  System.out.println("成功执行语句"+sql+change+deskCB.getDeskRecord());
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
 	  
    }//isDeskUsed_ClanBoom












}//class MySQLUtil
