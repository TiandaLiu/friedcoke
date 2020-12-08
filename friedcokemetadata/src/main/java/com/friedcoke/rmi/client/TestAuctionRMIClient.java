package com.friedcoke.rmi.client;

import com.friedcoke.rmi.AuctionRMI;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.UUID;

public class TestAuctionRMIClient
{
    static public void main(String args[])
    {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager (new RMISecurityManager() {
                public void checkConnect (String host, int port) {}
                public void checkConnect (String host, int port, Object context) {}
            });
        }
        AuctionRMI rmiServer;
        Registry registry;
        String serverAddress="192.168.1.105";
        String serverPort="12346";
        System.out.println("sending to "+serverAddress+":"+serverPort);
        try{
            // get the registry
            registry=LocateRegistry.getRegistry(
                    serverAddress,
                    (new Integer(serverPort)).intValue()
            );
            // look up the remote object in the RMI Registry
            rmiServer=
                    (AuctionRMI)(registry.lookup("rmiServer"));
            // call the remote method
            UUID uuid = UUID.randomUUID();
            String category = "clothing";
            int status = rmiServer.addCategory(uuid, category);
            System.out.println(uuid.toString());
            System.out.println(status);
        }
        catch(RemoteException e){
            e.printStackTrace();
        }
        catch(NotBoundException e){
            e.printStackTrace();
        }
    }
}

