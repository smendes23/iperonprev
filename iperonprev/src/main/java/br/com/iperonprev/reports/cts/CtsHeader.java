package br.com.iperonprev.reports.cts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.CertidaoTempoServico;
import br.com.iperonprev.models.Enderecos;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class CtsHeader implements JasperReportBuiderInterface{

	private CertidaoTempoServico cts = new CertidaoTempoServico();

	public CtsHeader() {
	}
	public CtsHeader(CertidaoTempoServico cts) {
		this.cts = cts;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("matricula", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("dataNascimento", "string"));
		field.add(new Field("cargo", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("numero", "string"));
		field.add(new Field("orgaoExpedidor", "string"));
		field.add(new Field("cnpj", "string"));
		field.add(new Field("endereco", "string"));
		field.add(new Field("rg", "string"));
		field.add(new Field("lotacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("matricula","nome","cpf","sexo","dataNascimento","cargo","orgao",
				"numero","orgaoExpedidor","cnpj","endereco","rg","lotacao");
		PessoasFuncionais pf = this.cts.getREL_funcional();
		Enderecos endereco = pf.getNUMR_idDoObjetoPessoas().getNUMR_idDoObjetoEndereco();
		dataSource.add(
				pf.getDESC_matricula(),
				pf.getNUMR_idDoObjetoPessoas().getDESC_nome(),
				pf.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
				pf.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome(),
				sdf.format(pf.getNUMR_idDoObjetoPessoas().getDATA_nascimento()),
				pf.getNUMR_idDoObjetoCargo().getDESC_nome(),
				pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
				this.cts.getDESC_numeroCertidao(),
				this.cts.getREL_orgaoExpedidor().getDESC_nome(),
				pf.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_cnpj(),
				new StringBuilder().append(endereco.getNUMR_tipoLogradouro().getDESC_nome()).append(": ").append(endereco.getDESC_logradouro()).append(", ")
				.append(endereco.getDESC_numero()).append(", Bairro: ").append(endereco.getDESC_bairro()).append(", cep: ").append(endereco.getNUMR_cep()).toString(),
				pf.getNUMR_idDoObjetoPessoas().getNUMR_identidade(),
				this.cts.getDESC_lotacao());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "cts_header";
	}

}
