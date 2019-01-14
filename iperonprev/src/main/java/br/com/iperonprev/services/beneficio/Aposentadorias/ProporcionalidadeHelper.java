package br.com.iperonprev.services.beneficio.Aposentadorias;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.dao.FuncionaisFuncoesDao;
import br.com.iperonprev.models.FuncionaisFuncoes;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class ProporcionalidadeHelper {
    private static final int TEMPOHOMEM = 12775;
    private static final int TEMPOMULHER = 10950;
    private PessoasFuncionais pf;
    private TipoProventosEnum tipoProventos;
    Proporcionalidade proporcionalidade = new Proporcionalidade();
    DecimalFormatter df = new DecimalFormatter();
    private int tempo = 0;

    public ProporcionalidadeHelper() {
    }

    public ProporcionalidadeHelper(PessoasFuncionais pf) {
        this.pf = pf;
    }

    public ProporcionalidadeHelper(PessoasFuncionais pf, TipoProventosEnum tipoProventos) {
        this.pf = pf;
        this.tipoProventos = tipoProventos;
    }

    public ProporcionalidadeHelper tempoApurado() {
        try {
            List<FuncionaisFuncoes> ff = new FuncionaisFuncoesDao().devolveFuncionalFuncoes(this.pf.getNUMR_idDoObjetoPessoas().getNUMR_cpf());
            this.tempo = RetornaTempos.retornaDiasApartirDeDuasDatas((Date)((FuncionaisFuncoes)ff.get(0)).getDATA_ingressoEnte(), (Date)this.pf.getDATA_Beneficio());
            if (this.tempo >= 10950) {
                this.tempo = 10950;
            }
            this.proporcionalidade.setTempoLiquido(this.df.formatterToThousand(new BigDecimal(this.tempo)) + "/" + "10.950");
            if (TipoProventosEnum.INTEGRAL == this.tipoProventos) {
                this.proporcionalidade.setTempoLiquido(this.df.formatterToThousand(new BigDecimal(10950)) + "/" + "10.950");
            }
            if (this.pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M) {
                if (this.tempo >= 12775) {
                    this.tempo = 12775;
                }
                if (TipoProventosEnum.INTEGRAL == this.tipoProventos) {
                    this.proporcionalidade.setTempoLiquido(this.df.formatterToThousand(new BigDecimal(12775)) + "/" + "12.775");
                } else {
                    this.proporcionalidade.setTempoLiquido(this.df.formatterToThousand(new BigDecimal(this.tempo)) + "/" + "12.775");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Erro ao gerar tempo apurado" + e.getMessage());
        }
        return this;
    }

    public ProporcionalidadeHelper percentualApurado() {
        try {
            this.proporcionalidade.setPercentual(new BigDecimal(this.tempo).multiply(new BigDecimal(100)).divide(new BigDecimal(10950), 2, RoundingMode.FLOOR));
            if (TipoProventosEnum.INTEGRAL == this.tipoProventos) {
                this.proporcionalidade.setPercentual(new BigDecimal(100));
            }
            if (this.pf.getNUMR_idDoObjetoPessoas().getDESC_sexo() == SexoEnum.M) {
                this.proporcionalidade.setPercentual(new BigDecimal(this.tempo).multiply(new BigDecimal(100)).divide(new BigDecimal(12775), 2, RoundingMode.FLOOR));
                if (TipoProventosEnum.INTEGRAL == this.tipoProventos) {
                    this.proporcionalidade.setPercentual(new BigDecimal(100));
                }
            }
        }
        catch (Exception e) {
            System.out.println("Erro ao gerar percentual: " + e.getMessage());
        }
        return this;
    }

    public Proporcionalidade resultado() {
        return this.proporcionalidade;
    }
}