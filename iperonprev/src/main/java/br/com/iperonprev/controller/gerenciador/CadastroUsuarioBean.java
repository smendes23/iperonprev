package br.com.iperonprev.controller.gerenciador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.dao.CadastroUsuarioDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.UsuarioDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.ModulosPerfil;
import br.com.iperonprev.models.PerfilAcesso;
import br.com.iperonprev.models.Roles;
import br.com.iperonprev.models.Users;
import br.com.iperonprev.security.CriptografiaSenha;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class CadastroUsuarioBean implements GenericBean<Users>, Serializable{

	private static final long serialVersionUID = 1L;
	ModulosPerfil modulos = new ModulosPerfil();
	Users users = new Users();
	private String proximaSenha;
	private boolean contaBloqueada;
	private boolean redefineSenha;
	
	static private List<Users> filtroDeUsuarios;
	
	public boolean isRedefineSenha() {
		return redefineSenha;
	}
	public void setRedefineSenha(boolean redefineSenha) {
		this.redefineSenha = redefineSenha;
	}
	@Override
	public void salvarObjeto(){
		try{
			this.modulos = new CadastroUsuarioDao().listarRelacionamento("ModulosPerfil", "perfil", this.users.getPerfil().getId()).get(0);
			
			this.users.setRoles(listaDeRegras());
			
			if(proximaSenha.length() < 15){
				this.users.setSenha(CriptografiaSenha.criptografa(proximaSenha));
			}else{
				this.users.setSenha(proximaSenha);
			}
			
			if(contaBloqueada == true){
				this.users.setContaHabilitada(false);
			}else{
				this.users.setContaHabilitada(true);
			}
			this.users.setMatricula(this.numeroIdentificador);
			this.users.setRedefinirSenha(redefineSenha);
			new GenericPersistence<Users>(Users.class).salvar(users);
			novoObjeto();
			Message.addSuccessMessage("Conta cadastrada com sucesso!");
			
		}catch(Exception e){
			Message.addErrorMessage("Erro ao cadastrar conta!");
		}
	}
	@Override
	public void novoObjeto(){
		this.modulos = new ModulosPerfil();
		this.users = new Users();
		this.contaBloqueada = false;
		this.proximaSenha = null;
		this.numeroIdentificador = new String();
	}
	
	@Override
	public List<Users> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaDeUsuarios");
	}

	@Override
	public void pegaInstanciaDialogo(Users obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		try{
			this.users = (Users)event.getObject();
			if(this.users.isContaHabilitada() != true){
				this.contaBloqueada = true;
			}else{
				this.contaBloqueada = false;
			}
			
			this.proximaSenha = this.users.getSenha();
			this.redefineSenha = this.users.isRedefinirSenha();
		}catch(Exception e){
			System.out.println("Não foi possível carregar o usuário...");
		}
	}
	public void removeUsuario(String matricula){
		
		new UsuarioDao().removeUsuario(matricula);
	}
	
	public List<Users> getFiltroDeUsuarios() {
		return filtroDeUsuarios;
	}
	
	@SuppressWarnings("static-access")
	public void setFiltroDeUsuarios(List<Users> filtroDeUsuarios) {
		this.filtroDeUsuarios = filtroDeUsuarios;
	}
	public boolean isContaBloqueada() {
		return contaBloqueada;
	}

	public void setContaBloqueada(boolean contaBloqueada) {
		this.contaBloqueada = contaBloqueada;
	}

	public String getProximaSenha() {
		return proximaSenha;
	}


	public void setProximaSenha(String proximaSenha) {
		this.proximaSenha = proximaSenha;
	}


	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<PerfilAcesso> getListaDePerfis(){
		return new GenericPersistence<PerfilAcesso>(PerfilAcesso.class).listarTodos("PerfilAcesso");
	}
	
	public List<Users> getListaDeUsuarios(){
		return new GenericPersistence<Users>(Users.class).listarTodos("Users");
	}
	
	public void fechaListaUsuario(){
		try{
			RequestContext.getCurrentInstance().closeDialog("listaDeUsuarios");
		}catch(Exception e){
			System.out.println("Fechou a lista de usuários");
		}
	}
	
	private String numeroIdentificador;
	
	
	public String getNumeroIdentificador() {
		return numeroIdentificador;
	}
	public void setNumeroIdentificador(String numeroIdentificador) {
		this.numeroIdentificador = numeroIdentificador;
	}
	public void buscaUsuario() {
		try {
			this.users = new GenericPersistence<Users>(Users.class).buscarPorString(this.numeroIdentificador);
			if(this.users.isContaHabilitada() != true){
				this.contaBloqueada = true;
			}else{
				this.contaBloqueada = false;
			}
			this.proximaSenha = this.users.getSenha();
			this.redefineSenha = this.users.isRedefinirSenha();
		} catch (Exception e) {
			Message.addErrorMessage("Não foi possível carregar o usuário");
		}
	}
	
	private List<Roles> listaDeRegras(){
		List<Roles> lista = new ArrayList<>();
	
		if(this.modulos.isAcessoAdministracao()){
			lista.add(new Roles("ROLE_ADMINISTRACAOA"));
		}
		
		if(this.modulos.isAcessoArrecadacao()){
			lista.add(new Roles("ROLE_ARRECADACAOA"));
		}
		
		if(this.modulos.isAcessoBeneficio()){
			lista.add(new Roles("ROLE_BENEFICIOA"));
		}
		
		if(this.modulos.isAcessoCadastro()){
			lista.add(new Roles("ROLE_CADASTROA"));
		}
		
		if(this.modulos.isAcessoCenso()){
			lista.add(new Roles("ROLE_CENSOA"));
		}
		
		if(this.modulos.isAcessoRelatorios()){
			lista.add(new Roles("ROLE_RELATORIOSA"));
		}
		
		if(this.modulos.isIncluirAdministracao()){
			lista.add(new Roles("ROLE_ADMINISTRACAOI"));
		}
		if(this.modulos.isIncluirArrecadacao()){
			lista.add(new Roles("ROLE_ARRECADACAOI"));
		}
		if(this.modulos.isIncluirBeneficio()){
			lista.add(new Roles("ROLE_BENEFICIOI"));
		}
		
		if(this.modulos.isIncluirCadastro()){
			lista.add(new Roles("ROLE_CADASTROI"));
		}
		
		if(this.modulos.isIncluirCenso()){
			lista.add(new Roles("ROLE_CENSOI"));
		}
		
		if(this.modulos.isIncluirRelatorios()){
			lista.add(new Roles("ROLE_RELATORIOSI"));
		}
		
		if(this.modulos.isExcluirAdministracao()){
			lista.add(new Roles("ROLE_ADMINISTRACAOE"));
		}
		if(this.modulos.isExcluirArrecadacao()){
			lista.add(new Roles("ROLE_ARRECADACAOE"));
		}
		if(this.modulos.isExcluirBeneficio()){
			lista.add(new Roles("ROLE_BENEFICIOE"));
		}
		
		if(this.modulos.isExcluirCadastro()){
			lista.add(new Roles("ROLE_CADASTROE"));
		}
		
		if(this.modulos.isEcluirCenso()){
			lista.add(new Roles("ROLE_CENSOE"));
		}
		
		if(this.modulos.isExcluirRelatorios()){
			lista.add(new Roles("ROLE_RELATORIOSE"));
		}
		return lista;
	}
}
