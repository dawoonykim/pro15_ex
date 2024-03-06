package sec01.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download.do")
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		System.out.println("다운로드서블릿 초기화");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String file_repo="C:\\file_repo_1";
		
		String fileName = /* (String) */ request.getParameter("fileName");
		String downFile=file_repo+"\\"+fileName;
		System.out.println("서버에 있는 파일"+downFile);
		
		OutputStream out=response.getOutputStream();
		File f=new File(downFile);
		
		String encodedFileName=URLEncoder.encode(fileName,"utf-8");
		response.setHeader("Cache-Control", "no-cache");
		
//		response.addHeader("Content-disposition", "attachment;fileName="+fileName);
		response.addHeader("Content-disposition", "attachment;fileName="+encodedFileName);
		FileInputStream in=new FileInputStream(f);
		byte[] buffer = new byte[1024*8];
		
		while(true) {
			int count=in.read(buffer);
			if(count==-1) {
				break;
			}
			out.write(buffer,0,count);
		}
		in.close();
	}

}
