package br.com.iperonprev.controller.cadastro;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.constantes.TipoRecadastramentoEnum;
import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.dao.AtestadoVidaResidenciaDao;
import br.com.iperonprev.dao.CensoDao;
import br.com.iperonprev.dao.CensoPrevidenciarioDao;
import br.com.iperonprev.dao.DependentesDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.PessoasFuncionaisDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.interfaces.GenericDao;
import br.com.iperonprev.models.AtestadosVidaResidencia;
import br.com.iperonprev.models.CensoPrevidenciario;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.Dependentes;
import br.com.iperonprev.models.DocumentoPessoal;
import br.com.iperonprev.models.Enderecos;
import br.com.iperonprev.models.EstadoCivil;
import br.com.iperonprev.models.Estados;
import br.com.iperonprev.models.GrauDeInstrucao;
import br.com.iperonprev.models.GrauParentesco;
import br.com.iperonprev.models.MotivoFimDependencia;
import br.com.iperonprev.models.Municipios;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Sisobi;
import br.com.iperonprev.models.TipoLogradouro;
import br.com.iperonprev.models.UnidadesCenso;
import br.com.iperonprev.models.Users;
import br.com.iperonprev.reports.container.ComprovanteCensoReport;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Message;
import br.com.iperonprev.util.jsf.TemposPrevidenciarios;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@ManagedBean
//@javax.faces.view.ViewScoped
@SessionScoped
public class PessoasBean implements Serializable, GenericBean<Pessoas>{

	private static final long serialVersionUID = 1L;
	 GenericDao<Pessoas> dao = new PessoasDao();
	    Pessoas pessoa = new Pessoas();
	    Pessoas pessoaDependente = new Pessoas();
	    private String cpfDependente;
	    private String nomeDependente;
	    private Date dataNascimentoDependente;
	    Dependentes dependente = new Dependentes();
	    private boolean skip = false;
	    List<Dependentes> listaDep = new ArrayList<Dependentes>();
	    Enderecos endereco = new Enderecos();
	    Estados estado = new Estados();
	    Municipios municipio = new Municipios();
	    EstadoCivil estadoCivil = new EstadoCivil();
	    TipoLogradouro logradouro = new TipoLogradouro();
	    CensoPrevidenciario censoPrevidenciario = new CensoPrevidenciario();
//	    public String colorRecadastramento = "green";
	    Sisobi sisobi = new Sisobi();
	    private List<Pessoas> filtroDePessoas;
	    static List<Municipios> listaM = new ArrayList<Municipios>();
	    private boolean servidorInativo = false;
	    List<CensoPrevidenciario> listaRecadastramento = new ArrayList<CensoPrevidenciario>();
	    private boolean habilitarRecadastramento = false;
	    DadosCenso dadosCenso = new DadosCenso();
	    CensoPrevidenciarioDao cpDao = new CensoPrevidenciarioDao();
	    Pessoas pessoaRecad = new Pessoas();
	    boolean habilitaNovoBOtao = false;
	    boolean res = false;
	    Users users = new Users();
	    List<AtestadosVidaResidencia> listaVidaResidencia = new ArrayList<AtestadosVidaResidencia>();
	    private static boolean actionButton = false;
	    static List<Pessoas> listaP = new ArrayList<Pessoas>();
	    CopyFile copyFile = new CopyFile();
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    private boolean recadastramentoOnline = false;
	    List<DocumentoPessoal> listaDocumentos = new ArrayList<DocumentoPessoal>();
	    
	    boolean verificaSeCarregouPessoa = false;

	    public CensoPrevidenciario getCensoPrevidenciario() {
	        return this.censoPrevidenciario;
	    }

	    public List<TipoRecadastramentoEnum> listaTipoRecadastramento() {
	        return Arrays.asList(TipoRecadastramentoEnum.values());
	    }

	    public void setCensoPrevidenciario(CensoPrevidenciario censoPrevidenciario) {
	        this.censoPrevidenciario = censoPrevidenciario;
	    }

	  /*  public String getColorRecadastramento() {
	        return this.colorRecadastramento;
	    }*/

	    public String getCpfDependente() {
	        return this.cpfDependente;
	    }

	    public void setCpfDependente(String cpfDependente) {
	        this.cpfDependente = cpfDependente;
	    }

	    public String getNomeDependente() {
	        return this.nomeDependente;
	    }

	    public void setNomeDependente(String nomeDependente) {
	        this.nomeDependente = nomeDependente;
	    }

	    public Date getDataNascimentoDependente() {
	        return this.dataNascimentoDependente;
	    }

	    public void setDataNascimentoDependente(Date dataNascimentoDependente) {
	        this.dataNascimentoDependente = dataNascimentoDependente;
	    }

