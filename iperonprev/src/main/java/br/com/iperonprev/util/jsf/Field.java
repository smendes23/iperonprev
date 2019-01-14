package br.com.iperonprev.util.jsf;

public class Field {
	
	public Field(){}
	public Field(String field,String dataType){
		this.field = field;
		this.dataType = dataType;
	}
	
	private String field;
	private String dataType;
	public String getField() {
		return field;
	}
	public String getDataType() {
		return dataType;
	}
	
}
