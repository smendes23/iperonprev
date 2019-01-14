package br.com.iperonprev.helper;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/file/*")
public class FileServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) 
				throws ServletException, IOException {
		String path = req.getRequestURI().split("/file")[1];
		Path source = Paths.get(FileSaver.SERVER_PATH + "/" + java.net.URLDecoder.decode(path,"ISO-8859-1").replace(" ","%20"));
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor("file:"+source);
		res.reset();
		res.setContentType(contentType);
		res.setHeader("Content-Length", String.valueOf(Files.size(source)));
		res.setCharacterEncoding("UTF-8");
		res.setHeader("Content-Disposition", 
				"filename=\""+source.getFileName().toString()  
				.replace("%20", " ")
				+ "\"");
		FileSaver.transfer(source, res.getOutputStream());
	}
	
}