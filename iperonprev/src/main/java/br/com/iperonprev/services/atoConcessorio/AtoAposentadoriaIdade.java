package br.com.iperonprev.services.atoConcessorio;

import java.text.SimpleDateFormat;

import br.com.iperonprev.constantes.TipoBeneficioEnum;
import br.com.iperonprev.constantes.TipoProventosEnum;
import br.com.iperonprev.controller.dto.AtoConcessorioDto;
import br.com.iperonprev.dao.GenericPersistence;
import br.com.iperonprev.interfaces.AtoConcessorio;
import br.com.iperonprev.models.AposentadoriaIdade;
import br.com.iperonprev.models.AtosConcessorios;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.PoderPessoas;

public class AtoAposentadoriaIdade implements AtoConcessorio{
	private AtoConcessorio proximo;
	@Override
	public AtoConcessorioDto redirecionaAto(AtosConcessorios atosConcessorios,PoderPessoas chefePoder,PoderPessoas presidenteIperon) {
		try{
			PessoasFuncionais pf = atosConcessorios.getREL_pessoasFuncionais();
			if(pf.getENUM_tipoAposentadoria() == TipoBeneficioEnum.IDADE){
			AposentadoriaIdade idade = new GenericPersistence<AposentadoriaIdade>(AposentadoriaIdade.class).buscaObjetoRelacionamento("AposentadoriaIdade", "NUMR_idDoObejtoPessoasFuncionais", pf.getNUMG_idDoObjeto());
			AtoConcessorioDto dto = new AtoConcessorioDto();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String proventos = new String();
			CabecalhoAtoDto ccb = new CabecalhoAtoConcessorioBuilder(chefePoder,presidenteIperon).devolveCabecalho();
			
			StringBuilder atoLegal = new StringBuilder();
			for (int i = 0; i < idade.getREL_atoLegais().size(); i++) {
				atoLegal.append(idade.getREL_atoLegais().get(i).getDESC_tituloEmenta());
				if(i != idade.getREL_atoLegais().size()-1){
					atoLegal.append(";");
				}else{
					atoLegal.append(".");
				}
			}
			
			dto.setNumeroProcesso(idade.getNUMR_processo());
				dto.setTituloRelatorio(new StringBuilder().append("ATO CONCESSÓRIO DE APOSENTADORIA Nº ")
					.append(atosConcessorios.getNUMR_ato()).append("/IPERON/").append(chefePoder.getREL_orgao().getDESC_sigla())
					.append(", DE ").append(sdf.format(atosConcessorios.getDATA_publicacao())).append(".").toString());
				
				if(idade.getENUM_proventos() == TipoProventosEnum.INTEGRAL){
					proventos = "integrais";
				}else{
					proventos = "proporcionais";
				}
				dto.setCabecalho(ccb.getTituloCabecalho());
				
				dto.setTexto1(
						new StringBuilder().append("1 - Conceder aposentadoria  voluntária por idade com proventos ").append(proventos).append(" ao(a) servidor(a) ")
						.append(pf.getNUMR_idDoObjetoPessoas().getDESC_nome().toUpperCase()).append(", ocupante do cargo de ").append(pf.getNUMR_idDoObjetoCargo().getDESC_nome()).append(", matrícula nº ")
						.append(pf.getDESC_matricula()).append(", com carga horária de ").append(pf.getNUMR_idDoObjetoCargo().getNUMR_cargaHoraria())
						.append(" horas semanais, pertencente ao quado de pessoal do(a) ").append(chefePoder.getREL_orgao().getDESC_nome()).append(" , com fulcro no ").append(atoLegal.toString()).toString()
						);
				dto.setTexto2(
						new StringBuilder().append("2 - Os reajustes serão revistos na mesma data e proporção, sempre que se modificar a remuneração dos servidores em atividade.").toString()
						);
				dto.setTexto3(
						new StringBuilder().append("3 - Este ato entra em vigor na data de sua publicação.").toString()
						);
				
				dto.setChefeIperon(presidenteIperon.getNUMR_idDoObjetoPessoa().getDESC_nome().toUpperCase());
				dto.setChefePoder(ccb.getChefePoder());
				dto.setPoder(ccb.getTituloChefePoder());
				return dto;
			}	
		}catch(Exception e){
			
		}
		
		return proximo.redirecionaAto(atosConcessorios,chefePoder,presidenteIperon);
	}

	@Override
	public void proximoAto(AtoConcessorio ato) {
		this.proximo = ato;
	}

}
