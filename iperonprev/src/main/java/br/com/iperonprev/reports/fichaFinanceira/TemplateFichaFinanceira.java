package br.com.iperonprev.reports.fichaFinanceira;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class TemplateFichaFinanceira implements JasperReportBuiderInterface {

	private PessoasFuncionais pf;
	private String anoFichaFinanceira;

	public TemplateFichaFinanceira(PessoasFuncionais pf, String anoFichaFinanceira) {
		this.pf = pf;
		this.anoFichaFinanceira = anoFichaFinanceira;
	}
	
	public TemplateFichaFinanceira() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("nome", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("nascimento", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("estadoCivil", "string"));
		field.add(new Field("pis", "string"));
		field.add(new Field("grau", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("dataAdmissao", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("municipio", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource ds = new DRDataSource("nome","cpf","nascimento","sexo","estadoCivil","pis","grau","matricula","dataAdmissao","cargo",
				"orgao","municipio","tituloRelatorio");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Pessoas pessoa = this.pf.getNUMR_idDoObjetoPessoas();
		ds.add(pessoa.getDESC_nome(),
				pessoa.getNUMR_cpf(),
				sdf.format(pessoa.getDATA_nascimento()),
				pessoa.getDESC_sexo().getNome(),
				pessoa.getNUMR_estadoCivil().getDESC_nome(),
				pessoa.getNUMR_pisPasep(),
				pessoa.getNUMR_instrucao().getDESC_nome(),
				this.pf.getDESC_matricula(),
				sdf.format(this.pf.getDATA_efetivoExercicio()),
				this.pf.getNUMR_idDoObjetoCargo().getDESC_nome(),
				this.pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
				pessoa.getNUMR_idDoObjetoEndereco().getNUMR_idDoObjetoMunicipio().getDESC_nome(),
				new StringBuilder().append("Ficha Financeira Individual ").append(anoFichaFinanceira).toString());
		return ds;
	}

	@Override
	public String templateDesigner() {
		return "ff_template";
	}

}
