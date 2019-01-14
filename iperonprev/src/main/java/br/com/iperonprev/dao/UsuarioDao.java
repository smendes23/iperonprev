package br.com.iperonprev.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.iperonprev.models.Roles;
import br.com.iperonprev.models.Users;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
@Repository
public class UsuarioDao implements UserDetailsService{
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	EntityTransaction transaction = getEm().getTransaction();
	
	@Override
	public Users loadUserByUsername(String matricula) throws UsernameNotFoundException {
		
		List<Users> users = getEm().createQuery("select u from Users u where u.matricula = :matricula",Users.class)
					.setParameter("matricula", matricula)
					.getResultList();
			if(users.isEmpty()){
				return new Users();
			}
		getEm().close();
		return users.get(0);
	}
	
	public Users loadUserBeneficio(String matricula){
		Users user = new Users();
		try{
			Query q = getEm().createNativeQuery("select * from Users where matricula = :matricula", Users.class)
					.setParameter("matricula", matricula);
			if(!q.getResultList().isEmpty()){
				user = (Users) q.getResultList().get(0);
			}
			
		}catch(Exception e){
			System.out.println("Erro ao carregar usuário");
		}
		return user;
	}
	
	/*@SuppressWarnings("static-access")
	public Users devolveUsuario(String matricula, String senha){
		List<Users> users = new ArrayList<Users>();
		users = em.createQuery("select u from Users u where u.matricula = :matricula and u.senha =:senha",Users.class)
				.setParameter("matricula", matricula)
				.setParameter("senha", new CriptografiaSenha().criptografa(senha))
				.getResultList();
		if(users.isEmpty()){
			return new Users();
		}
	return users.get(0);
	}*/
	
	public void removeUsuario(String matricula){
		Users us = new GenericPersistence<Users>(Users.class).buscarPorString(matricula);
		us.setRoles(new ArrayList<Roles>());
		new GenericPersistence<Users>(Users.class).salvar(us);
		new GenericPersistence<Users>(Users.class).removerComString(matricula);
		
	}
}
