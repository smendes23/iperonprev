package br.com.iperonprev.util.jsf;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.context.RequestContext;

public class DialogsPrime {

	public void showDialog(boolean modal,boolean minimize,boolean maximize,boolean resize,String page){
		Map<String,Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", modal);
		opcoes.put("minimizable",minimize);
		opcoes.put("maximizable",maximize);
		opcoes.put("resizable", resize);
		opcoes.put("contentHeight", 480);
		opcoes.put("contentWidth", 900);
		RequestContext.getCurrentInstance().openDialog(page,opcoes,null);
	}
	
	public void showDialogWithAndHeight(boolean modal,boolean minimize,boolean maximize,boolean resize,String page, int height, int width){
		Map<String,Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", modal);
		opcoes.put("minimizable",minimize);
		opcoes.put("maximizable",maximize);
		opcoes.put("resizable", resize);
		opcoes.put("contentHeight", height);
		opcoes.put("contentWidth", width);
		RequestContext.getCurrentInstance().openDialog(page,opcoes,null);
	}
	
	public void showDialogWithAndHeightWihoutClose(boolean modal,boolean minimize,boolean maximize,boolean resize,String page, int height, int width){
		Map<String,Object> opcoes = new HashMap<String, Object>();
		opcoes.put("modal", modal);
		opcoes.put("minimizable",minimize);
		opcoes.put("maximizable",maximize);
		opcoes.put("resizable", resize);
		opcoes.put("contentHeight", height);
		opcoes.put("contentWidth", width);
		opcoes.put("closable", false);
		RequestContext.getCurrentInstance().openDialog(page,opcoes,null);
	}

	public void getInstanceObjectDialog(Object obj){
		RequestContext.getCurrentInstance().closeDialog(obj);
	}
	
	public void closeDialog(String obj){
		RequestContext.getCurrentInstance().closeDialog(obj);
	}
	
	/*
	 * opcoes.put("contentHeight", 480);
		opcoes.put("contentWidth", 900);
	 * */
	
}
