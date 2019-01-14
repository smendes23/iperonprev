package br.com.iperonprev.controller.arrecadacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;

import br.com.iperonprev.constantes.TipoOnusEnum;
import br.com.iperonprev.dao.FinanceiroDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.OrgaosDao;
import br.com.iperonprev.models.AfastamentosDisposicao;
import br.com.iperonprev.models.Financeiro;
import br.com.iperonprev.models.FundoPrevidenciario;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.RetornaTempos;

@Named
@ViewScoped
public class ArrecadacaoPjBean implements Serializable{

	private static final long serialVersionUID = 1L;
	AfastamentosDisposicao disposicao = new AfastamentosDisposicao();
	Orgaos orgao = new Orgaos();
	PessoasFuncionais funcionais = new PessoasFuncionais();
	List<AfastamentosDisposicao> listaAfastamento = new ArrayList<>();
	List<PessoasFuncionais> listaFuncionais = new ArrayList<PessoasFuncionais>();
	Financeiro financeiro = new Financeiro();
	FundoPrevidenciario receita = new FundoPrevidenciario();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public FundoPrevidenciario getReceita() {
		return receita;
	}

	public void setReceita(FundoPrevidenciario receita) {
		this.receita = receita;
	}

	private String competencia;
	private String cnpj;
	
	
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCompetencia() {
		return competencia;
	}

	public void setCompetencia(String competencia) {
		this.competencia = competencia;
	}

	public Financeiro getFinanceiro() {
		return financeiro;
	}

	public void setFinanceiro(Financeiro financeiro) {
		this.financeiro = financeiro;
	}

	public List<PessoasFuncionais> getListaFuncionais() {
		return listaFuncionais;
	}

	public PessoasFuncionais getFuncionais() {
		return funcionais;
	}

	public void setFuncionais(PessoasFuncionais funcionais) {
		this.funcionais = funcionais;
	}

	
	public AfastamentosDisposicao getDisposicao() {
		return disposicao;
	}

	public void setDisposicao(AfastamentosDisposicao disposicao) {
		this.disposicao = disposicao;
	}

