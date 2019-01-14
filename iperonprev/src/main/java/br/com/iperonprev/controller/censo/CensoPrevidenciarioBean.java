package br.com.iperonprev.controller.censo;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.joda.time.LocalDate;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.dao.AtestadoVidaResidenciaDao;
import br.com.iperonprev.dao.CensoPrevidenciarioDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.AtestadosVidaResidencia;
import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.reports.container.ComprovanteCensoReport;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;

@ManagedBean
@SessionScoped
public class CensoPrevidenciarioBean implements GenericBean<CensoPrevidenciario>, Serializable {

	private static final long serialVersionUID = 1L;
	ConvocacaoCensoDto convocacao = new ConvocacaoCensoDto();
	CensoPrevidenciario censo = new CensoPrevidenciario();
	DadosCenso dadosCenso = new DadosCenso();
	private String cpf;
	private int idPessoa;
	List<ConvocacaoCensoDto> listaConvocacao = new ArrayList<ConvocacaoCensoDto>();
	private boolean pular;
	private boolean comprovanteRecadastramento = false;
	private boolean recadastramento = false;
	List<AtestadosVidaResidencia> listaVidaResidencia = new ArrayList<AtestadosVidaResidencia>();
	
	/*@PostConstruct
	public void init() {
		try {
			if (!ec.getFlash().isEmpty()) {
				ConvocacaoCenso conv = (ConvocacaoCenso) ec.getFlash().get("convocacao");
				Pessoas pessoa = new PessoasDao().devolvePessoa(conv.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getNUMR_cpf());
				listaConvocacao = new ConvocacaoCensoDao().devolveConvocadoComMultiplosParametros(
						pessoa.getNUMR_cpf(),
						conv.getNUMR_idCenso().getNUMG_idDoObjeto());
				
				
			}
		} catch (Exception e) {
			System.out.println("Erro ao inicializar Censo");
		}

	}*/

