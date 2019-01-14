package br.com.iperonprev.controller.beneficio;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.Part;

import org.joda.time.LocalDateTime;
import org.omnifaces.cdi.ViewScoped;

import br.com.iperonprev.dao.SisobiDao;
import br.com.iperonprev.models.Sisobi;
import br.com.iperonprev.reports.container.Reports;
import br.com.iperonprev.reports.container.Templates;
import br.com.iperonprev.util.jsf.Column;
import br.com.iperonprev.util.jsf.CopyFile;
import br.com.iperonprev.util.jsf.Field;
import br.com.iperonprev.util.jsf.Message;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
@Named
@ViewScoped
public class SisobiBean extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Part arquivo;
	private String competenciaInicio;
	private String competenciaFim;
	private String querySisobi;
	List<Sisobi> listaSisobi = new ArrayList<>();
	SisobiDao dao = new SisobiDao();
	
	
	public String getQuerySisobi() {
		return querySisobi;
	}

	public void setQuerySisobi(String querySisobi) {
		this.querySisobi = querySisobi;
	}

	public String getCompetenciaInicio() {
		return competenciaInicio;
	}

	public void setCompetenciaInicio(String competenciaInicio) {
		this.competenciaInicio = competenciaInicio;
	}

	public String getCompetenciaFim() {
		return competenciaFim;
	}

	public void setCompetenciaFim(String competenciaFim) {
		this.competenciaFim = competenciaFim;
	}

	public Part getArquivo() {
		return arquivo;
	}

	public void setArquivo(Part arquivo) {
		this.arquivo = arquivo;
	}

	public void pegaArquivo() throws Exception {
		CopyFile cf = new CopyFile();
		boolean statusFile = false;
		try {
			if(cf.getFileAndSendSmb(arquivo, "smb://192.168.130.30/SISOBI/","Administrator:1p3r0n.2015") != true ){
//			if(cf.getFileAndSendSmb(arquivo, "smb://localhost/SISOBI/","Saulo:123") != true ){
				Message.addErrorMessage("Arquivo já existe no servidor!");
				statusFile = true;
			}
		} catch (Exception e) {
			System.out.println("Nulo");
		}finally {
			if(statusFile == false){
				new SisobiDao().procedureSisobi(cf.getFilename(arquivo));
				
			}
			
		}
	}
	

	public void geraSisobi() throws IOException {
		List<Sisobi> listaSisobi = new ArrayList<Sisobi>();
		SisobiDao dao = new SisobiDao();
		String mes1 = "12";
		String ano = new String();
		
		try{
			
				if(Integer.parseInt(competenciaInicio.substring(0,2)) > 1 && Integer.parseInt(competenciaInicio.substring(0,2)) < 12){
					int mesCompetencia= Integer.parseInt(competenciaInicio.substring(0,2)) -1;
					if(mesCompetencia<10){
						mes1  = new StringBuilder().append(0).append(mesCompetencia).toString();
					}else{
						mes1  = new StringBuilder().append(mesCompetencia).toString();
					}
					ano = new StringBuilder().append(Integer.parseInt(competenciaInicio.substring(2,6))).toString();
				} else if(Integer.parseInt(competenciaInicio.substring(0,2)) == 12){
					mes1  = new StringBuilder().append(Integer.parseInt(competenciaInicio.substring(0,2)) -1).toString();
					ano = new StringBuilder().append(Integer.parseInt(competenciaInicio.substring(2,6))).toString();
				}else if(Integer.parseInt(competenciaInicio.substring(0,2)) == 1){
					mes1  = new StringBuilder().append(mes1).toString();
					ano = new StringBuilder().append(Integer.parseInt(competenciaInicio.substring(2,6)) -1).toString();
				}
				listaSisobi = dao.devolveListaDeSisobi(new StringBuilder().append(mes1).append(ano).toString());
			
			if(!listaSisobi.isEmpty()){
				Reports report = new Reports();
				report.createReport(Templates.reportTemplate, "sisobi", new ArrayList<Column>(), fields(),
						dataSourceSisobi(fields(), listaSisobi));
				
			}else{
				Message.addErrorMessage("Não existe dados para essa competência!");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Objeto Nulo");
		}
	}
	
	private List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("cpf", "string"));
		field.add(new Field("nome", "string"));
		field.add(new Field("mae", "string"));
		field.add(new Field("dataNascimento", "string"));
		field.add(new Field("dataObito", "string"));
		field.add(new Field("tituloRelatorio", "string"));
		return field;
	}
	
	private JRDataSource dataSourceSisobi(List<Field> fields,List<Sisobi> listaSisobi) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		DRDataSource dataSource = new DRDataSource("cpf","nome","mae","dataNascimento","dataObito","tituloRelatorio");

		LocalDateTime data = new LocalDateTime();
		String mes = new String();
		String mesInicio = data.withMonthOfYear(Integer.parseInt(competenciaInicio.substring(0, 2))).monthOfYear().getAsText();
		if(!this.competenciaInicio.equals("")){
			mes = new StringBuilder().append("Relatório Sisobi - ")
					.append(mesInicio)
					.append(" de ").append(this.competenciaInicio.substring(2, 6)).toString();
		}else{
			Message.addErrorMessage("Não existem dados para esta competência!");
		}
		
		for (Sisobi s : listaSisobi) {
			dataSource.add(
					s.getNUMR_idDoObjetoPessoa().getNUMR_cpf(),
					s.getNUMR_idDoObjetoPessoa().getDESC_nome(),
					s.getNUMR_idDoObjetoPessoa().getDESC_mae(),
					sdf.format(s.getNUMR_idDoObjetoPessoa().getDATA_nascimento()),
					sdf.format(s.getDATA_obito()),
					mes
					);
		}
		return dataSource;
	}
	
	public void consultaSisobi(){
		try{
			if(!dao.devolveListaSisobiNome(querySisobi).isEmpty()){
				listaSisobi = dao.devolveListaSisobiNome(querySisobi);
				return;
			}else if(!dao.devolveListaSisobiCpf(querySisobi).isEmpty()){
				listaSisobi = dao.devolveListaSisobiCpf(querySisobi);
				return;
			}else if(!dao.devolveListaSisobiNomeDaMae(querySisobi).isEmpty()){
				listaSisobi = dao.devolveListaSisobiNomeDaMae(querySisobi);
				return;
			}else{
				Message.addErrorMessage("Servidor não encontrado!");
			}
		}catch(Exception e){
			
		}
	}
	
	public List<Sisobi> getListaDeConsultaSisobi(){
		return listaSisobi;
	}
	
}
