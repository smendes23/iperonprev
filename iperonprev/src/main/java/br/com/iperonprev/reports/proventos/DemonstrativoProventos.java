package br.com.iperonprev.reports.proventos;

import java.math.RoundingMode;
import java.math.BigDecimal;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;
import br.com.iperonprev.util.jsf.Field;
import java.util.ArrayList;
import br.com.iperonprev.models.DemonstrativoFinanceiro;
import java.util.List;
import br.com.iperonprev.interfaces.JasperReportBuiderInterface;

public class DemonstrativoProventos implements JasperReportBuiderInterface
{
	 List<DemonstrativoFinanceiro> listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();

	    public DemonstrativoProventos(List<DemonstrativoFinanceiro> listaDemonstrativo) {
	        this.listaDemonstrativo = listaDemonstrativo;
	    }

	    public DemonstrativoProventos() {
	    }

	    public List<Field> listaFields() {
	        ArrayList<Field> field = new ArrayList<Field>();
	        field.add(new Field("codigo", "string"));
	        field.add(new Field("descricao", "string"));
	        field.add(new Field("percentual", "string"));
	        field.add(new Field("fundamentacao", "string"));
	        field.add(new Field("valor", "string"));
	        field.add(new Field("total", "string"));
	        return field;
	    }

	    public JRDataSource dataSource() {
	        DRDataSource dataSource = new DRDataSource(new String[]{"codigo", "descricao", "percentual", "fundamentacao", "valor", "total"});
	        DecimalFormatter df = new DecimalFormatter();
	        BigDecimal total = this.listaDemonstrativo.stream().map(d -> d.getVALR_remuneracao()).filter(c -> !c.equals(new BigDecimal("0"))).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(3, RoundingMode.CEILING);
	        this.listaDemonstrativo.forEach(d -> dataSource.add(new Object[]{d.getNUMR_rubricas().getNUMR_codigo(), d.getNUMR_rubricas().getDESC_descricao(), d.getNUMR_percentual() + "%", d.getNUMR_atosLegais().getDESC_tituloEmenta(), df.formatterToCurrencyBrazilian(d.getVALR_remuneracao()), df.formatterToCurrencyBrazilian(total)}));
	        return dataSource;
	    }

	    public String templateDesigner() {
	        return "proventos_demonstrativo";
	    }
}