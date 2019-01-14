package br.com.iperonprev.controller.pericia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;
import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.AreaMedica;
import br.com.iperonprev.models.Medicos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class MedicoBean implements GenericBean<Medicos>,Serializable{

	private static final long serialVersionUID = 1L;
	
	Medicos medico = new Medicos();
	PessoasFuncionais pf = new PessoasFuncionais();
	AreaMedica area = new AreaMedica();
	private String cpfServidor;
	Pessoas pessoas = new Pessoas();
	private List<PessoasFuncionais> listaFuncionais = new ArrayList<PessoasFuncionais>();
	
	public List<PessoasFuncionais> getListaFuncionais() {
		return listaFuncionais;
	}

	public AreaMedica getArea() {
		return area;
	}

	public void setArea(AreaMedica area) {
		this.area = area;
	}

	public Medicos getMedico() {
		return medico;
	}

	public void setMedico(Medicos medico) {
		this.medico = medico;
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

	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public void buscaServidor() {
		try {
			if(!cpfServidor.isEmpty()){
				this.pessoas = new PessoasDao().devolvePessoa(cpfServidor);
				listaFuncionais  = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", this.pessoas.getNUMG_idDoObjeto());
			}else{
				Message.addErrorMessage("Cpf Nulo");
			}
			
			if (this.pessoas.getNUMG_idDoObjeto() > 0) {
				this.pf.setFLAG_abonoPermanencia(DecisaoEnum.NAO);
			} else {
				Message.addErrorMessage("Servidor não encontrado!");
			}

			this.cpfServidor = "";
		} catch (Exception e) {
			Message.addErrorMessage("Não foi possível carregar os dados pessoais!");
		}
	}

	@Override
	public void salvarObjeto() {
		try {
			this.medico.setNUMR_funcionais(this.pf);
			this.medico.setNUMR_areaMedica(this.area);
			new GenericPersistence<Medicos>(Medicos.class).salvar(this.medico);
			novoObjeto();
			Message.addSuccessMessage("Médico salvo com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar médico");
		}
	}

	@Override
	public void novoObjeto() {
		this.area = new AreaMedica();
		this.pf = new PessoasFuncionais();
		this.medico = new Medicos();
	}

	@Override
	public List<Medicos> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaMedico");
	}

	@Override
	public void pegaInstanciaDialogo(Medicos obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.medico = (Medicos) event.getObject();
		this.pf = this.medico.getNUMR_funcionais();
		this.area = this.medico.getNUMR_areaMedica();
	}
	
	public List<Medicos> getListaMedicos(){
		return new GenericPersistence<Medicos>(Medicos.class).listarTodos("Medicos");
	}
	
	public List<AreaMedica> getListaAreaMedica(){
		return new GenericPersistence<AreaMedica>(AreaMedica.class).listarTodos("AreaMedica");
	}
	
	public void exibeListaDeObjetosFuncionais() {
		new DialogsPrime().showDialog(true, true, true, false, "listaFuncionalMedico");
		
	}
	
	public void selecionaObjetoDialogoFuncionais(SelectEvent event) {
		this.pf = (PessoasFuncionais)event.getObject();
	}
	
	static List<PessoasFuncionais> listaP = new ArrayList<PessoasFuncionais>();
	public void pegaLista(ValueChangeEvent event){
		
		if(event.getNewValue().toString().length() >= 4 ){
			listaP = new PessoasFuncionaisDao().devolveFuncionalComClausulaLike(event.getNewValue().toString());
		}
	}
	
	
	public void pegaInstanciaDialogoFuncional(PessoasFuncionais obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
		
	}
	
	public void buscaFuncional(ValueChangeEvent event){
		this.pf = (PessoasFuncionais)event.getNewValue();
	}

}
