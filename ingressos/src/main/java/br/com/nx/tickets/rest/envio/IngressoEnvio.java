package br.com.nx.tickets.rest.envio;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author marceloeugenio Mar 16, 2016 6:28:21 PM 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class IngressoEnvio {

    @XmlElement(name = "id_usuario")
    private Integer idUsuario;

    public Integer getIdUsuario() {
        return idUsuario;
    }

	@Override
	public String toString() {
		return "IngressoEnvio [idUsuario=" + idUsuario + "]";
	}
}