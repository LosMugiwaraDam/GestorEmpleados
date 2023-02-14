package ModeloBD_Vista;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClimaInterface extends Remote{
	
	String getClima() throws RemoteException;
}
