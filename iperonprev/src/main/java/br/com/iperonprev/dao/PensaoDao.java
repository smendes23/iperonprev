package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Pensao;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class PensaoDao implements GenericDao<Pensao>,Serializable{

	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Pensao obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Pensão salva com sucesso");
		}catch(PersistenceException e){
			Message.addErrorMessage("Erro ao salvar pensão.");
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pensao> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<Pensao>(Pensao.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<Pensao> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<Pensao>(Pensao.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		
	}
	
	public Pensao devolvePensao(int idFuncional){
		Pensao pensao = new Pensao();
		try{
			Query q = getEm().createNativeQuery("select * from Pensao where REL_pessoasFuncionais_NUMG_idDoObjeto = :funcional",Pensao.class);
			q.setParameter("funcional", idFuncional);
			if(!q.getResultList().isEmpty()){
				pensao = (Pensao)q.getResultList().get(0);
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao carregar pensão!");
		}
		return pensao;
	}
	
	public boolean verificaExistenciaPensao(int idFuncional){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from Pensao where REL_pessoasFuncionais_NUMG_idDoObjeto = :funcional",Pensao.class);
			q.setParameter("funcional", idFuncional);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar Pensão");
		}
		return res;
	}
	
	public List<Pensao> listaDePensoes(String cpf){
		List<Pensao> lista = new ArrayList<>();
		try {
			Query q  = getEm().createNativeQuery("select * from Pensao pen, PessoasFuncionais pf,Dependentes dep, Pessoas p " + 
					" where pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = dep.NUMR_idDoObjetoDependentes_NUMG_idDoObjeto " + 
					"	and " + 
					"	pen.REL_pessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto " + 
					"	and " + 
					"	p.NUMG_idDoObjeto = dep.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto " + 
					"	and " + 
					"	p.NUMR_cpf = :cpf",Pensao.class);
			q.setParameter("cpf", cpf);
			if(!q.getResultList().isEmpty()) {
				lista = q.getResultList();
			}
		}catch(Exception e) {
			System.out.println("Não foi possivel carregar pensões");
		}
		return lista;
	}

}
