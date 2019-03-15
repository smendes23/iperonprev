package br.com.iperonprev.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.Financeiro;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;
import br.com.iperonprev.util.jsf.Message;

public class ContribuicaoDao implements GenericDao<Financeiro>,Serializable{

	JdbcUtil conexao;
	
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Financeiro obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Financeiro> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Financeiro> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	public ContribuicoeseAliquotas devolveContribuicao(String competencia,int id){
		ContribuicoeseAliquotas contribuicao = new ContribuicoeseAliquotas();
		try{
				Query q = getEm().createQuery("select c from ContribuicoeseAliquotas c  join c.NUMR_idPessoasFuncionais pf "
											  +"where pf.NUMG_idDoObjeto = :idFuncional "
											  + "order by c.NUMG_idDoObjeto desc",ContribuicoeseAliquotas.class);
				q.setParameter("idFuncional", id);
				
				if(!q.getResultList().isEmpty()){
					contribuicao = (ContribuicoeseAliquotas)q.getResultList().get(0);
				}else{
					Query q2 = getEm().createNativeQuery("select * from ContribuicoeseAliquotas c, PessoasFuncionais pf "
													+"where c.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto and"
													+"pf.NUMG_idDoObjeto = :idFuncional");
					q2.setParameter("idFuncional", id);
					
					contribuicao = (ContribuicoeseAliquotas)q2.getResultList().get(q2.getResultList().size()-1);
					contribuicao.setNUMG_idDoObjeto(contribuicao.getNUMG_idDoObjeto()+1);
				}
				contribuicao.setVALR_multaSegurado(new BigDecimal("0.00"));
				contribuicao.setVALR_multaPatronal(new BigDecimal("0.00"));
				contribuicao.setVALR_jurosSegurado(new BigDecimal("0.00"));
				contribuicao.setVALR_jurosPatronal(new BigDecimal("0.00"));
				
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao consultar contribuições!");
		}finally{
			getEm().close();
		}
		return contribuicao;
	}
	
	public boolean verificaExistenciaContribuicao(int idFuncional){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select top 1 * from ContribuicoeseAliquotas where NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :funcional",ContribuicoeseAliquotas.class);
			q.setParameter("funcional", idFuncional);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao consultar contribuição");
		}
		return res;
	}
	
	public List<ContribuicoeseAliquotas> devolveListaContribuicoes(int idFuncional){
		List<ContribuicoeseAliquotas> lista = new ArrayList<>();
		
		try{
			@SuppressWarnings("static-access")
			Connection con = conexao.getInstance().getConnection();
			String sql = "select * from ContribuicoeseAliquotas where NUMR_idPessoasFuncionais_NUMG_idDoObjeto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idFuncional);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ContribuicoeseAliquotas cont = new ContribuicoeseAliquotas();
				cont.setNUMG_idDoObjeto(rs.getInt(1));
				cont.setBOL_contribuicaoInvalida(rs.getInt(2) == 0?false:true);
				cont.setDESC_competencia(rs.getString(3));
				cont.setNUMR_aliquotaPatronal(rs.getFloat(4));
				cont.setNUMR_aliquotaSegurado(rs.getFloat(5));
				cont.setNUMR_versao(rs.getInt(6));
				cont.setVALR_contribPatronal(rs.getBigDecimal(7));
				cont.setVALR_contribSegurado(rs.getBigDecimal(8));
				cont.setVALR_contribuicaoPrevidenciaria(rs.getBigDecimal(9));
				cont.setVALR_devolPatronal(rs.getBigDecimal(10));
				cont.setVALR_devolSegurado(rs.getBigDecimal(11));
				cont.setVALR_jurosPatronal(rs.getBigDecimal(12));
				cont.setVALR_jurosSegurado(rs.getBigDecimal(13));
				cont.setVALR_multaPatronal(rs.getBigDecimal(14));
				cont.setVALR_multaSegurado(rs.getBigDecimal(15));
				cont.setNUMR_idPessoasFuncionais(new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(rs.getInt(16)));
				
				
				lista.add(cont);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Erro ao executar procedure");
		}
	
		return lista;
	}
	
	public List<ContribuicoeseAliquotas> devolveListaContribuicaoPor(int mesInicio,int mesFim, String ano,int idPessoaFuncional){
		List<ContribuicoeseAliquotas> lista = new ArrayList<>();
		
		try {
			
			Query q = getEm().createNativeQuery(" select * from ContribuicoeseAliquotas where " +
						"NUMR_idPessoasFuncionais_NUMG_idDoObjeto  = :idFuncional and convert(int,SUBSTRING(DESC_competencia,1,2)) between :valor1 and :valor2 "+ 
						"and SUBSTRING(DESC_competencia,3,7) = :ano ", ContribuicoeseAliquotas.class );
			q.setParameter("idFuncional", idPessoaFuncional);
			q.setParameter("valor1",mesInicio );
			q.setParameter("valor2",mesFim);
			q.setParameter("ano", ano);
			
			if(!q.getResultList().isEmpty()) {
				lista = q.getResultList();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}

}
