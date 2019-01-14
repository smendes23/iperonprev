package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.servlet.http.Part;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.PortariaDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Indice;
import br.com.iperonprev.models.Portaria;
import br.com.iperonprev.util.jsf.Message;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;

@ManagedBean(name="portariaBean")
public class PortariaBean implements  Serializable,GenericBean<Portaria>{

	private static final long serialVersionUID = 1L;
	Portaria portaria = new Portaria();
	GenericDao<Portaria> dao = new PortariaDao();
	
	private Part file;
	
	
	public Portaria getPortaria() {
		return portaria;
	}

	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}


	@Override
	public void salvarObjeto() {
		try{
			System.out.println(file.getSize());
			this.portaria.setNUMR_indice(this.listaDeIndices(file));
			dao.salvaObjeto(this.portaria);
			novoObjeto();
			Message.addSuccessMessage("Portaria salva com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar Portaria!");
		}
		
	}

	@Override
	public void novoObjeto() {
		this.portaria = new Portaria();
	}

	@Override
	public List<Portaria> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pegaInstanciaDialogo(Portaria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	private List<Indice> listaDeIndices(Part arq){
		List<String> lista = new ArrayList<>();
		List<Indice> listaDeIndices = new ArrayList<>();
		WorkbookSettings conf = new WorkbookSettings();
		conf.setEncoding("ISO-8859-1");
		try {
			Workbook w = Workbook.getWorkbook(arq.getInputStream(),conf);
			Sheet s = w.getSheet(0);
			
			for (int i = 0; i < s.getRows(); i++) {
				Indice indice = new Indice();
				Cell cel = s.getCell(0,i);
				Cell cel2 = s.getCell(1,i);
				if(!cel.getContents().isEmpty()){
					indice.setNUMR_mesAno(cel.getContents().replace("/", ""));
					indice.setVALR_indice(new BigDecimal(cel2.getContents().replace(",", ".")));
					String valor = cel.getContents();
					lista.add(valor);
					listaDeIndices.add(indice);

				}
			}
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return listaDeIndices;
	}
	
}
