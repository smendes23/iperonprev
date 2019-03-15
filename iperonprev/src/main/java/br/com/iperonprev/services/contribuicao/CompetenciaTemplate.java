package br.com.iperonprev.services.contribuicao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import br.com.iperonprev.dao.ContribuicaoDao;
import br.com.iperonprev.models.ContribuicoeseAliquotas;
import br.com.iperonprev.models.PessoasFuncionais;

public abstract class CompetenciaTemplate implements CompetenciaInterface{
	private CompetenciaTemplate proximo;
	protected DateTime dtI;
	protected DateTime dtF;
	protected List<ContribuicoeseAliquotas> listaDeContribuicoes = new ArrayList<>();
	private PessoasFuncionais pf;
	private BigDecimal baseDeCalculo;
	public CompetenciaTemplate() {};
	public CompetenciaTemplate(DateTime dtI,DateTime dtF,PessoasFuncionais pf,BigDecimal baseDeCalculo) {
		this.dtI = dtI;
		this.dtF = dtF;
		this.pf = pf;
		this.baseDeCalculo = baseDeCalculo;
	}
	
	public void executa() {
		if(validaAno()) {
			processaPeriodo();
		}else {
			proximo.executa();
		}
	}

	protected abstract void processaPeriodo();

	protected abstract boolean validaAno();
	
	protected List<ContribuicoeseAliquotas> processaData(DateTime dtI,DateTime dtF) {
		List<ContribuicoeseAliquotas> lista = new ArrayList<>();
		List<ContribuicoeseAliquotas> listaContrib = new ArrayList<>();
		try {
			lista = populaListaDeContribuicoesComCompetencia(dtI, dtF);
			listaContrib = new ContribuicaoDao().devolveListaContribuicaoPor(dtI.getMonthOfYear(), dtF.getMonthOfYear(),Integer.toString(dtI.getYear()), this.pf.getNUMG_idDoObjeto());
			
			if(!listaContrib.isEmpty()) {
				
				for (int i = 0; i < lista.size(); i++) {
					  
					  for (ContribuicoeseAliquotas cont : listaContrib) {
						  ContribuicoeseAliquotas contrib = new ContribuicoeseAliquotas();
						  if(cont.getDESC_competencia().equals(lista.get(i).getDESC_competencia())) {
							  contrib = lista.get(i);
							  contrib.setNUMG_idDoObjeto(cont.getNUMG_idDoObjeto());
							  contrib.setNUMR_versao(cont.getNUMR_versao());
							  lista.set(i, contrib);
						  }
						  
					  }
					  
				  }
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	@Override
	public void setProximo(CompetenciaTemplate proximo) {
		this.proximo = proximo;
	}
	
	private List<ContribuicoeseAliquotas> populaListaDeContribuicoesComCompetencia(DateTime dtI,DateTime dtF) {
		List<ContribuicoeseAliquotas> lista = new ArrayList<>();
		
  		try {
			for (int i = dtI.getMonthOfYear(); i <= dtF.getMonthOfYear(); i++) {
				lista.add(
						new QualificaBaseDeCalculo().executa(new ContribuicoeseAliquotas(new StringBuilder().append(i<= 9?"0"+(dtI.withMonthOfYear(i).getMonthOfYear()):dtI.withMonthOfYear(i).getMonthOfYear()).append(dtI.getYear()).toString(),
								this.baseDeCalculo,
								new Double(0.00),
								new BigDecimal("0.00"),
								new BigDecimal("0.00"),
								new Double(0.00),
								new BigDecimal("0.00"),
								new BigDecimal("0.00"),this.pf), 
								this.pf.getDATA_efetivoExercicio()
								)
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
  		
  		return lista;
  		
  	}
}
