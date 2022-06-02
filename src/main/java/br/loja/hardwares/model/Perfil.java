package br.loja.hardwares.model;

public enum Perfil {
	ADMINSTRADOR(1, "Administrador"), 
	CLIENTE(2, "Cliente");
	
	private int id;
	private String label;
	
	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}
	
	public static Perfil valueOf(int id) {
		for (Perfil perfil : Perfil.values()) {
			if (id == perfil.getId())
				return perfil;
		}
		return null;
	}
}
