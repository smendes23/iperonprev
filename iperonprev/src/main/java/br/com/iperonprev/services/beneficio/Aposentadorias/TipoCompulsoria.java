package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.AposentadoriaCompulsoria;
import br.com.iperonprev.models.PessoasFuncionais;

public class TipoCompulsoria
implements TipoAposentadoria {
    private TipoAposentadoria tipoAposentadoria;

    @Override
    public TipoAposentadoriaImpl devolveTipoAposentadoria(PessoasFuncionais pf) {
        try {
            if (TipoBeneficioEnum.COMPULSORIA == pf.getENUM_tipoAposentadoria()) {
                AposentadoriaCompulsoria compulsoria = (AposentadoriaCompulsoria)new GenericPersistence<AposentadoriaCompulsoria>(AposentadoriaCompulsoria.class).buscaObjetoRelacionamento("AposentadoriaCompulsoria", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto().intValue());
                return new TipoAposentadoriaImpl(pf.getENUM_tipoAposentadoria(), compulsoria.getENUM_proventos(), compulsoria.getENUM_rejuste(), new ProporcionalidadeHelper(pf, compulsoria.getENUM_proventos()).tempoApurado().percentualApurado().resultado());
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