package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.CheckList;
import br.com.iperonprev.models.DocumentosChecklist;
import br.com.iperonprev.models.RequisitosBeneficio;
import br.com.iperonprev.models.TituloBeneficio;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class CheckListDao implements GenericDao<RequisitosBeneficio>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(RequisitosBeneficio obj) {
		new GenericPersistence<RequisitosBeneficio>(RequisitosBeneficio.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequisitosBeneficio> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequisitosBeneficio> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public List<TituloBeneficio> devolveListaTituloLimitada(){
		List<TituloBeneficio> lista = new ArrayList<>();
		Query q = getEm().createNativeQuery("select NUMG_idDoObjeto,convert(varchar(96),DESC_titulo)+'...'as DESC_titulo from TituloBeneficio",TituloBeneficio.class);
		lista = q.getResultList();
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<RequisitosBeneficio> listaDeRequisitos(String tipoBeneficio){
		List<RequisitosBeneficio> lista = new ArrayList<>();
		Query q = getEm().createNativeQuery("select * from RequisitosBeneficio where ENUM_tipoBeneficio = :tipoBeneficio",RequisitosBeneficio.class);
		q.setParameter("tipoBeneficio", tipoBeneficio);
		lista = q.getResultList();
		return lista;
	}

	public boolean verificaExistenciaArquivo(int idRequisito,int idPessoasFuncionais) {
		boolean res = false;
		Query q = getEm().createNativeQuery("select * from  DocumentosChecklist where REL_requisitos_NUMG_idDoObjeto = :idRequisito and REL_pessoasFuncionais_NUMG_idDoObjeto = :idPessoasFuncionais",DocumentosChecklist.class);
		q.setParameter("idRequisito", idRequisito);
		q.setParameter("idPessoasFuncionais", idPessoasFuncionais);
		
		if(!q.getResultList().isEmpty()){
			res = true;
		}
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentosChecklist> devolveListaDocumentos(int idRequisito,int idPessoasFuncionais) {
		List<DocumentosChecklist> lista = new ArrayList<>();
		
		try {
			Query q = getEm().createNativeQuery("select * from  DocumentosChecklist where REL_requisitos_NUMG_idDoObjeto = :idRequisito and REL_pessoasFuncionais_NUMG_idDoObjeto = :idPessoasFuncionais",DocumentosChecklist.class);
			q.setParameter("idRequisito", idRequisito);
			q.setParameter("idPessoasFuncionais", idPessoasFuncionais);
			
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		} catch (Exception e) {
			System.out.println("Erro ao gerar lista de documentos");
		}
		
		return lista;
	}
	
	public void removeDocumento(int idDocumento) {
		EntityManager em = getEm();
		try{
			
			em.getTransaction().begin();
			Query q = em.createNativeQuery("delete from DocumentosChecklist where NUMG_idDoObjeto = :idDocumento",DocumentosChecklist.class);
			q.setParameter("idDocumento", idDocumento)
			.executeUpdate();
			em.getTransaction().commit();
			Message.addSuccessMessage("Documento removido com sucesso!");
		}catch (Exception e) {
			em.getTransaction().rollback();
			Message.addErrorMessage("Erro ao remover o registro!");
		}finally{
			em.close();
		}
	}
	
	public List<CheckList> devolveListaCheckList(int idFuncional, String tipoBeneficio){
		EntityManager em = getEm();
		List<CheckList> lista = new ArrayList<>();
		try{
			Query q = getEm().createNativeQuery("select * from CheckList where REL_pessoasFuncionais_NUMG_idDoObjeto = :idFuncional and ENuM_tipoBeneficio = :tipoBeneficio",CheckList.class);
			q.setParameter("idFuncional", idFuncional);
			q.setParameter("tipoBeneficio", tipoBeneficio);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao carregar checklist");
		}
		return lista;
	}

}
