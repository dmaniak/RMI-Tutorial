package client;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import compute.Compute;

public class Main {
	public static void main(String args[]) {
        if (System.getSecurityManager() == null) System.setSecurityManager(new SecurityManager());
        
        try {
        	String name = "Compute";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            Compute comp = (Compute) registry.lookup(name);
            
            runPiTask(comp, args[1]);
            runFiboTask(comp, args[1]);
        } catch (Exception ex) {
        	System.err.println("ComputePi exception:");
            ex.printStackTrace();
        }
    }
	
	private static void runPiTask(Compute comp, String digits) throws RemoteException {
		Pi piTask = new Pi(Integer.parseInt(digits));
        BigDecimal pi = comp.executeTask(piTask);
        System.out.println("PI with " + digits + " digits : " + pi);
	}
	
	private static void runFiboTask(Compute comp, String digits) throws RemoteException {
		Fibonacci fiTask = new Fibonacci(Integer.parseInt(digits));
        BigDecimal fibo = comp.executeTask(fiTask);
        System.out.println("Fibonacci of " + digits + ": " + fibo.toBigInteger());
	}
}
