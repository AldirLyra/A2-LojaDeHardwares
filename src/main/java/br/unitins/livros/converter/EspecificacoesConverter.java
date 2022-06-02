package br.unitins.livros.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.loja.hardwares.dao.EspecificacoesDAO;
import br.loja.hardwares.model.Especificacoes;

@FacesConverter(forClass = Especificacoes.class)
public class EspecificacoesConverter implements Converter <Especificacoes> {

	@Override
	public Especificacoes getAsObject(FacesContext context, UIComponent component, String id) {
		if (id == null || id.isBlank())
			return null;
		EspecificacoesDAO dao = new EspecificacoesDAO();
		return dao.getById(Integer.valueOf(id));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Especificacoes especificacoes) {
		if (especificacoes == null || especificacoes.getId() == null)
			return null;
		return especificacoes.getId().toString();
	}

}
