package br.com.iperonprev.services.contribuicao;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.models.Indice;
import br.com.iperonprev.util.jsf.Message;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class IndicesServices {

	public List<Indice> devolveListaIndices(String nomeDoArquivo){
		List<Indice> listaIndices = new ArrayList<>();
		try{
			List<String> competencia = new IndicesServices().pegaValorCelula(0, nomeDoArquivo);
			List<String> indices = pegaValorCelula(1,nomeDoArquivo);
		
			for (int i = 0; i < competencia.size(); i++) {
				Indice in = new Indice();
				in.setNUMR_mesAno(competencia.get(i).replace("/", ""));
				in.setVALR_indice(new BigDecimal(indices.get(i).toString()));
				listaIndices.add(in);
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível devolver a lista de Indices.");
		}
		return listaIndices;
	}
	private List<String> pegaValorCelula(Integer num,String arquivo) throws BiffException, IOException{
		List<String> lista = new ArrayList<>();
		Workbook w = Workbook.getWorkbook(new File("indices/Janeiro.xls").getAbsoluteFile());
		System.out.println("indices/"+arquivo);
		Sheet s = w.getSheet(0);
		int linhas = s.getRows();
		
		for (int i = 0; i < linhas; i++) {
			Cell cel = s.getCell(num,i);
			String valor = cel.getContents();
			lista.add(valor);
		}
		w.close();
		return lista;
	}
}
