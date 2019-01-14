package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import br.com.iperonprev.constantes.TipoBeneficioEnum;

@Entity
public class AtosLegais  implements Serializable{


	
	private static final long serialVersionUID = -6792480271821903726L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@ManyToOne
	private TipoAtosLegais NUMR_tipoAtos;
//	@Column(length = 20)
	private String NUMR_numeroAno;
	private Date DATA_publicacao;
	private Date DATA_vigencia;
	private Date DATA_revogacao;
//	@Column(length=20)
	private String DESC_doe;
//	@Column(length=100)
	private String DESC_tituloEmenta;
	private String DESC_ementa;
	@Enumerated(EnumType.STRING)
	private TipoBeneficioEnum ENUM_tipoBeneficio;
	@Version
	private int NUMR_versao;
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public TipoAtosLegais getNUMR_tipoAtos() {
		return NUMR_tipoAtos;
	}
	public void setNUMR_tipoAtos(TipoAtosLegais nUMR_tipoAtos) {
		NUMR_tipoAtos = nUMR_tipoAtos;
	}
	public String getNUMR_numeroAno() {
		return NUMR_numeroAno;
	}
	public void setNUMR_numeroAno(String nUMR_numeroAno) {
		NUMR_numeroAno = nUMR_numeroAno;
	}
	public Date getDATA_publicacao() {
		return DATA_publicacao;
	}
	public void setDATA_publicacao(Date dATA_publicacao) {
		DATA_publicacao = dATA_publicacao;
	}
	public Date getDATA_vigencia() {
		return DATA_vigencia;
	}
	public void setDATA_vigencia(Date dATA_vigencia) {
		DATA_vigencia = dATA_vigencia;
	}
	public Date getDATA_revogacao() {
		return DATA_revogacao;
	}
	public void setDATA_revogacao(Date dATA_revogacao) {
		DATA_revogacao = dATA_revogacao;
	}
	public String getDESC_doe() {
		return DESC_doe;
	}
	public void setDESC_doe(String dESC_doe) {
		DESC_doe = dESC_doe;
	}
	public String getDESC_tituloEmenta() {
		return DESC_tituloEmenta;
	}
	public void setDESC_tituloEmenta(String dESC_tituloEmenta) {
		DESC_tituloEmenta = dESC_tituloEmenta;
	}
	public String getDESC_ementa() {
		return DESC_ementa;
	}
	public void setDESC_ementa(String dESC_ementa) {
		DESC_ementa = dESC_ementa;
	}
	public TipoBeneficioEnum getENUM_tipoBeneficio() {
		return ENUM_tipoBeneficio;
	}
	public void setENUM_tipoBeneficio(TipoBeneficioEnum eNUM_tipoBeneficio) {
		ENUM_tipoBeneficio = eNUM_tipoBeneficio;
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
		result = prime * result + ((DATA_publicacao == null) ? 0 : DATA_publicacao.hashCode());
		result = prime * result + ((DATA_revogacao == null) ? 0 : DATA_revogacao.hashCode());
		result = prime * result + ((DATA_vigencia == null) ? 0 : DATA_vigencia.hashCode());
		result = prime * result + ((DESC_doe == null) ? 0 : DESC_doe.hashCode());
		result = prime * result + ((DESC_ementa == null) ? 0 : DESC_ementa.hashCode());
		result = prime * result + ((DESC_tituloEmenta == null) ? 0 : DESC_tituloEmenta.hashCode());
		result = prime * result + ((ENUM_tipoBeneficio == null) ? 0 : ENUM_tipoBeneficio.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_numeroAno == null) ? 0 : NUMR_numeroAno.hashCode());
		result = prime * result + ((NUMR_tipoAtos == null) ? 0 : NUMR_tipoAtos.hashCode());
		result = prime * result + NUMR_versao;
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
		AtosLegais other = (AtosLegais) obj;
		if (DATA_publicacao == null) {
			if (other.DATA_publicacao != null)
				return false;
		} else if (!DATA_publicacao.equals(other.DATA_publicacao))
			return false;
		if (DATA_revogacao == null) {
			if (other.DATA_revogacao != null)
				return false;
		} else if (!DATA_revogacao.equals(other.DATA_revogacao))
			return false;
		if (DATA_vigencia == null) {
			if (other.DATA_vigencia != null)
				return false;
		} else if (!DATA_vigencia.equals(other.DATA_vigencia))
			return false;
		if (DESC_doe == null) {
			if (other.DESC_doe != null)
				return false;
		} else if (!DESC_doe.equals(other.DESC_doe))
			return false;
		if (DESC_ementa == null) {
			if (other.DESC_ementa != null)
				return false;
		} else if (!DESC_ementa.equals(other.DESC_ementa))
			return false;
		if (DESC_tituloEmenta == null) {
			if (other.DESC_tituloEmenta != null)
				return false;
		} else if (!DESC_tituloEmenta.equals(other.DESC_tituloEmenta))
			return false;
		if (ENUM_tipoBeneficio != other.ENUM_tipoBeneficio)
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_numeroAno == null) {
			if (other.NUMR_numeroAno != null)
				return false;
		} else if (!NUMR_numeroAno.equals(other.NUMR_numeroAno))
			return false;
		if (NUMR_tipoAtos == null) {
			if (other.NUMR_tipoAtos != null)
				return false;
		} else if (!NUMR_tipoAtos.equals(other.NUMR_tipoAtos))
			return false;
		if (NUMR_versao != other.NUMR_versao)
			return false;
		return true;
	}
}
