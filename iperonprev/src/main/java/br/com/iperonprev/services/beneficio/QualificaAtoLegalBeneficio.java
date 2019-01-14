package br.com.iperonprev.services.beneficio;

import java.util.Date;

import br.com.iperonprev.constantes.LegislacaoBeneficioEnum;
import br.com.iperonprev.interfaces.TemporalidadeDeBeneficios;
import br.com.iperonprev.models.Pensao;
import br.com.iperonprev.util.jsf.RetornaTempos;

public class QualificaAtoLegalBeneficio {


	public LegislacaoBeneficioEnum executa(Pensao pensao,Date obito){
		
//		Pessoas dependente = pensao.getNUMR_idDoObjetoDependentes().getNUMR_dependente();
		/*List<Sisobi> lista = new GenericPersistence<Sisobi>(Sisobi.class).listarRelacionamento("Sisobi", 
				"NUMR_idDoObjetoPessoa", pensao.getNUMR_idDoObjetoDependentes().getNUMR_servidor().getNUMG_idDoObjeto());
		Sisobi obito = lista.get(0);*/
		
			TemporalidadeDeBeneficios dl0942 = new DL09AE42();
			TemporalidadeDeBeneficios dl09683 = new DL09ALei683();
			TemporalidadeDeBeneficios lei068 = new Lei068();
			TemporalidadeDeBeneficios lc228 = new LeiComplementar228();
			TemporalidadeDeBeneficios lc253 = new LeiComplementar253();
			TemporalidadeDeBeneficios lc432 = new LeiComplementar432();
			TemporalidadeDeBeneficios leiNula = new LeiNula();
			
			dl0942.setProximoTempoBeneficio(dl09683);
			dl09683.setProximoTempoBeneficio(lei068);
			lei068.setProximoTempoBeneficio(lc228);
			lc228.setProximoTempoBeneficio(lc253);
			lc253.setProximoTempoBeneficio(lc432);
			lc432.setProximoTempoBeneficio(leiNula);
			
			return dl0942.devolveAtoLegal(RetornaTempos.retornaAnosApartirDeDuasDatas(pensao.getREL_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDATA_nascimento(), obito), 
					obito, pensao.getENUM_invalido(), pensao.getENUM_estudante(), 
					"pensao.getNUMR_idDoObjetoDependentes().getNUMR_tipoDependencia().getDESC_nome()", pensao.getREL_pessoasFuncionais().getNUMR_idDoObjetoPessoas().getDESC_sexo());
			
	}
}
