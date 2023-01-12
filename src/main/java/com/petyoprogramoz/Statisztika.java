package com.petyoprogramoz;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

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

    public void otszaznalhosszabb(){

        int otszaznalnagyobb = 0;

        for (int i = 0; i < konyvek.size(); i++) {
            if (konyvek.get(i).getPage_count() > 500){
                otszaznalnagyobb++;
            }
        }
        System.out.println("Ötszáznál oldalnál hosszabb könyvek száma : " + otszaznalnagyobb);
    }
    public boolean regikonyv(){
        for (int i = 0; i < konyvek.size(); i++) {
            if (konyvek.get(i).getPublish_year() < 1950){
                System.out.println("Van 1950-nél régebbi könyv");
                return true;
            }

        }
        return false;
    }
    
    public void leghosszabbkonyvadatai(){

        int leghosszabbid = 0;

        for (int i = 0; i < konyvek.size(); i++) {
            if (konyvek.get(i).getPage_count() > konyvek.get(leghosszabbid).getPage_count()) {
                leghosszabbid = i;
            }
        }
        System.out.println("Szerző : " + konyvek.get(leghosszabbid).getAuthor());
        System.out.println("Cím : " + konyvek.get(leghosszabbid).getTitle());
        System.out.println("Kiadás éve : " + konyvek.get(leghosszabbid).getPublish_year());
        System.out.println("Oldalak Száma : " + konyvek.get(leghosszabbid).getPage_count());
    }

    public void legtobbkonyvelrendelkezo(){

        List<String> irokmegjelenesszam = new ArrayList();
        for (int i = 0; i < konyvek.size(); i++) {
            irokmegjelenesszam.add(konyvek.get(i).getAuthor());
        }
        String legtobbkonyvesiro = irokmegjelenesszam.stream().collect(Collectors.groupingBy(w -> w , Collectors.counting())).entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();

        System.out.println("A legtöbb könyvvel rendelkező szerző : " + legtobbkonyvesiro);

    }

}
