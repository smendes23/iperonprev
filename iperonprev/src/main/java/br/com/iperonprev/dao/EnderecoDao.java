package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Enderecos;
import br.com.iperonprev.models.Municipios;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class EnderecoDao implements GenericDao<Enderecos>,Serializable{
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Enderecos obj) {
		try{
			new GenericPersistence<Enderecos>(Enderecos.class).salvar(obj);
			Message.addSuccessMessage("Endereço cadastrada com sucesso!");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao cadastrar o endereço!"+ e.getMessage());
		}
		
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enderecos> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Enderecos> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public Municipios devolveMunicipio(String uf){
		Municipios mun = new Municipios();
		try{
			Query q = getEm().createNativeQuery("select m.* from Municipios m inner join Estados e on m.NUMR_idDoObjetoEstado_NUMG_idDoObjeto = e.NUMG_idDoObjeto"
					+ " where e.DESC_sigla = :sigla",Municipios.class);
			q.setParameter("sigla", uf);
			if(!q.getResultList().isEmpty()){
				mun = (Municipios)q.getResultList().get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getEm().close();
		}
		return mun;
	}
	
	
	
	public Municipios devolveMunicipioLike(String localidade){
		Municipios mun = new Municipios();
		try{
			Query q = getEm().createNativeQuery("select m.* from Municipios m inner join Estados e on m.NUMR_idDoObjetoEstado_NUMG_idDoObjeto = e.NUMG_idDoObjeto"
					+ " where m.DESC_nome like '%"+localidade+"%'",Municipios.class);
			if(!q.getResultList().isEmpty()){
				mun = (Municipios)q.getResultList().get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			getEm().close();
		}
		return mun;
	}

}
