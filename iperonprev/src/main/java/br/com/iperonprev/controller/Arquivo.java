package br.com.iperonprev.controller;

import java.io.File;

import javax.faces.bean.ManagedBean;

import org.primefaces.model.UploadedFile;

@ManagedBean
public class Arquivo {
	
	private static String dir = "/home/saulo/workspace/iperonprev/src/main/webapp/arquivos/";
	
	private UploadedFile file;

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	public void upload(){
		File file = new File(dir,"197375");
		if(file.exists()){
			System.out.println("Diretorio existente");
		}else{
			file.mkdirs();
		}
	}
	
	public static void main(String[] args) {
//		new SisobiDao().importa("OBI200803.TXT");
	}
}
