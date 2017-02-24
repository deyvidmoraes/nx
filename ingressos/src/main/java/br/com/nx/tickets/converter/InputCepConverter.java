package br.com.nx.tickets.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.nx.tickets.util.SistemaConstantes;

@FacesConverter("br.com.nx.tickets.converter.InputCepConverter")
public class InputCepConverter implements Converter {

	/**
	 * Sera settado no bean
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value != null) {
			value = value.replace("-", "");
			value = value.replace(".", "");
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
			String cep = value.toString();
			if (cep.length() <= SistemaConstantes.OITO
					&& cep.length() > SistemaConstantes.CINCO
					&& !cep.contains("-")) {
				cep = cep.substring(SistemaConstantes.ZERO, SistemaConstantes.DOIS) 
						+ "."
						+ cep.substring(SistemaConstantes.DOIS, SistemaConstantes.CINCO) 
						+ "-" 
						+ cep.substring(SistemaConstantes.CINCO, cep.length()); 
			}
			return cep;
		} else {
			return (String) value;
		}
	}
}
