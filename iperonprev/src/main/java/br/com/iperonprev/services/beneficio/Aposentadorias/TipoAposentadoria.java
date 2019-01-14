package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoAposentadoriaImpl;

public interface TipoAposentadoria {
    public TipoAposentadoriaImpl devolveTipoAposentadoria(PessoasFuncionais var1);

    public void proximo(TipoAposentadoria var1);
}