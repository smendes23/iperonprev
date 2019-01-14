package br.com.iperonprev.controller.cadastro;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;

import br.com.iperonprev.constantes.TipoRecadastramentoEnum;
import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.controller.gerenciador.MasterBean;
import br.com.iperonprev.dao.AtestadoVidaResidenciaDao;
import br.com.iperonprev.dao.CensoPrevidenciarioDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.models.AtestadosVidaResidencia;
import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.UnidadesCenso;
import br.com.iperonprev.reports.container.ComprovanteCensoReport;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.Message;

@ManagedBean
@SessionScoped
public class RecadastramentoBean {

	
	
	Pessoas pessoa = new Pessoas();
	
	CensoPrevidenciario censoPrevidenciario = new CensoPrevidenciario();
	
	public CensoPrevidenciario getCensoPrevidenciario() {
		return censoPrevidenciario;
	}
	
	public List<TipoRecadastramentoEnum> listaTipoRecadastramento(){
		return Arrays.asList(TipoRecadastramentoEnum.values());
	}

	public void setCensoPrevidenciario(CensoPrevidenciario censoPrevidenciario) {
		this.censoPrevidenciario = censoPrevidenciario;
	}
	
	
	@PostConstruct
	public void init(){
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try{
			if(ec.getSessionMap().containsKey("pessoa")){
				
				this.pessoa = (Pessoas)ec.getSessionMap().get("pessoa");
				this.buscaRecadastramento();
				listaVidaResidencia = new AtestadoVidaResidenciaDao().devolveListaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto());
			}
		}catch(Exception e){
			System.out.println("Dados pessoais não existe.");
		}
		
