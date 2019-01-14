package br.com.iperonprev.reports.funcional;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class HeaderTitleFuncionalReport implements JasperReportBuiderInterface{

	

	private PessoasFuncionais funcional;
	private String template;

	public HeaderTitleFuncionalReport() {
		// TODO Auto-generated constructor stub
	}
	
	
	public HeaderTitleFuncionalReport(PessoasFuncionais funcional,String template) {
		this.funcional = funcional;
		this.template = template;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("mat", "string"));
		field.add(new Field("serv", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("dataNascimento", "date"));
		field.add(new Field("estadoCivil", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("dataAdmissao", "date"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("mat", "serv", "cpf", "sexo", "dataNascimento", "estadoCivil",
				"cargo", "dataAdmissao", "orgao", "tituloRelatorio");
		dataSource.add(funcional.getDESC_matricula(),
				funcional.getNUMR_idDoObjetoPessoas().getDESC_nome(),
				funcional.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
				funcional.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome().toString(),
				funcional.getNUMR_idDoObjetoPessoas().getDATA_nascimento(),
				funcional.getNUMR_idDoObjetoPessoas().getNUMR_estadoCivil().getDESC_nome(),
				funcional.getNUMR_idDoObjetoCargo().getDESC_nome(), 
				funcional.getDATA_efetivoExercicio(),
				funcional.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
				"Ficha Funcional"); 
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return template;
	}

}
