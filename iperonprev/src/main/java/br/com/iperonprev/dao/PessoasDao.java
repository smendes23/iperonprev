package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;
import br.com.iperonprev.util.jsf.Message;

public class PessoasDao implements GenericDao<Pessoas>,Serializable{

	private static final long serialVersionUID = 1L;
	JdbcUtil conexao;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(Pessoas obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Pessoa cadastrada com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao cadastrar a pessoa!"+ e.getMessage());
		}
		
	}
	
	SessionFactory sessionFactory;
	
	@Override
	public Pessoas buscaObjetoIndividual(int idDoObjeto) {
		Session sessao = sessionFactory.openSession();
		Pessoas p = (Pessoas)sessao.load(Pessoas.class, idDoObjeto);
		p.getNUMG_idDoObjeto();
		sessao.close();
		return p;
	}

	@Override
	public List<Pessoas> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<Pessoas>(Pessoas.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<Pessoas> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public Pessoas devolvePessoa(String cpf){
		Pessoas pessoa = new Pessoas();
		try{
			Query q = getEm().createQuery("SELECT p FROM Pessoas p JOIN FETCH p.NUMR_idDoObjetoEndereco WHERE p.NUMR_cpf = :cpf",Pessoas.class);
			q.setParameter("cpf", cpf);
			if(!q.getResultList().isEmpty()){
				pessoa = (Pessoas)q.getResultList().get(0);			
			}
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Cpf não cadastrado ou nulo!");
		}finally{
			getEm().close();
		}
		return pessoa;
	}
	
	
	public List<Pessoas> devolveListaDePessoasComClausulaLike(String nome){
		List<Pessoas> lista = new ArrayList<Pessoas>();
		try{
			TypedQuery<Pessoas> q = getEm().createQuery("select p from Pessoas p where p.DESC_nome like :nome ",Pessoas.class);
			q.setParameter("nome", "%"+nome+"%");
			lista = q.getResultList();			
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao carregar a lista!");
		}finally{
			getEm().close();
		}
		return lista;
	}
	
	public boolean verificaServidorInativo(String cpf){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select p.* from Pessoas p "
					+ "inner join FuncionaisFuncoes ff on ff.NUMR_idPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
					+ "inner join SituacaoPrevidenciaria sp on ff.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = sp.NUMG_idDoObjeto "
					+ "where p.NUMR_cpf = :cpf and "
					+ "sp.NUMR_codigo = 1");
			q.setParameter("cpf", cpf);
			if(q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao verificar inativo");
		}finally{
			getEm().close();
		}
		return res;
	}
	
	public boolean verificaExistenciaPessoa(String cpf){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from Pessoas where NUMR_cpf = :cpf");
			q.setParameter("cpf", cpf);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao verificar existência de pessoa");
		}finally{
			getEm().close();
		}
		return res;
		
	}
	
}
