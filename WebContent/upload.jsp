<%@page import="java.util.Arrays"%>
<%@page import="org.apache.commons.fileupload.FileUploadBase"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.*"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
String path=request.getContextPath();
String basePath=request.getScheme()+"://"+request.getServerName()+
":"+request.getServletPath();
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!--在jsp页面中进行文件上传功能的编写  -->
<%//创建FileItem工厂
	FileItemFactory factory=new DiskFileItemFactory();
	ServletFileUpload upload=new ServletFileUpload(factory);
	//判断是否是文件上传的表单
	boolean isMultipart=ServletFileUpload.isMultipartContent(request);
	upload.setSizeMax(30*1024);
	try{
		if(isMultipart){
			//获取fileItem 集合
			List<FileItem> items=upload.parseRequest(request);
			System.out.print("size>>"+items.size());
			for(FileItem item:items){
				//确定是否是文件组件
				boolean isFormField=item.isFormField();
				if(isFormField){
					//获取普通组件名称使用getFieldName();
					String name=item.getFieldName();
					String value=item.getString("utf-8");
					System.out.print("name>>>"+name+"value<<<"+value);
				}else{
					String savePath=application.getRealPath("upload");
					String fileName=item.getName();
					File file=new File(savePath+"/"+fileName);
					//file有多个构造方法
					File file2=new File(savePath,fileName);
					item.write(file);
					//规定上传文件类型
				/* 	String name=fileName.substring(fileName.lastIndexOf(".")+1);
					List<String> list=Arrays.asList("png","jpg","txt","doc");
					if(!list.contains(name)){
						out.print("不支持此类型");
					} */
				}
			}
				}else{
			out.print("非法");
		}
		
	}catch(FileUploadBase.SizeLimitExceededException e){
		e.printStackTrace();
		out.print("您上传文件过大，不能完成");
	}

%>

</body>
</html>