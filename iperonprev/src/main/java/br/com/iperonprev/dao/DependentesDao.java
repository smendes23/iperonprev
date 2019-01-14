package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Dependentes;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class DependentesDao implements GenericDao<Dependentes>,Serializable{

	
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	@Override
	public void salvaObjeto(Dependentes obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Dependente cadastrado com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao cadastrar o dependente!"+e.getMessage());
		}
		
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dependentes> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<Dependentes>(Dependentes.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<Dependentes> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<Dependentes>(Dependentes.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public Dependentes devolveDependentePeloCpf(String cpf){
		Dependentes dependente = new Dependentes();
		try{
			Query q = getEm().createQuery("select d from Dependentes d JOIN d.NUMR_dependente c where c.NUMR_cpf = :cpf");
			q.setParameter("cpf", cpf);
			dependente = (Dependentes)q.getResultList().get(0);			
		}catch(Exception e){
			Message.addErrorMessage("Cpf não existe!");
		}finally{
			getEm().close();
		}
		return dependente;
	}
	
	public boolean verificaExisteRegistro(int...value){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from Dependentes where NUMR_idDoObjetoDependentes_NUMG_idDoObjeto = :dependente and "
					+ "NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = :servidor",Dependentes.class);
			q.setParameter("dependente", value[0]);
			q.setParameter("servidor", value[1]);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao verificar existência de dependentes");
		}finally{
			getEm().close();
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dependentes> listaDependentesPensionistas(int idServidor){
		List<Dependentes> lista = new ArrayList<Dependentes>();
		try{
			Query q = getEm().createNativeQuery("select distinct * from Dependentes where NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = :idServidor ",Dependentes.class);
			q.setParameter("idServidor", idServidor);
			lista = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao lista Dependentes e Pensionistas");
		}finally{
			getEm().close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Dependentes> listaDependentesParaPensao(String cpf){
		List<Dependentes> lista = new ArrayList<Dependentes>();
		try{
			Query q = getEm().createNativeQuery("select * from Dependentes d,Pessoas p  "
					+ "where p.NUMR_cpf = :cpf and "
					+ "p.DATA_obito is not null and "
					+ "d.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto",Dependentes.class);
			q.setParameter("cpf", cpf);
			lista = q.getResultList();
		}catch(Exception e){
			System.out.println("Erro ao lista Dependentes e Pensionistas");
		}finally{
			getEm().close();
		}
		return lista;
	}
	
	public boolean existeDependente(int idServidor){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select distinct * from Dependentes where NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = :idServidor ",Dependentes.class);
			q.setParameter("idServidor", idServidor);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
			
		}catch(Exception e){
			System.out.println("Erro ao verificar existência de dependentes.");
		}finally{
			getEm().close();
		}
		return res;
	}
	
	
	public List<Dependentes> listaDependentes(int idDependente){
		List<Dependentes> lista = new ArrayList<Dependentes>();
		try{
			Query q = getEm().createNativeQuery("select * from Dependentes where NUMR_idDoObjetoDependentes_NUMG_idDoObjeto = :idDependente ",Dependentes.class);
			q.setParameter("idDependente", idDependente);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao lista Dependentes.");
		}finally{
			getEm().close();
		}
		return lista;
	}
		
}
