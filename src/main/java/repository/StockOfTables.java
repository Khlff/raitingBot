package repository;

import java.sql.*;

public class StockOfTables {
    // Блок объявления констант
    private static String DATABASE_USER;
    private static String DATABASE_PASSWORD;
    private static String DATABASE_URL;

    // Таблицы СУБД
    public Users users;

    // Получить новое соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(StockOfTables.DATABASE_URL, StockOfTables.DATABASE_USER, StockOfTables.DATABASE_PASSWORD);
    }

    // Инициализация
    public StockOfTables(String DATABASE_USER, String DATABASE_PASSWORD, String DATABASE_URL) throws SQLException {
        StockOfTables.DATABASE_USER = DATABASE_USER;
        StockOfTables.DATABASE_PASSWORD = DATABASE_PASSWORD;
        StockOfTables.DATABASE_URL = DATABASE_URL;
        // Инициализируем таблицы
        users = new Users();
    }
}
