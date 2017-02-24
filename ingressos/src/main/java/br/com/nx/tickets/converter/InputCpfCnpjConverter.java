package br.com.nx.tickets.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.nx.tickets.util.Util;

@FacesConverter("br.com.nx.tickets.converter.InputCpfCnpjConverter")
public class InputCpfCnpjConverter implements Converter {

	/**
	 * Sera settado no bean
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			value = Util.removerNaoDigitos(value);
		}
		return value;
	}

	/**
	 * Sera exibido na interface
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return (String) value;
	}
}