	public Orgaos getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgaos orgao) {
		this.orgao = orgao;
	}

	public List<AfastamentosDisposicao> getListaAfastamento() {
		return listaAfastamento;
	}
	
	public void buscaOrgaoEServidores(){
		try{
			novoObjeto();
			
			this.orgao = new OrgaosDao().devolveOrgao(cnpj);
			
			this.listaAfastamento = new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).listarTodos("AfastamentosDisposicao");
			this.listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoOrgao", this.orgao.getNUMG_idDoObjeto());
			
			if(!listaFuncionais.isEmpty()){
				
				for (int i = 0; i < listaFuncionais.size(); i++) {
					
					for (int j = 0; j < listaAfastamento.size(); j++) {
						
						if(listaFuncionais.get(i).getNUMG_idDoObjeto() == listaAfastamento.get(j).getNUMR_idDoObjetoPessoasFuncionais().getNUMG_idDoObjeto()
								&& listaAfastamento.get(j).getENUM_tipoOnus() == TipoOnusEnum.DESTINO){
							listaFuncionais.remove(i);
							
						}
						
					}
				}
			}else{
				for (int i = 0; i < listaAfastamento.size(); i++) {
					if(listaAfastamento.get(i).getENUM_tipoOnus() != TipoOnusEnum.ORIGEM ){
						listaFuncionais.add(listaAfastamento.get(i).getNUMR_idDoObjetoPessoasFuncionais());
					}
				}
			}
			
			this.cnpj = new String();
		}catch(Exception e){
			Message.addWarningMessage("Não foi possível carregar os dados funcionais!");
		}
	}
	
	public void buscaOrgaoCedido(ValueChangeEvent event){
		this.funcionais = (PessoasFuncionais)event.getNewValue();
		for (AfastamentosDisposicao af : listaAfastamento) {
			if(af.getNUMR_idDoObjetoPessoasFuncionais().equals(this.funcionais)){
				this.disposicao = af;
			}
		}
	}

	public void setListaAfastamento(List<AfastamentosDisposicao> listaAfastamento) {
		this.listaAfastamento = listaAfastamento;
	}

	public List<Orgaos> getListaDeOrgaos(){
		return new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
	}
	
	public List<FundoPrevidenciario> getListaDeContribuicao(){
		return new GenericPersistence<FundoPrevidenciario>(FundoPrevidenciario.class).listarTodos("FundoPrevidenciario");
	}
	
		
	public void buscaContribuicao(){
		try{
			
				this.financeiro = somaFinanceiro(competencia);
				if(!this.financeiro.equals(null)){
				
				}else{
					Message.addErrorMessage("Não existe contribuições a serem arrecadadas deste órgão!");
				}
			
			
		}catch(Exception e){
			Message.addErrorMessage("Não existe financeiro para esta competência!");
		}
		
	}
	
	private Financeiro somaFinanceiro(String competencia){
		Financeiro finan = new Financeiro();
		BigDecimal contribuicaoSegurado = new BigDecimal(0);
		BigDecimal contribuicaoPatronal = new BigDecimal(0);
		
		for (PessoasFuncionais pf : listaFuncionais) {
			Financeiro fin = new FinanceiroDao().devolveFinanceiro(this.competencia,pf.getNUMG_idDoObjeto());
			if(!this.listaAfastamento.isEmpty() || verificaSeServidorEstaCedido(pf.getDESC_matricula()) != true && fin.getVALR_contribSegurado() != null && 
					fin.getNUMR_idDoObjetoFuncional().getDATA_exoneracao() == null){
				
				contribuicaoSegurado = contribuicaoSegurado.add(fin.getVALR_contribSegurado()) ;
				contribuicaoPatronal = contribuicaoPatronal.add(fin.getVALR_contribPatronal());
				
			}
		}
		finan.setVALR_contribSegurado(contribuicaoSegurado);
		finan.setVALR_contribPatronal(contribuicaoPatronal);
		return finan;
	}
	
	@SuppressWarnings("static-access")
	public BigDecimal retornaAliquotaMulta(String competencia){
		BigDecimal res = new BigDecimal(0);
		StringBuilder sb = new StringBuilder().append("20/").append(Integer.parseInt(competencia.substring(0, 2))+1).append("/").append(competencia.substring(3, 7));

		try {
			if(new LocalDate().now().toDate().after(sdf.parse(sb.toString()))){
				int diasPassados = RetornaTempos.retornaDiasApartirDeDuasDatas(sdf.parse(sb.toString()),new LocalDate().now().toDate());
				if(diasPassados>= 60){
					res = new BigDecimal(0.198);
				}else{
					res = new BigDecimal((diasPassados*0.0033));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@SuppressWarnings({ "static-access", "unused" })
	private List<String> devolveListaAliquotas(List<String> listaVigencia){
		return null;
	}
	
	public void novoObjeto(){
		
		this.competencia = new String();
		this.financeiro = new Financeiro();
		this.disposicao = new AfastamentosDisposicao();
		this.funcionais = new PessoasFuncionais();
		this.orgao = new Orgaos();
		this.listaAfastamento  = new ArrayList<>();
		this.listaFuncionais = new ArrayList<>();
	}
	
	public boolean verificaSeServidorEstaCedido(String matricula){
		List<AfastamentosDisposicao> lista = new GenericPersistence<AfastamentosDisposicao>(AfastamentosDisposicao.class).listarTodos("AfastamentosDisposicao");
			for (AfastamentosDisposicao af : lista) {
				if(af.getNUMR_idDoObjetoPessoasFuncionais().getDESC_matricula().equals(matricula)){
					return true;
				}
			}
		return false;
	}
}
