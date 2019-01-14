package br.com.iperonprev.util.jsf;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;



public class ReportGenerator {


	public void geraReport(String nomeDoArquivo,HashMap<String, Object> parametroMap) throws  JRException, SQLException, IOException, ClassNotFoundException{
		StringBuilder sb = new StringBuilder();
		FacesContext context = FacesContext.getCurrentInstance();
		
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();
		
		String caminho = context.getExternalContext().getRealPath(sb.append("/relatorios/")
					.append(nomeDoArquivo).append(".jasper").toString());
		ServletOutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline;filename=relatorio.pdf");
			JasperPrint impressao = JasperFillManager.fillReport(caminho,parametroMap);
			JasperExportManager.exportReportToPdfStream(impressao, responseStream);
			responseStream.flush();

			responseStream.close();

			context.renderResponse();

			context.responseComplete();
				
			
	
	}
	


}