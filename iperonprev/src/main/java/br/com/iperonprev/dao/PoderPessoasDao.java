package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.PoderPessoas;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class PoderPessoasDao implements GenericDao<PoderPessoas>,Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(PoderPessoas obj) {
		try{
			new GenericPersistence<Object>(Object.class).salvar(obj);
			Message.addSuccessMessage("Gestor cadastrado com sucesso!");
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao cadastrar o gestor!"+e.getMessage());
		}
		
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PoderPessoas> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<PoderPessoas>(PoderPessoas.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<PoderPessoas> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Orgaos> listaDeOrgaosGestores(){
		List<Orgaos> lista = new ArrayList<>();
		try{
			Query q = getEm().createNativeQuery("select * from Orgaos where NUMG_idDoObjeto in(3564,2475,2467,1,29,31,32,28)",Orgaos.class);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar órgãos gestores");
		}
		
		return lista;
	}

	public PoderPessoas devolvePessoaPoder(Date dataAto,int idOrgao){
		System.out.println("Data do Ato: "+dataAto);
		System.out.println("Orgao: "+idOrgao);
		PoderPessoas poder = new PoderPessoas();
		try{
			
			Query q = getEm().createNativeQuery("select pp.* from PoderPessoas pp inner join Orgaos o on"
					+ " pp.REL_orgao_NUMG_idDoObjeto = o.NUMG_idDoObjeto"
					+ " where :dataAto between pp.DATA_inicioMandato and pp.DATA_fimMandato"
					+ " and o.NUMG_idDoObjeto = :idOrgao",PoderPessoas.class);
			q.setParameter("dataAto", dataAto);
			q.setParameter("idOrgao", idOrgao);
			if(!q.getResultList().isEmpty()){
				poder = (PoderPessoas) q.getResultList().get(0);
			}else{
				Query q2 = getEm().createNativeQuery("select pp.* from PoderPessoas pp inner join Orgaos o on"
						+ " pp.REL_orgao_NUMG_idDoObjeto = o.NUMG_idDoObjeto"
						+ " where :dataAto between pp.DATA_inicioMandato and pp.DATA_fimMandato"
						+ " and o.NUMG_idDoObjeto = :idOrgao",PoderPessoas.class);
				q2.setParameter("dataAto", dataAto);
				q2.setParameter("idOrgao", 3564);
				
				if(!q2.getResultList().isEmpty()){
					poder = (PoderPessoas) q2.getResultList().get(0);
				}
				/*else{
					Query q3 = em.createNativeQuery("select top 1 * from PoderPessoas "
							+ " where DATA_inicioMandato <= :dataAto"
//							+ " and DATA_fimMandato is null"
							+ " and REL_orgao_NUMG_idDoObjeto = 3564",PoderPessoas.class);
					q3.setParameter("dataAto", dataAto);
					poder = (PoderPessoas) q3.getResultList().get(0);
				}*/
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao buscar Ato Concessório!");
		}
		
		return poder;
	}
}
