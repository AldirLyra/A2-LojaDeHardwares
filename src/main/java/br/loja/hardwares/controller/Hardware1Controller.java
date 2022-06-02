package br.loja.hardwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.loja.hardwares.application.Util;
import br.loja.hardwares.dao.HardwareDAO;
import br.loja.hardwares.model.Hardware;

@Named
@ViewScoped
public class Hardware1Controller implements Serializable {

	private static final long serialVersionUID = -6409248486676105500L;
	private List<Hardware> listaHardware;
	private String filtro;
	
	public void editar(int id) {
		HardwareDAO dao = new HardwareDAO();
		Hardware hardware = dao.getById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("hardwareFlash", hardware);
		
		Util.redirect("produto2.xhtml");
	}
	
	public void novo() {
		Util.redirect("produto2.xhtml");
	}
	
	public void pesquisar() {
		HardwareDAO dao = new HardwareDAO();
		setListaHardware(dao.getByNome(getFiltro()));
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Hardware> getListaHardware() {
		return listaHardware;
	}

	public void setListaHardware(List<Hardware> listaHardware) {
		this.listaHardware = listaHardware;
	}


}
