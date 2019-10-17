package br.edu.unicid.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.edu.unicid.bean.Bagagem;
import br.edu.unicid.util.ConnectionFactory;

public class BagagemDAO {
	private Connection conn;
	private PreparedStatement ps;

	public BagagemDAO() throws Exception {
		try {
			this.conn = ConnectionFactory.getConnection();
		} catch (Exception e) {
			throw new Exception("erro: \n" + e.getMessage());
		}
	}

	public void salvar(Bagagem bagagem) throws Exception {
		if (bagagem == null)
			throw new Exception("O valor passado nao pode ser nulo");
		try {
			String SQL = "INSERT INTO bagagem (tagUID, nomePassageiro, poltronaPassageiro) "
					+ "VALUES (?, ?, ?)";
			conn = this.conn;
			ps = conn.prepareStatement(SQL);
			ps.setString(1, bagagem.getTagUID());
			ps.setString(2, bagagem.getNomePassageiro());
			ps.setString(3, bagagem.getPoltronaPassageiro());
			ps.executeUpdate();
		} catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}

	}

	public Bagagem consultarBagagem(String tagUID)throws Exception{
		Bagagem bagagem = new Bagagem();	
		try {
			String SQL = "Select * from bagagem Where tagUID = ?";
			ps = this.conn.prepareStatement(SQL);

			ps.setString(1, tagUID);

			ResultSet dados = ps.executeQuery();
			while(dados.next()){
				bagagem.setTagUID(dados.getString("tagUID"));
				bagagem.setNomePassageiro(dados.getString("nomePassageiro"));
				bagagem.setPoltronaPassageiro(dados.getString("poltronaPassageiro"));				
			}
		} catch (SQLException sqle) {
			throw new Exception("Erro ao listar dados da bagagem " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}
		return bagagem;
	}

	public List<Bagagem> listar() throws Exception {
		List<Bagagem> listaBagagens = new ArrayList<>();
		try {
			String SQL = "SELECT * from bagagem";

			conn = this.conn;
			ps = conn.prepareStatement(SQL);

			ResultSet dados = ps.executeQuery();
			while(dados.next()) {
				Bagagem bagagem = new Bagagem();
				bagagem.setTagUID(dados.getString("tagUID"));
				bagagem.setNomePassageiro(dados.getString("nomePassageiro"));
				bagagem.setPoltronaPassageiro(dados.getString("poltronaPassageiro"));
				listaBagagens.add(bagagem);
			}

		} catch (SQLException sqle) {
			throw new Exception("Erro ao inserir dados " + sqle);
		} finally {
			ConnectionFactory.closeConnection(conn, ps);
		}

		return listaBagagens;
	}
}