	    public Dependentes getDependente() {
	        return this.dependente;
	    }

	    public void setDependente(Dependentes dependente) {
	        this.dependente = dependente;
	    }

	    public TipoLogradouro getLogradouro() {
	        return this.logradouro;
	    }

	    public void setLogradouro(TipoLogradouro logradouro) {
	        this.logradouro = logradouro;
	    }

	    public List<Dependentes> getListaDep() {
	        return this.listaDep;
	    }

	    public void setListaDep(List<Dependentes> listaDep) {
	        this.listaDep = listaDep;
	    }

	    public Pessoas getPessoaDependente() {
	        return this.pessoaDependente;
	    }

	    public void setPessoaDependente(Pessoas pessoaDependente) {
	        this.pessoaDependente = pessoaDependente;
	    }

	    public EstadoCivil getEstadoCivil() {
	        return this.estadoCivil;
	    }

	    public void setEstadoCivil(EstadoCivil estadoCivil) {
	        this.estadoCivil = estadoCivil;
	    }

	    public Sisobi getSisobi() {
	        return this.sisobi;
	    }

	    public void setSisobi(Sisobi sisobi) {
	        this.sisobi = sisobi;
	    }

	    public List<Pessoas> getFiltroDePessoas() {
	        return this.filtroDePessoas;
	    }

	    public void setFiltroDePessoas(List<Pessoas> filtroDePessoas) {
	        this.filtroDePessoas = filtroDePessoas;
	    }

	    public Estados getEstado() {
	        return this.estado;
	    }

	    public void setEstado(Estados estado) {
	        this.estado = estado;
	    }

	    public Municipios getMunicipio() {
	        return this.municipio;
	    }

	    public void setMunicipio(Municipios municipio) {
	        this.municipio = municipio;
	    }

	    public Enderecos getEndereco() {
	        return this.endereco;
	    }

	    public void setEndereco(Enderecos endereco) {
	        this.endereco = endereco;
	    }

	    public Pessoas getPessoa() {
	        return this.pessoa;
	    }

	    public void setPessoa(Pessoas pessoa) {
	        this.pessoa = pessoa;
	    }

	    public List<SexoEnum> getSexoEnum() {
	        List<SexoEnum> lista = Arrays.asList(SexoEnum.values());
	        return lista;
	    }

	    public List<SexoEnum> getSexoDependenteEnum() {
	        List<SexoEnum> lista = Arrays.asList(SexoEnum.values());
	        return lista;
	    }

	    public List<EstadoCivil> getListaEstadosCivis() {
	        return new GenericPersistence<EstadoCivil>(EstadoCivil.class).listarTodos("EstadoCivil");
	    }

	    public List<GrauDeInstrucao> getListaGrauDeInstrucao() {
	        return new GenericPersistence<GrauDeInstrucao>(GrauDeInstrucao.class).listarTodos("GrauDeInstrucao");
	    }

	    public List<TipoLogradouro> getListaTipoLogradouro() {
	        return new GenericPersistence<TipoLogradouro>(TipoLogradouro.class).listarTodos("TipoLogradouro");
	    }

	    public void populaMunicipios() {
	        listaM = new GenericPersistence<Municipios>(Municipios.class).listarRelacionamento("Municipios", "NUMR_idDoObjetoEstado", this.estado.getNUMG_idDoObjeto());
	    }

	    public List<Municipios> getListaDeMunicipios() {
	        return listaM;
	    }

	    public List<Estados> getListaDeEstados() {
	        List<Estados> lista = new GenericPersistence<Estados>(Estados.class).listarTodos("Estados");
	        return lista;
	    }

	    @PostConstruct
	    public void init() {
	        this.pessoa.setDESC_sexo(SexoEnum.M);
	        this.pessoaDependente.setDESC_sexo(SexoEnum.M);
	        this.habilitarRecadastramento = false;
	    }

	    public boolean isServidorInativo() {
	        return this.servidorInativo;
	    }

	    public void setServidorInativo(boolean servidorInativo) {
	        this.servidorInativo = servidorInativo;
	    }

	    private void verificaServidorInativo(Pessoas p) {
	        List<PessoasFuncionais> lista = new PessoasFuncionaisDao().devolveListaDeFuncionais(p.getNUMR_cpf());
	        try {
	            for (PessoasFuncionais pf : lista) {
	                int situacao = pf.getNUMR_situacaoPrevidenciaria().getNUMG_idDoObjeto();
	                if (situacao != 2 && situacao != 4) continue;
	                this.servidorInativo = true;
	            }
	            this.habilitaNovoRecadastramento();
	        }
	        catch (Exception e) {
	            System.out.println("Não foi possivel carregar a situação previdenciária.");
	        }
	    }

