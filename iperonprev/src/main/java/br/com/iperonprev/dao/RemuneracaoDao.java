package br.com.iperonprev.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.controller.dto.VerbasRubricasDto;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Remuneracoes;
import br.com.iperonprev.models.Rubricas;
import br.com.iperonprev.util.jpa.EntityManagerProducer;
import br.com.iperonprev.util.jpa.JdbcUtil;
import br.com.iperonprev.util.jsf.Message;

public class RemuneracaoDao implements GenericDao<Remuneracoes>, Serializable {
	private EntityManager getEm() {
		return EntityManagerProducer.getInstance().getEntityManager();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public void salvaObjeto(Remuneracoes obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object buscaObjetoIndividual(int idDoObjeto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remuneracoes> buscaTodosObjetos(String nomeDaTabela) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Remuneracoes> listaRelacionamenoDoObjeto(String tabela, String campo, int valor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeObjeto(int id) {
		// TODO Auto-generated method stub

	}

	JdbcUtil conexao;

	public List<Remuneracoes> devolveListaDeRemuneracoesComBetween(String parametro1, String parametro2,
			int idFuncional) {
		List<Remuneracoes> lista = new ArrayList<Remuneracoes>();

		try {
			@SuppressWarnings("static-access")
			Connection con = conexao.getInstance().getConnection();
			String sql = "select * from Remuneracoes  where NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = ? and NUMR_competencia = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idFuncional);
			ps.setString(2, parametro1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Remuneracoes rem = new Remuneracoes();
				rem.setNUMG_idDoObjeto(rs.getInt(1));
				rem.setFLAG_decimoTerceiro(rs.getInt(2));
				rem.setNUMR_competencia(rs.getString(3));
				rem.setNUMR_folha(rs.getInt(4));
				rem.setNUMR_versao(rs.getInt(5));
				rem.setVALR_remuneracao(rs.getBigDecimal(6));
				rem.setNUMR_idDoObjetoFuncional(
						new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(rs.getInt(7)));
				rem.setNUMR_rubrica(new GenericPersistence<Rubricas>(Rubricas.class).buscarPorId(rs.getInt(8)));
				lista.add(rem);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar procedure");
		}
		/*
		 * try { Query q = getEm().createQuery(
		 * "select r from Remuneracoes r join r.NUMR_idDoObjetoFuncional f where f.NUMG_idDoObjeto = :idFuncional and r.NUMR_competencia = :parametro1"
		 * , Remuneracoes.class); q.setParameter("idFuncional", idFuncional);
		 * q.setParameter("parametro1", parametro1); lista = q.getResultList(); } catch
		 * (Exception e) {
		 * System.out.println("Erro ao devolver lista de Remunerações com Between."); }
		 * finally { getEm().close(); }
		 */

		return lista;
	}

	public boolean verificaSeExisteRegistroNaTabela(int idFuncional) {
		boolean res = false;
		try {
			Query q = getEm().createNativeQuery(
					"select top 1 * from ContribuicoeseAliquotas where NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :idFuncional");
			q.setParameter("idFuncional", idFuncional);
			if (!q.getResultList().isEmpty()) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar a existência de registro na tabela.");
		} finally {
			getEm().close();
		}

		return res;
	}

	public boolean verificaSeExisteRemuneracoes(int valor) {
		boolean res = false;
		try {
			Query q = getEm().createNativeQuery(
					"select top 1 * from Remuneracoes where NUMR_idDoObjetoFuncional_NUMG_idDoObjeto =  :valor",
					Remuneracoes.class);
			q.setParameter("valor", valor);
			if (!q.getResultList().isEmpty()) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar a existência de remunerações.");
		} finally {
			getEm().close();
		}

		return res;
	}

	@SuppressWarnings("static-access")
	public List<Remuneracoes> devolveListaRemuneracoesContribuicao(int idFuncional) {

		List<Remuneracoes> lista = new ArrayList<Remuneracoes>();

		try {
			Connection con = conexao.getInstance().getConnection();
			String sql = "select r.* from Remuneracoes r,PessoasFuncionais pf where "
					+ " r.NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = pf.NUMG_idDoObjeto and"
					+ " r.NUMR_rubrica_NUMG_idDoObjeto in (254,2237,5450,5456,1875,5446,5448,5450,1821,1847,1875,3722,3208,6484,6485,6680,6666,6634,6615,6598) and"
					+ " pf.NUMG_idDoObjeto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idFuncional);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Remuneracoes rem = new Remuneracoes();
				rem.setNUMG_idDoObjeto(rs.getInt(1));
				rem.setFLAG_decimoTerceiro(rs.getInt(2));
				rem.setNUMR_competencia(rs.getString(3));
				rem.setNUMR_folha(rs.getInt(4));
				rem.setNUMR_versao(rs.getInt(5));
				rem.setVALR_remuneracao(rs.getBigDecimal(6));
				rem.setNUMR_idDoObjetoFuncional(
						new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(rs.getInt(7)));
				rem.setNUMR_rubrica(new GenericPersistence<Rubricas>(Rubricas.class).buscarPorId(rs.getInt(8)));
				lista.add(rem);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar procedure");
		}
		/*
		 * try { Query q = getEm().
		 * createNativeQuery("select r.* from Remuneracoes r,PessoasFuncionais pf where "
		 * + " r.NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = pf.NUMG_idDoObjeto and" +
		 * " r.NUMR_rubrica_NUMG_idDoObjeto in (254,5450,5456,1875,5446,5448,5450,1821,1847,1875,3722,3208,6484,6485) and"
		 * + " pf.NUMG_idDoObjeto = :idFuncional", Remuneracoes.class);
		 * q.setParameter("idFuncional", idFuncional); lista = q.getResultList(); }
		 * catch (Exception e) {
		 * System.out.println("Erro ao devolver a lista de remunerações."); } finally {
		 * if (getEm().isOpen()) { getEm().close(); } }
		 */

		return lista;
	}

	public ContribuicoeseAliquotas devolveContribuicaoeAliquota(int idFuncional, String competencia) {
		ContribuicoeseAliquotas contribuicao = new ContribuicoeseAliquotas();
		try {

			Query q = getEm().createQuery(
					"select c from ContribuicoeseAliquotas c JOIN c.NUMR_idPessoasFuncionais f where f.NUMG_idDoObjeto = :valor1 and c.DESC_competencia = :valor2");
			q.setParameter("valor1", idFuncional);
			q.setParameter("valor2", competencia);
			contribuicao = (ContribuicoeseAliquotas) q.getResultList().get(0);
		} catch (Exception e) {
			System.out.println("Erro ao devolver contribuição");
		} finally {
			if (getEm().isOpen()) {
				getEm().close();
			}
		}
		return contribuicao;
	}

	@SuppressWarnings("unchecked")
	public List<ContribuicoeseAliquotas> devolveOitentaMaiores(int idPortaria, String matricula) {
		List<ContribuicoeseAliquotas> lista = new ArrayList<ContribuicoeseAliquotas>();
		try {
			Query q = getEm().createNativeQuery(
					"select ca.* from ContribuicoeseAliquotas ca, Indice i, Portaria p,PessoasFuncionais pf where "
							+ "	i.portaria_NUMG_idDoObjeto = p.NUMG_idDoObjeto and"
							+ " ca.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto and"
							+ " ca.DESC_competencia = i.NUMR_mesAno and" + " p.NUMG_idDoObjeto = :portaria and"
							+ " pf.DESC_matricula = :matricula " + " order by ca.VALR_contribuicaoPrevidenciaria desc",
					ContribuicoeseAliquotas.class);
			q.setParameter("portaria", idPortaria);
			q.setParameter("matricula", matricula);
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao listar oitenta maiores");
		} finally {
			getEm().close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	public List<ContribuicoeseAliquotas> devolveContribuicoesComPortaria(int idPortaria, int idFuncional) {
		List<ContribuicoeseAliquotas> lista = new ArrayList<ContribuicoeseAliquotas>();
		try {
			Query q = getEm().createNativeQuery(
					"select ca.* from ContribuicoeseAliquotas ca, Indice i, Portaria p,PessoasFuncionais pf where "
							+ "	i.portaria_NUMG_idDoObjeto = p.NUMG_idDoObjeto and"
							+ " ca.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto and"
							+ " ca.DESC_competencia = i.NUMR_mesAno and" + " p.NUMG_idDoObjeto = :portaria and"
							+ " pf.NUMG_idDoObjeto = :idFuncional",
					ContribuicoeseAliquotas.class);
			q.setParameter("portaria", idPortaria);
			q.setParameter("idFuncional", idFuncional);
			lista = q.getResultList();
		} catch (Exception e) {
			System.out.println("Erro ao listar contribuições");
		} finally {
			getEm().close();
		}
		return lista;
	}

	public ContribuicoeseAliquotas devolveUltimaContribuicao(String matricula) {
		ContribuicoeseAliquotas ca = new ContribuicoeseAliquotas();
		try {
			Query q = getEm().createNativeQuery(
					"select top 1 ca.* from " + " ContribuicoeseAliquotas ca,PessoasFuncionais pf where "
							+ "ca.NUMR_idPessoasFuncionais_NUMG_idDoObjeto = pf.NUMG_idDoObjeto and "
							+ "pf.DESC_matricula = :matricula order by ca.NUMG_idDoObjeto desc",
					ContribuicoeseAliquotas.class);
			q.setParameter("matricula", matricula);
			if (!q.getResultList().isEmpty()) {
				ca = (ContribuicoeseAliquotas) q.getResultList().get(0);
			} else {
				Message.addErrorMessage("Não existe contribuição para este funcional");
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar contribuições");
		}
		return ca;
	}
	
	@SuppressWarnings("unchecked")
	public List<ContribuicoeseAliquotas> listaContribuicoesPorAno(String ano,int idFuncional) {
		List<ContribuicoeseAliquotas> lista = new ArrayList<ContribuicoeseAliquotas>();
		try {
			Query q = getEm().createNativeQuery(
					"select * from ContribuicoeseAliquotas where SUBSTRING(DESC_competencia,3,6) = :ano and NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :idFuncional",
					ContribuicoeseAliquotas.class);
			q.setParameter("ano", ano);
			q.setParameter("idFuncional", idFuncional);
			if (!q.getResultList().isEmpty()) {
				lista = q.getResultList();
			} 
		} catch (Exception e) {
			System.out.println("Erro ao carregar contribuições");
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<Remuneracoes> listaRemuneracoesPorAno(String ano,int idFuncional) {
		List<Remuneracoes> lista = new ArrayList<Remuneracoes>();
		try {
			Query q = getEm().createNativeQuery(
					"select distinct re.* from " + 
					"	Remuneracoes re " + 
					"where " + 
					"	NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = :idFuncional " + 
					"and " + 
					"	SUBSTRING(NUMR_competencia,3,6) = :ano " + 
					"and " + 
					"	NUMR_rubrica_NUMG_idDoObjeto " + 
					"in " + 
					"	(254,5450,5456,1875,5446,5448,5450,1821,1847,1875,3722,3208,6484,6485) " + 
					"and re.NUMR_competencia not in (select DESC_competencia from ContribuicoeseAliquotas where SUBSTRING(DESC_competencia,3,6) = :ano and NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :idFuncional)",
					Remuneracoes.class);
			q.setParameter("ano", ano);
			q.setParameter("idFuncional", idFuncional);
			if (!q.getResultList().isEmpty()) {
				lista = q.getResultList();
			} 
		} catch (Exception e) {
			System.out.println("Erro ao carregar Remuneracoes");
		}
		return lista;
	}

	public boolean verificaSeExisteContribuicao(String competencia, int idFuncional) {
		boolean res = false;
		try {
			Query q = getEm()
					.createNativeQuery(
							"select * from ContribuicoeseAliquotas where DESC_competencia = :competencia and "
									+ "NUMR_idPessoasFuncionais_NUMG_idDoObjeto = :idFuncional",
							ContribuicoeseAliquotas.class);
			q.setParameter("competencia", competencia);
			q.setParameter("idFuncional", idFuncional);
			if (!q.getResultList().isEmpty()) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Erro ao carregar contribuições");
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<VerbasRubricasDto> listaVerbasRubricas(String ano, int idFuncional) {
		List<VerbasRubricasDto> lista = new ArrayList<>();
		try {
			Query q = getEm().createNativeQuery("select ru.NUMR_codigo as codigo,ru.DESC_descricao as descricao,"
					+ "r.NUMR_competencia as competencia,r.VALR_remuneracao as valor,r.FLAG_decimoTerceiro as decimoTerceiro from Remuneracoes r"
					+ " join Rubricas ru on r.NUMR_rubrica_NUMG_idDoObjeto = ru.NUMG_idDoObjeto"
					+ " where r.NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = :funcional and"
					+ " SUBSTRING(r.NUMR_competencia,3,5) = :ano ", VerbasRubricasDto.class);
			q.setParameter("funcional", idFuncional);
			q.setParameter("ano", ano);
			if (!q.getResultList().isEmpty()) {
				lista = q.getResultList();
			}
		} catch (Exception e) {
			System.out.println("Erro listar rúbricas.");
		}

		return lista;
	}

	public List<Remuneracoes> devolveListaRemuneracoesContribuicao(int idFuncional, String ano) {

		List<Remuneracoes> lista = new ArrayList<Remuneracoes>();

		try {
			Connection con = conexao.getInstance().getConnection();
			String sql = "select * from Remuneracoes where NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = ? and SUBSTRING(NUMR_competencia,3,6) = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idFuncional);
			ps.setString(2, ano);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Remuneracoes rem = new Remuneracoes();
				rem.setNUMG_idDoObjeto(rs.getInt(1));
				rem.setFLAG_decimoTerceiro(rs.getInt(2));
				rem.setNUMR_competencia(rs.getString(3));
				rem.setNUMR_folha(rs.getInt(4));
				rem.setNUMR_versao(rs.getInt(5));
				rem.setVALR_remuneracao(rs.getBigDecimal(6));
				rem.setNUMR_idDoObjetoFuncional(
						new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class).buscarPorId(rs.getInt(7)));
				rem.setNUMR_rubrica(new GenericPersistence<Rubricas>(Rubricas.class).buscarPorId(rs.getInt(8)));
				lista.add(rem);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao executar procedure");
		}
		return lista;
	}

	public Remuneracoes devolveRemuneracoesContribuicaoPorCompetencia(int idFuncional, int idRubrica,
			String competencia) {
		Remuneracoes remu = new Remuneracoes();
		String comp = competencia.replace("/", "");
		try {
			Connection con = JdbcUtil.getInstance().getConnection();
			String sql = "select * from Remuneracoes where NUMR_idDoObjetoFuncional_NUMG_idDoObjeto = ? and NUMR_competencia = ? and NUMR_rubrica_NUMG_idDoObjeto = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idFuncional);
			ps.setString(2, comp);
			ps.setInt(3, idRubrica);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				remu.setNUMG_idDoObjeto(rs.getInt(1));
				remu.setFLAG_decimoTerceiro(rs.getInt(2));
				remu.setNUMR_competencia(rs.getString(3));
				remu.setNUMR_folha(rs.getInt(4));
				remu.setNUMR_versao(rs.getInt(5));
				remu.setVALR_remuneracao(rs.getBigDecimal(6));
				remu.setNUMR_idDoObjetoFuncional(new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class)
						.buscarPorId(rs.getInt(7)));
				remu.setNUMR_rubrica(new GenericPersistence<Rubricas>(Rubricas.class).buscarPorId(rs.getInt(8)));
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Erro ao buscar remunera��o para essa compet�ncia.");
		}
		return remu;
	}

}
