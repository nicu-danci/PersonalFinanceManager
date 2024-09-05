package org.example;


import org.example.DatabaseManager;

public class FinanceManager {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.createNewTables();
    }
}
