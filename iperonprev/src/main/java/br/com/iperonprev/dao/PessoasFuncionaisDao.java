package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.constantes.DecisaoEnum;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.ClasseFuncional;
import br.com.iperonprev.models.DocumentoFuncional;
import br.com.iperonprev.models.NivelFuncional;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.SituacaoFuncional;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jsf.Message;

public class PessoasFuncionaisDao implements GenericDao<PessoasFuncionais>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	@Override
	public void salvaObjeto(PessoasFuncionais obj) {
		new GenericPersistence<Object>(Object.class).salvar(obj);
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PessoasFuncionais> buscaTodosObjetos(String nomeDaTabela) {
		return new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarTodos(nomeDaTabela);
	}

	@Override
	public List<PessoasFuncionais> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		return new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).listarRelacionamento(tabela, campo, valor);
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public List<Orgaos> listaDeOrgaos(){
		return new GenericPersistence<Orgaos>(Orgaos.class).listarTodos("Orgaos");
	}
	
	public List<Cargos> listaDeCargos(){
		return new GenericPersistence<Cargos>(Cargos.class).listarTodos("Cargos");
	}
	
	public List<ClasseFuncional> listaClasseFuncional(){
		return new GenericPersistence<ClasseFuncional>(ClasseFuncional.class).listarTodos("ClasseFuncional");
	}
	
	public List<NivelFuncional> listaNivelFuncional(){
		return new GenericPersistence<NivelFuncional>(NivelFuncional.class).listarTodos("NivelFuncional");
	}
	
	public List<SituacaoFuncional> listaSituacaoFuncional(){
		return new GenericPersistence<SituacaoFuncional>(SituacaoFuncional.class).listarTodos("SituacaoFuncional");
	}
	
	public List<DecisaoEnum> listaAbonoDePermanencia(){
		List<DecisaoEnum> decisao = Arrays.asList(DecisaoEnum.values());
		return decisao;
	}
	
	public PessoasFuncionais devolveFuncional(String matricula){
		PessoasFuncionais funcional = new PessoasFuncionais();
		try{
			Query q = getEm().createQuery("select p from PessoasFuncionais p where p.DESC_matricula = :matricula ");
			q.setParameter("matricula", matricula);
			funcional = (PessoasFuncionais)q.getResultList().get(0);			
		}catch(Exception e){
			Message.addErrorMessage("Matrícula não existe!");
		}finally{
			getEm().close();
		}
		return funcional;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoasFuncionais> devolveListaDeFuncionaisAposentadoPensionista(String cpf)
	  {
	    List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
	    try
	    {
	      Query q = getEm().createNativeQuery("select * from PessoasFuncionais pf inner join Pessoas p on p.NUMG_idDoObjeto = pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto where p.NUMR_cpf = :cpf and pf.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = 2 or p.NUMR_cpf = :cpf and pf.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = 4", 
	      
	        PessoasFuncionais.class);
	      q.setParameter("cpf", cpf);
	      System.out.println(q.getResultList().size());
	      if (!q.getResultList().isEmpty()) {
	        lista = q.getResultList();
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.out.println("Erro ao buscar funcional");
	    }
	    return lista;
	  }
	
	@SuppressWarnings("unchecked")
	public List<PessoasFuncionais> devolveListaDeAposentadosPorCompetenciaAniversario(String competencia){
		List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
		int valor = Integer.parseInt(competencia.substring(0,2).toString());
		try{
			Query  q = getEm().createNativeQuery("select pf.* from pessoas p "+
					"inner join pessoasfuncionais pf on pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "+
					"inner join FuncionaisFuncoes ff on ff.NUMR_idPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "+
					"inner join SituacaoPrevidenciaria sp on ff.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = sp.NUMG_idDoObjeto "+
					"left join enderecos e on p.NUMR_idDoObjetoEndereco_NUMG_idDoObjeto = e.NUMG_idDoObjeto "+
					"left join graudeinstrucao i on p.NUMR_instrucao_NUMG_idDoObjeto = i.NUMG_idDoObjeto "+
					"left join estadocivil c on p.NUMR_estadoCivil_NUMG_idDoObjeto = c.NUMG_idDoObjeto "+
					" where "+
					"sp.NUMR_codigo = 1 and "+
					"MONTH(p.DATA_nascimento) = :valor and "+
					"p.DATA_obito is null ",PessoasFuncionais.class);
			q.setParameter("valor", valor);
			lista = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoasFuncionais> devolveListaDePensionistasPorCompetenciaAniversario(String competencia){
		List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
		int valor = Integer.parseInt(competencia.substring(0,2).toString());
		try{
			Query  q = getEm().createNativeQuery("select p.* from pessoas p "+
						"inner join Dependentes d on d.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "+
						" where "+
						"MONTH(p.DATA_nascimento) = :valor and "+
						"p.DATA_obito is null ",PessoasFuncionais.class);
			q.setParameter("valor", valor);
			lista = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		
		return lista;
	}
	
	public Pessoas devolveAposentado(String competencia, String cpf){
		Pessoas pessoa = new Pessoas();
		int valor = Integer.parseInt(competencia.substring(0,2).toString());
		try{
			Query  q = getEm().createNativeQuery("select p.* from pessoas p "+
						"inner join FuncionaisFuncoes ff on ff.NUMR_idPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "+
						"inner join SituacaoPrevidenciaria sp on ff.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = sp.NUMG_idDoObjeto "+
						"left join enderecos e on p.NUMR_idDoObjetoEndereco_NUMG_idDoObjeto = e.NUMG_idDoObjeto "+
						"left join graudeinstrucao i on p.NUMR_instrucao_NUMG_idDoObjeto = i.NUMG_idDoObjeto "+
						"left join estadocivil c on p.NUMR_estadoCivil_NUMG_idDoObjeto = c.NUMG_idDoObjeto "+
						" where "+
						"sp.NUMR_codigo = 1 and "+
						"MONTH(p.DATA_nascimento) = :valor and "+
						"p.NUMR_cpf = :cpf and"+
						"p.DATA_obito is null ",PessoasFuncionais.class);
			q.setParameter("valor", valor);
			q.setParameter("cpf", cpf);
			pessoa = (Pessoas) q.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		
		return pessoa;
	}
	
	public Pessoas devolveDePensionistas(String competencia,String cpf){
		Pessoas pessoa = new Pessoas();
		int valor = Integer.parseInt(competencia.substring(0,2).toString());
		try{
			Query  q = getEm().createNativeQuery("select p.* from pessoas p "+
						"inner join Dependentes d on d.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "+
						" where "+
						"MONTH(p.DATA_nascimento) = :valor and "+
						"p.DATA_obito is null and"
						+ " p.NUMR_cpf = :cpf",PessoasFuncionais.class);
			q.setParameter("valor", valor);
			q.setParameter("cpf", cpf);
			pessoa = (Pessoas)q.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		
		return pessoa;
	}
	
	public boolean verificaExistenciaFuncional(PessoasFuncionais pf){
		boolean res = false;
		try{
			Query  q = getEm().createNativeQuery("select * from PessoasFuncionais where "
					+ " NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = :idPessoas and"
					+ " DESC_matricula = :matricula");
			q.setParameter("idPessoas", pf.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
			q.setParameter("matricula", pf.getDESC_matricula());
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		return res;
	}
	
	public boolean verificaExistenciaDoFuncional(int idPessoa){
		boolean res = false;
		try{
			Query  q = getEm().createNativeQuery("select * from PessoasFuncionais where "
					+ " NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = :idPessoas ",PessoasFuncionais.class);
			q.setParameter("idPessoas", idPessoa);
			if(!q.getResultList().isEmpty()){
				res = true;
			}
		}catch(Exception e){
			System.out.println("Não foi possivel verificar a existência de um funcional pelo id da pessoa.");
		}finally {
			getEm().close();
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoasFuncionais> devolveFuncionalComClausulaLike(String nome){
		List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
		try{
			Query q = getEm().createNativeQuery("select pf.* from Pessoas p inner join PessoasFuncionais pf "
					+" on pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
					+" where p.DESC_nome like '%"+nome+"%'",PessoasFuncionais.class);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return lista;
	}
	
	public PessoasFuncionais devolveFuncionalAtoConcessorio(String cpf){
		PessoasFuncionais pf = new PessoasFuncionais();
		try{
			Query q = getEm().createNativeQuery("select * from PessoasFuncionais pf, Pessoas p "
					+ "where pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto and "
					+ "ENUM_tipoAposentadoria is  not NULL and "
					+ " p.NUMR_cpf = :cpf",PessoasFuncionais.class);
			q.setParameter("cpf", cpf);
			if(!q.getResultList().isEmpty()){
				pf = (PessoasFuncionais)q.getResultList().get(0);
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return pf;
	}
	
	@SuppressWarnings("unchecked")
	public List<DocumentoFuncional> devolveListaDocumentos(int idFuncional){
		List<DocumentoFuncional> lista = new ArrayList<>();
		try{
			Query q = getEm().createNativeQuery("select * from DocumentoFuncional where REL_funcional_NUMG_idDoObjeto = :idFuncional ",DocumentoFuncional.class);
			q.setParameter("idFuncional", idFuncional);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Não existem documentos");
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoasFuncionais> devolveListaFuncionalComConvocado(String cpf){
		List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
		try{
			Query q = getEm().createNativeQuery("select pf.* from PessoasFuncionais pf "
										+"inner join ConvocacaoCenso cc on "
										+ "cc.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto"
										+ " inner join Pessoas p on "
										+ "pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
										+ "where p.NUMR_cpf = :cpf",PessoasFuncionais.class);
			q.setParameter("cpf", cpf);
	
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<PessoasFuncionais> devolveListaDeFuncionais(String cpf){
		List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
		try{
			Query q = getEm().createNativeQuery("select pf.* from PessoasFuncionais pf "
										+ " inner join Pessoas p on "
										+ "pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
										+ "where p.NUMR_cpf = :cpf",PessoasFuncionais.class);
			q.setParameter("cpf", cpf);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return lista;
	}
	
	
	public PessoasFuncionais devolveFuncionalCpfMatricula(String cpf,String matricula){
		PessoasFuncionais pf = new PessoasFuncionais();
		
		try{
			Query q = getEm().createNativeQuery("select pf.* from PessoasFuncionais pf "
										+ " inner join Pessoas p on "
										+ "pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto "
										+ "where p.NUMR_cpf = :cpf and pf.DESC_matricula = :matricula",PessoasFuncionais.class);
			q.setParameter("cpf", cpf);
			q.setParameter("matricula", matricula);
			if(!q.getResultList().isEmpty()){
				pf = (PessoasFuncionais) q.getResultList().get(0);
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return pf;
	}
	
	public List<PessoasFuncionais> listaDeFuncionaisInstituidorPensao(){
		List<PessoasFuncionais> pf = new ArrayList<PessoasFuncionais>();
		
		try{
			Query q = getEm().createNativeQuery("select  distinct" + 
					" pf2.*	from " + 
					"		PessoasFuncionais pf2" + 
					"	where " + 
					"		pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto " + 
					"	in " + 
					"		(select NUMG_idDoObjeto from Pessoas where DATA_obito is not null)" + 
					"	and " + 
					"		pf2.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto != 2 " + 
					"	order by " + 
					"		pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto",PessoasFuncionais.class);
			if(!q.getResultList().isEmpty()){
				pf =q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return pf;
	}
	
	public List<String> listaDeMatriculasInstituidorPensao(){
		List<String> lista = new ArrayList<String>();
		try{
			Query q = getEm().createNativeQuery("select " + 
					"	STUFF((select ','+pf2.DESC_matricula from PessoasFuncionais pf2 where " + 
					"pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto in " + 
					"(select NUMG_idDoObjeto from Pessoas where DATA_obito is not null) and " + 
					"	pf2.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto != 3 and pf1.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto " + 
					"	or " + 
					"pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto in " + 
					"(select NUMG_idDoObjeto from Pessoas where DATA_obito is not null) and pf2.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto != 5 and pf1.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto " + 
					"order by pf2.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto asc FOR XML PATH, TYPE).value(N'.[1]', N'varchar(max)'),1,2,'') AS MATRICULA " + 
					"from PessoasFuncionais pf1 " + 
					"group by pf1.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto order by pf1.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto");
			if(!q.getResultList().isEmpty()){
				lista =q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return lista;
	}
	
	public List<PessoasFuncionais> listaDeFuncionaisRecadastrados(){
		List<PessoasFuncionais> lista = new ArrayList<PessoasFuncionais>();
		try{
			Query q = getEm().createNativeQuery("select * from PessoasFuncionais where NUMG_idDoObjeto in ( " + 
					"	select NUMR_pessoasFuncionais_NUMG_idDoObjeto from CensoPrevidenciario where NUMR_pessoasFuncionais_NUMG_idDoObjeto in ( " + 
					"select NUMG_idDoObjeto from PessoasFuncionais where NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = 3 and YEAR(DATA_posse) <= '2016' " + 
					") and NUMR_idCenso_NUMG_idDoObjeto = 2 " + 
					")",PessoasFuncionais.class);
			if(!q.getResultList().isEmpty()){
				lista = q.getResultList();
			}
		}catch(Exception e){
			System.out.println("Erro ao buscar funcional");
		}
		
		return lista;
	}
	
}
