package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ExcludeDefaultListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.iperonprev.constantes.TipoOperacaoEnum;

@Entity
@ExcludeDefaultListeners
public class GenericAudit implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String entidade;
	@Enumerated(EnumType.STRING)
	private TipoOperacaoEnum operacao;
	private String valoresAlterados;
	private String usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataOcorrencia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEntidade() {
		return entidade;
	}
	public void setEntidade(String entidade) {
		this.entidade = entidade;
	}
	public TipoOperacaoEnum getOperacao() {
		return operacao;
	}
	public void setOperacao(TipoOperacaoEnum operacao) {
		this.operacao = operacao;
	}
	public String getValoresAlterados() {
		return valoresAlterados;
	}
	public void setValoresAlterados(String valoresAlterados) {
		this.valoresAlterados = valoresAlterados;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}
	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataOcorrencia == null) ? 0 : dataOcorrencia.hashCode());
		result = prime * result + ((entidade == null) ? 0 : entidade.hashCode());
		result = prime * result + id;
		result = prime * result + ((operacao == null) ? 0 : operacao.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valoresAlterados == null) ? 0 : valoresAlterados.hashCode());
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
		GenericAudit other = (GenericAudit) obj;
		if (dataOcorrencia == null) {
			if (other.dataOcorrencia != null)
				return false;
		} else if (!dataOcorrencia.equals(other.dataOcorrencia))
			return false;
		if (entidade == null) {
			if (other.entidade != null)
				return false;
		} else if (!entidade.equals(other.entidade))
			return false;
		if (id != other.id)
			return false;
		if (operacao != other.operacao)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valoresAlterados == null) {
			if (other.valoresAlterados != null)
				return false;
		} else if (!valoresAlterados.equals(other.valoresAlterados))
			return false;
		return true;
	}
}
