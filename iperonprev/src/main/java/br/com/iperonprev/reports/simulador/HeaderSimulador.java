package br.com.iperonprev.reports.simulador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.LocalDate;

import br.com.iperonprev.constantes.FundoPrevidenciarioEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class HeaderSimulador implements JasperReportBuiderInterface {

	private PessoasFuncionais obj;

	public HeaderSimulador() {
	}
	
	public HeaderSimulador(PessoasFuncionais obj) {
		this.obj = obj;
	}
	
	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("nome", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("stiuacaoPrev", "string"));
		field.add(new Field("dtNascimento", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("vinculo", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("regime", "string"));
		field.add(new Field("plano", "string"));
		field.add(new Field("professor", "string"));
		field.add(new Field("policial", "string"));
		field.add(new Field("situacaoFunc", "string"));
		field.add(new Field("dtEnte", "string"));
		field.add(new Field("dtCarreira", "string"));
		field.add(new Field("dtPosse", "string"));
		field.add(new Field("cargoEspecial", "string"));
		field.add(new Field("licencaNaoGozada", "string"));
		field.add(new Field("licencaEmDobro", "string"));
		field.add(new Field("dataCompulsoria", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource ds = new DRDataSource("nome", "cpf", "matricula", "situacaoPrev", "dtNascimento", "sexo",
				"vinculo", "orgao", "regime", "plano", "professor", "policial", "situacaoFunc", "dtEnte", "dtCarreira",
				"dtPosse", "cargoEspecial", "licencaNaoGozada", "licencaEmDobro", "dataCompulsoria");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String policialCivil = "Não";
		String professor = "Não";
		String cargoEspecial = "Não";
		if (cargoEspecial.equals("1")) {
			cargoEspecial = "Sim";
		}

		if (StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(), "professor")) {
			professor = "Sim";
		}

		if (StringUtils.containsIgnoreCase(obj.getNUMR_idDoObjetoCargo().getDESC_nome(), "policia")) {
			policialCivil = "Sim";
		}
		String fundoPrevidenciario = new String();

		FuncionaisFuncoes sp = new FuncionaisFuncoes();
		try {
			if (obj.getDATA_efetivoExercicio().before(sdf.parse("01/01/2010"))) {
				fundoPrevidenciario = FundoPrevidenciarioEnum.FINANCEIRO.toString();
			} else {
				fundoPrevidenciario = FundoPrevidenciarioEnum.CAPITALIZADO.toString();
			}
			sp = new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).buscaObjetoRelacionamento(
					"FuncionaisFuncoes", "NUMR_idPessoas", obj.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
		} catch (Exception e) {
			Message.addErrorMessage("Informação funcional incompleta!");
		}
		
		ds.add(obj.getNUMR_idDoObjetoPessoas().getDESC_nome(),
				obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(), 
				obj.getDESC_matricula(),
				obj.getNUMR_situacaoPrevidenciaria().getDESC_descricao(),
				sdf.format(obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento()),
				obj.getNUMR_idDoObjetoPessoas().getDESC_sexo().getNome().toString(),
				obj.getNUMR_vinculoPrevidenciario().getDESC_descricao(),
				obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
				"Regime Próprio dos Servidores Públicos", 
				fundoPrevidenciario, 
				professor, 
				policialCivil,
				obj.getNUMR_situacaoFuncional().getDESC_nome(), 
				sdf.format(sp.getDATA_ingressoEnte()),
				sdf.format(obj.getDATA_efetivoExercicio()), 
				sdf.format(obj.getDATA_efetivoExercicio()), 
				cargoEspecial,
				"0",
				"0",
				sdf.format(new LocalDate(obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento()).plusYears(70).toDate()));
		
		return ds;
	}

	@Override
	public String templateDesigner() {
		return "simulacaoAposentadoriaTemplate";
	}

}
