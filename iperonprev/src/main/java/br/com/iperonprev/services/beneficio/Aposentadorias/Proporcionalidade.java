package br.com.iperonprev.services.beneficio.Aposentadorias;

import java.math.BigDecimal;

public class Proporcionalidade {
    private BigDecimal percentual;
    private String tempoLiquido;

    public BigDecimal getPercentual() {
        return this.percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public String getTempoLiquido() {
        return this.tempoLiquido;
    }

    public void setTempoLiquido(String tempoLiquido) {
        this.tempoLiquido = tempoLiquido;
    }
}