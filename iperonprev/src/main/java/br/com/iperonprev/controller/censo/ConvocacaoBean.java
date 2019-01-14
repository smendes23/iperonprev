package br.com.iperonprev.controller.censo;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.event.ValueChangeEvent;

import org.joda.time.LocalDate;
import org.primefaces.event.SelectEvent;

import br.com.iperonprev.controller.dto.ConvocacaoCensoDto;
import br.com.iperonprev.dao.ConvocacaoCensoDao;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.dao.PessoasDao;
import br.com.iperonprev.dao.UsuarioDao;
import br.com.iperonprev.interfaces.GenericBean;
import br.com.iperonprev.models.Cargos;
import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.ModulosPerfil;
import br.com.iperonprev.models.Orgaos;
import br.com.iperonprev.models.PerfilAcesso;
import br.com.iperonprev.models.Pessoas;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Roles;
import br.com.iperonprev.models.Users;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.security.CriptografiaSenha;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.DialogsPrime;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

@ManagedBean
@javax.faces.view.ViewScoped
public class ConvocacaoBean implements GenericBean<ConvocacaoCensoDto>, Serializable {

	private static final long serialVersionUID = 1L;
	ConvocacaoCensoDto convoca = new ConvocacaoCensoDto();
	DadosCenso dadosCenso = new DadosCenso();
	ConvocacaoCensoDto convocacaoGerenciamento = new ConvocacaoCensoDto();
	Pessoas pessoa = new Pessoas();
	PessoasFuncionais funcional = new PessoasFuncionais();
	Cargos cargo = new Cargos();
	Orgaos orgao = new Orgaos();
	private Date envio;
	private Date recebimento;
	private String cpf;

	public Date getEnvio() {
		return envio;
	}

	public void setEnvio(Date envio) {
		this.envio = envio;
	}

	public Date getRecebimento() {
		return recebimento;
	}

	public void setRecebimento(Date recebimento) {
		this.recebimento = recebimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ConvocacaoCensoDto getConvocacaoGerenciamento() {
		return convocacaoGerenciamento;
	}

	public void setConvocacaoGerenciamento(ConvocacaoCensoDto convocacaoGerenciamento) {
		this.convocacaoGerenciamento = convocacaoGerenciamento;
	}

	public DadosCenso getDadosCenso() {
		return dadosCenso;
	}

	public void setDadosCenso(DadosCenso dadosCenso) {
		this.dadosCenso = dadosCenso;
	}

	public ConvocacaoCensoDto getConvoca() {
		return convoca;
	}

	public void setConvoca(ConvocacaoCensoDto convoca) {
		this.convoca = convoca;
	}
	

	public Pessoas getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
	}

	public PessoasFuncionais getFuncional() {
		return funcional;
	}

	public void setFuncional(PessoasFuncionais funcional) {
		this.funcional = funcional;
	}
	
	
	public Cargos getCargo() {
		return cargo;
	}

	public void setCargo(Cargos cargo) {
		this.cargo = cargo;
	}

