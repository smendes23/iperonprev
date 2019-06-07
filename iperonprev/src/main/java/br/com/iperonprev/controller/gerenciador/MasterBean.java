package br.com.iperonprev.controller.gerenciador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.joda.time.LocalDateTime;
import org.primefaces.event.SelectEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.ModulosPerfil;
import br.com.iperonprev.models.PerfilAcesso;
import br.com.iperonprev.models.Users;
import br.com.iperonprev.security.CriptografiaSenha;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@javax.faces.view.ViewScoped
public class MasterBean implements Serializable,GenericBean<ModulosPerfil>{
	
	private static final long serialVersionUID = 1L;
	Users users = new Users();
	private String usuarioLogado;
	ModulosPerfil modulos = new ModulosPerfil();
	PerfilAcesso perfil = new PerfilAcesso();
	private String nomeUsuario;
	
	private boolean salvaSenha;
	
	private String senha;
	private String repeteSenha;

	
	public boolean isSalvaSenha() {
		return salvaSenha;
	}

	public void setSalvaSenha(boolean salvaSenha) {
		this.salvaSenha = salvaSenha;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRepeteSenha() {
		return repeteSenha;
	}

	public void setRepeteSenha(String repeteSenha) {
		this.repeteSenha = repeteSenha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public ModulosPerfil getModulos() {
		return modulos;
	}

	public void setModulos(ModulosPerfil modulos) {
		this.modulos = modulos;
	}

	public PerfilAcesso getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilAcesso perfil) {
		this.perfil = perfil;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	private String nome;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@PostConstruct
	public void init(){
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
		if(aut.getPrincipal() instanceof Users && !aut.equals(null)){
			users = (Users)aut.getPrincipal();
			LocalDateTime local = new LocalDateTime();
			nomeUsuario = this.users.getNome();
			if(local.getHourOfDay() >= 0 && local.getHourOfDay() <= 12 ){
				this.usuarioLogado = "Bom dia, "+this.users.getNome();
			}else if(local.getHourOfDay() > 12 && local.getHourOfDay() <= 17 ){
				this.usuarioLogado = "Boa tarde, "+this.users.getNome();
			}else{
				this.usuarioLogado = "Boa noite, "+this.users.getNome();
			}
		}
	}

	@Override
	public void salvarObjeto() {
		List<PerfilAcesso> listaP = new GenericPersistence<PerfilAcesso>(PerfilAcesso.class).listarTodos("PerfilAcesso"); 
		try{
			if(this.perfil.getId() == 0){
				int id = listaP.get(listaP.size()-1).getId();
				this.perfil.setId(id+1);
			}
			new GenericPersistence<PerfilAcesso>(PerfilAcesso.class).salvar(this.perfil);
			this.modulos.setPerfil(perfil);
			new GenericPersistence<ModulosPerfil>(ModulosPerfil.class).salvar(modulos);
			Message.addSuccessMessage("Perfil cadastrado com sucesso!");
			FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
			novoObjeto();
		}catch(Exception e){
			Message.addErrorMessage("Erro ao cadastrar perfil!");
		}
	}

	@Override
	public void novoObjeto() {
		this.modulos = new ModulosPerfil();
		this.perfil = new PerfilAcesso();
	}

	@Override
	public List<ModulosPerfil> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaPerfilDeAcesso");
	}

	@Override
	public void pegaInstanciaDialogo(ModulosPerfil obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);
	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.modulos = (ModulosPerfil)event.getObject();
		this.perfil = this.modulos.getPerfil();
	}
	
	public List<ModulosPerfil> getListaDeModulos(){
		return new GenericPersistence<ModulosPerfil>(ModulosPerfil.class).listarTodos("ModulosPerfil");
	}
	
	public void removeObjeto(int id){
		new GenericPersistence<ModulosPerfil>(ModulosPerfil.class).remover("ModulosPerfil", "id", id);
	}
	
	public void alteraSenha(){
		try {
		Authentication aut = SecurityContextHolder.getContext().getAuthentication();
			if(aut.getPrincipal() instanceof Users && !aut.equals(null)){
				this.users =(Users)aut.getPrincipal();
				this.users.setRedefinirSenha(false);
				if(this.senha.equals(this.repeteSenha)){
					this.users.setSenha(CriptografiaSenha.criptografa(this.senha));
					new GenericPersistence<Users>(Users.class).salvar(users);
				this.senha = new String();
				this.repeteSenha = new String();
				FacesContext.getCurrentInstance().getExternalContext().redirect("/logout");
				}
			}else{
				Message.addErrorMessage("Senhas não conferem!");
			}
			new Logout().sair();
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao alterar a senha");
		}
	}
	
	public void confereSenhaIgual(){
		try{
			if(this.senha.equals(this.repeteSenha)){
				this.salvaSenha = true;
			}
		}catch(Exception e){
			Message.addErrorMessage("Senha não confere!");
		}
	}
	

}
