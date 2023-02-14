package ModeloBD_DTO;

public class puesto {
	int IdPuesto;
	String Puesto;
	
	public puesto(int idPuesto, String puesto) {
		IdPuesto = idPuesto;
		Puesto = puesto;
	}

	public int getIdPuesto() {
		return IdPuesto;
	}

	public void setIdPuesto(int idPuesto) {
		IdPuesto = idPuesto;
	}

	public String getPuesto() {
		return Puesto;
	}

	public void setPuesto(String puesto) {
		Puesto = puesto;
	}
	
	
	
}
