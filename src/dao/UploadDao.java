package dao;

import java.sql.SQLException;

import entity.Upload;

public class UploadDao extends BasoDao {

	public int save(Upload up) throws ClassNotFoundException, SQLException {
		String sql="insert into uploadfile values(null,?,?,?)";
		return super.executeUpdate(sql,up.getName(),up.getEmail(),up.getPath());
		
	}

}
