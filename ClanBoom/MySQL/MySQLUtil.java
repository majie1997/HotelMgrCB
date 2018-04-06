package ClanBoom.MySQL;
/**
 * 2017-07-14
 * mysql���ݿ⺯����װ 
 * **************˵��********************
 * ���к�������public static����������ʵ�����ֱ࣬��ʹ�� ������.������
 * ���к���������β����_ ClanBoom
 * ������֮ǰ��Ҫ�ȿ���MySQL���񣬲�����MySQL_shell\\createtable.sql�ļ�������ڿ���̨��windowsshell
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
	 * ִ��SQL���   String sql
	 * ������Ӱ�������
	 */
	public static int update(String sql){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		int cow = 0;
		try {//��������
			conn=getConnection(); 
			stmt=conn.createStatement();  
			cow = stmt.executeUpdate(sql);
			/*if(cow>0){
				System.out.println("�ɹ�ִ�����"+sql);
			}else{
				System.out.println("û�гɹ�ִ��");
			}*/
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
		return cow;
	}
	/*
	 * ��ȡ���ݿ����Ӷ���
	 * �������Ӷ��������
	 */
	public static Connection getConnection() {
		Connection conn=null;
		if(conn==null){
			// ��ȡ���Ӳ������쳣
			try {
				Class.forName(driver);
				//System.out.println("��������");
				conn = DriverManager.getConnection(url,user,password );
				//System.out.println("��������");
			} catch (Exception e) {
				e.printStackTrace();// �쳣����
			}
		}	
		return conn;// �������Ӷ���
	}
	/*
	 * �ر����ݿ����ӡ�
	 */
	public static void closeAll(Connection conn, Statement stmt,ResultSet rs){
		// �����������Ϊ�գ���ر�
		if (rs != null) {
			try {
				rs.close();
				//System.out.println("�ر�rs");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		}
		// ��Statement����Ϊ�գ���ر�
		if (stmt != null) {
			try {
				stmt.close();
				//System.out.println("�ر�stmt");
			} catch (Exception e) {
				e.printStackTrace();				
			}
		}
		// �����ݿ����Ӷ���Ϊ�գ���ر�
		if (conn != null) {
			try {
				conn.close();
				//System.out.println("�ر�conn");
			} catch (Exception e) {
				e.printStackTrace();	
			}
		}
	}
	
	/*
	 * �����������ݿ���Ϣ
	 * 2017-07-12
	 * // ʹ��rs��resultset���󣩣�����Ӧ�����ݿ���ص����ͼ���
	 */
	public static void addAll_ClanBoom(ManUtil manUtil){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		//PreparedStatement psmt=null; 
		 
		//2 ��������
		try{
			conn=getConnection(); 
			stmt=conn.createStatement(); 
			//����ʳƷ��Ϣ  List<FoodCB> Foodmap
			
			rs=stmt.executeQuery("select * from foodmapsql"); 
			while(rs.next()){
				FoodCB foodCB = new FoodCB(rs.getInt(1),rs.getString(2),rs.getDouble(3));
				manUtil.Foodmap.add(foodCB);
			}
			// ���ػ�Ա��Ϣ Map<String,MemberCB> Memmap
			rs=stmt.executeQuery("select * from MemmapSQL"); 
			while(rs.next()){
				MemberCB memberCB = new MemberCB(rs.getString(2), rs.getString(1),
						      rs.getString(3), rs.getDouble(4), rs.getString(5));  
				manUtil.Memmap.put(memberCB.getNum(), memberCB);
			}
			//���ز�����ϢList<DeskCB>DeskList
			rs=stmt.executeQuery("select * from DeskListSQL"); 
			while(rs.next()){
				DeskCB deskCB = new DeskCB(rs.getInt(2), rs.getString(1),
						rs.getInt(3), rs.getInt(4));
				manUtil.DeskList.add(deskCB);
			}
			//���ع���Ա��Ϣ  Map<String,AdminCB> Adminmap
			rs=stmt.executeQuery("select * from AdminmapSQL"); 
			while(rs.next()){
				AdminCB adminCB = new AdminCB(rs.getString(2),
						rs.getString(1), rs.getString(3), rs.getString(4));
				manUtil.Adminmap.put(adminCB.getNum(), adminCB);
			}
			//���ؾ�����Ϣ  List<BusinessCB> BusinessList
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
	 * ������Ա
	 * 2017-07-12	
	 */
	public static void addMember_ClanBoom(MemberCB memberCB){
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		PreparedStatement psmt=null; 
		try {
			conn=getConnection(); //����
			stmt=conn.createStatement(); 
			String sql="insert into MemmapSQL(Num,ManName,Password,RestMoney,Birth)values(?,?,?,?,?) ";
			psmt=conn.prepareStatement(sql);// ʹ�ø�ʽ������SQL���
			// ����5�ж�Ӧ�����е�5����
			psmt.setString(1,memberCB.getNum());
			psmt.setString(2, memberCB.getManName());
			psmt.setString(3, memberCB.getPassword());
			psmt.setDouble(4, memberCB.getRestMoney());
			psmt.setString(5, memberCB.getBirth());
		    psmt.execute();
		   // System.out.println("�ɹ������Ա���������ݿ�");
		   // System.out.println("�ɹ�ִ�����"+sql);
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
		
	}//addMember_ClanBoom
	/*
	 * ɾ����Ա
	 * 2017-07-12	
	 */
	public static void deleteMember_ClanBoom(MemberCB memberCB){		    
		//String sql="delete from MemmapSQL where Num = "+"'"+memberCB.getNum()+"'";
		// �ȶ���SQL��䣬����update����ִ��
		String sql="delete from MemmapSQL where Num = " +memberCB.getNum() ;
		update(sql); 
		
	}

	/*
	 * �޸Ļ�Ա
	 * 2017-07-12	
	 * search 1=�޸����� 2=�޸�����,3���
	 * ѡ���Ӧ��search 
	 * Ҫ���ڷ��ͼ������޸ĺ��memberCB ��Ϊ������Ҳ���ǽ��ĺõ�memberCB�������ݿ���������
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
			System.out.println("��������� search��ֵ����");
			break;
		}//switch
		update(sql);
		
	}//changeMember_ClanBoom

	/*
	 * �½���Ʒ
	 * Ҫ���ڷ��ͼ������޸ĺ��foodCB ��Ϊ������Ҳ���ǽ��ĺõ�foodCB�������ݿ���������
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
			psmt=conn.prepareStatement(sql);// ʹ�ø�ʽ������SQL���
			// ����3�ж�Ӧ�����е�3����
			psmt.setInt(1, foodCB.getNum());
			psmt.setString(2, foodCB.getFood());
			psmt.setDouble(3, foodCB.getPrice());
		    psmt.execute();
		    //System.out.println("�ɹ�ִ�����"+sql);
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
		
	}//addFood_ClanBoom
	/*
	 ɾ����Ʒ
	 Ҫ�ڷ��ͼ���ɾ��֮ǰ��foodCB��Ϊ����
	 ���ҰѺ���Ĳ�Ʒ�����һ
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
			// ɾ����Ӧ��food
		    rs=stmt.executeQuery("select * from FoodmapSQL");
		    int max = foodCB.getNum();
		    while(rs.next()){
		    	if(rs.getInt(1) > max){
		    		max = rs.getInt(1);
		    	}
		    }// while
		    // forѭ�� ���μ�һ
		    	for(int i=(foodCB.getNum()+1);i<=max;i++ ){
		    		sql = "update FoodmapSQL set Num = "+(i-1)+" where Num = "+i;
		    		stmt.executeUpdate(sql);
		    	//	System.out.println("�ɹ�ִ�����"+sql);
		    	} 
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
 	 
	}//deleteFood_ClanBoom
	/*
	 * ������⣬Ĭ��δ���
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
		   // System.out.println("�ɹ����������ݿ�"+sql);
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
 	 
    }
    /*
     * ����������ɣ�����־λ��Ϊ1
     */
    public static void isOver_ClanBoom(MemberCB memberCB){
    	String sql = "update BusinessListSQL set isOver = 1 where name = "
    			+"'"+memberCB.getManName()+"'";
    	update(sql);
    }
    /*
     * �޸Ĳ����Ƿ�ռ����Ϣ����DeskUseȡ��
     * Ҫ���ڷ��ͼ������޸�֮ǰ��deskcb��Ϊ����
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
		  //  System.out.println("�ɹ�ִ�����"+sql+change+deskCB.getDeskRecord());
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			closeAll(conn,stmt,rs);
		}//finally
 	  
    }//isDeskUsed_ClanBoom












}//class MySQLUtil
