package br.com.iperonprev.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Financeiro;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class FinanceiroDao implements GenericDao<Financeiro>,Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	
	@Override
	public void salvaObjeto(Financeiro obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		return null;
	}

	@Override
	public List<Financeiro> buscaTodosObjetos(String nomeDaTabela) {
		return null;
	}

	@Override
	public List<Financeiro> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<Financeiro>(Financeiro.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		
	}
	
	public Financeiro devolveFinanceiro(String competencia,int id){
		Financeiro financeiro = new Financeiro();
		try{

				Query q = getEm().createQuery("select f from Financeiro f JOIN f.NUMR_idDoObjetoFuncional pf where pf.NUMG_idDoObjeto = :id and f.NUMR_competencia = :competencia");
				q.setParameter("id", id);
				q.setParameter("competencia", competencia);
				
				if(!q.getResultList().isEmpty()){
					financeiro = (Financeiro)q.getResultList().get(q.getResultList().size()-1);
				}else{
					Query q2 = getEm().createQuery("select f from Financeiro f JOIN f.NUMR_idDoObjetoFuncional pf where pf.NUMG_idDoObjeto = :id");
					q2.setParameter("id", id);
					
					financeiro = (Financeiro)q2.getResultList().get(q2.getResultList().size()-1);
					financeiro.setNUMG_idDoObjeto(financeiro.getNUMG_idDoObjeto()+1);
				}
				financeiro.setVALR_multaSegurado(new BigDecimal("0.00"));
				financeiro.setVALR_multaPatronal(new BigDecimal("0.00"));
				financeiro.setVALR_jurosSegurado(new BigDecimal("0.00"));
				financeiro.setVALR_jurosPatronal(new BigDecimal("0.00"));
				
		}catch(Exception e){
			Message.addErrorMessage("Erro ao consultar financeiro!");
		}finally{
			getEm().close();
		}
		return financeiro;
	}

}
