package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.iperonprev.controller.dto.TemposDeduzidosDto;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.AfastamentosLicenca;
import br.com.iperonprev.models.Deducao;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;

public class TempoDeduzidoSimuladorService {

	private List<PessoasFuncionais> listaFuncionais;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	List<TemposDeduzidosDto> listaTempoDeduzido = new ArrayList<>();
	private int totalDias = 0;
	public TempoDeduzidoSimuladorService(){}
	
	public TempoDeduzidoSimuladorService(Pessoas pessoa){
		try{
			listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(pessoa.getNUMR_cpf());
		}catch(Exception e){
			System.out.println("Erro ao carregar funcionais!");
		}
	}
	
	public List<TemposDeduzidosDto> devolveListaTemposDeduzidos(){
		try{
			for (PessoasFuncionais pf : listaFuncionais) {
				
				DeducaoUtil deducaoUtil = new DeducaoUtil(pf);
				
				if(!deducaoUtil.getListaDeAfastamentos().isEmpty()){
					for (AfastamentosLicenca licenca : deducaoUtil.getListaDeAfastamentos()) {
						Days days = Days.daysBetween(new LocalDate(licenca.getDATA_inicioLicenca()),new LocalDate(licenca.getDATA_fimLicenca()));
						TemposDeduzidosDto td = new TemposDeduzidosDto();
						td.setTipoDeducao(licenca.getNUMR_tipoLicenca().getDESC_nome());
						td.setPeriodo(new StringBuilder().append(sdf.format(licenca.getDATA_inicioLicenca())).append(" a ").append(sdf.format(licenca.getDATA_fimLicenca())).toString());
						td.setQuantidade(days.getDays());
						listaTempoDeduzido.add(td);
						totalDias+=days.getDays();
					}
				}
				
				if(!deducaoUtil.getListaDeFaltas().isEmpty()){
					for(Deducao deducao : deducaoUtil.getListaDeFaltas()){
						TemposDeduzidosDto td = new TemposDeduzidosDto();
						td.setTipoDeducao(deducao.getENUM_tipoDeducao().getNome());
						td.setPeriodo(new StringBuilder().append(sdf.format(deducao.getDATA_inicio())).append(" a ").append(sdf.format(deducao.getDATA_fim())).toString());
						td.setQuantidade(deducao.getNUMR_qtdDias());
						listaTempoDeduzido.add(td);
						totalDias+= deducao.getNUMR_qtdDias();
					}
					
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listaTempoDeduzido;
	}
	
	public int devolveTotalDias(){
		return totalDias;
	}
}
