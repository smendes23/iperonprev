package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.controller.dto.AtoConcessorioDto;
import br.com.iperonprev.dao.AtoConcessorioDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PoderPessoasDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.AposentadoriaCompulsoria;
import br.com.iperonprev.models.AposentadoriaIdade;
import br.com.iperonprev.models.AposentadoriaIdadeTempo;
import br.com.iperonprev.models.AposentadoriaPorInvalidez;
import br.com.iperonprev.models.AtosConcessorios;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.PoderPessoas;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.services.atoConcessorio.QualificaAto;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@Named
@ViewScoped
public class AtoConcessorioBean implements Serializable, GenericBean<AtosConcessorios> {

	private static final long serialVersionUID = 1L;
	PessoasFuncionais pf = new PessoasFuncionais();
	List<PessoasFuncionais> listaFuncionais = new ArrayList<PessoasFuncionais>();
	private String cpfServidor;
	Pessoas pessoas = new Pessoas();
	private TipoBeneficioEnum beneficioEnum;
	AtosConcessorios ato = new AtosConcessorios();
	private boolean disponivel = false;
	
	

	public boolean isDisponivel() {
		return disponivel;
	}

	public AtosConcessorios getAto() {
		return ato;
	}

	public void setAto(AtosConcessorios ato) {
		this.ato = ato;
	}

	
	public TipoBeneficioEnum getBeneficioEnum() {
		return beneficioEnum;
	}

	public void setBeneficioEnum(TipoBeneficioEnum beneficioEnum) {
		this.beneficioEnum = beneficioEnum;
	}

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public PessoasFuncionais getPf() {
		return pf;
	}

	public void setPf(PessoasFuncionais pf) {
		this.pf = pf;
	}