	    public void buscaServidor() {
	        try {
	            if (!this.pessoa.getNUMR_cpf().isEmpty()) {
	                this.pessoa =new PessoasDao().devolvePessoa(this.pessoa.getNUMR_cpf());
	                if(this.pessoa.getNUMG_idDoObjeto() > 0) {
	                	 this.verificaSeCarregouPessoa = true;
	                }
	                
	                this.estadoCivil = this.pessoa.getNUMR_estadoCivil();
	                
	                this.endereco = this.pessoa.getNUMR_idDoObjetoEndereco();
	                this.logradouro = this.endereco.getNUMR_tipoLogradouro();
	                this.estado =this.endereco.getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado();
	                this.municipio = this.endereco.getNUMR_idDoObjetoMunicipio();
	                populaMunicipios();
	                verificaServidorInativo(this.pessoa);
	               
	                if (new DependentesDao().existeDependente(this.pessoa.getNUMG_idDoObjeto().intValue())) {
	                	this.listaDep = new DependentesDao().listaDependentesPensionistas(this.pessoa.getNUMG_idDoObjeto().intValue());
	                }
	                
//	                pessoaOp = null;
	               
	                this.carregaListaDocumentos();
	            } else {
	                Message.addErrorMessage((String)"Cpf Nulo");
	            }
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)"Não foi possível carregar os dados pessoais!");
	        }
	    }
	    
	    public void buscaDependentes() {
	    	try {
	    		if (!this.pessoaDependente.getNUMR_cpf().isEmpty()) {
	    			Optional<Pessoas> pessoaOp = Optional.ofNullable(new PessoasDao().devolvePessoa(this.pessoaDependente.getNUMR_cpf()));
	    			if (pessoaOp.isPresent()) {
	    				this.pessoaDependente = pessoaOp.get();
	    			}
	    		}
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }

	    public void salvarObjeto() {
	        try {
	        	
	        	Pessoas pes =  new Pessoas() ;
	        	pes = new PessoasDao().devolvePessoa(this.pessoa.getNUMR_cpf());
	        	
	            this.endereco.setNUMR_tipoLogradouro(this.logradouro);
	            this.endereco.setNUMR_idDoObjetoMunicipio(this.municipio);
	            this.pessoa.setNUMR_idDoObjetoEndereco(this.endereco);
	            this.pessoa.setNUMR_estadoCivil(this.estadoCivil);
	            
	            if (this.pessoa.getDESC_pai().contains("-")) {
	                this.pessoa.setDESC_pai(null);
	            }
	            
	            
	            
	            if(pes.getNUMG_idDoObjeto() > 0 && verificaSeCarregouPessoa == true
	            		|| pes.getNUMG_idDoObjeto() == 0) {

	            		new GenericPersistence<Pessoas>(Pessoas.class).salvar(this.pessoa);
	    	            this.novoObjeto();
	    	            this.habilitaNovoBOtao = false;
	    	            Message.addSuccessMessage((String)"Cadastro realizado com sucesso");
	            	
	            }else if(pes.getNUMG_idDoObjeto() == this.pessoa.getNUMG_idDoObjeto()) {
	            	new GenericPersistence<Pessoas>(Pessoas.class).salvar(this.pessoa);
    	            this.novoObjeto();
    	            this.habilitaNovoBOtao = false;
    	            Message.addSuccessMessage((String)"Cadastro realizado com sucesso");
	            }
	            
	            else if(pes.getNUMG_idDoObjeto() > 0 && verificaSeCarregouPessoa == false){
	            	Message.addErrorMessage("Cadastro existente");
	            	
	            }
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)"Erro ao salvar Pessoa");
	        }
	    }

	    public List<GrauParentesco> getListaGrauParentesco() {
	        return new GenericPersistence<GrauParentesco>(GrauParentesco.class).listarTodos("GrauParentesco");
	    }

	    public List<MotivoFimDependencia> getListaMotivosFimDependencia() {
	        return new GenericPersistence<MotivoFimDependencia>(MotivoFimDependencia.class).listarTodos("MotivoFimDependencia");
	    }
	    
	    public void teste() {
	    	Message.addSuccessMessage("teste");
	    }

	    public void salvarDependente() {
	        try {
	            if (pessoaDependente.getNUMG_idDoObjeto() != 0) {
	            	
		            Enderecos end = new Enderecos();
		            end.setDESC_bairro(this.endereco.getDESC_bairro());
		            end.setDESC_logradouro(this.endereco.getDESC_logradouro());
		            end.setDESC_numero(this.endereco.getDESC_numero());
		            end.setNUMR_cep(this.endereco.getNUMR_cep());
		            end.setNUMR_tipoLogradouro(this.endereco.getNUMR_tipoLogradouro());
		            end.setNUMR_idDoObjetoMunicipio(this.endereco.getNUMR_idDoObjetoMunicipio());
		            this.pessoaDependente.setDESC_nacionalidade(this.pessoa.getDESC_nacionalidade());
		            this.pessoaDependente.setDESC_ufNaturalidade(this.pessoa.getDESC_ufNaturalidade());
		            this.pessoaDependente.setDESC_naturalidade(this.pessoa.getDESC_naturalidade());
		            this.pessoaDependente.setNUMR_estadoCivil(this.pessoa.getNUMR_estadoCivil());
		            this.pessoaDependente.setDESC_pai(this.pessoa.getDESC_pai());
		            this.pessoaDependente.setDESC_mae(this.pessoa.getDESC_mae());
		            this.pessoaDependente.setNUMR_pisPasep(this.pessoa.getNUMR_pisPasep());
		            this.pessoaDependente.setNUMR_instrucao(this.pessoa.getNUMR_instrucao());
		            this.pessoaDependente.setNUMR_idDoObjetoEndereco(end);
		            
	            }
	            
	            if(this.salvaDependente(this.pessoa, this.pessoaDependente) == true) {
	            	this.cpfDependente = new String();
	            	this.nomeDependente = new String();
	            	this.dataNascimentoDependente = null;
	            	this.pessoaDependente = new Pessoas();
	            	this.dependente = new Dependentes();
	            	this.listaDep = new DependentesDao().listaDependentesPensionistas(this.pessoa.getNUMG_idDoObjeto().intValue());
	            }else {
	            	Message.addErrorMessage("Cpf do dependente nulo ou igual ao do servidor!");
	            }
	            
	        }
	        catch (Exception e) {
	            Message.addErrorMessage("Erro ao salvar dependentes");
	        }
	    }

	    private boolean salvaDependente(Pessoas pessoa1, Pessoas pessoa2) {
	    	boolean res = false;
	        try {
	        	if(!pessoa1.getNUMR_cpf().equals(pessoa2.getNUMR_cpf()) || pessoa2.getNUMR_cpf().equals(null)) {
	        		this.dependente.setNUMR_idDoObjetoPessoas(new PessoasDao().devolvePessoa(pessoa1.getNUMR_cpf()));
	        		this.dependente.setNUMR_idDoObjetoDependentes(pessoa2);
	        		new GenericPersistence<Dependentes>(Dependentes.class).salvar(this.dependente);
	        		Message.addSuccessMessage((String)"Dependente incluido com sucesso!");
	        		res = true;
	        	}
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)("Não foi possível salvar dependente! Erro: " + e.getMessage()));
	        }
	        return res;
	    }

	    public void novoObjeto() {
	    	dao = new PessoasDao();
		    pessoa = new Pessoas();
		    pessoaDependente = new Pessoas();
		    cpfDependente = new String();
		    nomeDependente = new String();
		    dataNascimentoDependente = null;
		    dependente = new Dependentes();
		    skip = false;
		    listaDep = new ArrayList<Dependentes>();
		    endereco = new Enderecos();
		    estado = new Estados();
		    municipio = new Municipios();
		    estadoCivil = new EstadoCivil();
		    logradouro = new TipoLogradouro();
		    censoPrevidenciario = new CensoPrevidenciario();
//		    public String colorRecadastramento = "green";
		    sisobi = new Sisobi();
		    filtroDePessoas = new ArrayList<>();
		    listaM = new ArrayList<Municipios>();
		    servidorInativo = false;
		    listaRecadastramento = new ArrayList<CensoPrevidenciario>();
		    habilitarRecadastramento = false;
		    dadosCenso = new DadosCenso();
		    cpDao = new CensoPrevidenciarioDao();
		    pessoaRecad = new Pessoas();
		    habilitaNovoBOtao = false;
		    res = false;
		    users = new Users();
		    listaVidaResidencia = new ArrayList<AtestadosVidaResidencia>();
		    actionButton = false;
		    listaP = new ArrayList<Pessoas>();
		    copyFile = new CopyFile();
		    recadastramentoOnline = false;
		    listaDocumentos = new ArrayList<DocumentoPessoal>();
		    verificaSeCarregouPessoa = false;
		    this.pessoa.setDESC_sexo(SexoEnum.M);
	        this.pessoaDependente.setDESC_sexo(SexoEnum.M);
	        this.habilitarRecadastramento = false;
	    }

	    public void carregaDependente(Dependentes dep) {
	        this.dependente = new GenericPersistence<Dependentes>(Dependentes.class).buscarPorId(dep.getNUMG_idDoObjeto());
	        this.pessoaDependente = this.dependente.getNUMR_idDoObjetoDependentes();
	        this.cpfDependente = this.pessoaDependente.getNUMR_cpf();
	        this.nomeDependente = this.pessoaDependente.getDESC_nome();
	        this.dataNascimentoDependente = this.pessoaDependente.getDATA_nascimento();
	    }

	    public void recarregaPagina() {
	        this.novoObjeto();
	        this.pessoaDependente = new Pessoas();
	        this.dependente = new Dependentes();
	        this.estado = new Estados();
	        this.listaDep = new ArrayList<Dependentes>();
	        listaM = new ArrayList<Municipios>();
	        listaP = new ArrayList<Pessoas>();
	        this.logradouro = new TipoLogradouro();
	        this.municipio = new Municipios();
	        this.cpfDependente = new String();
	        this.dataNascimentoDependente = null;
	        this.filtroDePessoas = new ArrayList<Pessoas>();
	        this.nomeDependente = new String();
	        this.habilitarRecadastramento = false;
	        this.verificaSeCarregouPessoa = false;
	    }

	    public List<Pessoas> listaDeObjetos() {
	        return new ArrayList<>();
	    }

	    public void exibeRecadastramento() {
	        new DialogsPrime().showDialogWithAndHeight(true, true, true, false, "listaRecadastramento", 480, 980);
	    }

	    public boolean isHabilitarRecadastramento() {
	        return this.habilitarRecadastramento;
	    }

	    public void buscaRecadastramento() {
	        try {
	            this.listaRecadastramento = this.cpDao.devolveListaRecadastramento(this.pessoa.getNUMR_cpf());
	            this.habilitarRecadastramento = false;
	            this.habilitaNovoRecadastramento();
	            if (!this.listaRecadastramento.isEmpty() || !new PessoasFuncionaisDao().devolveListaDeFuncionaisAposentadoPensionista(this.pessoa.getNUMR_cpf(),this.dadosCenso.getNUMG_idDoObjeto()).isEmpty()) {
	                this.habilitarRecadastramento = true;
	            }
	            this.pessoaRecad = this.pessoa;
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)"Servidor inexistente!");
	        }
	    }

	    public boolean isHabilitaNovoBOtao() {
	        return this.habilitaNovoBOtao;
	    }

	    public void setHabilitaNovoBOtao(boolean habilitaNovoBOtao) {
	        this.habilitaNovoBOtao = habilitaNovoBOtao;
	    }

	    public void habilitaNovoRecadastramento() {
	        if (!new CensoDao().devolveListaCensoPendente(this.pessoa.getNUMR_cpf()).isEmpty()) {
	            new org.joda.time.LocalDate();
	            if (new LocalDate((Object)this.pessoa.getDATA_nascimento()).getMonthOfYear() <= LocalDate.now().getMonthOfYear() || this.verificaRecadastramentoAtrasado()) {
	                this.habilitaNovoBOtao = true;
	            }
	        } else {
	            this.habilitaNovoBOtao = false;
	        }
	    }

	    private boolean verificaRecadastramentoAtrasado() {
	        this.res = false;
	        try {
	            new CensoDao().devolveListaCensoPendente(this.pessoa.getNUMR_cpf()).forEach(c -> {
	                new org.joda.time.LocalDate();
	                if (LocalDate.now().getYear() > new LocalDate((Object)c.getDATA_inicio()).getYear()) {
	                    this.res = true;
	                }
	            });
	        }
	        catch (Exception e) {
	            System.out.println("Não foi possivel verificar se existe recadastramento atrasado");
	        }
	        return this.res;
	    }

	    public void salvaRecadastramento() {
	        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
	        this.users = new Users();
	        int idInfucional = 0;
	        List<PessoasFuncionais> listaDeFuncionaisAposentadosPensionistas = new ArrayList<PessoasFuncionais>();
	        try {
	            idInfucional = this.censoPrevidenciario.getNUMG_idDoObjeto();
	        }
	        catch (Exception e) {
	            System.out.println("Não possui funcional");
	        }
	        try {
	            if (aut.getPrincipal() instanceof Users && !aut.equals(null)) {
	                this.users = (Users)aut.getPrincipal();
	            }
	            if (idInfucional > 0) {
	                if (this.censoPrevidenciario.getENUM_tipoRecadastramento() == TipoRecadastramentoEnum.O) {
	                    this.censoPrevidenciario.setFLAG_pendente(true);
	                } else {
	                    this.censoPrevidenciario.setFLAG_pendente(false);
	                }
	                if (new AtestadoVidaResidenciaDao().verificaExistenciaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto())) {
	                    this.censoPrevidenciario.setFLAG_pendente(false);
	                }
	                System.out.println("Id Censo: "+this.dadosCenso.getNUMG_idDoObjeto());
	                if(new CensoPrevidenciarioDao().existeRecadastramentoFuncionalIdCenso(idInfucional, this.dadosCenso.getNUMG_idDoObjeto()) == false) {
	                	new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(this.censoPrevidenciario);
	                	
	                }else {
	                  
	                	CensoPrevidenciario cp = new CensoPrevidenciarioDao().getCensoIdFuncional(idInfucional, this.dadosCenso.getNUMG_idDoObjeto());
	                	this.censoPrevidenciario.setNUMG_idDoObjeto(cp.getNUMG_idDoObjeto());
	                	new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(this.censoPrevidenciario);
	                }
	            } else if (!new PessoasFuncionaisDao().devolveListaDeFuncionaisAposentadoPensionista(this.pessoaRecad.getNUMR_cpf(),this.dadosCenso.getNUMG_idDoObjeto()).isEmpty()) {
	                listaDeFuncionaisAposentadosPensionistas = new PessoasFuncionaisDao().devolveListaDeFuncionaisAposentadoPensionista(this.pessoaRecad.getNUMR_cpf(),this.dadosCenso.getNUMG_idDoObjeto());
	                listaDeFuncionaisAposentadosPensionistas.forEach(f -> {
	                    CensoPrevidenciario cp = new CensoPrevidenciario();
	                    cp.setNUMR_pessoasFuncionais(f);
	                    new org.joda.time.LocalDate();
	                    cp.setDATA_recadastramento(LocalDate.now().toDate());
	                    cp.setNUMR_recadastramento(String.valueOf(new LocalDate().getMonthOfYear()) + new LocalDate().getYear() + f.getDESC_matricula() + this.pessoaRecad.getNUMR_cpf());
	                    cp.setDESC_atendente(this.users.getNome());
	                    cp.setNUMR_idCenso(this.censoPrevidenciario.getNUMR_idCenso());
	                    cp.setDESC_observacao(this.censoPrevidenciario.getDESC_observacao());
	                    cp.setNUMR_unidade(this.censoPrevidenciario.getNUMR_unidade());
	                    cp.setENUM_tipoRecadastramento(this.censoPrevidenciario.getENUM_tipoRecadastramento());
	                    cp.setTipoBeneficiario(Integer.valueOf(new TemposPrevidenciarios().devolveSituacaoPrevidenciaria(f)));
	                    new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(cp);
	                });
	            }
	            this.listaRecadastramento = new CensoPrevidenciarioDao().devolveListaRecadastramento(this.pessoa.getNUMR_cpf());
	            RequestContext.getCurrentInstance().execute("PF('recadastramento').hide()");
	            this.users = new Users();
	            this.censoPrevidenciario = new CensoPrevidenciario();
	            this.pessoaRecad = new Pessoas();
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)"Erro ao salvar recadastramento");
	        }
	    }

	    public List<AtestadosVidaResidencia> devolveListaAtestados() {
	        this.listaVidaResidencia = new AtestadoVidaResidenciaDao().devolveListaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto());
	        return this.listaVidaResidencia;
	    }

	    public List<CensoPrevidenciario> devolveListaRecadastramento() {
	        return this.listaRecadastramento;
	    }

	    public List<UnidadesCenso> getListaUnidadeCenso() {
	        List<UnidadesCenso> lista = new GenericPersistence<UnidadesCenso>(UnidadesCenso.class).listarTodos("UnidadesCenso");
	        return lista;
	    }

	    public List<DadosCenso> getListaDeCenso() {
	        return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	    }

	    public void exibeListaDeObjetos() {
	        new DialogsPrime().showDialog(true, true, true, false, "listaPessoas");
	    }

	    public void abreRecadastramento() {
	        RequestContext.getCurrentInstance().execute("PF('listaRecadastramento').hide()");
	        RequestContext.getCurrentInstance().execute("PF('recadastramento').show()");
	    }

	    public void fechaPaginaTeste() {
	        new DialogsPrime().closeDialog("teste");
	    }

	    public void fechaListaPessoas() {
	        actionButton = true;
	        RequestContext.getCurrentInstance().closeDialog((Object)"listaPessoas");
	    }

	    public void actionClose() {
	        actionButton = false;
	    }

	    public void pegaInstanciaDialogo(Pessoas obj) {
	        try {
	            new DialogsPrime().getInstanceObjectDialog((Object)obj);
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao carregar pessoa.");
	        }
	    }

	    public void selecionaObjetoDialogo(SelectEvent event) {
	        if (!actionButton) {
	            try {
	                Pessoas pes = (Pessoas)event.getObject();
	                this.pessoa = new GenericPersistence<Pessoas>(Pessoas.class).buscarPorId(pes.getNUMG_idDoObjeto());
	                if (this.pessoa.getNUMR_idDoObjetoEndereco() != null) {
	                    this.endereco = this.pessoa.getNUMR_idDoObjetoEndereco();
	                }
	                this.logradouro = this.endereco.getNUMR_tipoLogradouro();
	                this.estadoCivil = this.pessoa.getNUMR_estadoCivil();
	                this.estado = this.endereco.getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado();
	                this.municipio = this.endereco.getNUMR_idDoObjetoMunicipio();
	                this.populaMunicipios();
	                if (new DependentesDao().existeDependente(this.pessoa.getNUMG_idDoObjeto().intValue())) {
	                    this.listaDep = new DependentesDao().listaDependentesPensionistas(this.pessoa.getNUMG_idDoObjeto().intValue());
	                }
	                this.carregaListaDocumentos();
	                this.verificaSeCarregouPessoa = true;
	            }
	            catch (Exception e) {
	                System.out.println("Erro ao buscar a pessoa");
	            }
	        }
	        actionButton = false;
	    }

	    public void pegaLista(ValueChangeEvent event) {
	        try {
	            if (event.getNewValue().toString().length() >= 4) {
	                listaP = new PessoasDao().devolveListaDePessoasComClausulaLike(event.getNewValue().toString());
	            }
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao pegar a lista.");
	        }
	    }

	    public List<Pessoas> getListaDePessoas() {
	        try {
	            if (listaP.isEmpty()) {
	                Message.addWarningMessage((String)"Nome deve conter pelo menos 4 dígitos!");
	                return null;
	            }
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao carregar a lista.");
	        }
	        return listaP;
	    }

	    public void handleFileUpload(FileUploadEvent event) throws IOException, Exception {
	        AtestadosVidaResidencia atestado = new AtestadosVidaResidencia();
	        new org.joda.time.LocalDate();
	        String caminho = this.pessoa.getNUMR_cpf() + "\\" + "recadastramento" + "\\" + this.censoPrevidenciario.getNUMR_pessoasFuncionais().getDESC_matricula() + "\\" + this.sdf.format(LocalDate.now().toDate()).replace("/", "");
	        if (this.pessoa.getNUMG_idDoObjeto() > 0) {
	            this.copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
	            atestado.setDESC_caminhoRelativo(caminho);
	            atestado.setDESC_nome(event.getFile().getFileName());
	            atestado.setREL_pessoa(this.pessoa);
	            atestado.setREL_censo(this.censoPrevidenciario);
	            new GenericPersistence<AtestadosVidaResidencia>(AtestadosVidaResidencia.class).salvar(atestado);
	            if (new AtestadoVidaResidenciaDao().verificaExistenciaDeAtesdatos(this.censoPrevidenciario.getNUMG_idDoObjeto())) {
	                this.censoPrevidenciario.setFLAG_pendente(false);
	            }
	            new GenericPersistence<CensoPrevidenciario>(CensoPrevidenciario.class).salvar(this.censoPrevidenciario);
	        } else {
	            Message.addErrorMessage((String)"Funcional inexistente");
	        }
	    }

	    public boolean isRecadastramentoOnline() {
	        this.verificaSeRecadastramentoOnline();
	        return this.recadastramentoOnline;
	    }

	    public void verificaSeRecadastramentoOnline() {
	        this.recadastramentoOnline = this.censoPrevidenciario.getENUM_tipoRecadastramento() == TipoRecadastramentoEnum.O;
	        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("declaracao");
	        RequestContext.getCurrentInstance().update("declaracao");
	    }

	    public void handleClose(CloseEvent event) {
	        this.censoPrevidenciario = new CensoPrevidenciario();
	        this.recadastramentoOnline = false;
	        if (new CensoPrevidenciarioDao().verificaSevidorFezRecadastramentoAtual(this.pessoa.getNUMR_cpf())) {
	            this.habilitarRecadastramento = false;
	        }
	    }

	    public void devolveCensoPrevidenciario(CensoPrevidenciario censo) {
	        this.verificaSeRecadastramentoOnline();
	        this.censoPrevidenciario = censo;
	    }

	    public boolean isSkip() {
	        return this.skip;
	    }

	    public void setSkip(boolean skip) {
	        this.skip = skip;
	    }

	    public void geraComprovante(CensoPrevidenciario censoP) {
	        try {
	            ConvocacaoCensoDto dto = new ConvocacaoCensoDto();
	            dto.setDESC_competencia(String.valueOf(new LocalDate().getMonthOfYear()) + new LocalDate().getYear());
	            dto.setFLAG_pendente(false);
	            dto.setFLAG_recadastramento(true);
	            dto.setNUMR_idCenso(censoP.getNUMR_idCenso());
	            dto.setNUMR_idPessoasFuncionais(censoP.getNUMR_pessoasFuncionais());
	            
	            dto.setNUMR_tipoBeneficiario(2);
	            if (censoP.getTipoBeneficiario() == 2) {
	                dto.setNUMR_tipoBeneficiario(1);
	            }
	            new ComprovanteCensoReport().gera(new CensoPrevidenciarioDao().getCensoIdFuncional(dto.getNUMR_idPessoasFuncionais().getNUMG_idDoObjeto(), dto.getNUMR_idCenso().getNUMG_idDoObjeto()));
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao gerar comprovante. Erro: " + e.getMessage());
	        }
	    }

	    public void removeArquivo(AtestadosVidaResidencia atesta) {
	        try {
	            new GenericPersistence<AtestadosVidaResidencia>(AtestadosVidaResidencia.class).remover("AtestadosVidaResidencia", "NUMG_idDoObjeto", atesta.getNUMG_idDoObjeto());
	            this.copyFile.removeFile(atesta.getDESC_caminhoRelativo(), atesta.getDESC_nome());
	            int i = 0;
	            while (i < this.listaVidaResidencia.size()) {
	                if (this.listaVidaResidencia.get(i).getNUMG_idDoObjeto() == atesta.getNUMG_idDoObjeto()) {
	                    this.listaVidaResidencia.remove(i);
	                }
	                ++i;
	            }
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)("Não foi possível remover o arquivo.Erro: " + e.getMessage()));
	        }
	    }

	    public String onFlowProcess(FlowEvent event) {
	        if (this.skip) {
	            this.skip = false;
	            return "confirm";
	        }
	        return event.getNewStep();
	    }

	    public void printReport(Map<String, Object> params, String jasperPath, List<?> dataSource, String fileName) throws JRException, IOException {
	        String relativeWebPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(jasperPath);
	        File file = new File(relativeWebPath);
	        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSource, false);
	        JasperPrint print = JasperFillManager.fillReport((String)file.getPath(), params, (JRDataSource)source);
	        HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	        response.addHeader("Content-disposition", "attachment;filename=" + fileName);
	        ServletOutputStream stream = response.getOutputStream();
	        JasperExportManager.exportReportToPdfStream((JasperPrint)print, (OutputStream)stream);
	        FacesContext.getCurrentInstance().responseComplete();
	    }

	    public List<DocumentoPessoal> getListaDocumentos() {
	        return this.listaDocumentos;
	    }

	    public void carregaArquivoPessoa(FileUploadEvent event) throws IOException, Exception {
	        DocumentoPessoal dp = new DocumentoPessoal();
	        new org.joda.time.LocalDate();
	        String caminho = this.pessoa.getNUMR_cpf() + "\\" + this.sdf.format(LocalDate.now().toDate()).replace("/", "");
	        if (this.pessoa.getNUMG_idDoObjeto() > 0) {
	            this.copyFile.saveArchive(event.getFile().getInputstream(), caminho, event.getFile().getFileName());
	            dp.setDESC_caminhoRelativo(caminho);
	            dp.setDESC_nome(event.getFile().getFileName());
	            dp.setREL_pessoa(this.pessoa);
	            new GenericPersistence<DocumentoPessoal>(DocumentoPessoal.class).salvar(dp);
	            this.carregaListaDocumentos();
	        } else {
	            Message.addErrorMessage((String)"Funcional inexistente");
	        }
	    }

	    private void carregaListaDocumentos() {
	        try {
	            this.listaDocumentos = new GenericPersistence<DocumentoPessoal>(DocumentoPessoal.class).listarRelacionamento("DocumentoPessoal", "REL_pessoa", this.pessoa.getNUMG_idDoObjeto());
	        }
	        catch (Exception e) {
	            System.out.println("Erro ao lista documentos");
	        }
	    }

	    public void removeArquivo(DocumentoPessoal dp) {
	        try {
	            new GenericPersistence<DocumentoPessoal>(DocumentoPessoal.class).remover("DocumentoPessoal", "NUMG_idDoObjeto", dp.getNUMG_idDoObjeto());
	            this.copyFile.removeFile(dp.getDESC_caminhoRelativo(), dp.getDESC_nome());
	            int i = 0;
	            while (i < this.listaDocumentos.size()) {
	                if (this.listaDocumentos.get(i).getNUMG_idDoObjeto() == dp.getNUMG_idDoObjeto()) {
	                    this.listaDocumentos.remove(i);
	                }
	                ++i;
	            }
	        }
	        catch (Exception e) {
	            Message.addErrorMessage((String)"Não foi possível remover o arquivo.");
	        }
	    }
}
