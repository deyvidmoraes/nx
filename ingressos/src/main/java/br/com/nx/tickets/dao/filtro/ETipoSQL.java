package br.com.nx.tickets.dao.filtro;

public enum ETipoSQL {
	SELECT, COUNT, GRAFICO, HIERARQUIA, COUNT_HIERARQUIA, GRAFICO_HIERARQUIA;
	
	public boolean isSelect() {
		if (this.equals(SELECT)) {
			return true;
		}
		return false;
	}
	
	public boolean isCount() {
		if (this.equals(COUNT) || this.equals(COUNT_HIERARQUIA)) {
			return true;
		}
		return false;
	}
	
	public boolean isGrafico() {
		if (this.equals(GRAFICO) || this.equals(GRAFICO_HIERARQUIA)) {
			return true;
		}
		return false;
	}
	
	public boolean isHierarquia() {
		if (this.equals(HIERARQUIA)) {
			return true;
		}
		return false;
	}
}
