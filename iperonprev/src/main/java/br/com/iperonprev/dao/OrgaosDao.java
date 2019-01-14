package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.EntesFederados;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class OrgaosDao implements GenericDao<Orgaos>, Serializable {
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Orgaos obj) {
		QualificaOrgao qualifica = new QualificaOrgao();
		qualifica.valida(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Orgaos> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<Orgaos>(Orgaos.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<Orgaos> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub

	}

	public Orgaos verificaUnidadeGestora(Orgaos obj) {
		Orgaos org = new Orgaos();
		TypedQuery<Orgaos> query = getEm().createQuery("SELECT o FROM Orgaos o WHERE o.NUMR_unidadeGestora = :unidade and "
				+ "o.NUMR_idDoObjetoEnteFederado = :entes", Orgaos.class);

		query.setParameter("unidade", true);
		query.setParameter("entes", obj.getNUMR_idDoObjetoEnteFederado());

		List<Orgaos> lista = query.getResultList();
		if (!lista.isEmpty()) {
			org = lista.get(0);
		}
		return org;
	}
	
	public Orgaos devolveUnidadeGestora(EntesFederados entes) {
		Orgaos org = new Orgaos();
		try{
			
			TypedQuery<Orgaos> query = getEm().createQuery("SELECT o FROM Orgaos o WHERE o.NUMR_unidadeGestora = :unidade and "
					+ "o.NUMR_idDoObjetoEnteFederado = :entes", Orgaos.class);
			
			query.setParameter("unidade", true);
			query.setParameter("entes", entes);
			
			List<Orgaos> lista = query.getResultList();
			if (!lista.isEmpty()) {
				org = lista.get(0);
			}
		}catch(Exception e){
			
		}finally {
			getEm().close();
		}
		return org;
	}
	
	public Orgaos devolveOrgao(String cnpj){
		Orgaos orgao = new Orgaos();
		try{

			Query q = getEm().createQuery("select o from Orgaos o where o.DESC_cnpj = :cnpj ");
			q.setParameter("cnpj", cnpj);
			orgao = (Orgaos)q.getResultList().get(0);			
		}catch(Exception e){
			Message.addErrorMessage("CNPJ não encontrado!");
		}finally{
			getEm().close();
		}
		return orgao;
	}
}

