
package br.com.iperonprev.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Path;

import javax.servlet.http.Part;

public class FileSaver {

	public static final String SERVER_PATH = "C:\\tomcat8\\webapps\\ROOT\\arquivos\\";
	
//	public static final String SERVER_PATH = "C:\\tomcat8\\webapps\\iperonprev\\src\\main\\webapp\\arquivos\\";
//	public static final String SERVER_PATH = "C:\\Users\\IPERON\\workspacejboss\\iperonprev\\src\\main\\webapp\\arquivos\\";
//	public static final String SERVER_PATH = "C:\\Users\\Saulo\\workspace\\iperonprev\\src\\main\\webapp\\arquivos\\";
//	public static final String SERVER_PATH = "C:\\workspace\\iperonprev\\src\\main\\webapp\\arquivos\\";
	

    public String write(Part arquivo, String path) throws UnsupportedEncodingException {
    	
        String relativePath =  path+"\\"+  arquivo.getSubmittedFileName().getBytes("UTF-8");
        try {
        	File caminho = new File(SERVER_PATH+"\\"+relativePath);
        	caminho.getParentFile().mkdirs();
            arquivo.write(SERVER_PATH+"\\"+relativePath);

            return relativePath;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void transfer(Path source, OutputStream outputStream) {
		try {
			FileInputStream input = new FileInputStream(source.toFile());
			try( ReadableByteChannel inputChannel = Channels.newChannel(input);
					WritableByteChannel outputChannel = Channels.newChannel(outputStream)) {
				ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 10);
				
				while(inputChannel.read(buffer) != -1) {
					buffer.flip();
					outputChannel.write(buffer);
					buffer.clear();
				}
			} catch (IOException e) {
				throw new RuntimeException(e); 
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e); 
		}
	}
}