	public String getCpfServidor() {
		return cpfServidor;
	}

	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}


	public List<PessoasFuncionais> getListaFuncionais() {
		return listaFuncionais;
	}

	@Override
	public void salvarObjeto() {
		try {
			if (this.pf.getNUMR_idDoObjetoCargo().getNUMG_idDoObjeto() > 0) {
				this.ato.setREL_pessoasFuncionais(pf);
				if(atualizaFuncional(this.pf) == true){
					new GenericPersistence<AtosConcessorios>(AtosConcessorios.class).salvar(ato);
					this.disponivel = true;
					Message.addSuccessMessage("Ato concessório salvo com sucesso");
				}
			} else {
				Message.addErrorMessage("Órgão e cargo do servidor são obrigatórios");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Erro ao salvar ato concessório");
		}
	}

	private boolean atualizaFuncional(PessoasFuncionais pf) {
		switch (pf.getENUM_tipoAposentadoria()) {
		case COMPULSORIA:
			AposentadoriaCompulsoria compulsoria = new GenericPersistence<AposentadoriaCompulsoria>(AposentadoriaCompulsoria.class).buscaObjetoRelacionamento("AposentadoriaCompulsoria", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
			compulsoria.setDATA_publicacao(ato.getDATA_publicacao());
			compulsoria.setNUMR_atoConcessorio(ato.getNUMR_ato());
			new GenericPersistence<AposentadoriaCompulsoria>(AposentadoriaCompulsoria.class).salvar(compulsoria);
			return true;
		case IDADE:
			AposentadoriaIdade idade = new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class).buscaObjetoRelacionamento("AposentadoriaIdade", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
			idade.setDATA_publicacao(ato.getDATA_publicacao());
			idade.setNUMR_atoConcessorio(ato.getNUMR_ato());
			new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class).salvar(idade);
			return true;
		case INVALIDEZ:
			AposentadoriaPorInvalidez invalidez = new GenericPersistence<AposentadoriaPorInvalidez>(AposentadoriaPorInvalidez.class).buscaObjetoRelacionamento("AposentadoriaPorInvalidez", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
			invalidez.setDATA_publicacao(ato.getDATA_publicacao());
			invalidez.setNUMR_atoConcessorio(ato.getNUMR_ato());
			new GenericPersistence<AposentadoriaPorInvalidez>(AposentadoriaPorInvalidez.class).salvar(invalidez);
			return true;
		case IDADETEMPO:
			AposentadoriaIdadeTempo it = new GenericPersistence<AposentadoriaIdadeTempo>(AposentadoriaIdadeTempo.class).buscaObjetoRelacionamento("AposentadoriaIdadeTempo", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
			it.setDATA_publicacao(ato.getDATA_publicacao());
			it.setNUMR_atoConcessorio(ato.getNUMR_ato());
			new GenericPersistence<AposentadoriaIdadeTempo>(AposentadoriaIdadeTempo.class).salvar(it);
			return true;
		case PENSAO:

			break;
		case RESERVA:

			break;
		case REFORMA:

			break;
		default:
		return false;
		}
		return false;
	}

	@Override
	public void novoObjeto() {
		listaFuncionais = new ArrayList<>();
		this.pessoas = new Pessoas();
		this.pf = new PessoasFuncionais();
		this.ato = new AtosConcessorios();
		this.beneficioEnum = null;
	}

	@Override
	public List<AtosConcessorios> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pegaInstanciaDialogo(AtosConcessorios obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub

	}

	public List<TipoBeneficioEnum> getListaDeBeneficio() {
		return Arrays.asList(TipoBeneficioEnum.values());
	}

	public void buscaServidor() {
		try {
			this.pessoas = new PessoasDao().devolvePessoa(cpfServidor);
			listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", this.pessoas.getNUMG_idDoObjeto());
			this.pf = new PessoasFuncionais();
			this.ato = new AtosConcessorios();
			this.cpfServidor = new String();
		} catch (NullPointerException e) {
			Message.addErrorMessage("Cpf inválido!");
		}

	}

	public void buscaFuncional(ValueChangeEvent event){
		try{
			this.pf = (PessoasFuncionais)event.getNewValue();
			if (pf.getNUMG_idDoObjeto() > 0  && pf.getENUM_tipoAposentadoria() != null) {
					if (new AtoConcessorioDao().devolveAto(pf.getNUMG_idDoObjeto()).getNUMG_idDoObjeto() > 0) {
						this.ato = new AtoConcessorioDao().devolveAto(pf.getNUMG_idDoObjeto());
						this.disponivel = true;
					}	
				}else {
					this.ato = new AtosConcessorios();
					this.disponivel = false;
					Message.addWarningMessage("Benefício indisponível para este funcional");
				}
			
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao selecionar funcional.");
		}
	}
	
	public void geraAto() throws IOException {
		System.out.println("Iniciou relatorio");
		Reports report = new Reports();
		report.createReport(Templates.reportTemplate, "atoConcessorio", new ArrayList<Column>(), fields(),
				dataSourceAtoConcessorio());
	}

	private List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("tituloRelatorio", "string"));
		field.add(new Field("cabecalho", "string"));
		field.add(new Field("texto1", "string"));
		field.add(new Field("texto2", "string"));
		field.add(new Field("texto3", "string"));
		field.add(new Field("chefePoder", "string"));
		field.add(new Field("poder", "string"));
		field.add(new Field("chefeIperon", "string"));
		field.add(new Field("numeroProcesso", "string"));
		return field;
	}

	private JRDataSource dataSourceAtoConcessorio() {

		DRDataSource ds = new DRDataSource("tituloRelatorio", "cabecalho", "texto1", "texto2", "texto3", "chefePoder",
				"poder", "chefeIperon","numeroProcesso");
		Orgaos orgaoChefePoder = this.ato.getREL_pessoasFuncionais().getNUMR_idDoObjetoCargo().getNUMR_idDoObjetoOrgaos();
		System.out.println("ID ORGAO: "+orgaoChefePoder.getNUMG_idDoObjeto());
		PoderPessoas chefePoder = new PoderPessoasDao().devolvePessoaPoder(ato.getDATA_publicacao(), orgaoChefePoder.getNUMG_idDoObjeto());
		if(orgaoChefePoder.getNUMG_idDoObjeto() == 32){
			chefePoder = new PoderPessoasDao().devolvePessoaPoder(ato.getDATA_publicacao(), 3564);
		}

		PoderPessoas presidenteIperon = new PoderPessoasDao().devolvePessoaPoder(ato.getDATA_publicacao(), 32);
		AtoConcessorioDto dto = new QualificaAto().executa(ato, chefePoder, presidenteIperon);

		ds.add(
				dto.getTituloRelatorio(), 
				dto.getCabecalho(), 
				dto.getTexto1(), 
				dto.getTexto2(), 
				dto.getTexto3(),
				dto.getChefePoder(), 
				dto.getPoder(), 
				dto.getChefeIperon(),
				dto.getNumeroProcesso());
		/*
		 * AposentadoriaPorInvalidez iv = new
		 * GenericPersistence<AposentadoriaPorInvalidez>(
		 * AposentadoriaPorInvalidez.class).buscaObjetoRelacionamento(
		 * "AposentadoriaPorInvalidez", "NUMR_idDoObejtoPessoasFuncionais",
		 * this.funcionais.getNUMG_idDoObjeto());
		 * 
		 * List<AtosLegais> lista = iv.getREL_atoLegais(); StringBuilder
		 * atoLegal = new StringBuilder(); for (int i = 0; i < lista.size();
		 * i++) { atoLegal.append(lista.get(i).getDESC_tituloEmenta()); if(i !=
		 * lista.size()-1){ atoLegal.append(";"); }else{ atoLegal.append("."); }
		 * }
		 * 
		 * ds.add( new
		 * StringBuilder().append("ATO CONCESSÓRIO DE APOSENTADORIA Nº ")
		 * .append(this.ato.getNUMR_ato()).append("/IPERON/GOV-RO, DE ").append(
		 * sdf.format(this.ato.getDATA_publicacao())).append(".").toString(),
		 * new StringBuilder().
		 * append("O GOVERNO DO ESTADO DE RONDONIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA DOS SERVIDORES "
		 * )
		 * .append("PÚBLICOS DO ESTADO DE RONDÔNIA-IPERON no uso das atribuições que lhes conferem as Leis Complementares nº 228/2000,"
		 * )
		 * .append("publicada no DOE nº 4422, de 31.01.2000 e 432/2008, publicada no DOE nº 0955, de 13.03.2008, conforme processo nº "
		 * ).append(iv.getNUMR_processo()).append(".").toString(), new
		 * StringBuilder().
		 * append("1 - Conceder aposentadoria voluntária por Invalidez o(a) servidor(a) "
		 * ).append(this.funcionais.getNUMR_idDoObjetoPessoas().getDESC_nome())
		 * .append(", ocupante do cargo de ").append(this.funcionais.
		 * getNUMR_idDoObjetoCargo().getDESC_nome()).append(", matrícula nº ")
		 * .append(this.funcionais.getDESC_matricula()).
		 * append(", com carga horária de ").append(this.funcionais.
		 * getNUMR_idDoObjetoCargo().getNUMR_cargaHoraria())
		 * .append(" horas semanais, pertencente ao quado de pessoal do Governo do Estado de Rondônia, com fulcro no "
		 * ).append(atoLegal.toString()).toString(), new StringBuilder().
		 * append("2 - Os reajustes serão revistos na mesma data e proporção, sempre que se modificar a remuneração dos servidores em atividade."
		 * ).toString(), new StringBuilder().
		 * append("3 - Este ato entra em vigor na data de sua publicação.").
		 * toString(), "CONFÚCIO AIRES MOURA",
		 * "Governador do Estado de Rondônia",
		 * "MARIA REJANE S. DOS SANTOS VIEIRA" );
		 */

		return ds;
	}

}

/*
 * DRDataSource ds = new
 * DRDataSource("tituloRelatorio","cabecalho","texto1","texto2","texto3",
 * "chefePoder","poder","chefeIperon");
 * 
 * AtoConcessorioDto dto = new QualificaAto().executa(ato);
 * 
 * ds.add( dto.getTituloRelatorio(), dto.getCabecalho(), dto.getTexto1(),
 * dto.getTexto2(), dto.getTexto3(), dto.getChefePoder(), dto.getPoder(),
 * dto.getChefeIperon() );
 */

/*
 * public String redirecionaAto(){ try{ QualificaAto qualifica = new
 * QualificaAto(); String res = qualifica.executa(pf, beneficioEnum); if(res !=
 * null){ if(res.equals("sem")){
 * Message.addErrorMessage("Aposentadoria não iniciada!"); }else{ novoObjeto();
 * return res; } }else{
 * Message.addErrorMessage("Regra inválida para este Servidor"); } this.pessoas
 * = this.pf.getNUMR_idDoObjetoPessoas(); }catch(Exception e){
 * Message.addErrorMessage("Não foi possível criar o Ato Concessório."); }
 * return null; }
 */
