package br.loja.hardwares.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.loja.hardwares.application.Session;
import br.loja.hardwares.application.Util;
import br.loja.hardwares.model.Usuario;


@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -4784761085725358702L;
	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) 
			usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		return usuarioLogado;
	}
	
	public void encerrarSessao() {
		Session.getInstance().invalidateSession();
		Util.redirect("login2.xhtml");
	}
}