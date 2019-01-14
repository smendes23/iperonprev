package br.com.iperonprev.services.beneficio.Aposentadorias;

import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoAposentadoria;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoAposentadoriaImpl;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoCompulsoria;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoIdade;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoIdadeTempoContribuicao;
import br.com.iperonprev.services.beneficio.Aposentadorias.TipoInvalidez;

public class QualificaProporcionalidade {
    public TipoAposentadoriaImpl devolveProporcionalidade(PessoasFuncionais pf) {
        TipoIdade idade = new TipoIdade();
        TipoIdadeTempoContribuicao idadeTempo = new TipoIdadeTempoContribuicao();
        TipoCompulsoria compulsoria = new TipoCompulsoria();
        TipoInvalidez invalidez = new TipoInvalidez();
        TipoIdade nulo = new TipoIdade();
        idade.proximo((TipoAposentadoria)idadeTempo);
        idadeTempo.proximo((TipoAposentadoria)compulsoria);
        compulsoria.proximo((TipoAposentadoria)invalidez);
        invalidez.proximo((TipoAposentadoria)nulo);
        return idade.devolveTipoAposentadoria(pf);
    }
}