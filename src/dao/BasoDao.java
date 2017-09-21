package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BasoDao {
	Connection coon=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	//建立数据库连接
	public Connection  getcon() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.jdbc.Driver");
		return coon=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
		
	}
	//增删改通用方法
	public int executeUpdate(String sql, Object... obj) throws ClassNotFoundException, SQLException {
		coon = getcon(); 
		ps=coon.prepareStatement(sql);
		for(int i=0;i<obj.length;i++){
			ps.setObject(i+1, obj[i]);//???????????????why+1;
		}
		int count=ps.executeUpdate();
		
		return count;
	}
	//查询通用方法
	public ResultSet execute(String sql,Object... obj) throws ClassNotFoundException, SQLException{
		coon=getcon();
		coon.prepareStatement(sql);
		for(int i=0;i<obj.length;i++){
			ps.setObject(i, obj[i]);
			rs=ps.executeQuery();
		}
		return rs;
	}
	//关闭
	public void closeAll(){
		
			try {
				if(rs!=null){
				rs.close();
				}
				if(ps!=null){
				ps.close();	
				}
				if(coon!=null){
					coon.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			
		}
	}
}
