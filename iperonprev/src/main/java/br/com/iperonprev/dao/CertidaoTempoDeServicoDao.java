package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.CertidaoTempoServico;
import br.com.iperonprev.models.DesbloqueioCtc;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class CertidaoTempoDeServicoDao implements GenericDao<CertidaoTempoServico>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(CertidaoTempoServico obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Dados da CTC salvos com sucesso!");
		}catch(PersistenceException e){
			Message.addErrorMessage("Erro ao salvar os dados da CTC!");
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertidaoTempoServico> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CertidaoTempoServico> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<CertidaoTempoServico>(CertidaoTempoServico.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public CertidaoTempoServico devolveCertidao(String matricula){
		CertidaoTempoServico ctc = new CertidaoTempoServico();
		try{
			Query q = getEm().createQuery("select c from CertidaoTempoServico c join c.REL_listaDeFuncionais pf where pf.DESC_matricula = :matricula");
			/*Query q = em.createNativeQuery("select c.* from CertidaoTempoServico c inner join PessoasFuncionais pf"
					+ " on c.NUMR_idDoObjetoPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto "
					+ " where pf.DESC_matricula = :matricula",CertidaoTempoServico.class);*/
			q.setParameter("matricula", matricula);
			ctc = (CertidaoTempoServico)q.getResultList().get(0);
		}catch(Exception e){
			System.out.println("Não foi possível carregar ctc");
		}finally{
			getEm().close();
		}
		return ctc;
	}
	
	@SuppressWarnings("unchecked")
	public List<DesbloqueioCtc> devolveListaCtcBloqueada(int numeroCtc){
		List<DesbloqueioCtc> lista = new ArrayList<DesbloqueioCtc>();
		
		try{
			Query q = getEm().createNativeQuery("select * from DesbloqueioCtc where NUMR_ctc_NUMR_certidao = :certidao",DesbloqueioCtc.class);
			q.setParameter("certidao", numeroCtc);
			lista = q.getResultList();
		}catch(Exception e){
			System.out.println("Erro ao lista de  Ctcs bloqueada");
		}finally {
			if(getEm().isOpen()){
				getEm().close();
			}
		}
		return lista;
	}
	
	public boolean verificaSeExisteCertidao(int...values){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from CertidaoTempoServico where NUMR_certidao = :certidao and NUMR_ano = :ano",CertidaoTempoServico.class);
			q.setParameter("certidao", values[0]);
			q.setParameter("ano", values[1]);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao lista de  Cts bloqueada");
		}finally {
			if(getEm().isOpen()){
				getEm().close();
			}
		}
		return res;
	}
	
	public int devolveNumeroDaCertidao(int ano){
		int numeroCertidao = 1;
		try{
			Query q = getEm().createNativeQuery("select top 1 NUMR_certidao from CertidaoTempoServico where NUMR_ano = :ano order by NUMR_certidao desc");
			q.setParameter("ano", ano);
			if(!q.getResultList().isEmpty()){
				numeroCertidao =(int) q.getResultList().get(0)+1;
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar número da ctc!");
		}
		return numeroCertidao;
	}
	
	public boolean verificaExistenciaCtcFuncional(int idFuncional) {
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from CertidaoTempoServico where REL_funcional_NUMG_idDoObjeto  = :funcional");
			q.setParameter("funcional", idFuncional);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao verificar existência da CTS");
		}finally {
			if(getEm().isOpen()){
				getEm().close();
			}
		}
		return res;
	}
	
	public CertidaoTempoServico devolveCertidao(int idFuncional) {
		CertidaoTempoServico cts = new CertidaoTempoServico();
		try{
			Query q = getEm().createQuery("select c from CertidaoTempoServico c inner join c.REL_funcional f where f.NUMG_idDoObjeto = :funcional",CertidaoTempoServico.class);
			q.setParameter("funcional", idFuncional);
			if(!q.getResultList().isEmpty()){
				cts = (CertidaoTempoServico) q.getResultList().get(0);
			}
		}catch(Exception e){
			System.out.println("Erro ao verificar existência da CTC");
		}finally {
			if(getEm().isOpen()){
				getEm().close();
			}
		}
		return cts;
	}

}
