package com.friedcoke.utils;
import com.friedcoke.rmi.Auction;
import com.friedcoke.rmi.Category;
import com.google.gson.Gson;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.friedcoke.utils.CommonStrings.auction_item;
import static com.friedcoke.utils.CommonStrings.category_table;
import static com.friedcoke.utils.PostgresConnection.connect;

public class tableManipulation {
    public static int tableModification(String query) {
        Statement stmt = null;
        Connection conn = connect();
        System.out.println(query);
        try {
            stmt = conn.createStatement();
            int rows = stmt.executeUpdate(query);
            if (rows>0){
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("failed");
        return 0;
    }

    public static List<String> tableScan(String tableName, String query) throws RemoteException {
        List<String> res = new LinkedList<>();
        Statement stmt = null;
        Connection conn = connect();
        ResultSet rs = null;
        Gson gson = new Gson();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                if (tableName.equals(category_table)){
                    String id = rs.getString("category_id");
                    String description = rs.getString("category_description");
                    Category cat = new Category(id, description);
                    res.add(gson.toJson(cat));
                } else {
                    HashMap<String, String> record = new HashMap<>();
                    for(String key: auction_item){
                        record.put(key, rs.getString(key));
                    }
                    Auction auction = new Auction(record);
                    res.add(gson.toJson(auction));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
