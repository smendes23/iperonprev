package br.com.iperonprev.phase;

import java.util.ArrayList;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.lang3.StringUtils;

public class PhaseView implements PhaseListener{

	private static final long serialVersionUID = 1L;
	
	static String pagina = new String();
	List<String> listaPaginas = new ArrayList<String>();
	
	@SuppressWarnings("static-access")
	@Override
	public void afterPhase(PhaseEvent event) {
		listaPaginas.add("/paginas/cadastro/funcional.xhtml");
		listaPaginas.add("/paginas/beneficio/auxiliodoenca.xhtml");
		listaPaginas.add("/paginas/beneficio/listaAuxilioDoenca.xhtml");
		listaPaginas.add("/paginas/beneficio/aposentadoriaInvalidez.xhtml");
		listaPaginas.add("/paginas/beneficio/aposentadoriaCompulsoria.xhtml");
		listaPaginas.add("/paginas/beneficio/aposentadoriaIdade.xhtml");
		listaPaginas.add("/paginas/beneficio/aposentadoriaIdadeContribuicao.xhtml");
		listaPaginas.add("/paginas/beneficio/pensao.xhtml");
		listaPaginas.add("/paginas/beneficio/listaDePensoes.xhtml");
		listaPaginas.add("/paginas/cadastro/pericia.xhtml");
		listaPaginas.add("/paginas/cadastro/listaPericia.xhtml");
		listaPaginas.add("/paginas/cadastro/ctc.xhtml");
		listaPaginas.add("/paginas/cadastro/cts.xhtml");
		listaPaginas.add("/paginas/censo/relatorioRecadastrados.xhtml");
		listaPaginas.add("/paginas/relatorios/recadatradosReport.xhtml");
		
		ExternalContext ec = event.getFacesContext().getExternalContext();
		
				for (String estatica : listaPaginas) {
					if(new StringUtils().contains(event.getFacesContext().getViewRoot().getViewId(),estatica)){
						pagina = event.getFacesContext().getViewRoot().getViewId();
						return;
					}else{
						pagina = new String();
					}
				}
				
				if(pagina.isEmpty()){
					if(ec.getSessionMap().containsKey("funcional")){
						ec.getSessionMap().remove("funcional");
					}
				}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
