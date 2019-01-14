package br.com.iperonprev.controller.dto;

public class FichaFinanceiraDto {

	private String verba;
	private String jan;
	private String fev;
	private String mar;
	private String abr;
	private String mai;
	private String jun;
	private String jul;
	private String ago;
	private String set;
	private String out;
	private String nov;
	private String dez;
	private String decimo;
	private String total;
	public String getVerba() {
		return verba;
	}
	public void setVerba(String verba) {
		this.verba = verba;
	}
	public String getJan() {
		return jan;
	}
	public void setJan(String jan) {
		this.jan = jan;
	}
	public String getFev() {
		return fev;
	}
	public void setFev(String fev) {
		this.fev = fev;
	}
	public String getMar() {
		return mar;
	}
	public void setMar(String mar) {
		this.mar = mar;
	}
	public String getAbr() {
		return abr;
	}
	public void setAbr(String abr) {
		this.abr = abr;
	}
	public String getMai() {
		return mai;
	}
	public void setMai(String mai) {
		this.mai = mai;
	}
	public String getJun() {
		return jun;
	}
	public void setJun(String jun) {
		this.jun = jun;
	}
	public String getJul() {
		return jul;
	}
	public void setJul(String jul) {
		this.jul = jul;
	}
	public String getAgo() {
		return ago;
	}
	public void setAgo(String ago) {
		this.ago = ago;
	}
	public String getSet() {
		return set;
	}
	public void setSet(String set) {
		this.set = set;
	}
	public String getOut() {
		return out;
	}
	public void setOut(String out) {
		this.out = out;
	}
	public String getNov() {
		return nov;
	}
	public void setNov(String nov) {
		this.nov = nov;
	}
	public String getDez() {
		return dez;
	}
	public void setDez(String dez) {
		this.dez = dez;
	}
	public String getDecimo() {
		return decimo;
	}
	public void setDecimo(String decimo) {
		this.decimo = decimo;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abr == null) ? 0 : abr.hashCode());
		result = prime * result + ((ago == null) ? 0 : ago.hashCode());
		result = prime * result + ((decimo == null) ? 0 : decimo.hashCode());
		result = prime * result + ((dez == null) ? 0 : dez.hashCode());
		result = prime * result + ((fev == null) ? 0 : fev.hashCode());
		result = prime * result + ((jan == null) ? 0 : jan.hashCode());
		result = prime * result + ((jul == null) ? 0 : jul.hashCode());
		result = prime * result + ((jun == null) ? 0 : jun.hashCode());
		result = prime * result + ((mai == null) ? 0 : mai.hashCode());
		result = prime * result + ((mar == null) ? 0 : mar.hashCode());
		result = prime * result + ((nov == null) ? 0 : nov.hashCode());
		result = prime * result + ((out == null) ? 0 : out.hashCode());
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		result = prime * result + ((total == null) ? 0 : total.hashCode());
		result = prime * result + ((verba == null) ? 0 : verba.hashCode());
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
		FichaFinanceiraDto other = (FichaFinanceiraDto) obj;
		if (abr == null) {
			if (other.abr != null)
				return false;
		} else if (!abr.equals(other.abr))
			return false;
		if (ago == null) {
			if (other.ago != null)
				return false;
		} else if (!ago.equals(other.ago))
			return false;
		if (decimo == null) {
			if (other.decimo != null)
				return false;
		} else if (!decimo.equals(other.decimo))
			return false;
		if (dez == null) {
			if (other.dez != null)
				return false;
		} else if (!dez.equals(other.dez))
			return false;
		if (fev == null) {
			if (other.fev != null)
				return false;
		} else if (!fev.equals(other.fev))
			return false;
		if (jan == null) {
			if (other.jan != null)
				return false;
		} else if (!jan.equals(other.jan))
			return false;
		if (jul == null) {
			if (other.jul != null)
				return false;
		} else if (!jul.equals(other.jul))
			return false;
		if (jun == null) {
			if (other.jun != null)
				return false;
		} else if (!jun.equals(other.jun))
			return false;
		if (mai == null) {
			if (other.mai != null)
				return false;
		} else if (!mai.equals(other.mai))
			return false;
		if (mar == null) {
			if (other.mar != null)
				return false;
		} else if (!mar.equals(other.mar))
			return false;
		if (nov == null) {
			if (other.nov != null)
				return false;
		} else if (!nov.equals(other.nov))
			return false;
		if (out == null) {
			if (other.out != null)
				return false;
		} else if (!out.equals(other.out))
			return false;
		if (set == null) {
			if (other.set != null)
				return false;
		} else if (!set.equals(other.set))
			return false;
		if (total == null) {
			if (other.total != null)
				return false;
		} else if (!total.equals(other.total))
			return false;
		if (verba == null) {
			if (other.verba != null)
				return false;
		} else if (!verba.equals(other.verba))
			return false;
		return true;
	}
	
}
