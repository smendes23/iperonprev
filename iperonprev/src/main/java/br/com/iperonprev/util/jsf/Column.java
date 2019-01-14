package br.com.iperonprev.util.jsf;

public class Column {

	public Column(){}
	public Column(String title,String field,String dataType){
		this.title = title;
		this.field = field;
		this.dataType = dataType;
	}
	
	private String title;
	private String field;
	private String dataType;
	
	
	public String getTitle() {
		return title;
	}


	public String getField() {
		return field;
	}


	public String getDataType() {
		return dataType;
	}


}
