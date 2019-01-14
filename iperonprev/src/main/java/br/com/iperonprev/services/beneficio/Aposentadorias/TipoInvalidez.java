package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.models.AposentadoriaPorInvalidez;
import br.com.iperonprev.models.PessoasFuncionais;

public class TipoInvalidez
implements TipoAposentadoria {
    private TipoAposentadoria tipoAposentadoria;

    @Override
    public TipoAposentadoriaImpl devolveTipoAposentadoria(PessoasFuncionais pf) {
        try {
            System.out.println(pf.getDATA_Beneficio());
            if (TipoBeneficioEnum.INVALIDEZ == pf.getENUM_tipoAposentadoria()) {
                AposentadoriaPorInvalidez invalidez = new GenericPersistence<AposentadoriaPorInvalidez>(AposentadoriaPorInvalidez.class).buscaObjetoRelacionamento("AposentadoriaPorInvalidez", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
                return new TipoAposentadoriaImpl(pf.getENUM_tipoAposentadoria(), invalidez.getENUM_proventos(), invalidez.getENUM_reajuste(), new ProporcionalidadeHelper(pf, invalidez.getENUM_proventos()).tempoApurado().percentualApurado().resultado());
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