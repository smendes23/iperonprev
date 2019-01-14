package br.com.iperonprev.util.audit;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.iperonprev.models.Users;

public class AuditListener {
	Authentication aut = SecurityContextHolder.getContext().getAuthentication();
	Users users = new Users();
	Class<?> clazz;
	@PrePersist
	public void beforeInsert(Object obj){
		users = (Users)aut.getPrincipal();
		System.out.println("Inseriu");
	}
	@PreUpdate
	public void beforeUpdate(Object obj){
		users = (Users)aut.getPrincipal();
		System.out.println(users.getNome());
	}
	@PreRemove
	public void beforeRemove(Object obj){}
	@PostPersist
	public void afterInsert(Object obj){}
	@PostUpdate
	public void afterUpdate(Object obj){}
	@PostRemove
	public void afterRemove(Object obj){}
	
	
}
