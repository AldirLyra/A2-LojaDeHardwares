package br.loja.hardwares.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.loja.hardwares.application.Util;
import br.loja.hardwares.dao.EspecificacoesDAO;
import br.loja.hardwares.dao.HardwareDAO;
import br.loja.hardwares.model.Especificacoes;
import br.loja.hardwares.model.Fornecedor;
import br.loja.hardwares.model.Hardware;

@Named
@ViewScoped
public class Hardware2Controller implements Serializable {

	private static final long serialVersionUID = -6409248486676105500L;
	private List<Especificacoes> listaEspecificacoes;
	private Hardware hardware;
	
	public Hardware2Controller() {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("hardwareFlash");
		setHardware((Hardware)flash.get("hardwareFlash"));
	}
	
	public List<Especificacoes> getListaEspecificacoes() {
		if (listaEspecificacoes == null) {
			EspecificacoesDAO dao = new EspecificacoesDAO();
			listaEspecificacoes = dao.getAll();
			if (listaEspecificacoes == null)
				listaEspecificacoes = new ArrayList<Especificacoes>();
		}
		return listaEspecificacoes;
	}
	
	public void voltar() {
		Util.redirect("produto1.xhtml");
	}

	public void incluir() {
		HardwareDAO dao = new HardwareDAO();
		if (!dao.insert(getHardware())) {
			Util.addMessageInfo("Erro ao tentar incluir o hardware.");
			return;
		}
		limpar();
		Util.addMessageInfo("Inclusão realizada com sucesso.");
	}

	public void alterar() {
		HardwareDAO dao = new HardwareDAO();
		if (!dao.update(getHardware())) {
			Util.addMessageInfo("Erro ao tentar alterar o hardware.");
			return;
		}
		limpar();
		Util.addMessageInfo("Alteração realizada com sucesso.");
	}

	public void excluir() {
		HardwareDAO dao = new HardwareDAO();
		if (!dao.delete(getHardware().getId())) {
			Util.addMessageInfo("Erro ao tentar excluir o hardware.");
			return;
		}
		Util.addMessageInfo("Exclusão realizada com sucesso.");
		limpar();
	}

	public void limpar() {
		hardware = null;
	}
	
	public Hardware getHardware() {
		if (hardware == null) {
			hardware = new Hardware();
			hardware.setEspecificacoes(new Especificacoes());
		}
		return hardware;
	}
	
	public Fornecedor[] getListaFornecedor() {
		return Fornecedor.values();
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
	}


}
