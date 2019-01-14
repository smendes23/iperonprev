package br.com.iperonprev.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class DadosBancarios {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer NUMG_idDoObjeto = 0;
	@OneToOne
	private Pessoas REL_pessoa;
	@OneToOne
	private Bancos REL_banco;
	private String DESC_agencia;
	private String DESC_conta;
	@Lob
	private String DESC_observacao;
	@Version
	private int NUMR_versao;
	
	public String getDESC_observacao() {
		return DESC_observacao;
	}
	public void setDESC_observacao(String dESC_observacao) {
		DESC_observacao = dESC_observacao;
	}
	public Integer getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(Integer nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public Pessoas getREL_pessoa() {
		return REL_pessoa;
	}
	public void setREL_pessoa(Pessoas rEL_pessoa) {
		REL_pessoa = rEL_pessoa;
	}
	public Bancos getREL_banco() {
		return REL_banco;
	}
	public void setREL_banco(Bancos rEL_banco) {
		REL_banco = rEL_banco;
	}
	public String getDESC_agencia() {
		return DESC_agencia;
	}
	public void setDESC_agencia(String dESC_agencia) {
		DESC_agencia = dESC_agencia;
	}
	public String getDESC_conta() {
		return DESC_conta;
	}
	public void setDESC_conta(String dESC_conta) {
		DESC_conta = dESC_conta;
	}
	public int getNUMR_versao() {
		return NUMR_versao;
	}
	public void setNUMR_versao(int nUMR_versao) {
		NUMR_versao = nUMR_versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_agencia == null) ? 0 : DESC_agencia.hashCode());
		result = prime * result + ((DESC_conta == null) ? 0 : DESC_conta.hashCode());
		result = prime * result + ((NUMG_idDoObjeto == null) ? 0 : NUMG_idDoObjeto.hashCode());
		result = prime * result + NUMR_versao;
		result = prime * result + ((REL_banco == null) ? 0 : REL_banco.hashCode());
		result = prime * result + ((REL_pessoa == null) ? 0 : REL_pessoa.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DadosBancarios other = (DadosBancarios) obj;
		if (DESC_agencia == null) {
			if (other.DESC_agencia != null)
				return false;
		} else if (!DESC_agencia.equals(other.DESC_agencia))
			return false;
		if (DESC_conta == null) {
			if (other.DESC_conta != null)
				return false;
		} else if (!DESC_conta.equals(other.DESC_conta))
			return false;
		if (NUMG_idDoObjeto == null) {
			if (other.NUMG_idDoObjeto != null)
				return false;
		} else if (!NUMG_idDoObjeto.equals(other.NUMG_idDoObjeto))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		if (REL_banco == null) {
			if (other.REL_banco != null)
				return false;
		} else if (!REL_banco.equals(other.REL_banco))
			return false;
		if (REL_pessoa == null) {
			if (other.REL_pessoa != null)
				return false;
		} else if (!REL_pessoa.equals(other.REL_pessoa))
			return false;
		return true;
	}
}
