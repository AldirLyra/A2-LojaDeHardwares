package br.loja.hardwares.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.loja.hardwares.model.Especificacoes;

public class EspecificacoesDAO implements DAO<Especificacoes>{

	@Override
	public boolean insert(Especificacoes obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Especificacoes obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Especificacoes> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Especificacoes> lista = new ArrayList<Especificacoes>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  e.id, ");
		sql.append("  e.ano_fabricacao, ");
		sql.append("  e.numero_serie, ");
		sql.append("  e.marca, ");
		sql.append("  e.cor, ");
		sql.append("  e.dimensoes, ");
		sql.append("  e.nome ");
		sql.append("FROM ");
		sql.append("  Especificacoes e ");
		sql.append("ORDER BY ");
		sql.append("  e.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Especificacoes especificacoes = new Especificacoes();
				especificacoes.setId(rs.getInt("id"));
				especificacoes.setAnoFabricacao(rs.getDate("ano_fabricacao").toLocalDate());
				especificacoes.setNumDeSerie(rs.getLong("numero_serie"));
				especificacoes.setMarca(rs.getString("marca"));
				especificacoes.setCor(rs.getString("cor"));
				especificacoes.setDimensoes(rs.getString("dimensoes"));
				especificacoes.setNome(rs.getString("nome"));
				
				lista.add(especificacoes);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			lista = null;
		}
		
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Especificacoes getById(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}
		
		Especificacoes especificacoes = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  e.id, ");
		sql.append("  e.ano_fabricacao, ");
		sql.append("  e.numero_serie, ");
		sql.append("  e.marca, ");
		sql.append("  e.cor, ");
		sql.append("  e.dimensoes, ");
		sql.append("  e.nome ");
		sql.append("FROM ");
		sql.append("  Especificacoes e ");
		sql.append("WHERE ");
		sql.append("  e.id = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				especificacoes = new Especificacoes();
				especificacoes.setId(rs.getInt("id"));
				especificacoes.setAnoFabricacao(rs.getDate("ano_fabricacao").toLocalDate());
				especificacoes.setNumDeSerie(rs.getLong("numero_serie"));
				especificacoes.setMarca(rs.getString("marca"));
				especificacoes.setCor(rs.getString("cor"));
				especificacoes.setDimensoes(rs.getString("dimensoes"));
				especificacoes.setNome(rs.getString("nome"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return especificacoes;
	}
}
