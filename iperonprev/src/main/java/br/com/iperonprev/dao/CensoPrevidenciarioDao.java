package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.controller.dto.RelatorioPensaoDto;
import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;
import br.com.iperonprev.util.jsf.Message;

public class CensoPrevidenciarioDao implements Serializable{

	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
//	EntityTransaction transaction = em.getTransaction();
	JdbcUtil conexao;
	
	public boolean verificaRecadastramento( String cpf,int idCenso){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select cp.* from CensoPrevidenciario cp "
					 +"inner join PessoasFuncionais pf on pf.NUMG_idDoObjeto = cp.NUMR_pessoasFuncionais_NUMG_idDoObjeto "
					 +"inner join Pessoas p on p.NUMG_idDoObjeto = pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto "
					 +"where p.NUMR_cpf = :cpf and cp.NUMR_idCenso_NUMG_idDoObjeto = :idCenso",CensoPrevidenciario.class);
				q.setParameter("cpf", cpf);
				q.setParameter("idCenso", idCenso);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível verificar recadastramento. Erro: "+e.getMessage());
		}
		
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<CensoPrevidenciario> devolveBeneficiarioRecadastrado(String cpf,int idCenso){
		List<CensoPrevidenciario> cp = new ArrayList<>();
		try{
			Query q = getEm().createNativeQuery("select cp.* from CensoPrevidenciario cp "
				 +"inner join PessoasFuncionais pf on pf.NUMG_idDoObjeto = cp.NUMR_pessoasFuncionais_NUMG_idDoObjeto "
				 +"inner join Pessoas p on p.NUMG_idDoObjeto = pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto "
				 +"where p.NUMR_cpf = :cpf and cp.NUMR_idCenso_NUMG_idDoObjeto = :idCenso",CensoPrevidenciario.class);
			q.setParameter("cpf", cpf);
			q.setParameter("idCenso", idCenso);
			
			if(!q.getResultList().isEmpty()){
				cp = q.getResultList();
			}
			
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível verificar recadastramento");
		}finally{
			getEm().close();
		}
		
		return cp;
	}
	
	public List<CensoPrevidenciario> devolveListaRecadastramento(String cpf){
		List<CensoPrevidenciario> cp = new ArrayList<>();
		try{
			Query q = getEm().createNativeQuery("select cp.* from CensoPrevidenciario cp "
				 +"inner join PessoasFuncionais pf on pf.NUMG_idDoObjeto = cp.NUMR_pessoasFuncionais_NUMG_idDoObjeto "
				 +"inner join Pessoas p on p.NUMG_idDoObjeto = pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto "
				 +"where p.NUMR_cpf = :cpf",CensoPrevidenciario.class);
			q.setParameter("cpf", cpf);
			
			if(!q.getResultList().isEmpty()){
				cp = q.getResultList();
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Não carregar lista de recadastramento para este servidor");
		}finally{
			getEm().close();
		}
		
		return cp;
	}
	
	public boolean verificaSevidorFezRecadastramentoAtual(String cpf){
		boolean res = false;
		try{
			Query q = getEm().createNativeQuery("select cp.* from CensoPrevidenciario cp "
				 +"inner join PessoasFuncionais pf on pf.NUMG_idDoObjeto = cp.NUMR_pessoasFuncionais_NUMG_idDoObjeto "
				 +"inner join Pessoas p on p.NUMG_idDoObjeto = pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto "
				 +"inner join DadosCenso dc on cp.NUMR_idCenso_NUMG_idDoObjeto = dc.NUMG_idDoObjeto "
				 +"where p.NUMR_cpf = :cpf and dc.NUMG_idDoObjeto = (select top 1 NUMG_idDoObjeto from DadosCenso order by NUMG_idDoObjeto desc)",CensoPrevidenciario.class);
			q.setParameter("cpf", cpf);
			
			if(!q.getResultList().isEmpty()){
				res = true;
			}
			
		}catch(Exception e){
			Message.addErrorMessage("Não carregar lista de recadastramento para este servidor");
		}finally{
			getEm().close();
		}
		
		return res;
	}
	
	public boolean verificaRecadastramentoPendente( int idConvocacao){
		boolean res = false;
		
		try{
			Query q = getEm().createNativeQuery("select * from CensoPrevidenciario where NUMR_convocacao_NUMG_idDoObjeto = :convocacao",CensoPrevidenciario.class);
			q.setParameter("convocacao", idConvocacao);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar relacionamento entre Censo Previdenciário e Convocação!");
		}
		
		return res;
		
	}
	
	@SuppressWarnings("unchecked")
	public List<CensoPrevidenciario> getListaRecadastramentoPresencial(String competenciaInicio, String competenciaFim,
			int tipoBeneficiario) {
		List<CensoPrevidenciario> lista = new ArrayList<>();
		
		try {
			String atendente = "WEB";
			if (!competenciaInicio.isEmpty() && !competenciaFim.isEmpty()) {
				Query q = getEm().createNativeQuery(
						"select distinct cp.* from ConvocacaoCenso cc inner join CensoPrevidenciario cp"
								+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
								+ " where cc.DESC_competencia between  :competenciaInicio and :competenciaFim and cp.DESC_atendente != :atendente"
								+ " and cc.NUMR_tipoBeneficiario = :tipoBeneficiario",CensoPrevidenciario.class);
				q.setParameter("competenciaInicio", competenciaInicio);
				q.setParameter("competenciaFim", competenciaFim);
				q.setParameter("tipoBeneficiario", tipoBeneficiario);
				q.setParameter("atendente", atendente);
				lista = q.getResultList();
			} else {
				Query q2 = getEm().createNativeQuery(
						"select distinct cp.* from ConvocacaoCenso cc inner join CensoPrevidenciario cp"
								+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
								+ " where cc.DESC_competencia = :competenciaInicio and cp.DESC_atendente != :atendente"
								+ " and cc.NUMR_tipoBeneficiario = :tipoBeneficiario",CensoPrevidenciario.class);
				q2.setParameter("competenciaInicio", competenciaInicio);
				q2.setParameter("tipoBeneficiario", tipoBeneficiario);
				q2.setParameter("atendente", atendente);
				lista = q2.getResultList();
			}

		} catch (Exception e) {
			System.out.println("Erro ao gerar ListaCenso!");
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CensoPrevidenciario> getListaRecadastramentoOnLine(String competenciaInicio, String competenciaFim,
			int tipoBeneficiario) {
		String atendente = "WEB";
		List<CensoPrevidenciario> lista = new ArrayList<>();

		try {
			if (!competenciaInicio.isEmpty() && !competenciaFim.isEmpty()) {
				Query q = getEm().createNativeQuery(
						"select distinct cp.* from ConvocacaoCenso cc inner join CensoPrevidenciario cp"
								+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
								+ " where cc.DESC_competencia between  :competenciaInicio and :competenciaFim and cp.DESC_atendente = :atendente"
								+ " and cc.NUMR_tipoBeneficiario = :tipoBeneficiario",CensoPrevidenciario.class);
				q.setParameter("competenciaInicio", competenciaInicio);
				q.setParameter("competenciaFim", competenciaFim);
				q.setParameter("tipoBeneficiario", tipoBeneficiario);
				q.setParameter("atendente", atendente);
				lista = q.getResultList();
			} else {
				Query q2 = getEm().createNativeQuery(
						"select distinct cp.* from ConvocacaoCenso cc inner join CensoPrevidenciario cp"
								+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
								+ " where cc.DESC_competencia = :competenciaInicio and cp.DESC_atendente = :atendente"
								+ " and cc.NUMR_tipoBeneficiario = :tipoBeneficiario",CensoPrevidenciario.class);
				q2.setParameter("competenciaInicio", competenciaInicio);
				q2.setParameter("tipoBeneficiario", tipoBeneficiario);
				q2.setParameter("atendente", atendente);
				lista = q2.getResultList();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public int getTotalRecadastrados(String competenciaInicio, String competenciaFim,
			int tipoBeneficiario) {
		int res = 0;
		try {
			if (!competenciaInicio.isEmpty() && !competenciaFim.isEmpty()) {
				Query q = getEm().createNativeQuery(
						"select distinct count(cp.NUMG_idDoObjeto) from ConvocacaoCenso cc inner join CensoPrevidenciario cp"
								+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
								+ " where cc.DESC_competencia between  :competenciaInicio and :competenciaFim"
								+ " and cc.NUMR_tipoBeneficiario = :tipoBeneficiario");
				q.setParameter("competenciaInicio", competenciaInicio);
				q.setParameter("competenciaFim", competenciaFim);
				q.setParameter("tipoBeneficiario", tipoBeneficiario);
				res = (int)q.getSingleResult();
			} else {
				Query q2 = getEm().createNativeQuery(
						"select distinct count(cp.NUMG_idDoObjeto) from ConvocacaoCenso cc inner join CensoPrevidenciario cp"
								+ " on cc.NUMG_idDoObjeto = cp.NUMR_convocacao_NUMG_idDoObjeto"
								+ " where cc.DESC_competencia = :competenciaInicio"
								+ " and cc.NUMR_tipoBeneficiario = :tipoBeneficiario");
				q2.setParameter("competenciaInicio", competenciaInicio);
				q2.setParameter("tipoBeneficiario", tipoBeneficiario);
				res = (int)q2.getSingleResult();
			}
		} catch (Exception e) {
			System.out.println("Online:"+e.getMessage());
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<ConvocacaoCensoDto> listaServidoresBeneficiariosNaoRecadastrados(String competenciaInicio, int idCenso) {
		List<ConvocacaoCensoDto> lista = new ArrayList<>();
		
		try {
				Query q = getEm().createNativeQuery(
						"select  * from ConvocacaoCenso  where DESC_competencia = :competencia"
							+" and NUMR_tipoBeneficiario = 1"
							+" and NUMR_idCenso_NUMG_idDoObjeto = :idCenso"
							+" and NUMG_idDoObjeto not in(select cp.NUMR_convocacao_NUMG_idDoObjeto from CensoPrevidenciario cp) ",ConvocacaoCensoDto.class);
				q.setParameter("competencia", competenciaInicio);
				q.setParameter("idCenso", idCenso);
				lista = q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao gerar Lista de Servidores e Beneficiários não recadastrados!");
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<RelatorioPensaoDto> listaPensionistasNaoRecadastrados(String competenciaInicio,int idCenso) {
		List<RelatorioPensaoDto> lista = new ArrayList<>();
		
		try {
				Query q = getEm().createNativeQuery(
						"select distinct p.NUMR_cpf as cpf,"
						+ "p.DESC_nome as nome,"
						+ "pen.NUMR_sequencia as sequencia,"
						+ "pf.DESC_matricula as matricula,"
						+ "p.DATA_nascimento as dataNascimento "
						+ "from ConvocacaoCenso cc " 
						+" inner join PessoasFuncionais pf on cc.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto"
						+" inner join Pessoas p on pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto"
						+" inner join Dependentes d on d.NUMR_idDoObjetoDependentes_NUMG_idDoObjeto = p.NUMG_idDoObjeto"
						+" inner join Pensao pen on d.NUMG_idDoObjeto = pen.NUMR_idDoObjetoDependentes_NUMG_idDoObjeto"
						+" where  cc.NUMR_tipoBeneficiario = 3"
						+" and cc.DESC_competencia = :competencia"
						+" and cc.NUMR_idCenso_NUMG_idDoObjeto = :idCenso  "
						+ "and cc.NUMG_idDoObjeto not in(select cp.NUMR_convocacao_NUMG_idDoObjeto from CensoPrevidenciario cp)",RelatorioPensaoDto.class);
				q.setParameter("competencia", competenciaInicio);
				q.setParameter("idCenso", idCenso);
				if(!q.getResultList().isEmpty()){
					lista = q.getResultList();
				}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao gerar Lista Pensionistas!");
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<CensoPrevidenciario> getListaDeRecadastrados(Date dataInicio, Date dataFim,
			int tipoBeneficiario,int tipoRecadastramento) {
		List<CensoPrevidenciario> lista = new ArrayList<>();
		System.out.println(dataInicio);
		System.out.println(dataFim);
		try {
				String recadastramento = "!= 'WEB'";
				
				if(tipoRecadastramento == 2){
					recadastramento = "= 'WEB'";
				}
				
				Query q = getEm().createNativeQuery(
						"select cp.* from CensoPrevidenciario cp"
						+" inner join PessoasFuncionais pf"
						+" on cp.NUMR_pessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto"
						+" where cp.DATA_recadastramento between  :dataInicio and :dataFim and "
						+ " pf.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = :tipoBeneficiario and DESC_atendente "+recadastramento,CensoPrevidenciario.class);
				q.setParameter("dataInicio",dataInicio);
				q.setParameter("dataFim", dataFim);
				q.setParameter("tipoBeneficiario", tipoBeneficiario);
				lista = q.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao gerar ListaCenso!");
		}
		return lista;
	}
	
	public CensoPrevidenciario getCensoIdFuncional(int idFuncional, int idCenso) {
		CensoPrevidenciario censo = new CensoPrevidenciario();
		
		try {
			
				Query q = getEm().createNativeQuery(
						"select * from CensoPrevidenciario where NUMR_pessoasFuncionais_NUMG_idDoObjeto = :idFuncional and "
						+ "NUMR_idCenso_NUMG_idDoObjeto = :idCenso",CensoPrevidenciario.class);
				q.setParameter("idFuncional",idFuncional);
				q.setParameter("idCenso",idCenso);
				if(!q.getResultList().isEmpty()){
					censo = (CensoPrevidenciario) q.getResultList().get(0);
				}

		} catch (Exception e) {
			System.out.println("Erro ao buscar censo previdenciario. Erro: "+e.getMessage());
		}finally {
			getEm().close();
		}
		return censo;
	}
	
}
