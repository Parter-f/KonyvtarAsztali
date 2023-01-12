package com.petyoprogramoz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Statisztika {

    public List<Konyv> konyvek = new ArrayList();


    public Boolean readInBooks(){
        try{
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/konyvek", "root" , ""

            );
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            while (rs.next()){
                Konyv konyv = new Konyv(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("publish_year"),
                        rs.getInt("page_count")
                );
                konyvek.add(konyv);
            }
        }catch(SQLException e){
            System.out.println("Error in reading process");
            return false;
        }
        System.out.println("yo mr white");
        return true;
    }

}
