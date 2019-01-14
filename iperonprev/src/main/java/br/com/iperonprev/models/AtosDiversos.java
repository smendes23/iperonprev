package br.com.iperonprev.models;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import br.com.iperonprev.constantes.TipoAtosDiversosEnum;

@Entity
public class AtosDiversos implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne(cascade=CascadeType.MERGE)
	private PessoasFuncionais NUMR_pessoasFuncionais;
	@Enumerated(EnumType.STRING)
	@NotNull(message="Tipo de Ato obrigatório!")
	private TipoAtosDiversosEnum ENUM_tipoAtosDiversos;
	@Size(min=10,message="Campo descrição deve conter pelo menos 10 carateres!")
	private String DESC_informacao;
	@Version
	private int versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public PessoasFuncionais getNUMR_pessoasFuncionais() {
		return NUMR_pessoasFuncionais;
	}
	public void setNUMR_pessoasFuncionais(PessoasFuncionais nUMR_pessoasFuncionais) {
		NUMR_pessoasFuncionais = nUMR_pessoasFuncionais;
	}
	public TipoAtosDiversosEnum getENUM_tipoAtosDiversos() {
		return ENUM_tipoAtosDiversos;
	}
	public void setENUM_tipoAtosDiversos(TipoAtosDiversosEnum eNUM_tipoAtosDiversos) {
		ENUM_tipoAtosDiversos = eNUM_tipoAtosDiversos;
	}
	public String getDESC_informacao() {
		return DESC_informacao;
	}
	public void setDESC_informacao(String dESC_informacao) {
		DESC_informacao = dESC_informacao;
	}
	public int getVersao() {
		return versao;
	}
	public void setVersao(int versao) {
		this.versao = versao;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_informacao == null) ? 0 : DESC_informacao.hashCode());
		result = prime * result + ((ENUM_tipoAtosDiversos == null) ? 0 : ENUM_tipoAtosDiversos.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_pessoasFuncionais == null) ? 0 : NUMR_pessoasFuncionais.hashCode());
		result = prime * result + versao;
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
		AtosDiversos other = (AtosDiversos) obj;
		if (DESC_informacao == null) {
			if (other.DESC_informacao != null)
				return false;
		} else if (!DESC_informacao.equals(other.DESC_informacao))
			return false;
		if (ENUM_tipoAtosDiversos != other.ENUM_tipoAtosDiversos)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_pessoasFuncionais == null) {
			if (other.NUMR_pessoasFuncionais != null)
				return false;
		} else if (!NUMR_pessoasFuncionais.equals(other.NUMR_pessoasFuncionais))
			return false;
		if (versao != other.versao)
			return false;
		return true;
	}
}
