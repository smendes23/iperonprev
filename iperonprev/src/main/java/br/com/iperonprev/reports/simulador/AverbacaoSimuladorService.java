package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.iperonprev.controller.dto.TempoAverbadoSimuladorDto;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;

public class AverbacaoSimuladorService {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	int totalRGPS = 0;
	int totalRPPS = 0;
	
	private List<Averbacao> listaAverbacao;
	public AverbacaoSimuladorService(){}
	
	List<TempoAverbadoSimuladorDto> lista = new ArrayList<>();
	
	public AverbacaoSimuladorService(List<Averbacao> listaAverbacao){
		this.listaAverbacao = listaAverbacao;
	}
	
	public List<TempoAverbadoSimuladorDto> devolveListaAverbada(){
		devolveListaParametrizada();
		try {
			if(!lista.isEmpty()){
				for (TempoAverbadoSimuladorDto tempoAverbadoSimuladorDto : lista) {
					TempoAverbadoSimuladorDto dto = tempoAverbadoSimuladorDto;
					dto.setTotalDiasRgps(totalRGPS);
					dto.setTotalDiasRpps(totalRPPS);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao gerar lista");
		}
		
		return lista;
	}
	
	private List<TempoAverbadoSimuladorDto> devolveListaParametrizada(){
		try {
			RetornaTemposAverbacao rta = new RetornaTemposAverbacao();
			if(!listaAverbacao.isEmpty()){
				
				for (Averbacao averbacao : listaAverbacao) {
					TempoAverbadoSimuladorDto dto = new TempoAverbadoSimuladorDto();
					
					dto.setTempoExtenso(rta.devolveDiaMesAnoTempoAproveitado(averbacao));
					dto.setPeriodo(new StringBuilder().append(sdf.format(averbacao.getDATA_admissao())).append(" a ").append(sdf.format(averbacao.getDATA_demissao())).toString());
					dto.setQuantidadeDias(rta.devolveDiasAproveitados(averbacao));
					dto.setRegime(averbacao.getNUMR_regime().getDESC_nome());
					dto.setAtividade(averbacao.getDESC_funcao());
					if(averbacao.getNUMR_regime().getNUMG_idDoObjeto() == 1){
						totalRGPS += dto.getQuantidadeDias();
					}else{
						totalRPPS += dto.getQuantidadeDias();
					}
					
					lista.add(dto);
				}
			}
		} catch (Exception e) {
			System.out.println("Erro ao popular averbação dto");
		}
		return lista;
	}
	
	
	
	
}
