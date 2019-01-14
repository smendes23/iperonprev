package br.com.iperonprev.phase;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class ViewAccess implements PhaseListener{

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent evento) {
		@SuppressWarnings("static-access")
		FacesContext contexto = evento.getFacesContext().getCurrentInstance();
		String pagina = contexto.getViewRoot().getViewId();
		System.out.println("Nome da Página"+pagina);
	}

	@Override
	public void beforePhase(PhaseEvent arg0) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
