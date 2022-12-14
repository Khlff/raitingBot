package repository;

import java.sql.SQLException;

// Операции, связанные с таблицей user'ов
public interface UsersOperation {
    void createNewUser(Long user_id) throws SQLException;
    void updateUserInformation(Long user_id, String username, String photo_id) throws SQLException;

    boolean getStatusOfWaitingUpdate(Long user_id) throws SQLException;
    void setStatusOfWaitingUpdate(Long user_id, boolean newStatus) throws SQLException;

    boolean getStatusOfWaitingRate(Long user_id) throws SQLException;
    void setStatusOfWaitingRate(Long user_id, boolean newStatus) throws SQLException;
    boolean getStatusOfRating(Long user_id) throws SQLException;
    void setStatusOfRating(Long user_id, boolean newStatus) throws SQLException;

    Long getRandomUserId(Long user_id) throws SQLException;
    String getUsernameByUserId(Long user_id) throws SQLException;
    String getPhotoIdByUserId(Long user_id) throws SQLException;

    boolean isUserExists(Long user_id) throws SQLException;
}
