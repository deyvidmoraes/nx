package br.com.nx.tickets.converter;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("br.com.nx.tickets.converter.NumberConverter")
public class NumberConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		return value;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value != null) {
			Locale brasil = new Locale("pt", "BR"); 
			DecimalFormat df = new DecimalFormat("###,###,###", new DecimalFormatSymbols(brasil));  
			return df.format(value);
		} else {
			return (String) value;
		}
	}
}