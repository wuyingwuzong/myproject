package servlet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import entity.Upload;
import service.UploadService;

/**
 * Servlet implementation class uploadServlet
 */
@WebServlet("/upload.do")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**文件上传的操作
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileItemFactory factory=new DiskFileItemFactory();
		ServletFileUpload upload =new ServletFileUpload(factory); 
		Upload up=new Upload();
		try {
			List<FileItem> items=upload.parseRequest(request);
			for(FileItem item :items){
				//判断是文件类型还是普通字段
			if(item.isFormField()){
				//true 是普通字段
				String fieldName = item.getFieldName();
				System.out.print(fieldName);
				String value=item.getString("utf-8");
				System.out.print(value);
				if(fieldName.equals("userName")){
					up.setName(value);
				}else if(fieldName.equals("email")){
					up.setEmail(value);
				}
			}else{
				//false是文件类型
				String name = item.getName();
				System.out.print( name);
				//以防文件重名进行的文件名操作
				Date date =new Date();
				SimpleDateFormat dates=new SimpleDateFormat("yyyyMMddHHmmss");
				name=dates.format(date)+name;
				//name.substring(beginIndex);
				//System.out.println(name);
				String path = request.getSession().getServletContext().getRealPath("upload");
				// getRealPath("upload")的upload 是在Tomcat中创建的文件名，你需要将文件保存的地方                                                            
				//File file=new File(path+"/"+name);
				//文件的多种构造方法
				File file2=new File(path, name);
				up.setPath("upload/"+path);
				item.write(file2);
				System.out.print("success");
				
			}
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将upload对象存数据库
		UploadService us=new UploadService();
		int count;
		try {
			count = us.save(up);
			if(count>0){
				response.sendRedirect("toUpload.jsp");
			}else{
				response.sendRedirect("upload.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
