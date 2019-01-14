package br.com.iperonprev.reports.ctc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.CertidaoTempoContribuicao;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class HeaderCtcComplemento implements JasperReportBuiderInterface {

	private CertidaoTempoContribuicao ctc;

	public HeaderCtcComplemento() {
		// TODO Auto-generated constructor stub
	}

	public HeaderCtcComplemento(CertidaoTempoContribuicao ctc) {
		this.ctc = ctc;
	}

	@Override
	public List<Field> listaFields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("periodoContribuicao", "string"));
		field.add(new Field("fonteInformacao", "string"));
		field.add(new Field("observacao", "string"));
		return field;
	}

	@Override
	public JRDataSource dataSource() {
		DRDataSource dataSource = new DRDataSource("periodoContribuicao", "fonteInformacao", "observacao");
		
		FuncionaisFuncoes ff = new GenericPersistence<FuncionaisFuncoes>(FuncionaisFuncoes.class).buscaObjetoRelacionamento("FuncionaisFuncoes", "NUMR_idPessoas",this.ctc.getREL_listaDeFuncionais().get(0).getNUMR_idDoObjetoPessoas().getNUMG_idDoObjeto());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String periodoCompreendido = new String();
		
		try{
			periodoCompreendido = new StringBuilder().append(sdf.format(ff.getDATA_ingressoEnte())).append(" A ")
					.append(sdf.format(new LocalDate(ff.getDATA_egressoEnte()).minusDays(1).toDate()))
					.toString();
			if(ctc.getDATA_ultimaContribuicao() != null){
				periodoCompreendido = new StringBuilder().append(sdf.format(ff.getDATA_ingressoEnte())).append(" A ")
				.append(sdf.format(new LocalDate(ctc.getDATA_ultimaContribuicao()).minusDays(1).toDate()))
				.toString();
			}
		}catch(Exception e){
			System.out.println("Erro ao converter data");
		}

		

		dataSource.add(
				periodoCompreendido,
				ctc.getDESC_fonte(),
				ctc.getDESC_observacao());
		return dataSource;
	}

	@Override
	public String templateDesigner() {
		return "ctc_header_complemento";
	}

}
