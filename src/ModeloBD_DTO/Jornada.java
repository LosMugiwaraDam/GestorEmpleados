package ModeloBD_DTO;

public class Jornada {
	int IdEmpleadoJornada, IdJornada;
	String Fecha, hora_entrada, hora_salida;
	
	public Jornada(int idJornada, int idEmpleadoJornada, String fecha, String hora_entrada, String hora_salida) {
		IdEmpleadoJornada = idEmpleadoJornada;
		IdJornada = idJornada;
		Fecha = fecha;
		this.hora_entrada = hora_entrada;
		this.hora_salida = hora_salida;
	}
	public int getIdEmpleadoJornada() {
		return IdEmpleadoJornada;
	}
	public void setIdEmpleadoJornada(int idEmpleadoJornada) {
		IdEmpleadoJornada = idEmpleadoJornada;
	}
	public int getIdJornada() {
		return IdJornada;
	}
	public void setIdJornada(int idJornada) {
		IdJornada = idJornada;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getHora_entrada() {
		return hora_entrada;
	}
	public void setHora_entrada(String hora_entrada) {
		this.hora_entrada = hora_entrada;
	}
	public String getHora_salida() {
		return hora_salida;
	}
	public void setHora_salida(String hora_salida) {
		this.hora_salida = hora_salida;
	}

	

}
