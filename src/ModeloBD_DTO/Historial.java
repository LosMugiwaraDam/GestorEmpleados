package ModeloBD_DTO;

import java.io.Serializable;
/**
 * La clase Historial es una clase para leer el archivo que almacena la actividad de el chat
 */

public class Historial implements Serializable{
	String usuario, hora,horaS;

	/**
	 * Constructor con los tres parametrso usuario hora de inicio de sesion y la hora de cierre de sesion
	 */
	public Historial(String usuario, String hora,String horaS) {
		this.usuario = usuario;
		this.hora = hora;
		this.horaS=horaS;
	}
/**
 * Getters para poder llenar la tabla en la aplicacion
 */
	public String getUsuario() {
		return usuario;
	}

	public String getHoraS() {
		return horaS;
	}

	public String getHora() {
		return hora;
	}
}
