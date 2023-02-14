package ModeloBD_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConexionBD.ConexionSGL;
import ModeloBD_DTO.Jornada;

public class TablaJornada implements Patron_DAO<Jornada>{
	ConexionSGL conexion = new ConexionSGL();
	Connection con = conexion.getCon();
	String Delete = "DELETE FROM `jornada` WHERE `IdEmpleadoJornada` = ?";
	String Buscar = "SELECT * FROM `jornada` WHERE `IdEmpleadoJornada` = ?";
	String BuscarId = "SELECT Max(`IdJornada`) FROM `jornada`";
	String Select = "SELECT * FROM `jornada`";
	String Update = "UPDATE `jornada` SET `hora_salida`=? WHERE `IdJornada`=?";
	String UpdateHora = "UPDATE `jornada` SET `hora_salida`=? WHERE `IdJornada`=?";
	String Insert = "INSERT INTO `jornada`(`IdJornada`, `IdEmpleadoJornada`, `Fecha`, `hora_entrada`, `hora_salida`) VALUES (?,?,?,?,?)";
	PreparedStatement ps;
	
	@Override
	public boolean insertar(Jornada t) throws SQLException {
		ps = con.prepareStatement(Insert);
		ps.setInt(1, t.getIdJornada());
		ps.setInt(2, t.getIdEmpleadoJornada());
		ps.setString(3, t.getFecha());
		ps.setString(4, t.getHora_entrada());
		ps.setString(5, t.getHora_salida());
		ps.executeUpdate();
		return false;
	}

	@Override
	public boolean borrar(Jornada t) throws SQLException {
		ps = con.prepareStatement(Delete);
		ps.setInt(1, t.getIdEmpleadoJornada());
		ps.executeUpdate();
		return false;
	}

	@Override
	public boolean actualizar(Jornada t) throws SQLException {
		ps = con.prepareStatement(Update);
		ps.setInt(1, t.getIdEmpleadoJornada());
		ps.setString(2, t.getFecha());
		ps.setString(3, t.getHora_entrada());
		ps.setString(4, t.getHora_salida());
		ps.setInt(5, t.getIdJornada());
		ps.executeUpdate();
		return false;
	}
	
	public boolean actualizar(String s, int id) throws SQLException {
		ps = con.prepareStatement(Update);
		ps.setString(1, s);
		ps.setInt(2, id);
		ps.executeUpdate();
		return false;
	}

	@Override
	public Jornada buscar(Object pk) throws SQLException {
		Jornada d1;
		ps = con.prepareStatement(Buscar);
		ps.setInt(1, (int)pk);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == true) {
			d1 = new Jornada(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
			return d1;
		} else {
			return null;
		}
	}
	
	public ArrayList buscarTodos(Object pk) throws SQLException {
		ArrayList<Jornada> jornadas = new ArrayList<>();
		ps = con.prepareStatement(Buscar);
		ps.setInt(1, (int)pk);
		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			Jornada d1 = new Jornada(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
			jornadas.add(d1);
		} 
		return jornadas;
	}
	
	public int buscarId() throws SQLException {
		int id;
		ps = con.prepareStatement(BuscarId);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == true) {
			id= rs.getInt(1);
			return id;
		} else {
			return -1;
		}
	}

	@Override
	public ArrayList listarTodos() throws SQLException {
		ArrayList<Jornada> jornadas = new ArrayList<>();
		ps = con.prepareStatement(Select);

		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			Jornada d1 = new Jornada(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5));
			jornadas.add(d1);
		}
		return (jornadas);
	}

}
