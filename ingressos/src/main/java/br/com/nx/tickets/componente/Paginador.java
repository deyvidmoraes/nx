package br.com.nx.tickets.componente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.nx.tickets.entidade.util.Reportable;
import br.com.nx.tickets.util.SistemaConstantes;

public class Paginador<T extends Paginavel> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer totalPaginas = 0;
	
	private Integer totalRegistros = 0;

	private Integer totalRegistrosPorPagina = 0;

	private Integer paginaCorrente = 0;

	private Integer offset = 0;

	private List<Paginavel> registros;
	
	private List<Reportable> dadosDTO;
	
	private String jsonGrafico;
	
	public Paginador(int totalRegistrosPorPagina) {
		this.totalRegistrosPorPagina = totalRegistrosPorPagina;
		this.paginaCorrente = 1;
		this.offset = 0;
	}

	public Integer getTotalRegistrosPorPagina() {
		return totalRegistrosPorPagina;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public Paginador<T> setTotalRegistros(Integer tr) {
		this.totalRegistros = tr;
		totalPaginas = tr / totalRegistrosPorPagina;
		if ((tr % totalRegistrosPorPagina) != 0) {
			totalPaginas++;
		}
		return this;
	}

	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	public Integer getPaginaCorrente() {
		return paginaCorrente;
	}

	public void setPaginaCorrente(Integer paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}

	public Integer getOffset() {
		return offset;
	}

	public List<Paginavel> getRegistros() {
		if (registros == null) {
			registros = new ArrayList<Paginavel>();
		}
		return registros;
	}
	
	public Paginador<T> setRegistros(List<Paginavel> rs) {
		this.registros = rs;
		configureOffset();
		return this;
	}

	public void paginar(Integer pagina) {
		this.paginaCorrente = pagina;
		configureOffset();
	}
	
	public void iniciarPaginador() {
		paginar(1);
	}
	
	private void configureOffset() {
		this.offset = ((totalRegistrosPorPagina * getPaginaCorrente()) - totalRegistrosPorPagina);
	}

	public List<Integer> numerosPaginas() {
		List<Integer> paginas = new ArrayList<Integer>();
		
		Integer inicio = 1;
		Integer fim   = totalRegistrosPorPagina;
		if (totalRegistros < totalPaginas) {
			fim = totalRegistros;
		}
		if (totalPaginas <= SistemaConstantes.DEZ) {
			fim = totalPaginas;
		}
		
		if (paginaCorrente >= SistemaConstantes.OITO && totalPaginas > SistemaConstantes.DEZ) {
			inicio = (paginaCorrente - SistemaConstantes.CINCO);
			fim    = (paginaCorrente + SistemaConstantes.QUATRO);
			if (fim > totalPaginas) {
				fim = totalPaginas;
			}
		}
		
		for (int i = inicio; i <= fim; i++) {
			paginas.add(i);
		}
		return paginas;
	}
	
	public void setDadosDTO(List<Reportable> dadosDTO) {
		this.dadosDTO = dadosDTO;
		if (this.dadosDTO == null) {
			this.jsonGrafico = null;
		}
	}
	
	public List<Reportable> getDadosDTO() {
		return dadosDTO;
	}
	
	public String getJsonGrafico() {
		return jsonGrafico;
	}
	
	public void setJsonGrafico(String jsonGrafico) {
		this.jsonGrafico = jsonGrafico;
	}

	@Override
	public String toString() {
		return "Paginador [totalRegistros=" + totalRegistros
				+ ", totalPaginas=" + getTotalPaginas() + ", paginaCorrente="
				+ paginaCorrente + ", offset=" + getOffset()
				+ ", totalRegistrosPorPagina=" + totalRegistrosPorPagina
				+ ", registros=" + getRegistros().size() + "]";
	}
}
