package br.com.iperonprev.services.atoConcessorio;

import br.com.iperonprev.constantes.SexoEnum;
import br.com.iperonprev.models.PoderPessoas;

public class CabecalhoAtoConcessorioBuilder {

	private PoderPessoas chefeDoPoder;
	private PoderPessoas chefeIperon;
	public CabecalhoAtoConcessorioBuilder() {
	}

	public CabecalhoAtoConcessorioBuilder(PoderPessoas chefeDoPoder, PoderPessoas chefeIperon) {
		this.chefeDoPoder = chefeDoPoder;
		this.chefeIperon = chefeIperon;
	}

	public CabecalhoAtoDto devolveCabecalho() {
		String tratamentoChefePoder=new String();
		String tratamentoIperon=new String();
		if(chefeIperon.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
			tratamentoIperon = "O";
		}else{
			tratamentoIperon = "A";
		}
		
		CabecalhoAtoDto cabecalho = new CabecalhoAtoDto();
		if(this.chefeDoPoder.getREL_orgao().getNUMG_idDoObjeto() == 1){
			
			if(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
				tratamentoChefePoder = "O";
			}else{
				tratamentoChefePoder = "A";
			}
			cabecalho.setTituloCabecalho( new StringBuilder().append(tratamentoChefePoder).append(" PRESIDENTE DO TRIBUNAL DE CONTAS DO ESTADO DE RONDÔNIA E ").append(tratamentoIperon).append(" PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
					.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
					.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008.").toString());
					cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
					cabecalho.setTituloChefePoder( "Presidente do Tribubal de Contas do Estado-RO");
					return cabecalho;
		}else if(this.chefeDoPoder.getREL_orgao().getNUMG_idDoObjeto() == 24){
			
			if(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
				tratamentoChefePoder = "O";
			}else{
				tratamentoChefePoder = "A";
			}
			
			cabecalho.setTituloCabecalho(new StringBuilder().append(tratamentoChefePoder).append(" PRESIDENTE DO TRIBUNAL DE JUSTIÇA DO ESTADO DE RONDÔNIA E ").append(tratamentoIperon).append(" PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
					.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
					.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008.").toString());
					cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
					cabecalho.setTituloChefePoder( "Presidente do Tribunal de Justiça-RO");
					return cabecalho;
		}else if(this.chefeDoPoder.getREL_orgao().getNUMG_idDoObjeto() == 28){
			
			if(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
				tratamentoChefePoder = "O DEFENSOR PÚBLICO";
			}else{
				tratamentoChefePoder = "A DEFENSORA PÚBLICA";
			}
			
			cabecalho.setTituloCabecalho( new StringBuilder().append(tratamentoChefePoder).append(" GERAL DO ESTADO DE RONDÔNIA E ").append(tratamentoIperon).append(" PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
					.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
					.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008.").toString());
					cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
					cabecalho.setTituloChefePoder( "Defensor Público Geral-RO");
					return cabecalho;
		}else if(this.chefeDoPoder.getREL_orgao().getNUMG_idDoObjeto() == 29){
			
			if(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
				tratamentoChefePoder = "O PROCURADOR";
			}else{
				tratamentoChefePoder = "A PROCURADORA";
			}
			
			cabecalho.setTituloCabecalho(  new StringBuilder().append(tratamentoChefePoder).append(" DO MINISTÉRIO PÚBLICO  DO ESTADO DE RONDÔNIA E ").append(tratamentoIperon).append(" PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
					.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
					.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008.").toString());
					cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
					cabecalho.setTituloChefePoder("Procurador Geral do Ministério Público-RO");
					return cabecalho;
		}else if(this.chefeDoPoder.getREL_orgao().getNUMG_idDoObjeto() == 31){
			
			if(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
				tratamentoChefePoder = "O";
			}else{
				tratamentoChefePoder = "A";
			}
			
			cabecalho.setTituloCabecalho(new StringBuilder().append(tratamentoChefePoder).append(" PRESIDENTE DA ASSEMBLÉIA LEGISLATIVA DO ESTADO DE RONDÔNIA E ").append(tratamentoIperon).append(" PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
					.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
					.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008.").toString());
					cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
					cabecalho.setTituloChefePoder("Presidente da Assembléia Legislativa-RO");
		}else{
			
			if(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_sexo() == SexoEnum.M){
				tratamentoChefePoder = "O GOVERNADOR";
			}else{
				tratamentoChefePoder = "A GOVERNADORA";
			}
			
			cabecalho.setTituloCabecalho( new StringBuilder().append(tratamentoChefePoder).append(" DO ESTADO DE RONDÔNIA E ").append(tratamentoIperon).append(" PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
					.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
					.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008.").toString());
					cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome().toUpperCase());
					cabecalho.setTituloChefePoder( "Governador do Estado de Rondônia");
					return cabecalho;
		}
		return null;
		/*switch (this.chefeDoPoder.getNUMG_idDoObjeto()) {
		case 1:
			cabecalho.setTituloCabecalho( new StringBuilder().append("O(A) PRESIDENTE DO TRIBUNAL DE CONTAS DO ESTADO DE RONDÔNIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
			.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
			.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008, conforme processo nº").append(processo)
			.append(".").toString());
			cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
			cabecalho.setTituloChefePoder( "Presidente do Tribubal de Contas do Estado-RO");
			return cabecalho;
		case 24:
			cabecalho.setTituloCabecalho(new StringBuilder().append("O(A) PRESIDENTE DO TRIBUNAL DE JUSTIÇA DO ESTADO DE RONDÔNIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
			.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
			.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008, conforme processo nº").append(processo)
			.append(".").toString());
			cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
			cabecalho.setTituloChefePoder( "Presidente do Tribunal de Justiça-RO");
			return cabecalho;
		case 28:
			cabecalho.setTituloCabecalho( new StringBuilder().append("O(A) DEFENSOR(A) PÚBLICO GERAL DO ESTADO DE RONDÔNIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
			.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
			.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008, conforme processo nº").append(processo)
			.append(".").toString());
			cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
			cabecalho.setTituloChefePoder( "Defensor Público Geral-RO");
			return cabecalho;
		case 29:
			cabecalho.setTituloCabecalho(  new StringBuilder().append("O(A) PROCURADOR(A) DO MINISTÉRIO PÚBLICO  DO ESTADO DE RONDÔNIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
			.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
			.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008, conforme processo nº").append(processo)
			.append(".").toString());
			cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
			cabecalho.setTituloChefePoder("Procurador Geral do Ministério Público-RO");
			return cabecalho;
		case 31:
			cabecalho.setTituloCabecalho(new StringBuilder().append("O(A) PROCURADOR(A) DA ASSEMBLÉIA LEGISLATIVA DO ESTADO DE RONDÔNIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
			.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
			.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008, conforme processo nº").append(processo)
			.append(".").toString());
			cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome());
			cabecalho.setTituloChefePoder("Presidente da Assembléia Legislativa-RO");
			break;
		case 2467:

			break;
		case 2475:

			break;
		case 3554:
			cabecalho.setTituloCabecalho( new StringBuilder().append("O(A) GOVERNADOR(A) DO ESTADO DE RONDÔNIA E O(A) PRESIDENTE DO INSTITUTO DE PREVIDÊNCIA")
			.append(" DOS SERVIDORES PÚBLICOS DO ESTADO DE RONDÔNIA - IPERON no uso das atribuições que lhes conferem as Leis complementares nº 228/2000,")
			.append(" publicada no DOE nº4422, de 31/01/2000 e 432/2008, publicada no DOE nº 0955, de 13/03/2008, conforme processo nº").append(processo)
			.append(".").toString());
			cabecalho.setChefePoder(chefeDoPoder.getNUMR_idDoObjetoPessoa().getDESC_nome().toUpperCase());
			cabecalho.setTituloChefePoder( "Governador do Estado de Rondônia");
			return cabecalho;
		default:
			break;
		}
		return null;*/
	}

}
