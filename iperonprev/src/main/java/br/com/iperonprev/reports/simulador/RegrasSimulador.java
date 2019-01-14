package br.com.iperonprev.reports.simulador;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.controller.dto.Simulador;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.beneficio.QualificaRegraDeAposentadoria;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class RegrasSimulador implements JasperReportBuiderInterface{
	
	PessoasFuncionais pf = new PessoasFuncionais();

	public RegrasSimulador() {
	}
	
	public RegrasSimulador(PessoasFuncionais pf) {
		this.pf = pf;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("regraAposentadoria", "tipoProventos", "descricaoRegra",
				"requisito1", "requisito2", "requisito3", "requisito4", "requisito5", "requerido1", "requerido2",
				"requerido3", "requerido4", "requerido5", "faltante1", "faltante2", "faltante3", "faltante4",
				"faltante5", "status");
		List<Simulador> lista = new QualificaRegraDeAposentadoria().devolveRegrasDeAposentadoria(pf);
		
		for (Simulador simulador : lista) {
			dataSource.add(
						simulador.getRegraAposentadoria(),
						simulador.getTipoProventos(),
						simulador.getDescricaoRegra(),
						simulador.getRequisito1(),
						simulador.getRequisito2(),
						simulador.getRequisito3(),
						simulador.getRequisito4(),
						simulador.getRequisito5(),
						simulador.getRequerido1(),
						simulador.getRequerido2(),
						simulador.getRequerido3(),
						simulador.getRequerido4(),
						simulador.getRequerido5(),
						simulador.getFaltante1(),
						simulador.getFaltante2(),
						simulador.getFaltante3(),
						simulador.getFaltante4(),
						simulador.getFaltante5(),
						simulador.getStatus()
					);
		}
		
		return dataSource;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("regraAposentadoria", "string"));
		field.add(new Field("tipoProventos", "string"));
		field.add(new Field("descricaoRegra", "string"));
		field.add(new Field("requisito1", "string"));
		field.add(new Field("requisito2", "string"));
		field.add(new Field("requisito3", "string"));
		field.add(new Field("requisito4", "string"));
		field.add(new Field("requisito5", "string"));
		field.add(new Field("requerido1", "string"));
		field.add(new Field("requerido2", "string"));
		field.add(new Field("requerido3", "string"));
		field.add(new Field("requerido4", "string"));
		field.add(new Field("requerido5", "string"));
		field.add(new Field("faltante1", "string"));
		field.add(new Field("faltante2", "string"));
		field.add(new Field("faltante3", "string"));
		field.add(new Field("faltante4", "string"));
		field.add(new Field("faltante5", "string"));
		field.add(new Field("status", "string"));
		return field;
	}

	@Override
	public String templateDesigner() {
		return "simuladorDetail";
	}
}
