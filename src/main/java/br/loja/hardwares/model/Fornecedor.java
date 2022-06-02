package br.loja.hardwares.model;

public enum Fornecedor {
	
	KABUM(1, "Kabum"), 
	TERABYTE(2, "Terabyte"), 
	PICHAU(3, "Pichau"), 
	MEUMICRO(4, "MeuMicro");
	
	private int id;
	private String label;
	
	Fornecedor(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static Fornecedor valueOf(int id) {
		for (Fornecedor fornecedor : Fornecedor.values()) {
			if (id == fornecedor.getId())
				return fornecedor;
		}
		return null;
	}
}
