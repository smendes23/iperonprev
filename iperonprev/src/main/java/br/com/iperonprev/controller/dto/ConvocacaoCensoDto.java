package br.com.iperonprev.controller.dto;

import java.io.Serializable;

import br.com.iperonprev.models.DadosCenso;
import br.com.iperonprev.models.PessoasFuncionais;
			
public class ConvocacaoCensoDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private int NUMR_tipoBeneficiario;
	private String DESC_competencia;
	private DadosCenso NUMR_idCenso;
	private PessoasFuncionais NUMR_idPessoasFuncionais;
	private transient boolean FLAG_recadastramento;
	private transient boolean FLAG_pendente;
	public int getNUMR_tipoBeneficiario() {
		return NUMR_tipoBeneficiario;
	}
	public void setNUMR_tipoBeneficiario(int nUMR_tipoBeneficiario) {
		NUMR_tipoBeneficiario = nUMR_tipoBeneficiario;
	}
	public String getDESC_competencia() {
		return DESC_competencia;
	}
	public void setDESC_competencia(String dESC_competencia) {
		DESC_competencia = dESC_competencia;
	}
	public DadosCenso getNUMR_idCenso() {
		return NUMR_idCenso;
	}
	public void setNUMR_idCenso(DadosCenso nUMR_idCenso) {
		NUMR_idCenso = nUMR_idCenso;
	}
	public PessoasFuncionais getNUMR_idPessoasFuncionais() {
		return NUMR_idPessoasFuncionais;
	}
	public void setNUMR_idPessoasFuncionais(PessoasFuncionais nUMR_idPessoasFuncionais) {
		NUMR_idPessoasFuncionais = nUMR_idPessoasFuncionais;
	}
	public boolean isFLAG_recadastramento() {
		return FLAG_recadastramento;
	}
	public void setFLAG_recadastramento(boolean fLAG_recadastramento) {
		FLAG_recadastramento = fLAG_recadastramento;
	}
	public boolean isFLAG_pendente() {
		return FLAG_pendente;
	}
	public void setFLAG_pendente(boolean fLAG_pendente) {
		FLAG_pendente = fLAG_pendente;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESC_competencia == null) ? 0 : DESC_competencia.hashCode());
		result = prime * result + ((NUMR_idCenso == null) ? 0 : NUMR_idCenso.hashCode());
		result = prime * result + ((NUMR_idPessoasFuncionais == null) ? 0 : NUMR_idPessoasFuncionais.hashCode());
		result = prime * result + NUMR_tipoBeneficiario;
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
		ConvocacaoCensoDto other = (ConvocacaoCensoDto) obj;
		if (DESC_competencia == null) {
			if (other.DESC_competencia != null)
				return false;
		} else if (!DESC_competencia.equals(other.DESC_competencia))
			return false;
		if (NUMR_idCenso == null) {
			if (other.NUMR_idCenso != null)
				return false;
		} else if (!NUMR_idCenso.equals(other.NUMR_idCenso))
			return false;
		if (NUMR_idPessoasFuncionais == null) {
			if (other.NUMR_idPessoasFuncionais != null)
				return false;
		} else if (!NUMR_idPessoasFuncionais.equals(other.NUMR_idPessoasFuncionais))
			return false;
		if (NUMR_tipoBeneficiario != other.NUMR_tipoBeneficiario)
			return false;
		return true;
	}
}
