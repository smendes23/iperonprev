package br.com.iperonprev.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;
import br.com.iperonprev.util.jsf.Message;

public class ConvocacaoCensoDao implements Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	EntityTransaction transaction = getEm().getTransaction();
	JdbcUtil conexao;
	
	public void procedureSalvaConvocacaoCenso(String competencia, int tipoBeneficiario, int tipoCenso){
		int mes = Integer.parseInt(competencia.substring(0,2).toString());
		
		try{
				@SuppressWarnings("static-access")
				Connection con = conexao.getInstance().getConnection();
				CallableStatement cs = con.prepareCall("{call dbo.ST_CRIACONVOCACAO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				cs.setString("COMPETENCIA", competencia);
				cs.setInt("TIPOBENEFICIARIO",tipoBeneficiario);
				cs.setInt("TIPOCENSO", tipoCenso);
				cs.setInt("MES", mes);
				cs.registerOutParameter("IDOBJETO", java.sql.Types.INTEGER);
				cs.registerOutParameter("DATACONV", java.sql.Types.DATE);
				cs.registerOutParameter("DATAENVIO", java.sql.Types.DATE);
				cs.registerOutParameter("DATARECEBIMENTO", java.sql.Types.DATE);
				cs.registerOutParameter("DESCCOMPETENCIA", java.sql.Types.DATE);
				cs.registerOutParameter("NUMBENEFICIARIO", java.sql.Types.INTEGER);
				cs.registerOutParameter("VERS", java.sql.Types.INTEGER);
				cs.registerOutParameter("CENSO", java.sql.Types.INTEGER);
				cs.registerOutParameter("FUNCIONAL", java.sql.Types.INTEGER);
				cs.execute();
				cs.close();
			}catch(Exception e){
				e.printStackTrace();
				Message.addErrorMessage("Erro ao executar procedure");
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<ConvocacaoCensoDto> devolveListaDeConvocacao(String competencia,int tipoBeneficiario,int idCenso){
		List<ConvocacaoCensoDto> lista = new ArrayList<ConvocacaoCensoDto>();
		try{
			
			
			Query q = getEm().createNativeQuery("select * from ConvocacaoCenso cc LEFT join CensoPrevidenciario cp"
										+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
										+ " where cc.DESC_competencia = :competencia and "
										+" cc.NUMR_tipoBeneficiario = :tipoBeneficiario and"
										+" cc.NUMR_idCenso_NUMG_idDoObjeto = :idCenso",ConvocacaoCensoDto.class);
			q.setParameter("competencia", competencia);
			q.setParameter("tipoBeneficiario", tipoBeneficiario);
			q.setParameter("idCenso", idCenso);
			lista = q.getResultList();
			System.out.println(lista.size());
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		
		return lista;
	}
	
	public ConvocacaoCensoDto devolveConvocadoPorCpf(String cpf){
		ConvocacaoCensoDto convocacao = new ConvocacaoCensoDto();
		try{
			Query q = getEm().createNativeQuery("select cc.* from ConvocacaoCenso cc "
					+ "inner join PessoasFuncionais pf on cc.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto "
					+ "inner join pessoas p on pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
					+ "where p.NUMR_cpf = :cpf", ConvocacaoCensoDto.class);
			q.setParameter("cpf",cpf);
			convocacao =(ConvocacaoCensoDto)q.getSingleResult();	
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível carregar convocação para o cpf: "+cpf);
		}finally {
			getEm().close();
		}
		return convocacao;
	}
	
	public ConvocacaoCensoDto devolveConvocadoPeloFuncional(int idFuncional){
		ConvocacaoCensoDto convocacao = new ConvocacaoCensoDto();
		try{
			Query q = getEm().createNativeQuery("select * from ConvocacaoCenso  where NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :idFuncional", ConvocacaoCensoDto.class);
			q.setParameter("idFuncional",idFuncional);
			if(!q.getResultList().isEmpty()){
				convocacao =(ConvocacaoCensoDto)q.getSingleResult();	
			}
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Não foi possível carregar convocação");
		}finally {
			getEm().close();
		}
		return convocacao;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ConvocacaoCensoDto> devolveConvocadoComMultiplosParametros(String cpf,int dadosCenso){
		List<ConvocacaoCensoDto> convocacao = new ArrayList<ConvocacaoCensoDto>();
		try{
			
			Query q = getEm().createQuery("select cc from ConvocacaoCenso cc"
					+ " join fetch cc.NUMR_idPessoasFuncionais pf "
					+ " join fetch pf.NUMR_idDoObjetoPessoas p"
					+ " join fetch p.NUMR_idDoObjetoEndereco "
					+ " join fetch cc.NUMR_idCenso dc"
					+ " where p.NUMR_cpf = :cpf and dc.NUMG_idDoObjeto = :dadosCenso",ConvocacaoCensoDto.class);
			
			/*Query q = em.createNativeQuery("select cc.* from ConvocacaoCenso cc "
					+ "inner join PessoasFuncionais pf on cc.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto "
					+ "inner join pessoas p on pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
					+ "where p.NUMR_cpf = :cpf and "
					+ "cc.NUMR_idCenso_NUMG_idDoObjeto = :dadosCenso", ConvocacaoCenso.class);*/
			q.setParameter("cpf",cpf);
			q.setParameter("dadosCenso",dadosCenso);
			convocacao = q.getResultList();	
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível carregar convocação para o cpf: "+cpf);
		}finally {
			getEm().close();
		}
		return convocacao;
	}
	
	public boolean atualizaDataEnvioConvocacao(){
		return false;
	}
	
	public boolean verificaExistenciaConvocado(int idFuncional){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select * from ConvocacaoCenso where NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :idFuncional",
					ConvocacaoCensoDto.class);
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Não foi possível verificar existencia de convocação individual");
		}
		return res;
	}
	
}
