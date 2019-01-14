package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.constantes.TipoReajusteEnum;

public class TipoAposentadoriaImpl {
    private TipoBeneficioEnum tipoBeneficio;
    private TipoProventosEnum tipoProventos;
    private TipoReajusteEnum tipoReajuste;
    private Proporcionalidade proporcionalidade;

    public TipoAposentadoriaImpl() {
    }

    public TipoAposentadoriaImpl(TipoBeneficioEnum tipoBeneficio, TipoProventosEnum tipoProventos, TipoReajusteEnum tipoReajuste, Proporcionalidade proporcionalidade) {
        this.tipoBeneficio = tipoBeneficio;
        this.tipoProventos = tipoProventos;
        this.tipoReajuste = tipoReajuste;
        this.proporcionalidade = proporcionalidade;
    }

    public TipoBeneficioEnum getTipoBeneficio() {
        return this.tipoBeneficio;
    }

    public TipoProventosEnum getTipoProventos() {
        return this.tipoProventos;
    }

    public TipoReajusteEnum getTipoReajuste() {
        return this.tipoReajuste;
    }

    public Proporcionalidade getProporcionalidade() {
        return this.proporcionalidade;
    }
}