package br.com.iperonprev.util.jsf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.Part;

import br.com.iperonprev.helper.FileSaver;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class CopyFile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Properties prop = new Properties();
	FileInputStream file;
	public CopyFile(){
	}
	
	public void copyFile(String fileName, InputStream in, String destino) {
		try {

			OutputStream out = new FileOutputStream(new File(FileSaver.SERVER_PATH+destino + fileName));

			int read = 0;
			byte[] bytes = new byte[1000000];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

		} catch (Exception e) {
			Message.addErrorMessage("Arquivo muito grande");
		}
	}
	
	
	public void saveArchive(InputStream in, String nomeDaPasta,String nomeDoArquivo){
		@SuppressWarnings("unused")
		StringBuilder sb = new StringBuilder();
		
		try{
			File caminho = new File(FileSaver.SERVER_PATH+"\\"+new StringBuilder().append(nomeDaPasta)
					.append("\\").append(nomeDoArquivo.replace(" ", "%20")).toString());
			System.out.println("Caminho: "+caminho);
        	caminho.getParentFile().mkdirs();
				
				sb = new StringBuilder();
				OutputStream out = new FileOutputStream(caminho);

				int read = 0;
				byte[] bytes = new byte[50000];

				while ((read = in.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				
				in.close();
				out.flush();
				out.close();
			
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível anexar o documento!"+e.getMessage());
		}
	}
	
	public List<FileLinkDto> getListArchive(String nomePasta,String nomeSubpasta){
		List<FileLinkDto> resultado = new ArrayList<FileLinkDto>();
		String SERVER_PATHContext = "/iperonprev/arquivos/";
		StringBuilder sb = new StringBuilder();
		File file = new File(sb.append(FileSaver.SERVER_PATH).append(nomePasta).append("/").append(nomeSubpasta).toString());
		List<File> lista = Arrays.asList(file.listFiles());
		
		if(!lista.isEmpty()){
			lista.forEach(l->{
				FileLinkDto fl = new FileLinkDto();
				StringBuilder sb2 = new StringBuilder();
				fl.setFileLink(sb2.append(SERVER_PATHContext).append(nomePasta).append("/").append(nomeSubpasta).append("/").append(l.getName()).toString());
				fl.setFileName(l.getName());
				resultado.add(fl);
			});
			
		}
		return resultado;
	}
	
	public int verifyIfSERVER_PATHectoryIsNull(String nomePasta,String nomeSubPasta){
		int resultado = 0;
		StringBuilder sb = new StringBuilder();
		File file = new File(sb.append(FileSaver.SERVER_PATH).append(nomePasta).append("/").append(nomeSubPasta).toString());
		if(file.listFiles().length > 0){
			resultado = 1;
		}
		return resultado;
	}
	
	public void removeFile(String caminho,String file){
		StringBuilder sb = new StringBuilder();
		File arq = new File(sb.append(FileSaver.SERVER_PATH).append(caminho.replace("/", "//")).append("//").append(file).toString());
		arq.delete();
	}
	
	public boolean verifyIfFolderExist(String nomePasta,String nomeSubPasta){
		boolean res = false;
		StringBuilder sb = new StringBuilder();
		File arq = new File(sb.append(FileSaver.SERVER_PATH).append(nomePasta).append("/").append(nomeSubPasta).toString());
		if(arq.exists()){
			res = true;
		}
		return res;
	}
	
	public  String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); 
            }
        }
        return null;
    }
	
	public boolean getFileAndSendSmb(Part archive, String path, String userAndPass) throws IOException {
		InputStream in = archive.getInputStream();
		boolean res = false;
		try {
			
			SmbFile sf = new SmbFile(new StringBuilder().append(path).append(getFilename(archive)).toString(), new NtlmPasswordAuthentication(userAndPass));
			
			if(sf.exists() == false){
				SmbFileOutputStream sfos = new SmbFileOutputStream(sf);
				int read = 0;
				byte[] bytes = new byte[52428800];
				
				while ((read = in.read(bytes)) != -1) {
					sfos.write(bytes, 0, read);
				}
				sfos.flush();
				sfos.close();
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Nulo");
		}finally {
			in.close();
			archive = null;
			
		}
		return res;
	}
}
