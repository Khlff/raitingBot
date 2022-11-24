package data;

import java.util.HashMap;

// todo Repository
public class UsersInformation  {

    private static HashMap<Long, User> data;

    public UsersInformation() {
        data = new HashMap<>();
    }

    public static boolean hasWaitingUpdate(Long chatId) {
        if (!data.containsKey(chatId))
            return false;
        return data.get(chatId).getStatusOfUpdate();
    }
    public static boolean hasWaitingRate(Long chatId) {
        if (!data.containsKey(chatId))
            return false;
        return data.get(chatId).getStatusOfRate();
    }

    public static void updateStatusOfPhotoAndName(Long chatId, boolean newStatus) {
        data.get(chatId).setStatusOfWaitingUpdate(newStatus);
    }
    public static void updateStatusOfRate(Long chatId, boolean newStatus) {
        data.get(chatId).setStatusOfWaitingRate(newStatus);
    }


    public void update(Long chatId, boolean newStatusOfWaitingUpdate, boolean newStatusOfWaitingRate) {
        if (!data.containsKey(chatId))
            data.put(chatId, new User(newStatusOfWaitingUpdate, newStatusOfWaitingRate));
        else {
            data.get(chatId).setStatusOfWaitingUpdate(newStatusOfWaitingUpdate);
            data.get(chatId).setStatusOfWaitingRate(newStatusOfWaitingRate);
        }
    }

}

