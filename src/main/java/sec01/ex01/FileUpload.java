package sec01.ex01;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/upload.do")
public class FileUpload extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("upload 시작");
	}

	public void destroy() {
		System.out.println("upload 종료");
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
		File serverRepo = new File("C:\\file_repo_1");
//		System.out.println(serverRepo);
		DiskFileItemFactory factory = new DiskFileItemFactory();
//		System.out.println(factory);

		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
//		System.out.println(upload);

		try {
			List fileItemList = upload.parseRequest(request);
//			System.out.println(fileItemList);
//			System.out.println(fileItemList.size());

			for (int i = 0; i < fileItemList.size(); i++) {
				FileItem fileItem = (FileItem) fileItemList.get(i);
				if (fileItem.getSize() > 0) {
					if (fileItem.isFormField()) {
						System.out.println(fileItem.getSize());
						System.out.println(fileItem.getFieldName() + "=" + fileItem.getString("utf-8"));
					} else {
						System.out.println("파일명: " + fileItem.getName());
						String uploadFileName = fileItem.getName();
//						System.out.println(uploadFileName);
						int index = uploadFileName.lastIndexOf("\\");
						System.out.println(index);
						String fileName = uploadFileName.substring(index + 1);
//						System.out.println(fileName);
						File uploadFile = new File(serverRepo + "\\" + uploadFileName);
						System.out.println(uploadFile);
						fileItem.write(uploadFile);
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
