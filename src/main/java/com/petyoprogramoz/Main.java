package com.petyoprogramoz;

public class Main {

    public static void main(String[] args) {
        Statisztika stat = new Statisztika();
        stat.readInBooks();
        System.out.println(stat.konyvek.get(0).getAuthor());
    }
}
