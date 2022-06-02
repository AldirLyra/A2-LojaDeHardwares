package br.loja.hardwares.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Named;

import br.loja.hardwares.application.Util;
import br.loja.hardwares.dao.UsuarioDAO;
import br.loja.hardwares.model.Usuario;

@Named
@ApplicationScoped
public class Usuario1Controller implements Serializable {

	private static final long serialVersionUID = 2121242934096338159L;
	private String filtro;
	private List<Usuario> listaUsuario;
	
	public void editar(int id) {
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.getById(id);
		
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuarioFlash", usuario);
		
		Util.redirect("usuario2.xhtml");
	}
	
	public void novo() {
		Util.redirect("usuario2.xhtml");
	}
	
	public void pesquisar() {
		UsuarioDAO dao = new UsuarioDAO();
		setListaUsuario(dao.getByNome(getFiltro()));
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}


}
