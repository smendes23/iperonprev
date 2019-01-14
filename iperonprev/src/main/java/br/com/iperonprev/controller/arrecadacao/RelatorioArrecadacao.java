package br.com.iperonprev.controller.arrecadacao;

import java.util.ArrayList;
import java.util.List;

import br.com.iperonprev.models.Enderecos;
import br.com.iperonprev.models.Financeiro;
import br.com.iperonprev.models.FundoPrevidenciario;
import br.com.iperonprev.models.PessoasFuncionais;
import br.com.iperonprev.models.Remessa;
import br.com.iperonprev.util.jsf.DecimalFormatter;
import br.com.iperonprev.util.jsf.Field;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

public class RelatorioArrecadacao {

	public synchronized List<Field> fields() {
		List<Field> field = new ArrayList<>();
		field.add(new Field("documento", "string"));
		field.add(new Field("ieCpfCnpj", "string"));
		field.add(new Field("compIdentificacao", "string"));
		field.add(new Field("competencia", "string"));
		field.add(new Field("vencimento", "string"));
		field.add(new Field("contribuinte", "string"));
		field.add(new Field("endereco", "string"));
		field.add(new Field("municipio", "string"));
		field.add(new Field("uf", "string"));
		field.add(new Field("cep", "string"));
		field.add(new Field("codigoBarras", "string"));
		field.add(new Field("textoCodigoBarras", "string"));
		field.add(new Field("informacoesComplementares", "string"));
		field.add(new Field("codigoReceita", "string"));
		field.add(new Field("numeroParcela", "string"));
		field.add(new Field("codigoMunicipio", "string"));
		field.add(new Field("valorPrincipal", "string"));
		field.add(new Field("valorMulta", "string"));
		field.add(new Field("valorJuros", "string"));
		field.add(new Field("acrescimos", "string"));
		field.add(new Field("valorTotal", "string"));
		return field;
	}
	
	public synchronized JRDataSource dataSourceSegurado(String tipoPessoa,PessoasFuncionais funcionais, String competencia,String vencimento, Remessa remessa, Financeiro financeiro,FundoPrevidenciario rcCodigos) throws Exception {
		DRDataSource dataSource = new DRDataSource("documento",
				"ieCpfCnpj",
				"compIdentificacao",
				"competencia",
				"vencimento",
				"contribuinte",
				"endereco",
				"municipio",
				"uf",
				"cep",
				"codigoBarras",
				"textoCodigoBarras",
				"informacoesComplementares",
				"codigoReceita",
				"numeroParcela",
				"codigoMunicipio",
				"valorPrincipal",
				"valorMulta",
				"valorJuros",
				"acrescimos",
				"valorTotal");
		
		Enderecos enderecos = funcionais.getNUMR_idDoObjetoPessoas().getNUMR_idDoObjetoEndereco();
		 
		dataSource.add(tipoPessoa,
				funcionais.getNUMR_idDoObjetoPessoas().getNUMR_cpf(),
				"-",
				competencia,vencimento,
				funcionais.getNUMR_idDoObjetoPessoas().getDESC_nome(),
				new StringBuilder().append(enderecos.getNUMR_tipoLogradouro()).append(": ").append(enderecos.getDESC_logradouro()).append(", n° ").append(enderecos.getDESC_numero()).append(", bairro: ").append(enderecos.getDESC_bairro()).toString(),
				enderecos.getNUMR_idDoObjetoMunicipio().getDESC_nome(),enderecos.getNUMR_idDoObjetoMunicipio().getNUMR_idDoObjetoEstado().getDESC_sigla(),enderecos.getNUMR_cep(),
				remessa.getDESC_codigoBarras(),remessa.getDESC_codigoBarrasFormatado(),"",
				new StringBuilder().append(rcCodigos.getNUMG_codigo()).toString(),
				"1",
				"-",
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(financeiro.getVALR_contribSegurado()),new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(financeiro.getVALR_multaSegurado()),
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(financeiro.getVALR_jurosSegurado()),
				"-",
				new DecimalFormatter().formatterToCurrencyBrazilianWithTwoDecimal(financeiro.getVALR_contribSegurado().add(financeiro.getVALR_jurosSegurado())
						.add(financeiro.getVALR_multaSegurado())));
		
		return dataSource;
	}
}
