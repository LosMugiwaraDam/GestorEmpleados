package ModeloBD_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConexionBD.ConexionSGL;
import ModeloBD_DTO.Empleado;

public class TablaEmpleados implements Patron_DAO<Empleado> {
	ConexionSGL conexion = new ConexionSGL();
	Connection con = conexion.getCon();
	String Delete = "DELETE FROM `empleados` WHERE `IdEmpleado` = ?";
	String Buscar = "SELECT * FROM `empleados` WHERE `IdEmpleado` = ?";
	String Select = "SELECT * FROM `empleados`";
	String SelectSueldoDesc = "SELECT * FROM `empleados` ORDER BY `Sueldo` desc";
	String SelectSueldoAsc = "SELECT * FROM `empleados` ORDER BY `Sueldo`";
	String SelectNombre = "SELECT * FROM `empleados` ORDER BY `Nombre`";
	String Update = "UPDATE `empleados` SET `Nombre`=?,`Contraseña`=? ,`Sueldo`=? ,`Admin`=? ,`IdPuesto`=?  WHERE `IdEmpleado`=?";
	String Insert = "INSERT INTO `empleados`(`IdEmpleado`, `Nombre`, `Contraseña`, `Sueldo`, `Admin`, `IdPuesto`) VALUES (?,?,?,?,?,?)";
	PreparedStatement ps;

	@Override
	public boolean insertar(Empleado t) throws SQLException {
		ps = con.prepareStatement(Insert);
		ps.setInt(1, t.getId());
		ps.setString(2, t.getNombre());
		ps.setString(3, t.getContraseña());
		ps.setFloat(4, t.getSueldo());
		ps.setInt(5, t.getAdmin());
		ps.setInt(6, t.getPuesto());
		ps.executeUpdate();
		return false;
	}

	@Override
	public boolean borrar(Empleado t) throws SQLException {
		ps = con.prepareStatement(Delete);
		ps.setInt(1, t.getId());
		ps.executeUpdate();
		return false;
	}

	@Override
	public boolean actualizar(Empleado t) throws SQLException {
		ps = con.prepareStatement(Update);
		ps.setString(1, t.getNombre());
		ps.setString(2, t.getContraseña());
		ps.setFloat(3, t.getSueldo());
		ps.setInt(4, t.getPuesto());
		ps.setInt(5, t.getAdmin());
		ps.setInt(6, t.getId());
		ps.executeUpdate();
		return false;
	}

	@Override
	public Empleado buscar(Object pk) throws SQLException {
		Empleado d1;
		ps = con.prepareStatement(Buscar);
		ps.setInt(1, (int)pk);
		ResultSet rs = ps.executeQuery();
		if (rs.next() == true) {
			d1 = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6));
			return d1;
		} else {
			return null;
		}
	}

	@Override
	public ArrayList listarTodos() throws SQLException {
		ArrayList<Empleado> empleados = new ArrayList<>();
		ps = con.prepareStatement(Select);

		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			Empleado d1 = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6));
			empleados.add(d1);
		}
		return (empleados);
	}
	
	public ArrayList ordenarSueldoDesc() throws SQLException {
		ArrayList<Empleado> empleados = new ArrayList<>();
		ps = con.prepareStatement(SelectSueldoDesc);

		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			Empleado d1 = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6));
			empleados.add(d1);
		}
		return (empleados);
	}
	
	public ArrayList ordenarSueldoAsc() throws SQLException {
		ArrayList<Empleado> empleados = new ArrayList<>();
		ps = con.prepareStatement(SelectSueldoAsc);

		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			Empleado d1 = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6));
			empleados.add(d1);
		}
		return (empleados);
	}
	
	public ArrayList ordenarNombre() throws SQLException {
		ArrayList<Empleado> empleados = new ArrayList<>();
		ps = con.prepareStatement(SelectNombre);

		ResultSet rs = ps.executeQuery();
		while (rs.next() == true) {
			Empleado d1 = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5), rs.getInt(6));
			empleados.add(d1);
		}
		return (empleados);
	}

}
