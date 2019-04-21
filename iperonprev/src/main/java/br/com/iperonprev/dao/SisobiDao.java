package br.com.iperonprev.dao;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.controller.dto.SisobiDto;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.Sisobi;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;
import br.com.iperonprev.util.jsf.Message;

public class SisobiDao implements GenericDao<Sisobi>,Serializable {
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private static final long serialVersionUID = 1L;
	JdbcUtil conexao;

	@Override
	public void salvaObjeto(Sisobi obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sisobi> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sisobi> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub
		
	}
	public void procedureSisobi(String nomeArquivo){
		StringBuilder sb = new StringBuilder().append(nomeArquivo);
        sb.delete(0, 3).delete(6, 10);
			try{
				@SuppressWarnings("static-access")
				Connection con = conexao.getInstance().getConnection();
				CallableStatement cs = con.prepareCall("{call dbo.ST_SISOBI(?,?)}");
				cs.setString("NOMEARQUIVO","C:\\SISOBI\\"+nomeArquivo);
				cs.setString("COMPETENCIA",new StringBuilder().append(sb.substring(4, 6)).append(sb.substring(0,4)).toString());
				cs.execute();
				cs.close();
				System.out.println("Sucesso!");
//				Message.addSuccessMessage("Sisobi gerado com sucesso!");
			}catch(Exception e){
				e.printStackTrace();
				Message.addErrorMessage("Erro ao executar procedure Sisobi");
			}
	}
	
	@SuppressWarnings("unchecked")
	public List<Sisobi> devolveListaDeSisobiComBetween(String competenciaInicio, String competenciaFim){
		List<Sisobi> lista = new ArrayList<Sisobi>();
		try{
			Query q = getEm().createQuery("select s from Sisobi s where  s.DESC_competencia between :parametro1 and :parametro2",Sisobi.class);
			q.setParameter("parametro1", competenciaInicio);
			q.setParameter("parametro2", competenciaFim);
			lista = q.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			getEm().close();
		}
		
		return lista;
	}
	
	
	@SuppressWarnings("static-access")
	public List<SisobiDto> devolveListaDeSisobi(String competenciaInicio){
		List<SisobiDto> lista = new ArrayList<SisobiDto>();
		try{
			Connection con = conexao.getInstance().getConnection();
			String sql ="select  p.NUMR_cpf as cpf, " + 
					"		p.DESC_nome AS nome, " + 
					"		pf.DESC_matricula as matricula, " + 
					"		sp.DESC_descricao AS situacao, " + 
					"		p.DESC_mae as mae, " + 
					"		p.DATA_nascimento as nascimento, " + 
					"		s.DATA_obito as obito, " + 
					"		s.DESC_competencia AS competencia " + 
					"		 from Sisobi s, Pessoas p, PessoasFuncionais pf, SituacaoPrevidenciaria sp where " + 
					"						s.pessoaId = p.NUMG_idDoObjeto and " + 
					"						pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto = p.NUMG_idDoObjeto and " + 
					"						pf.NUMR_situacaoPrevidenciaria_NUMG_idDoObjeto = sp.NUMG_idDoObjeto and " + 
					"						s.DESC_competencia = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, competenciaInicio);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SisobiDto siso = new SisobiDto();
				siso.setCpf(rs.getString(1));
				siso.setNome(rs.getString(2));
				siso.setMatricula(rs.getString(3));
				siso.setSituacao(rs.getString(4));
				siso.setMae(rs.getString(5));
				siso.setNascimento(rs.getDate(6));
				siso.setObito(rs.getDate(7));
				siso.setCompetencia(rs.getString(8));
				lista.add(siso);
			}
			rs.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sisobi> devolveListaSisobiCpf(String cpf){
		List<Sisobi> listaSisobi = new ArrayList<Sisobi>();
		try{
			Query q = getEm().createNativeQuery("select distinct s.* from Sisobi s inner join  Pessoas p on s.pessoaId = p.NUMG_idDoObjeto "
					+" where p.NUMR_cpf like "+"'%"+cpf+"%'",Sisobi.class);
			if(!q.getResultList().isEmpty()){
				listaSisobi = q.getResultList();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listaSisobi;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sisobi> devolveListaSisobiNome(String nome){
		List<Sisobi> listaSisobi = new ArrayList<Sisobi>();
		try{
			Query q = getEm().createNativeQuery("select distinct s.* from Sisobi s inner join  Pessoas p on s.pessoaId = p.NUMG_idDoObjeto "
					+" where p.DESC_nome like '%"+nome+"%'",Sisobi.class);
			if(!q.getResultList().isEmpty()){
				listaSisobi = q.getResultList();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listaSisobi;
	}
	
	@SuppressWarnings("unchecked")
	public List<Sisobi> devolveListaSisobiNomeDaMae(String mae){
		List<Sisobi> listaSisobi = new ArrayList<Sisobi>();
		try{
			Query q = getEm().createNativeQuery("select distinct s.* from Sisobi s inner join  Pessoas p on s.pessoaId = p.NUMG_idDoObjeto "
					+" where p.DESC_mae like '%"+mae+"%'",Sisobi.class);
			if(!q.getResultList().isEmpty()){
				listaSisobi = q.getResultList();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return listaSisobi;
	}
}
