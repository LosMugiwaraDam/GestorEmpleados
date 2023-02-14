package ModeloBD_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConexionBD.ConexionSGL;
import ModeloBD_DTO.Jornada;
import ModeloBD_DTO.puesto;

public class TablaPuestos implements Patron_DAO<puesto>{
	ConexionSGL conexion = new ConexionSGL();
	Connection con = conexion.getCon();
	String Delete = "DELETE FROM `puestos` WHERE `IdPuesto` = ?";
	String Buscar = "SELECT * FROM `puestos` WHERE `IdPuesto` = ?";
	String Select = "SELECT * FROM `puestos`";
	String Update = "UPDATE `puestos` SET `Puesto`=? WHERE `IdPuesto`=?";
	String Insert = "INSERT INTO `puestos`(`IdPuesto`, `Puesto`) VALUES (?,?)";
	PreparedStatement ps;
	
	@Override
	public boolean insertar(puesto t) throws SQLException {
		ps = con.prepareStatement(Insert);
		ps.setInt(1, t.getIdPuesto());
		ps.setString(2, t.getPuesto());
		ps.executeUpdate();
		return false;
	}

	@Override
	public boolean borrar(puesto t) throws SQLException {
		ps = con.prepareStatement(Delete);
		ps.setInt(1, t.getIdPuesto());
		ps.executeUpdate();
		return false;
	}

	@Override
	public boolean actualizar(puesto t) throws SQLException {
		ps = con.prepareStatement(Update);
		ps.setString(1, t.getPuesto());
		ps.setInt(2, t.getIdPuesto());
		ps.executeUpdate();
		return false;
	}

	@Override
	public puesto buscar(Object pk) throws SQLException {
		puesto d1;
		ps = con.prepareStatement(Buscar);
		ps.setInt(1, (int)pk);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == true) {
			d1 = new puesto(rs.getInt(1), rs.getString(2));
			return d1;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList listarTodos() throws SQLException {
		ArrayList<puesto> puestos = new ArrayList<>();
		ps = con.prepareStatement(Select);

		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			puesto d1 = new puesto(rs.getInt(1), rs.getString(2));
			puestos.add(d1);
		}
		return (puestos);
	}

}
