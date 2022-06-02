package br.loja.hardwares.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.loja.hardwares.model.Especificacoes;
import br.loja.hardwares.model.Fornecedor;
import br.loja.hardwares.model.Hardware;
import br.loja.hardwares.model.Perfil;
 
public class HardwareDAO implements DAO<Hardware> {
	
	@Override
	public boolean insert(Hardware obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO produto ( ");
		sql.append("  nome, ");
		sql.append("  valor, ");
		sql.append("  qtd_estoque, ");
		sql.append("  fornecedor, ");
		sql.append("  id_especificacoes ");
		sql.append(") VALUES ( ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ? ");
		sql.append(") ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setDouble(2, obj.getValor());
			stat.setInt(3, obj.getQtdEstoque());
			
			if (obj.getFornecedor() == null)
				stat.setObject(4, null);
			else
				stat.setInt(4, obj.getFornecedor().getId());
			
			if (obj.getEspecificacoes() == null || obj.getEspecificacoes().getId() == null)
				stat.setObject(5, null);
			else
				stat.setInt(5, obj.getEspecificacoes().getId());

			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean update(Hardware obj) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE produto SET  ");
		sql.append("  nome = ?, ");
		sql.append("  valor = ?, ");
		sql.append("  qtd_estoque = ?, ");
		sql.append("  fornecedor = ?, ");
		sql.append("  id_especificacoes = ? ");
		sql.append("WHERE ");
		sql.append("  id_produto = ?  ");

		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setDouble(2, obj.getValor());
			stat.setInt(3, obj.getQtdEstoque());
			
			if (obj.getFornecedor() == null)
				stat.setObject(4, null);
			else
				stat.setInt(4, obj.getFornecedor().getId());
			
			if (obj.getEspecificacoes() == null || obj.getEspecificacoes().getId() == null)
				stat.setObject(5, null);
			else
				stat.setInt(5, obj.getEspecificacoes().getId());
			
			stat.setInt(6, obj.getId());
			
			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public boolean delete(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return false;
		}

		boolean resultado = true;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE ");
		sql.append("  id_produto = ?  ");

		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);

			stat.execute();

		} catch (SQLException e) {
			e.printStackTrace();
			resultado = false;
		}

		try {
			stat.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultado;
	}

	@Override
	public List<Hardware> getAll() {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Hardware> lista = new ArrayList<Hardware>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id_produto, ");
		sql.append("  p.nome, ");
		sql.append("  p.valor, ");
		sql.append("  p.qtd_estoque, ");
		sql.append("  p.fornecedor, ");
		sql.append("  p.id_especificacoes, ");
		sql.append("  e.nome AS nomes_especificacoes, ");
		sql.append("  e.ano_fabricacao, ");
		sql.append("  e.numero_serie, ");
		sql.append("  e.marca, ");
		sql.append("  e.cor, ");
		sql.append("  e.dimensoes ");
		sql.append("FROM ");
		sql.append("  produto p LEFT JOIN especificacoes e ON e.id = p.id_especificacoes ");
		sql.append("ORDER BY ");
		sql.append("  p.nome ");

		ResultSet rs = null;

		try {
			rs = conn.createStatement().executeQuery(sql.toString());
			while (rs.next()) {
				Hardware hardware = new Hardware();
				hardware.setId(rs.getInt("id_produto"));
				hardware.setNome(rs.getString("nome"));
				hardware.setValor(rs.getDouble("valor"));
				hardware.setQtdEstoque(rs.getInt("qtd_estoque"));
				hardware.setFornecedor(Fornecedor.valueOf(rs.getInt("fornecedor")));
				
				hardware.setEspecificacoes(new Especificacoes());
				if (rs.getObject("id_especificacoes") != null) {
					hardware.getEspecificacoes().setId(rs.getInt("id_especificacoes"));
					hardware.getEspecificacoes().setAnoFabricacao(rs.getDate("ano_fabricacao").toLocalDate());
					hardware.getEspecificacoes().setNumDeSerie(rs.getLong("numero_serie"));
					hardware.getEspecificacoes().setMarca(rs.getString("marca"));
					hardware.getEspecificacoes().setCor(rs.getString("cor"));
					hardware.getEspecificacoes().setDimensoes(rs.getString("dimensoes"));
					hardware.getEspecificacoes().setNome(rs.getString("nomes_especificacoes"));
				
				}
				
				lista.add(hardware);
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

	public List<Hardware> getByNome(String nome) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}

		List<Hardware> lista = new ArrayList<Hardware>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id_produto, ");
		sql.append("  p.nome, ");
		sql.append("  p.valor, ");
		sql.append("  p.qtd_estoque, ");
		sql.append("  p.fornecedor, ");
		sql.append("  p.id_especificacoes, ");
		sql.append("  e.nome AS nomes_especificacoes, ");
		sql.append("  e.ano_fabricacao, ");
		sql.append("  e.numero_serie, ");
		sql.append("  e.marca, ");
		sql.append("  e.cor, ");
		sql.append("  e.dimensoes ");
		sql.append("FROM ");
		sql.append("  produto p LEFT JOIN especificacoes e ON e.id = p.id_especificacoes ");
		sql.append("WHERE ");
		sql.append(" p.nome iLIKE ? ");
		sql.append("ORDER BY ");
		sql.append("  p.nome ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "%" + nome + "%");
			
			rs = stat.executeQuery();
			
			while (rs.next()) {
				Hardware hardware = new Hardware();
				hardware.setId(rs.getInt("id_produto"));
				hardware.setNome(rs.getString("nome"));
				hardware.setValor(rs.getDouble("valor"));
				hardware.setQtdEstoque(rs.getInt("qtd_estoque"));
				hardware.setFornecedor(Fornecedor.valueOf(rs.getInt("fornecedor")));
				
				hardware.setEspecificacoes(new Especificacoes());
				if (rs.getObject("id_especificacoes") != null) {
					hardware.getEspecificacoes().setId(rs.getInt("id_especificacoes"));
					hardware.getEspecificacoes().setNome(rs.getString("nomes_especificacoes"));
					hardware.getEspecificacoes().setAnoFabricacao(rs.getDate("ano_fabricacao").toLocalDate());
					hardware.getEspecificacoes().setNumDeSerie(rs.getLong("numero_serie"));
					hardware.getEspecificacoes().setMarca(rs.getString("marca"));
					hardware.getEspecificacoes().setCor(rs.getString("cor"));
					hardware.getEspecificacoes().setDimensoes(rs.getString("dimensoes"));
					hardware.getEspecificacoes().setNome(rs.getString("nomes_especificacoes"));
				
				}
				
				lista.add(hardware);
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

	public Hardware getById(int id) {
		Connection conn = DAO.getConnection();
		if (conn == null) {
			return null;
		}
		
		Hardware hardware = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  p.id_produto, ");
		sql.append("  p.nome, ");
		sql.append("  p.valor, ");
		sql.append("  p.qtd_estoque, ");
		sql.append("  p.fornecedor, ");
		sql.append("  p.id_especificacoes, ");
		sql.append("  e.nome AS nomes_especificacoes, ");
		sql.append("  e.ano_fabricacao, ");
		sql.append("  e.numero_serie, ");
		sql.append("  e.marca, ");
		sql.append("  e.cor, ");
		sql.append("  e.dimensoes ");
		sql.append("FROM ");
		sql.append("  produto p LEFT JOIN especificacoes e ON e.id = p.id_especificacoes ");
		sql.append("WHERE ");
		sql.append(" p.id_produto = ? ");

		ResultSet rs = null;
		PreparedStatement stat = null;
		
		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, id);
			
			rs = stat.executeQuery();
			if (rs.next()) {
				hardware = new Hardware();
				hardware.setId(rs.getInt("id_produto"));
				hardware.setNome(rs.getString("nome"));
				hardware.setValor(rs.getDouble("valor"));
				hardware.setQtdEstoque(rs.getInt("qtd_estoque"));
				hardware.setFornecedor(Fornecedor.valueOf(rs.getInt("fornecedor")));
				
				hardware.setEspecificacoes(new Especificacoes());
				if (rs.getObject("id_especificacoes") != null) {
					hardware.getEspecificacoes().setId(rs.getInt("id_especificacoes"));
					hardware.getEspecificacoes().setNome(rs.getString("nomes_especificacoes"));
					hardware.getEspecificacoes().setAnoFabricacao(rs.getDate("ano_fabricacao").toLocalDate());
					hardware.getEspecificacoes().setNumDeSerie(rs.getLong("numero_serie"));
					hardware.getEspecificacoes().setMarca(rs.getString("marca"));
					hardware.getEspecificacoes().setCor(rs.getString("cor"));
					hardware.getEspecificacoes().setDimensoes(rs.getString("dimensoes"));
					hardware.getEspecificacoes().setNome(rs.getString("nomes_especificacoes"));
				}
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
		
		return hardware;
	}
	
}