	public Orgaos getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgaos orgao) {
		this.orgao = orgao;
	}


	static List<PessoasFuncionais> listaFuncionais = new ArrayList<>();

	public void buscaServidor() {
		listaFuncionais = new ArrayList<PessoasFuncionais>();
		try {
			this.pessoa= new PessoasDao().devolvePessoa(cpf);
			listaFuncionais = new GenericPersistence<PessoasFuncionais>(PessoasFuncionais.class)
					.listarRelacionamento("PessoasFuncionais", "NUMR_idDoObjetoPessoas", this.pessoa.getNUMG_idDoObjeto());
		} catch (Exception e) {
			Message.addErrorMessage("Cpf não encontrado");
		}
	}
	
	public void buscaFuncional(ValueChangeEvent event) {
		this.funcional = (PessoasFuncionais) event.getNewValue();
		this.cargo = this.funcional.getNUMR_idDoObjetoCargo();
		this.orgao = this.cargo.getNUMR_idDoObjetoOrgaos();
	}
	
	public List<PessoasFuncionais> getListaDeFuncionais() {
		return listaFuncionais;
	}
	
	private List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("nome", "string"));
		field.add(new Field("cpf", "string"));
		field.add(new Field("cabecalho", "string"));
		field.add(new Field("rodape", "string"));
		field.add(new Field("descricaoCenso", "string"));
		field.add(new Field("matricula", "string"));
		return field;
	}

	private JRDataSource dataSource(List<Field> fields, List<ConvocacaoCensoDto> listaConvocacao) {
		DRDataSource dataSource = new DRDataSource("nome", "cpf", "cabecalho", "rodape", "descricaoCenso", "matricula");
		LocalDate local = new LocalDate();
		String rodape = new StringBuilder().append("Porto Velho, ").append(local.getDayOfMonth()).append(" de ")
				.append(local.monthOfYear().getAsText()).append(" de ").append(local.getYear()).append(".").toString();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Collections.sort(listaConvocacao, (p1, p2) -> p1.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()
				.compareTo(p2.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()));
		for (ConvocacaoCensoDto c : listaConvocacao) {
			dataSource.add(new StringBuilder().append("Ao(a) Sr(a). ")
					.append(c.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_nome()).toString(),
					new StringBuilder().append("CPF: ")
							.append(c.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas()
									.getNUMR_cpf())
							.toString(),
					new StringBuilder()
							.append("O INSTITUTO DE PREVIDENCIA DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA, ")
							.append("convoca vossa senhoria a comparecer em uma de nossas unidades mais próxima,para realizar a atualização ")
							.append("cadastral no período de: ")
							.append(sdf.format(
									local.withMonthOfYear(Integer.parseInt(c.getDESC_competencia().substring(0, 2)))
											.withYear(Integer.parseInt(c.getDESC_competencia().substring(2, 6)))
											.dayOfMonth().withMinimumValue().toDate()))
							.append(" a ")
							.append(sdf.format(
									local.withMonthOfYear(Integer.parseInt(c.getDESC_competencia().substring(0, 2)))
											.withYear(Integer.parseInt(c.getDESC_competencia().substring(2, 6)))
											.dayOfMonth().withMaximumValue().toDate()))
							.append(", portando os seguintes documentos:").toString(),
					rodape, dadosCenso.getDESC_descricao(), new StringBuilder().append("Matrícula: ")
							.append(c.getNUMR_idPessoasFuncionais().getDESC_matricula()).toString());
		}
		return dataSource;
	}

	@Override
	public void salvarObjeto() {

		try {
			ConvocacaoCensoDao dao = new ConvocacaoCensoDao();
			convoca.setNUMR_idCenso(dadosCenso);
			List<ConvocacaoCensoDto> listaConvocacao = dao.devolveListaDeConvocacao(this.convoca.getDESC_competencia(),
					this.convoca.getNUMR_tipoBeneficiario(), this.convoca.getNUMR_idCenso().getNUMG_idDoObjeto());
			List<Pessoas> listaPessoas = new ArrayList<>();
			
			listaConvocacao.forEach(c->{
				listaPessoas.add(c.getNUMR_idPessoasFuncionais().getNUMR_idDoObjetoPessoas());
			});
			
			if (listaConvocacao.isEmpty() ) {
				
				dao.procedureSalvaConvocacaoCenso(this.convoca.getDESC_competencia(),
						this.convoca.getNUMR_tipoBeneficiario(), this.dadosCenso.getNUMG_idDoObjeto());
				criaUsuarioSenha(listaPessoas);
				listaConvocacao = dao.devolveListaDeConvocacao(this.convoca.getDESC_competencia(),
						this.convoca.getNUMR_tipoBeneficiario(), this.convoca.getNUMR_idCenso().getNUMG_idDoObjeto());
			}

			
			if(!listaConvocacao.isEmpty()){
				if (convoca.getNUMR_tipoBeneficiario() == 1) {
					convocacaoInativos(listaConvocacao);
				} else {
					convocacaoPensionistas(listaConvocacao);
				}
				
				Message.addSuccessMessage("Convocação salva com sucesso!");
			}else{
				Message.addErrorMessage("Não existem registros para esta competência!");
			}

		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar convocação!");
		} finally {
			novoObjeto();

		}

	}
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	ModulosPerfil modulos = new ModulosPerfil();
	private Users users = new Users();
	@SuppressWarnings("static-access")
	public void salvaConvocacao(){
		ConvocacaoCensoDao dao = new ConvocacaoCensoDao();
		
		try{
			System.out.println("Funcional: "+this.funcional.getNUMG_idDoObjeto());
			Optional<ConvocacaoCensoDto> conv = Optional.ofNullable(dao.devolveConvocadoPeloFuncional(this.funcional.getNUMG_idDoObjeto()));
			
			if(dao.verificaExistenciaConvocado(this.funcional.getNUMG_idDoObjeto()) == false
					|| conv.isPresent() && new LocalDate(conv.get().getNUMR_idCenso().getDATA_inicio()).getYear() != new LocalDate().now().getYear() ){
				
				List<Pessoas> listaPessoas = new ArrayList<>();
				listaPessoas.add(this.funcional.getNUMR_idDoObjetoPessoas());
				
					this.convoca.setDESC_competencia(sdf.format(this.funcional.getNUMR_idDoObjetoPessoas().getDATA_nascimento()).substring(3, 5)+
							sdf.format(dadosCenso.getDATA_inicio()).substring(6, 10));
					this.convoca.setNUMR_idPessoasFuncionais(this.funcional);
//					this.convoca.setDATA_convocacao(new LocalDate().now().toDate());
					this.convoca.setNUMR_idCenso(dadosCenso);
					/*this.convoca.setNUMR_tipoBeneficiario(ff.getNUMR_situacaoPrevidenciaria().getNUMR_codigo());*/
					new GenericPersistence<ConvocacaoCensoDto>(ConvocacaoCensoDto.class).salvar(this.convoca);
					Message.addSuccessMessage("Convocaçao efetuada com sucesso!");
				if (criaUsuarioSenha(listaPessoas) != true){
					Message.addWarningMessage("Usuário já cadastrado!");
				}
				
			}else{
				Message.addErrorMessage("Convocação existente!");
			}
			this.pessoa = new Pessoas();
			this.funcional = new PessoasFuncionais();
			this.cpf = new String();
			this.dadosCenso = new DadosCenso();
		}catch(Exception e){
			e.printStackTrace();
			Message.addErrorMessage("Erro ao convocar o servidor");
		}
	}

	private void convocacaoInativos(List<ConvocacaoCensoDto> lista) throws IOException {
		Reports report = new Reports();
		report.createReport(Templates.reportTemplate, "convocacaoAposentados", new ArrayList<Column>(), fields(),
				dataSource(fields(), lista));

	}

	private void convocacaoPensionistas(List<ConvocacaoCensoDto> lista) throws IOException {
		Reports report = new Reports();
		report.createReport(Templates.reportTemplate, "convocacaoPensionista", new ArrayList<Column>(), fields(),
				dataSource(fields(), lista));
	}

	@Override
	public void novoObjeto() {
		convoca = new ConvocacaoCensoDto();
		dadosCenso = new DadosCenso();
	}

	@Override
	public List<ConvocacaoCensoDto> listaDeObjetos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exibeListaDeObjetos() {
		new DialogsPrime().showDialog(true, true, true, false, "listaConvocacao");

	}

	@Override
	public void pegaInstanciaDialogo(ConvocacaoCensoDto obj) {
		new DialogsPrime().getInstanceObjectDialog(obj);

	}

	@Override
	public void selecionaObjetoDialogo(SelectEvent event) {
		this.convoca = (ConvocacaoCensoDto) event.getObject();
		this.dadosCenso = convoca.getNUMR_idCenso();

	}

	public List<DadosCenso> getListaCenso() {
		// List<DadosCenso> lista = new CensoDao().devolveListaCenso();
		return new GenericPersistence<DadosCenso>(DadosCenso.class).listarTodos("DadosCenso");
	}

	public List<ConvocacaoCensoDto> getListaDeConvocacao() {
		List<ConvocacaoCensoDto> lista = new GenericPersistence<ConvocacaoCensoDto>(ConvocacaoCensoDto.class)
				.listarTodos("ConvocacaoCenso");
		return lista;
	}

	public void salvaGerenciamentoConvocacao() {
		try {
			/*ConvocacaoCensoDto cc = new GenericPersistence<ConvocacaoCensoDto>(ConvocacaoCensoDto.class)
					.buscarPorId(convocacaoGerenciamento.getNUMG_idDoObjeto());
			cc.setDATA_envio(this.envio);
			cc.setDATA_recebimento(this.recebimento);
			new GenericPersistence<ConvocacaoCensoDto>(ConvocacaoCensoDto.class).salvar(cc);*/
			limpaGerenciamento();
			Message.addSuccessMessage("Gerenciamento salvo com sucesso!");
		} catch (Exception e) {
			Message.addErrorMessage("Erro ao salvar gerenciamento");
		}
	}
	
	boolean res = false;
	private boolean criaUsuarioSenha(List<Pessoas> listaPessoas){
		try{
			listaPessoas.forEach(p->{
				Optional<Users> u = Optional.of(new UsuarioDao().loadUserBeneficio(p.getNUMR_cpf()));
				if(u.get().getMatricula() == null){
					List<Roles> lista = new ArrayList<>();
					lista.add(new Roles("ROLE_ADMINISTRACAOA"));
					this.users.setNome(p.getDESC_nome());
					this.users.setSenha(CriptografiaSenha.criptografa(sdf.format(p.getDATA_nascimento()).replace("/", "").toString()));
					this.users.setMatricula(p.getNUMR_cpf());
					this.users.setRedefinirSenha(false);
					this.users.setContaHabilitada(true);
					this.users.setRoles(lista);
					this.users.setPerfil(new GenericPersistence<PerfilAcesso>(PerfilAcesso.class).buscarPorId(1));
					new GenericPersistence<Users>(Users.class).salvar(users);
					res = true;
				}
			});
		}catch(Exception e){
			e.printStackTrace();
			res = false;
		}
		return res;
	}

	public void limpaGerenciamento() {
		convocacaoGerenciamento = new ConvocacaoCensoDto();
		cpf = new String();
		this.envio = null;
		this.recebimento = null;
	}

}
