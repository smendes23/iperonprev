package br.com.iperonprev.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.contribuicao.QualificaCalculoContribuicao;

public class App {
	static PessoasFuncionais anterior = new PessoasFuncionais();
	public static void main(String[] args) throws ParseException, IOException {
		BigDecimal bd = 
				new BigDecimal("983.24")
				.multiply(new BigDecimal("0.11"))
				.setScale(2, RoundingMode.FLOOR);
		System.out.println(new App().formatDecimal(bd));
		
		
		
		
		/*String filename = "C:\\Users\\iperon\\Documents\\Relatorio_Recadastramento_Instituidores_Pensao2017.xls";
		List<PessoasFuncionais> lista = new PessoasFuncionaisDao().listaDeFuncionaisRecadastrados();
		 HSSFWorkbook workbook=new HSSFWorkbook();
         HSSFSheet sheet =  workbook.createSheet("Pensionista 2017"); 
         
         HSSFRow rowhead=   sheet.createRow((short)0);
         rowhead.createCell(0).setCellValue("CPF");
         rowhead.createCell(1).setCellValue("Nome");
         rowhead.createCell(2).setCellValue("Matrícula");
         rowhead.createCell(3).setCellValue("Data Benefício");
         
         for (int i = 0; i < lista.size(); i++) {
        	 HSSFRow row=   sheet.createRow((short)(i+1));
        	 row.createCell(0).setCellValue(lista.get(i).getNUMR_idDoObjetoPessoas().getNUMR_cpf());
             row.createCell(1).setCellValue(lista.get(i).getNUMR_idDoObjetoPessoas().getDESC_nome().replaceAll(" ", ""));
             row.createCell(2).setCellValue(lista.get(i).getDESC_matricula());
             row.createCell(3).setCellValue(new SimpleDateFormat("dd/MM/yyyy").format(lista.get(i).getDATA_efetivoExercicio()));
		}
         
         FileOutputStream fileOut =  new FileOutputStream(filename);
         workbook.write(fileOut);
         fileOut.close();
         System.out.println("Seu arquivo excel foi gerado!");*/
		
	}
	
	
	public String formatDecimal(BigDecimal valor) {
		String res = new String();
		res = valor.toString().replace(".", "_").replace(",", "-");
		res = res.replace("_", ",").replace("-", ".");
		return res;
	}
	
	
}
