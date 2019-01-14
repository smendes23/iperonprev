package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.controller.dto.TempoAverbadoSimuladorDto;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.averbacao.RetornaTemposAverbacao;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class AverbacaoSimulador implements JasperReportBuiderInterface{
	

	private PessoasFuncionais pf;

	public AverbacaoSimulador() {
	}
	
	public AverbacaoSimulador(PessoasFuncionais pf) {
		this.pf = pf;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("periodo","quantidadeDias","tempoExtenso","regime",
				"atividade","totalTempoExtensoRpps","totalDiasRgps","totalDiasRpps","totalTempoExtensoRgps");
		
		List<Averbacao>  listaAverbacao = new ArrayList<>();
		
		List<TempoAverbadoSimuladorDto> listaTempoAproveitadoAverbacao = new ArrayList<>();
		
		try{
			listaAverbacao = new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento("Averbacao", "NUMR_pessoasFuncionais", pf.getNUMG_idDoObjeto());
		}catch(Exception e){
			System.out.println("Erro ao carregar averbações");
		}
		
		listaTempoAproveitadoAverbacao.add(new TempoAverbadoSimuladorDto());
		
		if(!listaAverbacao.isEmpty()){
			listaTempoAproveitadoAverbacao = new AverbacaoSimuladorService(listaAverbacao).devolveListaAverbada();
		}
		
		
		 RetornaTemposAverbacao rta = new RetornaTemposAverbacao(listaAverbacao);
		
		for (TempoAverbadoSimuladorDto averbacao : listaTempoAproveitadoAverbacao) {
			dataSource.add(
						averbacao.getPeriodo(),
						new StringBuilder().append(averbacao.getQuantidadeDias()).toString(),
						averbacao.getTempoExtenso(),
						averbacao.getRegime(),
						averbacao.getAtividade(),
						new StringBuilder().append(rta.devolveDiaMesAnoIniciativa(1)).toString(),
						new StringBuilder().append(averbacao.getTotalDiasRgps()).toString(),
						new StringBuilder().append(averbacao.getTotalDiasRpps()).toString(),
						new StringBuilder().append(rta.devolveDiaMesAnoIniciativa(2)).toString()
					);
		}
		
		return dataSource;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("periodo", "string"));
		field.add(new Field("quantidadeDias", "string"));
		field.add(new Field("tempoExtenso", "string"));
		field.add(new Field("regime", "string"));
		field.add(new Field("atividade", "string"));
		field.add(new Field("totalEmpoExtensoRpps", "string"));
		field.add(new Field("totalDiasRgps", "string"));
		field.add(new Field("totalDiasRpps", "string"));
		field.add(new Field("totalTempoExtensoRgps", "string"));
		return field;
	}

	@Override
	public String templateDesigner() {
		return "simulador_tempos_averbados";
	}
}
