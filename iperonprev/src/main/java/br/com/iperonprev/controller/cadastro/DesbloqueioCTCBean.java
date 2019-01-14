package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import org.joda.time.LocalDate;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.CertidaoTempoDeContribuicaoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.CertidaoTempoContribuicao;
import br.com.iperonprev.models.DesbloqueioCtc;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class DesbloqueioCTCBean implements GenericBean<DesbloqueioCtc>,Serializable{

	private static final long serialVersionUID = 1L;
	CertidaoTempoContribuicao ctc = new CertidaoTempoContribuicao();
	DesbloqueioCtc desbloqueio = new DesbloqueioCtc();
	private String matricula;
	static List<DesbloqueioCtc> listaDeDesbloqueio = new ArrayList<DesbloqueioCtc>();
	Pessoas pessoa = new Pessoas();
	
	
	public Pessoas getPessoa() {
		return pessoa;
	}

	public CertidaoTempoContribuicao getCtc() {
		return ctc;
	}

	public void setCtc(CertidaoTempoContribuicao ctc) {
		this.ctc = ctc;
	}

	public DesbloqueioCtc getDesbloqueio() {
		return desbloqueio;
	}

	public void setDesbloqueio(DesbloqueioCtc desbloqueio) {
		this.desbloqueio = desbloqueio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public void buscaCertidao(){
		try{
			this.ctc = new CertidaoTempoDeContribuicaoDao().devolveCertidao(matricula);
			this.desbloqueio.setFLAG_impressaoCtc(this.ctc.isFLAG_impresso());
			this.desbloqueio.setFLAG_impressaoRrc(this.ctc.isFLAG_rrc());
			this.desbloqueio.setFLAG_edicao(this.ctc.isFLAG_salvo());
			listaDeDesbloqueio = new CertidaoTempoDeContribuicaoDao().devolveListaCtcBloqueada(this.ctc.getNUMR_certidao());
			this.pessoa = ctc.getREL_listaDeFuncionais().get(0).getNUMR_idDoObjetoPessoas();
			this.matricula = new String();
			
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível buscar certidão!");
		}
	}

	@SuppressWarnings("static-access")
	@Override
	public void salvarObjeto() {
		try{
			this.ctc.setFLAG_impresso(this.desbloqueio.isFLAG_impressaoCtc());
			this.ctc.setFLAG_rrc(this.desbloqueio.isFLAG_impressaoRrc());
			this.ctc.setFLAG_salvo(this.desbloqueio.isFLAG_edicao());
			new GenericPersistence<CertidaoTempoContribuicao>(CertidaoTempoContribuicao.class).salvar(ctc);
			this.desbloqueio.setNUMR_ctc(ctc);
			this.desbloqueio.setDT_cadastro(new LocalDate().now().toDate());
			new GenericPersistence<DesbloqueioCtc>(DesbloqueioCtc.class).salvar(desbloqueio);
			novoObjeto();
			Message.addSuccessMessage("Desbloqueio efetuado com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao efetuar desbloqueio.");
		}

	}

	@Override
	public void novoObjeto() {
		this.desbloqueio = new DesbloqueioCtc();
		this.ctc = new CertidaoTempoContribuicao();
		this.matricula = new String();
		listaDeDesbloqueio = new ArrayList<>();
		this.pessoa = new Pessoas();
	}

	@Override
	public List<DesbloqueioCtc> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDesbloqueios");
	}

	@Override
	public void pegaInstanciaDialogo(DesbloqueioCtc obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.desbloqueio = (DesbloqueioCtc)event.getObject();
		this.ctc = this.desbloqueio.getNUMR_ctc();
	}
	
	public List<DesbloqueioCtc> getListaDeDesbloqueios(){
		System.out.println(this.ctc.getNUMR_certidao());
		return listaDeDesbloqueio;
	}

	public void recarregaPagina(){
		novoObjeto();
	}
}
