package ModeloBD_DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Patron_DAO<TipoGen> {
	public boolean insertar(TipoGen t) throws SQLException;

	public boolean borrar(TipoGen t) throws SQLException;

	public boolean actualizar(TipoGen t) throws SQLException; 

	public TipoGen buscar(Object pk) throws SQLException; // Busca en base a la PrimaryKey

	public ArrayList listarTodos() throws SQLException;// Devuelve la lista de todos los registros de la tabla
}