package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    Connection getConnection() throws SQLException;
    void getFromDb();

    ResultSet getUsersTable();

    void deleteFromDb();

    void updateToDb(Long user_id, String username,String photo_id) throws SQLException;

}