	public int getIdPessoa() {
		return idPessoa;
	}
	

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}


	public List<AtestadosVidaResidencia> getListaVidaResidencia() {
		return listaVidaResidencia;
	}

	public boolean isComprovanteRecadastramento() {
		return comprovanteRecadastramento;
	}

	public boolean isRecadastramento() {
		return recadastramento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ConvocacaoCensoDto getConvocacao() {
		return convocacao;
	}

	public void setConvocacao(ConvocacaoCensoDto convocacao) {
		this.convocacao = convocacao;
	}

	public CensoPrevidenciario getCenso() {
		return censo;
	}

	public void setCenso(CensoPrevidenciario censo) {
		this.censo = censo;
	}

	public DadosCenso getDadosCenso() {
		return dadosCenso;
	}

	public void setDadosCenso(DadosCenso dadosCenso) {
		this.dadosCenso = dadosCenso;
	}

	@Override
	public void salvarObjeto() {
		// TODO Auto-generated method stub
	}

	@Override
	public void novoObjeto() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CensoPrevidenciario> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pegaInstanciaDialogo(CensoPrevidenciario obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		// TODO Auto-generated method stub

	}

	public List<DadosCenso> getListaDeCenso() {
		return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	}
	
//	static List<ConvocacaoCenso> listaConvoca= new ArrayList<>();

	List<PessoasFuncionais> listaFuncionais = new ArrayList<>();
	@SuppressWarnings("static-access")
	public void buscaConvocacao() {
		try {
			listaConvocacao = new ArrayList<>();
//			List<ConvocacaoCensoDto> listaConvoca= new ArrayList<>();
			boolean res = new CensoPrevidenciarioDao().verificaRecadastramento(this.cpf,this.dadosCenso.getNUMG_idDoObjeto());
			List<CensoPrevidenciario> censoP = new ArrayList<>();
			this.recadastramento = true;
			this.comprovanteRecadastramento = false;
			
			
//			listaConvoca =new ConvocacaoCensoDao().devolveConvocadoComMultiplosParametros(cpf,
//					dadosCenso.getNUMG_idDoObjeto());
			
			if(res == true){
				censoP = new CensoPrevidenciarioDao().devolveBeneficiarioRecadastrado(this.cpf,this.dadosCenso.getNUMG_idDoObjeto());
				for (CensoPrevidenciario cp : censoP) {
					this.recadastramento = false;
					this.comprovanteRecadastramento = true;
					ConvocacaoCensoDto conv = new ConvocacaoCensoDto();
					conv.setNUMR_idPessoasFuncionais(cp.getNUMR_pessoasFuncionais());
					conv.setNUMR_idCenso(dadosCenso);
					conv.setFLAG_pendente(cp.isFLAG_pendente());
					conv.setNUMR_tipoBeneficiario(cp.getTipoBeneficiario());
					listaConvocacao.add(conv);
					
				}
//				equiparaConvocacao(listaConvocacao,censoP);
			}else{
				listaFuncionais = new PessoasFuncionaisDao().devolveListaDeFuncionais(this.cpf);
				Pessoas pessoa = listaFuncionais.get(0).getNUMR_idDoObjetoPessoas();
				
				if(new LocalDate().now().getMonthOfYear() >= new LocalDate(pessoa.getDATA_nascimento()).getMonthOfYear()
						&& new LocalDate().now().getYear() >= new LocalDate(pessoa.getDATA_nascimento()).getYear()
						|| dadosCenso.getDATA_fim().before(new LocalDate().now().toDate())){
					
					for (PessoasFuncionais pessoasFuncionais : listaFuncionais) {
						int tipoBeneficiario = new TemposPrevidenciarios().devolveSituacaoPrevidenciaria(pessoasFuncionais);
							
						
						if(tipoBeneficiario > 0 || pessoasFuncionais.getNUMR_idDoObjetoCargo().getNUMG_idDoObjeto() == 4808){
							
							if(pessoasFuncionais.getNUMR_situacaoFuncional().getNUMG_idDoObjeto() == 1){
								ConvocacaoCensoDto conv = new ConvocacaoCensoDto();
								conv.setNUMR_idPessoasFuncionais(pessoasFuncionais);
								conv.setNUMR_idCenso(dadosCenso);
								conv.setNUMR_tipoBeneficiario(2);
								conv.setFLAG_pendente(false);
								if(tipoBeneficiario == 2){
									conv.setNUMR_tipoBeneficiario(1);
								}
								listaConvocacao.add(conv);
							}
						}
					}
				}else{
					Message.addWarningMessage("Recadastramento disponível no mês de aniversário do Servidor!");
				}
			}
		} catch (Exception e) {
			Message.addErrorMessage("Servidor inexistente ou não convocado!");
		}

	}
	
	
	public List<ConvocacaoCensoDto> getListaDeConvocacoes() {
		
		return listaConvocacao;
	}
	private PessoasFuncionais pf = new PessoasFuncionais();
	
	public PessoasFuncionais getPf() {
		return pf;
	}

	public String redirecionaCadastro(ConvocacaoCensoDto conv) {
		pf = conv.getNUMR_idPessoasFuncionais();
		
		if (conv.getNUMR_tipoBeneficiario() == 1) {
			return "/paginas/censo/aposentado.xhtml?faces-redirect=true";
		}
		return "/paginas/censo/pensionista.xhtml?faces-redirect=true";
	}

	public String onFlowProcess(FlowEvent event) {
		if (pular) {
			pular = false;
			return "Confirmar";
		}
		return event.getNewStep();
	}

	public void geraComprovante(ConvocacaoCensoDto dto) {
		try {
			
			new ComprovanteCensoReport().gera(new CensoPrevidenciarioDao().getCensoIdFuncional(dto.getNUMR_idPessoasFuncionais().getNUMG_idDoObjeto(),
					dto.getNUMR_idCenso().getNUMG_idDoObjeto()));
		} catch (Exception e) {
			System.out.println("Erro ao gerar comprovante. Erro: "+e.getMessage());
		}
	}

	public void recarregaPagina() {
		convocacao = new ConvocacaoCensoDto();
		censo = new CensoPrevidenciario();
		dadosCenso = new DadosCenso();
		cpf = new String();
		listaConvocacao = new ArrayList<>();
		pular = false;
		/*if (!ec.getFlash().isEmpty()) {
			FacesContext.getCurrentInstance().getExternalContext().getFlash().remove("convocacao");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().clear();
		}*/
	}

	public void atualizaStatusRecadastramento(boolean valor, int id) {

		try{
			CensoPrevidenciario cen = new CensoPrevidenciarioDao().getCensoIdFuncional(id, dadosCenso.getNUMG_idDoObjeto());
			cen.setFLAG_pendente(valor);
			new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(cen);
		}catch(Exception e){
			System.out.println("Erro ao atualizar status recadastramento.");
		}
		/*for (int i = 0; i < listaConvocacao.size(); i++) {
				if (cen.getNUMR_recadastramento().equals(null) || cen.getNUMR_recadastramento() == null) {
					cen.setNUMR_recadastramento(listaConvocacao.get(i).getDESC_competencia()
							+ (new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class)
									.listarTodos("CensoPrevidenciario").size() + 1));
				}
			}*/
	}
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
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
	CopyFile copyFile = new CopyFile();
	
	@SuppressWarnings("static-access")
	public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
		Pessoas pessoa = this.listaConvocacao.get(0).getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas();
		AtestadosVidaResidencia atestado = new AtestadosVidaResidencia();
		String caminho = new StringBuilder().append(pessoa.getNUMR_cpf()).append("\\")
				.append("recadastramento").append("\\").append(sdf.format(new LocalDate().now().toDate()).replace("/", "")).toString();
		
		if(pessoa.getNUMG_idDoObjeto() > 0){
			copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
			
			atestado.setDESC_caminhoRelativo(caminho);
			atestado.setDESC_nome(event.getFile().getFileName());
			atestado.setREL_pessoa(pessoa);
			new GenericPersistence<AtestadosVidaResidencia>(AtestadosVidaResidencia.class).salvar(atestado);
			listaVidaResidencia = devolveListaAtestados(pessoa.getNUMG_idDoObjeto());
		}else{
			Message.addErrorMessage("Funcional inexistente");
		}
		
    }
	
	private List<AtestadosVidaResidencia> devolveListaAtestados(int idPessoa){
		return new AtestadoVidaResidenciaDao().devolveListaDeAtesdatos(idPessoa);
	}

	/*
	 * private void populaListaConvocacao(String cp,int id){ listaConvocacao =
	 * new ConvocacaoCensoDao().devolveConvocadoComMultiplosParametros(cpf,
	 * dadosCenso.getNUMG_idDoObjeto()); listaConvocacao.forEach(c->{
	 * c.setFLAG_recadastramento(new
	 * CensoPrevidenciarioDao().verificaRecadastramento(c.getNUMG_idDoObjeto()))
	 * ; });
	 * 
	 * dadosCenso = new DadosCenso(); cpf = new String(); }
	 */
}
