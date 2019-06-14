package br.com.iperonprev.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.util.jpa.EntityManagerProducer;

public class CensoDao implements Serializable {
	private EntityManager getEm(){
		return EntityManagerProducer.getInstance().getEntityManager();
	}
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<DadosCenso> devolveListaCenso(Date dataFim) {
		List<DadosCenso> lista = new ArrayList<DadosCenso>();
		try {
			Query q = getEm().createNativeQuery(
					"select * from DadosCenso where GETDATE() >= DATA_inicio and   GETDATE() <= :dataFim",
					DadosCenso.class);
			q.setParameter("dataFim", dataFim);
			lista = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lista;
	}

	public boolean verificaExistenciaCenso(Date dtInicio, Date dtFim) {
		boolean res = false;
		try {
			Query q = getEm().createNativeQuery("select * from DadosCenso where DATA_inicio = :inicio or DATA_fim = :fim",
					DadosCenso.class);
			q.setParameter("inicio", dtInicio);
			q.setParameter("fim", dtFim);
			if (!q.getResultList().isEmpty()) {
				res = true;
			}
		} catch (Exception e) {
			System.out.println("Não foi possível verificar o censo");
		} finally {
			if (getEm().isOpen()) {
				getEm().close();
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	public List<DadosCenso> devolveListaCensoPendente(String cpf)
	  {
		System.out.println(cpf);
	    List<DadosCenso> lista = new ArrayList<DadosCenso>();
	    try
	    {
	      Query q = getEm().createNativeQuery(
	        "select * from DadosCenso where NUMG_idDoObjeto not in  (select dc.NUMG_idDoObjeto from DadosCenso dc inner join CensoPrevidenciario cp on dc.NUMG_idDoObjeto = cp.NUMR_idCenso_NUMG_idDoObjeto inner join PessoasFuncionais pf on pf.NUMG_idDoObjeto = cp.NUMR_pessoasFuncionais_NUMG_idDoObjeto inner join Pessoas p on p.NUMG_idDoObjeto = pf.NUMR_idDoObjetoPessoas_NUMG_idDoObjeto where p.NUMR_cpf = :cpf)", 
	        
	        DadosCenso.class);
	      q.setParameter("cpf", cpf);
	      if (!q.getResultList().isEmpty()) {
	        lista = q.getResultList();
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      System.out.println("N�o foi possivel carregar o censo pendente.");
	    }
	    return lista;
	  }
	
	public DadosCenso devolveCenso(Date dataInicio){
		DadosCenso dc = new DadosCenso();
		try {
			Query q = getEm().createQuery("select d from DadosCenso d where d.DATA_inicio = :inicio",
					DadosCenso.class);
			q.setParameter("inicio", dataInicio);
			if (!q.getResultList().isEmpty()) {
				dc =(DadosCenso) q.getResultList().get(0);
			}
		} catch (Exception e) {
			System.out.println("Não foi possível verificar o censo");
		} finally {
			if (getEm().isOpen()) {
				getEm().close();
			}
		}
		return dc;
	}
	
	public DadosCenso devolveUltimoCenso(){
		DadosCenso dc = new DadosCenso();
		try {
			Query q = getEm().createQuery("select top 1 * from DadosCenso order by NUMG_idDoObjeto desc",
					DadosCenso.class);
			if (!q.getResultList().isEmpty()) {
				dc =(DadosCenso) q.getResultList().get(0);
			}
		} catch (Exception e) {
			System.out.println("Não foi possível verificar o censo");
		} finally {
			if (getEm().isOpen()) {
				getEm().close();
			}
		}
		return dc;
	}
	
	
	
}