		System.out.println(this.pessoa.getNUMR_cpf());
		this.habilitarRecadastramento = false;
	}
	
	List<CensoPrevidenciario> listaRecadastramento = new ArrayList<>();

	private boolean habilitarRecadastramento = false;
	
	public boolean isHabilitarRecadastramento() {
		return habilitarRecadastramento;
	}
	
	
	@SuppressWarnings("static-access")
	private void buscaRecadastramento() {
		try {
			CensoPrevidenciarioDao cpDao = new CensoPrevidenciarioDao();
			this.listaRecadastramento = cpDao.devolveListaRecadastramento(this.pessoa.getNUMR_cpf());
			DadosCenso dadosCenso = new DadosCenso();
			this.habilitarRecadastramento = false;
			if(this.listaRecadastramento.isEmpty() ||
					!listaRecadastramento.isEmpty() && !cpDao.verificaSevidorFezRecadastramentoAtual(this.pessoa.getNUMR_cpf())){
				
				if(new LocalDate().now().getMonthOfYear() >= new LocalDate(pessoa.getDATA_nascimento()).getMonthOfYear()
						&& new LocalDate().now().getYear() >= new LocalDate(pessoa.getDATA_nascimento()).getYear()
						|| dadosCenso.getDATA_fim().before(new LocalDate().now().toDate())){
					this.habilitarRecadastramento = true;
				}
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			Message.addErrorMessage("Servidor inexistente!");
		}

	}
	
	@SuppressWarnings("static-access")
	public void salvaRecadastramento(){
		FacesContext fc = FacesContext.getCurrentInstance();
		@SuppressWarnings("deprecation")
		MasterBean mb = (MasterBean) fc.getApplication().getVariableResolver().resolveVariable(fc, "masterBean");
		int idInfucional = 0;
		
		try{
			idInfucional = this.censoPrevidenciario.getNUMG_idDoObjeto();
		}catch(Exception e){
			System.out.println("Não possui funcional");
		}
		
		try{
			if(idInfucional > 0){
				if(censoPrevidenciario.getENUM_tipoRecadastramento() == TipoRecadastramentoEnum.O){
					this.censoPrevidenciario.setFLAG_pendente(true);
				}else{
					this.censoPrevidenciario.setFLAG_pendente(false);
				}
				
				if(new AtestadoVidaResidenciaDao().verificaExistenciaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto()) == true){
					this.censoPrevidenciario.setFLAG_pendente(false);
				}
				new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(this.censoPrevidenciario);
			}else{
				List<PessoasFuncionais> listaFuncional = new PessoasFuncionaisDao().devolveListaDeFuncionais(this.pessoa.getNUMR_cpf());
				listaFuncional.forEach(f->{
					
					if(f.getNUMR_situacaoFuncional().getNUMG_idDoObjeto() == 1){
						CensoPrevidenciario cp = new CensoPrevidenciario();
						cp.setNUMR_pessoasFuncionais(f);
						cp.setDATA_recadastramento(new LocalDate().now().toDate());
						cp.setNUMR_recadastramento(new LocalDate().getMonthOfYear()+""+new LocalDate().getYear()+f.getDESC_matricula()+this.pessoa.getNUMR_cpf());
						cp.setDESC_atendente(mb.getNomeUsuario());
						cp.setNUMR_idCenso(this.censoPrevidenciario.getNUMR_idCenso());
						cp.setDESC_observacao(this.censoPrevidenciario.getDESC_observacao());
						cp.setNUMR_unidade(this.censoPrevidenciario.getNUMR_unidade());
						cp.setENUM_tipoRecadastramento(this.censoPrevidenciario.getENUM_tipoRecadastramento());
						cp.setTipoBeneficiario(f.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto());
						if(censoPrevidenciario.getENUM_tipoRecadastramento() == TipoRecadastramentoEnum.O){
							cp.setFLAG_pendente(true);
						}
						new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(cp);
					}
				});
			}
			listaRecadastramento = new CensoPrevidenciarioDao().devolveListaRecadastramento(this.pessoa.getNUMR_cpf());
			RequestContext.getCurrentInstance().execute("PF('recadastramento').hide()");
		}catch(Exception e){
			Message.addErrorMessage("Erro ao salvar recadastramento");
		}
		
	}
	
	/*private int verificaSeAposentadoOuPensionista(PessoasFuncionais pf){
		int tipoBeneficiario = 0;
		try{
				List<FuncionaisFuncoes> listaff = new FuncionaisFuncoesDao().listaRelacionamenoDoObjeto("FuncionaisFuncoes", "NUMR_idPessoas", pf.getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
				for (FuncionaisFuncoes ff : listaff) {
					if(ff.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto() == 2 ||
						ff.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto() == 4){
						tipoBeneficiario =ff.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto(); 
					}
				}
				
				if(pf.getNUMR_idDoObjetoCargo().getNUMG_idDoObjeto() == 4808){
					tipoBeneficiario = 4;
				}
		}catch(Exception e){
			System.out.println("Erro ao verificar se o beneficiário é Aposentado ou Pensionista");
		}
		
		return tipoBeneficiario;
	}*/
	
	List<AtestadosVidaResidencia> listaVidaResidencia = new ArrayList<AtestadosVidaResidencia>();
	
	public List<AtestadosVidaResidencia> devolveListaAtestados(){
		listaVidaResidencia = new AtestadoVidaResidenciaDao().devolveListaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto());
		return listaVidaResidencia;
	}
	
	public List<CensoPrevidenciario> devolveListaRecadastramento() {
		return this.listaRecadastramento;
	}
	
	public List<UnidadesCenso> getListaUnidadeCenso(){
		List<UnidadesCenso> lista = new GenericPersistence<UnidadesCenso>(UnidadesCenso.class).listarTodos("UnidadesCenso");
		return lista;
	}
	
	public List<DadosCenso> getListaDeCenso() {
		return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	}
	
	CopyFile copyFile = new CopyFile();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		AtestadosVidaResidencia atestado = new AtestadosVidaResidencia();
		String caminho = new StringBuilder().append(pessoa.getNUMR_cpf()).append("\\")
				.append("recadastramento").append("\\").append(this.censoPrevidenciario.getNUMR_pessoasFuncionais().getDESC_matricula())
				.append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(pessoa.getNUMG_idDoObjeto() > 0){
			copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
			
			atestado.setDESC_caminhoRelativo(caminho);
			atestado.setDESC_nome(event.getFile().getFileName());
			atestado.setREL_pessoa(pessoa);
			atestado.setREL_censo(this.censoPrevidenciario);
			new GenericPersistence<AtestadosVidaResidencia>(AtestadosVidaResidencia.class).salvar(atestado);
			
			if(new AtestadoVidaResidenciaDao().verificaExistenciaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto()) == true){
				this.censoPrevidenciario.setFLAG_pendente(false);
			}
			new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(this.censoPrevidenciario);
		}else{
			Message.addErrorMessage("Funcional inexistente");
		}
		
    }
	
	private boolean recadastramentoOnline = false;
	
	
	public boolean isRecadastramentoOnline() {
		verificaSeRecadastramentoOnline();
		return recadastramentoOnline;
	}

	public void verificaSeRecadastramentoOnline(){
		if(this.censoPrevidenciario.getENUM_tipoRecadastramento() == TipoRecadastramentoEnum.O){
			this.recadastramentoOnline = true;
		}else{
			this.recadastramentoOnline = false;
		}
		FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("declaracao");
		RequestContext.getCurrentInstance().update("declaracao");
	}
	
	public void handleClose(CloseEvent event) {
        this.censoPrevidenciario = new CensoPrevidenciario();
        this.recadastramentoOnline = false;
    }
	
	public void devolveCensoPrevidenciario(CensoPrevidenciario censo){
		this.censoPrevidenciario = censo;
	}
	
	public void geraComprovante(CensoPrevidenciario censoP) {
		try {
			System.out.println("Gerando comprovante, id censo: "+censoP.getNUMG_idDoObjeto());
			ConvocacaoCensoDto dto = new ConvocacaoCensoDto();
			dto.setDESC_competencia(new LocalDate().getMonthOfYear()+""+new LocalDate().getYear());
			dto.setFLAG_pendente(false);
			dto.setFLAG_recadastramento(true);
			dto.setNUMR_idCenso(censoP.getNUMR_idCenso());
			dto.setNUMR_idPessoasFuncionais(censoP.getNUMR_pessoasFuncionais());
			dto.setNUMR_tipoBeneficiario(2);
			if(censoP.getTipoBeneficiario() == 2){
				dto.setNUMR_tipoBeneficiario(1);
			}
			new ComprovanteCensoReport().gera(new CensoPrevidenciarioDao().getCensoIdFuncional(dto.getNUMR_idPessoasFuncionais().getNUMG_idDoObjeto(),
					dto.getNUMR_idCenso().getNUMG_idDoObjeto()));
		} catch (Exception e) {
			System.out.println("Erro ao gerar comprovante. Erro: "+e.getMessage());
		}
	}
    
    public void removeArquivo(AtestadosVidaResidencia atesta){
		try{
		new GenericPersistence<AtestadosVidaResidencia>(AtestadosVidaResidencia.class).remover("AtestadosVidaResidencia", "NUMG_idDoObjeto", atesta.getNUMG_idDoObjeto());
		this.copyFile.removeFile(atesta.getDESC_caminhoRelativo(), atesta.getDESC_nome());
			for (int i = 0; i < listaVidaResidencia.size(); i++) {
				if(listaVidaResidencia.get(i).getNUMG_idDoObjeto() == atesta.getNUMG_idDoObjeto()){
					listaVidaResidencia.remove(i);
				}
			}
		}catch(Exception e){
			Message.addErrorMessage("Não foi possível remover o arquivo.Erro: "+e.getMessage());
		}
	}
    
    public void recarregaPagina() {
		/*this.censoPrevidenciario = new CensoPrevidenciario();
		this.pessoa = new Pessoas();
		this.habilitarRecadastramento = false;
		this.recadastramentoOnline = false;
		this.listaRecadastramento = new ArrayList<>();
		this.listaVidaResidencia = new ArrayList<>();*/
	}
    
}
