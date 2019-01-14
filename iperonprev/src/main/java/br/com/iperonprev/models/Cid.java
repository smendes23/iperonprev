package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Cid implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int NUMG_idDoObjeto;
	@Lob
	@Column
	private String DESC_nome;
	private String NUMR_numCid;
	@ManyToMany(mappedBy="NUMR_cid")
	private List<AposentadoriaPorInvalidez> REL_invalidez;
	@ManyToMany(mappedBy="NUMR_cid")
	private List<AuxilioDoenca> REL_auxilio;
	@ManyToMany(mappedBy="NUMR_cid")
	private List<Pericia> REL_pericia;
	
	
	public List<Pericia> getREL_pericia() {
		return REL_pericia;
	}
	public void setREL_pericia(List<Pericia> rEL_pericia) {
		REL_pericia = rEL_pericia;
	}
	public List<AposentadoriaPorInvalidez> getREL_invalidez() {
		return REL_invalidez;
	}
	public void setREL_invalidez(List<AposentadoriaPorInvalidez> rEL_invalidez) {
		REL_invalidez = rEL_invalidez;
	}
	public List<AuxilioDoenca> getREL_auxilio() {
		return REL_auxilio;
	}
	public void setREL_auxilio(List<AuxilioDoenca> rEL_auxilio) {
		REL_auxilio = rEL_auxilio;
	}
	public int getNUMG_idDoObjeto() {
		return NUMG_idDoObjeto;
	}
	public void setNUMG_idDoObjeto(int nUMG_idDoObjeto) {
		NUMG_idDoObjeto = nUMG_idDoObjeto;
	}
	public String getNUMR_numCid() {
		return NUMR_numCid;
	}
	public void setNUMR_numCid(String nUMR_numCid) {
		NUMR_numCid = nUMR_numCid;
	}
	public String getDESC_nome() {
		return DESC_nome;
	}
	public void setDESC_nome(String dESC_nome) {
		DESC_nome = dESC_nome;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_nome == null) ? 0 : DESC_nome.hashCode());
		result = prime * result + NUMG_idDoObjeto;
		result = prime * result + ((NUMR_numCid == null) ? 0 : NUMR_numCid.hashCode());
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
		Cid other = (Cid) obj;
		if (DESC_nome == null) {
			if (other.DESC_nome != null)
				return false;
		} else if (!DESC_nome.equals(other.DESC_nome))
			return false;
		if (NUMG_idDoObjeto != other.NUMG_idDoObjeto)
			return false;
		if (NUMR_numCid == null) {
			if (other.NUMR_numCid != null)
				return false;
		} else if (!NUMR_numCid.equals(other.NUMR_numCid))
			return false;
		return true;
	}
	
}
