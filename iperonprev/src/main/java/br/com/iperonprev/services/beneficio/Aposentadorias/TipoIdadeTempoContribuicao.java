package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.AposentadoriaIdadeTempo;
import br.com.iperonprev.models.PessoasFuncionais;

public class TipoIdadeTempoContribuicao
implements TipoAposentadoria {
    private TipoAposentadoria tipoAposentadoria;

    @Override
    public TipoAposentadoriaImpl devolveTipoAposentadoria(PessoasFuncionais pf) {
        try {
            if (TipoBeneficioEnum.IDADETEMPO == pf.getENUM_tipoAposentadoria()) {
                AposentadoriaIdadeTempo idadeTempo = new GenericPersistence<AposentadoriaIdadeTempo>(AposentadoriaIdadeTempo.class).buscaObjetoRelacionamento("AposentadoriaIdadeTempo", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
                return new TipoAposentadoriaImpl(pf.getENUM_tipoAposentadoria(), idadeTempo.getENUM_proventos(), idadeTempo.getENUM_reajustes(), new ProporcionalidadeHelper(pf, idadeTempo.getENUM_proventos()).tempoApurado().percentualApurado().resultado());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return this.tipoAposentadoria.devolveTipoAposentadoria(pf);
    }

    @Override
    public void proximo(TipoAposentadoria tipoAposentadoria) {
        this.tipoAposentadoria = tipoAposentadoria;
    }
}