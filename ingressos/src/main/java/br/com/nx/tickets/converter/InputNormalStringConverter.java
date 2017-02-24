package br.com.nx.tickets.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("br.com.nx.tickets.converter.InputNormalStringConverter")
public class InputNormalStringConverter implements Converter {

	/**
	 * Sera settado no bean
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return value;
	}

	/**
	 * Sera exibido na interface
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return ((String) value);
		} else {
			return (String) value;
		}
	}
}
