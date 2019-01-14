package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoDeducaoEnum;
import br.com.iperonprev.dao.AverbacaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.Iniciativa;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.RegimesPrevidenciarios;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

public class AverbacaoBean implements GenericBean<Averbacao>, Serializable {

	private static final long serialVersionUID = 1L;
	PessoasFuncionais pf = new PessoasFuncionais();
	Averbacao averbacao = new Averbacao();
	GenericDao<Averbacao> dao = new AverbacaoDao();
	 public AverbacaoBean() {
	 }
	 
	 public AverbacaoBean(PessoasFuncionais pf) {
		 this.pf = pf;
	}
	 public AverbacaoBean(PessoasFuncionais pf, Averbacao averbacao) {
		 this.pf = pf;
		 this.averbacao = averbacao;
	}

	
	@Override
	public void salvarObjeto() {
		try {
			averbacao.setNUMR_pessoasFuncionais(pf);
			dao.salvaObjeto(this.averbacao);
			novoObjeto();
			Message.addSuccessMessage("Averbação salva com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar Averbação!");
		}
	}

	@Override
	public void novoObjeto() {
		this.averbacao = new Averbacao();
	}

	@Override
	public List<Averbacao> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeAverbacoes");
	}

	@Override
	public void pegaInstanciaDialogo(Averbacao obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.averbacao = (Averbacao) event.getObject();
	}

	public List<RegimesPrevidenciarios> getListaRegimesPrevidenciarios() {
		return new GenericPersistence<RegimesPrevidenciarios>(RegimesPrevidenciarios.class)
				.listarTodos("RegimesPrevidenciarios");
	}

	public List<Iniciativa> getListaDeIniciativas() {
		return new GenericPersistence<Iniciativa>(Iniciativa.class).listarTodos("Iniciativa");
	}

	public List<TipoDeducaoEnum> getListaDeDeducaoes() {
		return Arrays.asList(TipoDeducaoEnum.values());
	}

	public List<Orgaos> getListaDeOrgaos() {
		return new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
	}

	public List<Averbacao> getListaDeAverbacoes() {
		System.out.println("Id Funcional"+pf.getNUMG_idDoObjeto());
		List<Averbacao> lista = new ArrayList<>();

		if (pf.getNUMG_idDoObjeto() != 0) {
			lista = dao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais", pf.getNUMG_idDoObjeto());
		}
		return lista;

	}

	/*@SuppressWarnings("static-access")
	public void imprimeAverbacao() throws IOException {
		TempoLiquidoAverbacao tla = new TempoLiquidoAverbacao();
		if(aproveitado > 0){
			try{
				if(new GenericPersistence<TempoLiquidoAverbacao>(TempoLiquidoAverbacao.class).listarRelacionamento("TempoLiquidoAverbacao","NUMR_pessoaFuncional", pf.getNUMG_idDoObjeto()).isEmpty()){
					tla.setDiasAproveitado(aproveitado);
					tla.setTempoAproveitado(RetornaTempos.retornaDiaMesAno(pf.getDATA_posse(), new LocalDate(pf.getDATA_posse()).plusDays(aproveitado).toDate()).toString());
					tla.setNUMR_pessoaFuncional(pf);
					new GenericPersistence<TempoLiquidoAverbacao>(TempoLiquidoAverbacao.class).salvar(tla);
				}else{
					tla = new GenericPersistence<TempoLiquidoAverbacao>(TempoLiquidoAverbacao.class).listarRelacionamento("TempoLiquidoAverbacao","NUMR_pessoaFuncional", pf.getNUMG_idDoObjeto()).get(0);
				}
			}catch(Exception e){
				Message.addErrorMessage("Erro ao gravar o Tempo Aproveitado!");
			}
		}
		
		
		List<Averbacao> listaAverbacao = dao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				pf.getNUMG_idDoObjeto());
		
		for (Averbacao aver1 : listaAverbacao) {
			for (Averbacao aver2 : listaAverbacao) {
				
				Averbacao a = new GenericPersistence<Averbacao>(Averbacao.class).buscarPorId(aver1.getNUMG_idDoObjeto());
				Averbacao ab = new GenericPersistence<Averbacao>(Averbacao.class).buscarPorId(aver2.getNUMG_idDoObjeto());
				new QualificaConcomitancia().executa(a, ab);
				
			}
		}
		Reports report = new Reports();
		report.createReport(Templates.reportTemplate, "averbacao", new ArrayList<Column>(), fields(),
				dataSourceMedias(this.pf, new ArrayList<Column>(), fields(), aproveitado,tla));
	}

	private List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("cargo", "string"));
		field.add(new Field("certificamos", "string"));
		field.add(new Field("cnpj", "string"));
		field.add(new Field("contribuicaoPrivada", "string"));
		field.add(new Field("contribuicaoPublica", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("dataAdmissaoEDemissao", "string"));
		field.add(new Field("dataNascimento", "string"));
		field.add(new Field("dataPosse", "string"));
		field.add(new Field("empresaOrgao", "string"));
		field.add(new Field("estadoCivil", "string"));
		field.add(new Field("funcao", "string"));
		field.add(new Field("iniciativa", "string"));
		field.add(new Field("matricula", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("numProcesso", "string"));
		field.add(new Field("orgao", "string"));
		field.add(new Field("orgaoPrevidenciario", "string"));
		field.add(new Field("outrasDeducoes", "string"));
		field.add(new Field("periodos", "string"));
		field.add(new Field("sexo", "string"));
		field.add(new Field("tempoAproveitado", "string"));
		field.add(new Field("tempoCargo", "string"));
		field.add(new Field("tempoConcomitante", "string"));
		field.add(new Field("tempoContribuicao", "string"));
		field.add(new Field("certificamos2", "string"));
		return field;
	}

	@SuppressWarnings("static-access")
	private JRDataSource dataSourceMedias(PessoasFuncionais obj, List<Column> columns, List<Field> fields,
			int tmpAproveitado,TempoLiquidoAverbacao tempoLiquidoAverbacao) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Averbacao> listaAverbacao = dao.listaRelacionamenoDoObjeto("Averbacao", "NUMR_pessoasFuncionais",
				obj.getNUMG_idDoObjeto());
				
		TemposPrevidenciarios tp = new TemposPrevidenciarios(listaAverbacao);
		DRDataSource dataSource = new DRDataSource("cargo", "certificamos", "cnpj", "contribuicaoPrivada",
				"contribuicaoPublica", "cpf", "dataAdmissaoEDemissao", "dataNascimento", "dataPosse", "empresaOrgao",
				"estadoCivil", "funcao", "iniciativa", "matricula", "nome", "numProcesso", "orgao",
				"orgaoPrevidenciario", "outrasDeducoes", "periodos", "sexo", "tempoAproveitado", "tempoCargo",
				"tempoConcomitante", "tempoContribuicao", "certificamos2");
		int qtdAverbacoes = 0;
		for (Averbacao averbacao : listaAverbacao) {
			qtdAverbacoes++;
			String certificamos = new StringBuilder().append("A pedido do requente foi aproveitado(s) ")
					.append(tp.devolveTotalDiasAproveitados()).append(" dia(s),").append("correspondente a ")
					.append(tp.retornaAnoMesDiaTempoTotalContribuicaoAverbado())
					.append("Conforme Instrução Normativa INSS/PRES N°77, artigo 439 §2° , de 21 de Janeiro de 2015.")
					.toString();
			if (tmpAproveitado > 0) {
				certificamos = new StringBuilder().append("A pedido do requente foi aproveitado(s) ")
						.append(tempoLiquidoAverbacao.getDiasAproveitado()).append(" dia(s),").append("correspondente a ")
						.append(tempoLiquidoAverbacao.getTempoAproveitado())
						.append("Conforme Instrução Normativa INSS/PRES N°77, artigo 439 §2° , de 21 de Janeiro de 2015.")
						.toString();
			}
			
			dataSource.add(
					obj.getNUMR_idDoObjetoCargo().getDESC_nome(),
					new StringBuilder().append("Certificamos que consta de Tempo de Contribuição averbado, o total de ")
							.append(tp.devolveTotalDiasAproveitados()).append(" dia(s), correspondentes a ")
							.append(tp.retornaAnoMesDiaTempoTotalContribuicaoAverbado()).toString(),
					averbacao.getNUMR_cnpj(), 
					tp.retornaAnoMesDiaTempoTotalIniciativa(2), 
					tp.retornaAnoMesDiaTempoTotalIniciativa(1),
					obj.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
					new StringBuilder().append(sdf.format(averbacao.getDATA_admissao())).append(" a ")
							.append(sdf.format(averbacao.getDATA_demissao())).toString(),
					sdf.format(obj.getNUMR_idDoObjetoPessoas().getDATA_nascimento()), 
					sdf.format(obj.getDATA_posse()),
					averbacao.getDESC_empregador(), 
					obj.getNUMR_idDoObjetoPessoas().getNUMR_estadoCivil().getDESC_nome(),
					averbacao.getDESC_funcao(), 
					averbacao.getNUMR_iniciativa().getDESC_nome(), 
					obj.getDESC_matricula(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_nome(), 
					averbacao.getNUMR_processo(),
					obj.getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos().getDESC_nome(),
					averbacao.getNUMR_orgaoPrevidenciario().getDESC_nome(), 
					tp.retornaAnoMesDiaOutrasDedudoes(),
					new StringBuilder().append(qtdAverbacoes).append(" período(s)").toString(),
					obj.getNUMR_idDoObjetoPessoas().getDESC_sexo().toString(),
					averbacao.getDESC_tempoAproveitado(),
					RetornaTempos.retornaDiaMesAno(obj.getDATA_posse(), 
					new LocalDate().now().toDate()).toString(),
					tp.retornaAnoMesDiaTempoTotalConcomitante(), 
					averbacao.getDESC_tempoContribuicao(), 
					certificamos);
		}
		return dataSource;
	}*/

}
