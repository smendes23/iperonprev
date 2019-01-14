package br.com.iperonprev.controller.cadastro;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.DadosBancariosDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Bancos;
import br.com.iperonprev.models.DadosBancarios;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.util.jsf.Message;

@Named
@ViewScoped
public class DadosBancariosBean implements Serializable, GenericBean<DadosBancarios> {

	private static final long serialVersionUID = 1L;
	private String cpfServidor;
	Pessoas pessoas = new Pessoas();
	Bancos banco = new Bancos();
	DadosBancarios dadosBancarios = new DadosBancarios();
	
	
	

	public Bancos getBanco() {
		return banco;
	}

	public void setBanco(Bancos banco) {
		this.banco = banco;
	}

	public DadosBancarios getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(DadosBancarios dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}


	public Pessoas getPessoas() {
		return pessoas;
	}

	public void setPessoas(Pessoas pessoas) {
		this.pessoas = pessoas;
	}

	public String getCpfServidor() {
		return cpfServidor;
	}

	public void setCpfServidor(String cpfServidor) {
		this.cpfServidor = cpfServidor;
	}

	@Override
	public void salvarObjeto() {
		try {
			this.dadosBancarios.setREL_banco(this.banco);
			this.dadosBancarios.setREL_pessoa(this.pessoas);
			new GenericPersistence<DadosBancarios>(DadosBancarios.class).salvar(this.dadosBancarios);
			Message.addSuccessMessage("Dados bancários salvo com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar os dados bancários.");
		}
	}

	

	@Override
	public void novoObjeto() {
		recarregaPagina();
	}

	@Override
	public List<DadosBancarios> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pegaInstanciaDialogo(DadosBancarios obj) {
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
			if(!new DadosBancariosDao().devolveDadosBancariosPessoa(this.pessoas.getNUMG_idDoObjeto()).isEmpty()){
				this.dadosBancarios = new DadosBancariosDao().devolveDadosBancariosPessoa(this.pessoas.getNUMG_idDoObjeto()).get(0);
				this.banco = this.dadosBancarios.getREL_banco();
			}
		} catch (NullPointerException e) {
			Message.addErrorMessage("Cpf inválido!");
		}

	}

	public List<Bancos> getListaDeBancos(){
		return new GenericPersistence<Bancos>(Bancos.class).listarTodos("Bancos");
	}
	
	public void recarregaPagina() {
		this.pessoas = new Pessoas();
		this.cpfServidor = new String();
		this.dadosBancarios = new DadosBancarios();
		this.banco = new Bancos();
	}

}

