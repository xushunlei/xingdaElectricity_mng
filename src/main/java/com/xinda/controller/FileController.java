package com.xinda.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xinda.service.MeterService;

@Controller
@RequestMapping("filer/")
public class FileController {
	@Autowired
	private MeterService meterService;
	@ResponseBody
	@RequestMapping("analysis")
	public String analysis(HttpServletRequest request){
		String path=request.getParameter("filePath");
		System.out.println(path);
		return "{'path':"+path+"}";
	}
	/**上传Excel，完成上传后自动读取内容进行数据导入*/
	@ResponseBody
	@RequestMapping(value="upload",method=RequestMethod.POST)
	public String uploadExcel(HttpServletRequest request){
		//request.getContextPath()--应用名根目录
		//文件保存在tomcat路径下
		String savePath = request.getSession().getServletContext().getRealPath("/")+"file";
		System.out.println(savePath);
		File file = new File(savePath);
		//判断上传文件的保存目录是否存在
		if(!file.exists()&&!file.isDirectory()){
			//创建目录
			file.mkdir();
		}
		//消息提示
		String message = "";
		try{
			/*使用Apache文件上传组件处理文件上传步骤：*/
			//1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2、创建一个文件上传解析器，并设置字符集
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8"); 
			//3、判断提交上来的数据是否是上传表单的数据
			if(!ServletFileUpload.isMultipartContent(request)){
				//按照传统方式获取数据
				return null;
			}
			//4、解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			String zoneId="";
			for(FileItem item : list){
				//如果fileitem中封装的是普通输入项的数据
				if(item.isFormField()){
					String name = item.getFieldName();
					//解决普通输入项的数据的中文乱码问题
					String value = zoneId = item.getString("UTF-8");
					//value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
				}else{//如果fileitem中封装的是上传文件
					//得到上传的文件名称，
					String filename = item.getName();
					System.out.println(filename);
					if(filename==null || filename.trim().equals("")){
						continue;
					}
					//注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = request.getSession().getAttribute("loginUser")+filename.substring(filename.lastIndexOf("\\")+1);
					
					//获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					//创建一个文件输出流
					FileOutputStream out = new FileOutputStream(savePath + "\\" + filename);
					//创建一个缓冲区
					byte buffer[] = new byte[1024];
					//判断输入流中的数据是否已经读完的标识
					int len = 0;
					//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while((len=in.read(buffer))>0){
						//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
						out.write(buffer, 0, len);
					}
					//关闭输入流
					in.close();
					//关闭输出流
					out.close();
					
					//删除处理文件上传时生成的临时文件
					item.delete();
					meterService.tx_importFromExcel(savePath + "\\" + filename,zoneId);
					message = "文件上传成功！";
				}
			}
		}catch(Exception e){
			message = "文件上传失败！";
		}
		System.out.println(message);
		return "{completed:"+savePath+"}";
	}
}
