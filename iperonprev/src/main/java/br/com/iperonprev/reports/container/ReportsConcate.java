package br.com.iperonprev.reports.container;


import static net.sf.dynamicreports.report.builder.DynamicReports.concatenatedReport;

import java.io.IOException;
import java.io.OutputStream;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

public class ReportsConcate {
	
	public void generateReportsWithTwoBuilder(JasperReportBuilder createReport1, JasperReportBuilder createReport2 ) throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		OutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
		try{
			concatenatedReport()
				.concatenate(createReport1,createReport2)
				.toPdf(responseStream);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();
	}
	
	public void generateReportsWithTreeBuilder(JasperReportBuilder createReport1, JasperReportBuilder createReport2, JasperReportBuilder createReport3 ) throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		OutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
		try{
			concatenatedReport()
				.concatenate(createReport1,createReport2,createReport3)
				.toPdf(responseStream);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();
	}
	
	public void generateReportsWithFourBuilder(JasperReportBuilder createReport1, JasperReportBuilder createReport2, JasperReportBuilder createReport3,JasperReportBuilder createReport4 ) throws IOException{
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		OutputStream responseStream = response.getOutputStream();
		response.setContentType("application/pdf");
		try{
			concatenatedReport()
				.concatenate(createReport1,createReport2,createReport3,createReport4)
				.toPdf(responseStream);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();
	}

}
