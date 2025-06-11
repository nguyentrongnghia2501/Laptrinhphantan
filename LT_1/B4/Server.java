
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Hello {

    protected Server() throws RemoteException {
        super();
    }

    public String sayHello() throws RemoteException {
        return " ok con de";
    }

    public static void main(String[] args) {
        try {
            Server obj = new Server();
            Registry registry = LocateRegistry.createRegistry(1999);
            registry.bind("Hello", obj);
            System.out.println("Server is ready ok");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
