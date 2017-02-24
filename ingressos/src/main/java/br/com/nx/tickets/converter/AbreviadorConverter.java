package br.com.nx.tickets.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.nx.tickets.util.Util;

@FacesConverter("br.com.nx.tickets.converter.AbreviadorConverter")
public class AbreviadorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			return Util.abreviar(value).toUpperCase();
		} else {
			return (String) value;
		}
	}
}
