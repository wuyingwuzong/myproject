package service;

import java.sql.SQLException;

import dao.UploadDao;
import entity.Upload;

public class UploadService {
	UploadDao ud=new UploadDao();

	public int save(Upload up) throws ClassNotFoundException, SQLException {
		return ud.save(up);
		
	}

}
