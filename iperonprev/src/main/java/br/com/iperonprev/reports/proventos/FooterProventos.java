package br.com.iperonprev.reports.proventos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;

import com.ibm.icu.text.SimpleDateFormat;

import br.com.iperonprev.interfaces.JasperReportBuiderInterface;
import br.com.iperonprev.models.DemonstrativoFinanceiro;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;


public class FooterProventos
implements JasperReportBuiderInterface {
    List<DemonstrativoFinanceiro> listaDemonstrativo = new ArrayList<DemonstrativoFinanceiro>();

    public FooterProventos(List<DemonstrativoFinanceiro> listaDemonstrativo) {
        this.listaDemonstrativo = listaDemonstrativo;
    }

    public FooterProventos() {
    }

    public List<Field> listaFields() {
        ArrayList<Field> field = new ArrayList<Field>();
        field.add(new Field("descricao", "string"));
        field.add(new Field("dataPorExtenso", "string"));
        return field;
    }

    public JRDataSource dataSource() {
        DRDataSource dataSource = new DRDataSource(new String[]{"descricao", "dataPorExtenso"});
        new org.joda.time.LocalDate();
        Date hoje = LocalDate.now().toDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.listaDemonstrativo.forEach(l -> dataSource.add(new Object[]{l.getNUMR_atosLegais().getDESC_ementa(), sdf.format(hoje)}));
        return dataSource;
    }

    public String templateDesigner() {
        return "proventos_footer";
    }
}