package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.AposentadoriaIdade;
import br.com.iperonprev.models.PessoasFuncionais;

public class TipoIdade
implements TipoAposentadoria {
    private TipoAposentadoria tipoAposentadoria;

    @Override
    public TipoAposentadoriaImpl devolveTipoAposentadoria(PessoasFuncionais pf) {
        try {
            if (TipoBeneficioEnum.IDADE == pf.getENUM_tipoAposentadoria()) {
                AposentadoriaIdade idade = new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class).buscaObjetoRelacionamento("AposentadoriaIdade", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
                return new TipoAposentadoriaImpl(pf.getENUM_tipoAposentadoria(), idade.getENUM_proventos(), idade.getENUM_reajuste(), new ProporcionalidadeHelper(pf, idade.getENUM_proventos()).tempoApurado().percentualApurado().resultado());
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