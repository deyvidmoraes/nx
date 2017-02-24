package br.com.nx.tickets.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.nx.tickets.util.Util;

@FacesConverter(forClass = String.class)
public class InputStringConverter implements Converter {

	/**
	 * Sera settado no bean
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			String valor = value.trim();
			if (valor.contains("!")) {
				return valor;
			}
			return Util.removerCaracteresSpeciais(valor.toUpperCase().trim()).replaceAll(";", " ");
		}
		return value;
	}

	/**
	 * Sera exibido na interface
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			String valor = ((String) value).trim();
			if (valor.contains("!")) {
				return valor;
			}
			return valor.toUpperCase();
		} else {
			return (String) value;
		}
	}
}
