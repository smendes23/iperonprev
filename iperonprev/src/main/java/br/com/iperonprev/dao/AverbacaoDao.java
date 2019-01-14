package br.com.iperonprev.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Averbacao;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.services.averbacao.QualificaTempoDeContribuicao;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;

public class AverbacaoDao implements GenericDao<Averbacao>,Serializable{

	
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	@Override
	public void salvaObjeto(Averbacao obj) {
		try{
			Averbacao averbacao = new QualificaTempoDeContribuicao().executa(obj, obj.getNUMR_deducao());
			new GenericPersistence<Object>(Object.class).salvar(averbacao);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Averbacao> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Averbacao> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<Averbacao>(Averbacao.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		new GenericPersistence<Averbacao>(Averbacao.class).remover("Averbacao", "NUMG_idDoObjeto", id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Orgaos> devolveListaDeOrgaosPrevidenciarios(){
		List<Orgaos> lista = new ArrayList<>();
		try{
//			Query q = em.createNativeQuery("select * from Orgaos where NUMR_unidadeGestora = 1",Orgaos.class);
			Query q = getEm().createNativeQuery("select * from Orgaos",Orgaos.class);
			lista = q.getResultList();
		}catch(Exception e){
			System.out.println("Erro ao buscar a lista de Orgaos previdenciários");
		}finally{
			getEm().close();
		}
		return lista;
	}
	JdbcUtil conexao;
	
	@SuppressWarnings("static-access")
	public void limpaAverbacao(int idFuncional){
		try{
			Connection con = conexao.getInstance().getConnection();
			CallableStatement cs = con.prepareCall("{call dbo.SP_LIMPACONCOMITANCIA(?)}");
			cs.setInt("idFuncional", idFuncional);
			cs.execute();
			cs.close();
			System.out.println("Procedure executada com sucesso");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao executar procedure");
		}
	}
}
