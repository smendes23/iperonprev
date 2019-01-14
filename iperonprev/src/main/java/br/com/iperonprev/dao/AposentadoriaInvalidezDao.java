package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.LocalDate;

import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AposentadoriaPorInvalidez;
import br.com.iperonprev.models.MotivoCessacaoBeneficio;
import br.com.iperonprev.models.MotivoConcessaoBeneficio;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class AposentadoriaInvalidezDao implements GenericDao<AposentadoriaPorInvalidez>,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(AposentadoriaPorInvalidez obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaPorInvalidez> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AposentadoriaPorInvalidez> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<AposentadoriaPorInvalidez>(AposentadoriaPorInvalidez.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}

	public List<MotivoConcessaoBeneficio> devolveListaConcessaoBeneficio(){
		return new GenericPersistence<MotivoConcessaoBeneficio>(MotivoConcessaoBeneficio.class).listarTodos("MotivoConcessaoBeneficio");
	}
	
	public List<MotivoCessacaoBeneficio> devolveListaCessacaoBeneficio(){
		return new GenericPersistence<MotivoCessacaoBeneficio>(MotivoCessacaoBeneficio.class).listarTodos("MotivoCessacaoBeneficio");
	}
	
	public List<TipoProventosEnum> devolveListaDeTiposDeProventos(){
		return Arrays.asList(TipoProventosEnum.values());
	}
	
	public boolean verificaExistenciaAposentadoria(int idFuncional){
		boolean res = false;
		EntityManager em = getEm();
		try{
			Query q = em.createNativeQuery("select * from AposentadoriaPorInvalidez where NUMR_idDoObejtoPessoasFuncionais_NUMG_idDoObjeto = :idFuncional");
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			Message.addErrorMessage("Erro ao verificar a existência da aposentadoria");
		}finally {
			em.close();
		}
		return res;
	}
	
	public AposentadoriaPorInvalidez buscaObjetoRelacionamento(String tabela, String campo, int valor){
		AposentadoriaPorInvalidez invalidez = new AposentadoriaPorInvalidez();
		Query q = getEm().createQuery("select o from " + tabela + " o JOIN o." + campo + " c where c.id = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			invalidez = (AposentadoriaPorInvalidez) q.getSingleResult();
		}
		getEm().close();
		return invalidez;
	}
	
	public AposentadoriaPorInvalidez buscaObjetoComUmParametro(String tabela, String parametro,String valor){
		AposentadoriaPorInvalidez invalidez = new AposentadoriaPorInvalidez();
		Query q = getEm().createQuery("select o from " + tabela + " o  where o."+parametro+" = :valor");
		q.setParameter("valor", valor);
		if(!q.getResultList().isEmpty()){
			invalidez = (AposentadoriaPorInvalidez) q.getSingleResult();
		}
		getEm().close();
		return invalidez;
	}
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public List<AposentadoriaPorInvalidez> listaInvalidezComParametros(int proventos,int reajuste,int status){
		List<AposentadoriaPorInvalidez> lista = new ArrayList<>();
		try{
			
			String consulta = new String();
			
			TipoProventosEnum prov = proventos == 1 ? TipoProventosEnum.INTEGRAL : TipoProventosEnum.PROPORCIONAL;
			TipoReajusteEnum reaj = reajuste == 1 ? TipoReajusteEnum.CONFORMELEI : TipoReajusteEnum.PARIDADE;
			
			Date dataI = new LocalDate().now().toDate();
			Date dataF = new LocalDate().plusMonths(3).now().toDate();
			
			if(proventos > 0){
				consulta = "select * from AposentadoriaPorInvalidez where ENUM_proventos = :proventos ";
				if(reajuste > 0 && status == 0){
					consulta = "select * from AposentadoriaPorInvalidez where ENUM_proventos = :proventos and ENUM_reajuste = :reajuste";	
				}else if(reajuste > 0 && status > 0){
					if(status == 1){
						consulta = "select * from AposentadoriaPorInvalidez where ENUM_proventos = :proventos and ENUM_reajuste = :reajuste and"
								+ " DATA_proximaPericia > :dataF";
					}else if(status == 2){
						consulta = "select * from AposentadoriaPorInvalidez where ENUM_proventos = :proventos and ENUM_reajuste = :reajuste and"
								+ " DATA_proximaPericia > = :dataI and DATA_proximaPericia <= :dataF";
					}else{
						consulta = "select * from AposentadoriaPorInvalidez where ENUM_proventos = :proventos and ENUM_reajuste = :reajuste and"
								+ " DATA_proximaPericia <= :dataI";
					}
				}
			}else{
				consulta = "select * from AposentadoriaPorInvalidez";
			}
			
			Query q = getEm().createNativeQuery(consulta,AposentadoriaPorInvalidez.class);
			
			if(proventos > 0){
				q.setParameter("proventos",new StringBuilder().append(prov).toString());
				if(reajuste > 0 && status == 0){
					q.setParameter("reajuste", new StringBuilder().append(reaj).toString());
				}else if(reajuste > 0 && status > 0){
					q.setParameter("reajuste", new StringBuilder().append(reaj).toString());
					if(status == 1){
						q.setParameter("dataF", dataF);
					}else if(status == 2){
						q.setParameter("dataI", dataI);
						q.setParameter("dataF", dataF);
					}else{
						q.setParameter("dataI", dataI);
					}
				}
			}
			lista = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
}