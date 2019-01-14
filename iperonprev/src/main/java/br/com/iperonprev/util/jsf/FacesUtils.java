package br.com.iperonprev.util.jsf;

import java.util.Iterator;

import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;

public class FacesUtils {

	@SuppressWarnings("rawtypes")
	public static void cleanSubmittedValues(UIComponent component){
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
        }
        // Dependendo de como se implementa um Composite Component, ele retorna ZERO
        // na busca por filhos. Nesse caso devemos iterar sobre os componentes que o 
        // compõe de forma diferente.
        if(UIComponent.isCompositeComponent(component)) {
            Iterator i = component.getFacetsAndChildren();
            while(i.hasNext()) {
                UIComponent comp = (UIComponent) i.next();

                //TODO: isolar em um método?
                if (comp.getChildCount() > 0) {
                    for (UIComponent child : comp.getChildren()) {
                        cleanSubmittedValues(child);
                    }
                }
            }
        }
        //TODO: isolar em um método?
        if (component.getChildCount() > 0) {
            for (UIComponent child : component.getChildren()) {
                cleanSubmittedValues(child);
            }
        }
}
}
