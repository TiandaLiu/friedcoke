package com.friedcoke.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.UUID;


public class auctionTest {
    static public void main(String args[])
    {
        AuctionRMIInterface rmiServer;
        Registry registry;
        String serverAddress="127.0.0.1";
        String serverPort="12346";
        System.out.println("sending "+" to "+serverAddress+":"+serverPort);
        try{
            // get the registry
            registry= LocateRegistry.getRegistry(
                    serverAddress,
                    (new Integer(serverPort)).intValue()
            );
            // look up the remote object in the RMI Registry
            rmiServer=
                    (AuctionRMIInterface)(registry.lookup("rmiServer"));
            // call the remote method
            UUID id = UUID.randomUUID();
            rmiServer.addCategory(id, "ze");
            id = UUID.randomUUID();
            rmiServer.addCategory(id, "haha");
            List<String> res = rmiServer.getAllCategory();
            rmiServer.deleteCategory(id);
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
    }
}
