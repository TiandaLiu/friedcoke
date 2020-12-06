package com.friedcoke.rmi;

import com.google.gson.Gson;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.net.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class UserRMIServer extends java.rmi.server.UnicastRemoteObject
implements UserRemoteInterface
{
    int      port;
    String   address;
    Registry registry;    // rmi registry for lookup the remote objects.
    Connection conn = null;

    public int addUser(String userStr) throws RemoteException {
        User user = User.fromJson(userStr);
        String username = user.getUsername();
        String password = user.getPassword();
        String status = user.getStatus();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("add user " + username);
            stmt = conn.createStatement();
            String sql = "INSERT INTO users (username, password, status) "
            + "VALUES ('" + username  + "','" + password + "','" + status + "');";
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1;
    }

    public int deleteUser(String username) throws RemoteException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("delete user " + username);
            stmt = conn.createStatement();
            String sql = "DELETE FROM users where username = '" + username + "';";
	        return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1;
    }

    public int updateUser(String userId, String newUserStr) throws RemoteException {
        User user = User.fromJson(newUserStr);
        String username = user.getUsername();
        String password = user.getPassword();
        String status = user.getStatus();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("update user " + username);
            stmt = conn.createStatement();
            String sql = "UPDATE users set password = '" + password
            + "', status = '" + status  + "' WHERE username = '" + username + "';";
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1;
    }
     

    public String getUserById(String username) throws RemoteException {
        User user = new User(username, "", "");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("get user " + username);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users WHERE username = '" + username + "';");
            rs.next();
            String password = rs.getString("password");
            String status = rs.getString("status");
            return new User(username, password, status).toJson();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return new String();
    } 

    public int addAuctionToWatchlist(String username, UUID auctionId) throws RemoteException {
        String auction = auctionId.toString();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("add user " + username);
            stmt = conn.createStatement();
            String sql = "INSERT INTO watchlists (username, auction) "
            + "VALUES ('" + username  + "','" + auction + "');";
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }        
        return 0;
    }

    public int removeAuctionFromWatchlist(String username, UUID auctionId) throws RemoteException {
        String auction = auctionId.toString();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("delete user " + username);
            stmt = conn.createStatement();
            String sql = "DELETE FROM watchlists where username = '" + username + "' AND auction = '" + auction + "';";
	        return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1;        
    }

    public List<UUID> getWatchlist(String username) throws RemoteException {
        Statement stmt = null;
        ResultSet rs = null;
        List<UUID> list = new ArrayList<UUID>();
        try {
            System.out.println("get user " + username);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM carts WHERE username = '" + username + "';");
            while (rs.next()) {
                String uuid = rs.getString("auction");
                list.add(UUID.fromString(uuid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return list;        
    }

    public int addAuctionToCart(String username, UUID auctionId) throws RemoteException {
        String auction = auctionId.toString();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("add auction " + auction + " from user " + username);
            stmt = conn.createStatement();
            String sql = "INSERT INTO carts (username, auction) "
            + "VALUES ('" + username  + "','" + auction + "');";
            return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }        
        return 0;        
    }

    public int removeAuctionFromCart(String username, UUID auctionId) throws RemoteException {
        String auction = auctionId.toString();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println("delete auction " + auction + " from user " + username);
            stmt = conn.createStatement();
            String sql = "DELETE FROM carts where username = '" + username + "' AND auction = '" + auction + "';";
	        return stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        return -1;           
    }

    public List<UUID> getCart(String username) throws RemoteException {
        Statement stmt = null;
        ResultSet rs = null;
        List<UUID> list = new ArrayList<UUID>();
        try {
            System.out.println("get cart of user " + username);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM carts WHERE username = '" + username + "';");
            while (rs.next()) {
                String uuid = rs.getString("auction");
                list.add(UUID.fromString(uuid));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
        System.out.println(list.size());
        return list;         
    }


    public UserRMIServer() throws RemoteException
    {
        try{
            // get the address of this host.
            address= (InetAddress.getLocalHost()).toString();
        }
        catch(Exception e){
            throw new RemoteException("can't get inet address.");
        }
	    port=12344;  // our port
        System.out.println("using address="+address+",port="+port);
        try{
            // create the registry and bind the name and object.
            registry = LocateRegistry.createRegistry( port );
            registry.rebind("rmiServer", this);
            Class.forName("org.postgresql.Driver");
            String connectionUrl = "jdbc:postgresql://172.17.0.3:5432/userinfo";
            String connectionUser = "postgres";
            String connectionPassword = "mysecret";
            System.out.println("calling getConnection");
            conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
        }
        catch(Exception e){
            e.printStackTrace();
        }       
    }
   
    static public void main(String args[])
    {
        try{
            UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
            UserRMIServer s=new UserRMIServer();
            s.addUser((new User("a", "b", "c")).toJson());
            System.out.println(s.getUserById("b"));
            s.addAuctionToCart("b", uid);
            s.getCart("b");
            s.removeAuctionFromCart("b", uid);
            s.getCart("b");
            s.deleteUser("b");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
