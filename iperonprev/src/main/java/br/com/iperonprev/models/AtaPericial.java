package br.com.iperonprev.models;

import java.io.Serializable;
import java.util.List;

public class AtaPericial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String DESC_anamnese;
	private String DESC_inspecaoGeral;
	private String DESC_aparelhoRespiratório;
	private String DESC_aparelhoUrinario;
	private String DESC_sistemaEndocrino;
	private String DESC_exameMental;
	private String DESC_exameOtorrino;
	private String DESC_aparelhoCirculatorio;
	private String DESC_aparelhoDigestivo;
	private String DESC_sistemaLigamentar;
	private String DESC_sistemaNeurologico;
	private String DESC_exameOftalmologico;
	private List<TipoExames> NUMR_listaExames;
	public String getDESC_anamnese() {
		return DESC_anamnese;
	}
	public void setDESC_anamnese(String dESC_anamnese) {
		DESC_anamnese = dESC_anamnese;
	}
	public String getDESC_inspecaoGeral() {
		return DESC_inspecaoGeral;
	}
	public void setDESC_inspecaoGeral(String dESC_inspecaoGeral) {
		DESC_inspecaoGeral = dESC_inspecaoGeral;
	}
	public String getDESC_aparelhoRespiratório() {
		return DESC_aparelhoRespiratório;
	}
	public void setDESC_aparelhoRespiratório(String dESC_aparelhoRespiratório) {
		DESC_aparelhoRespiratório = dESC_aparelhoRespiratório;
	}
	public String getDESC_aparelhoUrinario() {
		return DESC_aparelhoUrinario;
	}
	public void setDESC_aparelhoUrinario(String dESC_aparelhoUrinario) {
		DESC_aparelhoUrinario = dESC_aparelhoUrinario;
	}
	public String getDESC_sistemaEndocrino() {
		return DESC_sistemaEndocrino;
	}
	public void setDESC_sistemaEndocrino(String dESC_sistemaEndocrino) {
		DESC_sistemaEndocrino = dESC_sistemaEndocrino;
	}
	public String getDESC_exameMental() {
		return DESC_exameMental;
	}
	public void setDESC_exameMental(String dESC_exameMental) {
		DESC_exameMental = dESC_exameMental;
	}
	public String getDESC_exameOtorrino() {
		return DESC_exameOtorrino;
	}
	public void setDESC_exameOtorrino(String dESC_exameOtorrino) {
		DESC_exameOtorrino = dESC_exameOtorrino;
	}
	public String getDESC_aparelhoCirculatorio() {
		return DESC_aparelhoCirculatorio;
	}
	public void setDESC_aparelhoCirculatorio(String dESC_aparelhoCirculatorio) {
		DESC_aparelhoCirculatorio = dESC_aparelhoCirculatorio;
	}
	public String getDESC_aparelhoDigestivo() {
		return DESC_aparelhoDigestivo;
	}
	public void setDESC_aparelhoDigestivo(String dESC_aparelhoDigestivo) {
		DESC_aparelhoDigestivo = dESC_aparelhoDigestivo;
	}
	public String getDESC_sistemaLigamentar() {
		return DESC_sistemaLigamentar;
	}
	public void setDESC_sistemaLigamentar(String dESC_sistemaLigamentar) {
		DESC_sistemaLigamentar = dESC_sistemaLigamentar;
	}
	public String getDESC_sistemaNeurologico() {
		return DESC_sistemaNeurologico;
	}
	public void setDESC_sistemaNeurologico(String dESC_sistemaNeurologico) {
		DESC_sistemaNeurologico = dESC_sistemaNeurologico;
	}
	public String getDESC_exameOftalmologico() {
		return DESC_exameOftalmologico;
	}
	public void setDESC_exameOftalmologico(String dESC_exameOftalmologico) {
		DESC_exameOftalmologico = dESC_exameOftalmologico;
	}
	public List<TipoExames> getNUMR_listaExames() {
		return NUMR_listaExames;
	}
	public void setNUMR_listaExames(List<TipoExames> nUMR_listaExames) {
		NUMR_listaExames = nUMR_listaExames;
	}
	
	
}
