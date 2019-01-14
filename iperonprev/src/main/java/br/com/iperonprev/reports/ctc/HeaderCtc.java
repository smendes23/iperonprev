package br.com.iperonprev.reports.ctc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.CertidaoTempoContribuicao;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class HeaderCtc implements JasperReportBuiderInterface {

	private CertidaoTempoContribuicao ctc;

	public HeaderCtc() {
		// TODO Auto-generated constructor stub
	}

	public HeaderCtc(CertidaoTempoContribuicao ctc) {
		this.ctc = ctc;
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
		field.add(new Field("filiacao", "string"));
		field.add(new Field("endereco", "string"));
		field.add(new Field("rg", "string"));
		field.add(new Field("pis", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		Pessoas pessoa = new PessoasDao().devolvePessoa(this.ctc.getREL_listaDeFuncionais().get(0).getNUMR_idDoObjetoPessoas().getNUMR_cpf());
		DRDataSource dataSource = new DRDataSource("matricula", "nome", "cpf", "sexo", "dataNascimento", "cargo",
				"orgao", "numero", "orgaoExpedidor", "cnpj", "filiacao", "endereco", "rg", "pis");
		
		List<PessoasFuncionais> listaFuncional = ctc.getREL_listaDeFuncionais();
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String filiacao = new String();
		StringBuilder matricula = new StringBuilder();
		StringBuilder cargo = new StringBuilder();
		StringBuilder orgao = new StringBuilder();
		
		
		for (int i = 0; i < listaFuncional.size(); i++) {
			matricula.append(listaFuncional.get(i).getDESC_matricula());
			cargo.append(listaFuncional.get(i).getNUMR_idDoObjetoCargo().getDESC_nome());
			orgao.append(listaFuncional.get(i).getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome());
			if(i < listaFuncional.size()-1){
				matricula.append(", ");
				cargo.append(", ");
				orgao.append(", ");
			}
		}

		if (pessoa.getDESC_pai() != null && pessoa.getDESC_mae() != null) {
			filiacao = new StringBuilder().append(pessoa.getDESC_mae()).append(" e ").append(pessoa.getDESC_pai()).toString();
		} else if (pessoa.getDESC_mae() != null) {
			filiacao = new StringBuilder().append(pessoa.getDESC_mae()).toString();
		}

		dataSource.add(matricula.toString(),
				pessoa.getDESC_nome(),
				pessoa.getNUMR_cpf(),
				pessoa.getDESC_sexo().toString(),
				sdf.format(pessoa.getDATA_nascimento()),
				cargo.toString(), 
				orgao.toString(),
				sb.append(ctc.getNUMR_certidao()).append("/").append(ctc.getNUMR_ano()).toString(),
				ctc.getNUMR_orgaoExpedidor().getDESC_nome(),
				"15.849.540/0001-11", 
				filiacao,
				new StringBuilder()
						.append(pessoa.getNUMR_idDoObjetoEndereco()
								.getNUMR_tipoLogradouro().getDESC_nome())
						.append(": ")
						.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_logradouro())
						.append(" N°:")
						.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_numero())
						.append(", Bairro: ")
						.append(pessoa.getNUMR_idDoObjetoEndereco().getDESC_bairro())
						.append(", ")
						.append("Cep: ")
						.append(pessoa.getNUMR_idDoObjetoEndereco().getNUMR_cep())
						.append(", ")
						.append(pessoa.getNUMR_idDoObjetoEndereco()
								.getNUMR_idDoObjetoMunicipio().getDESC_nome())
						.append(" - ")
						.append(pessoa.getNUMR_idDoObjetoEndereco()
								.getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado().getDESC_sigla())
						.toString(),
				new StringBuilder().append(pessoa.getNUMR_identidade()).append(" ")
						.append(pessoa.getNUMR_orgaoIdentidade()).toString(),
				pessoa.getNUMR_pisPasep());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "ctc_header";
	}

}
