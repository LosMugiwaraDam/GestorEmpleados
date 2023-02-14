package ModeloBD_DTO;

public class Empleado {
	String nombre;
	float sueldo;
	int id, admin, puesto;
	String contraseña;

	public Empleado(int id, String nombre, String contraseña, float sueldo, int admin, int puesto) {
		this.nombre = nombre;
		this.id = id;
		this.sueldo = sueldo;
		this.puesto = puesto;
		this.contraseña = contraseña;
		this.admin = admin;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getSueldo() {
		return sueldo;
	}

	public void setSueldo(float sueldo) {
		this.sueldo = sueldo;
	}

	public int getPuesto() {
		return puesto;
	}

	public void setPuesto(int puesto) {
		this.puesto = puesto;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String jornada) {
		this.contraseña = jornada;
	}

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

}
