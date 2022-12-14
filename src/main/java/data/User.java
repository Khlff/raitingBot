package data;

public class User {
    private Boolean statusOfWaitingUpdate;
    private Boolean statusOfWaitingRate;

    public User(boolean statusOfWaitingUpdate,boolean statusOfWaitingRate) {
        this.statusOfWaitingUpdate = statusOfWaitingUpdate;
        this.statusOfWaitingRate = statusOfWaitingRate;
    }

    public boolean getStatusOfUpdate() {
        return statusOfWaitingUpdate;
    }

    public boolean getStatusOfRate() {return statusOfWaitingRate;}

    public void setStatusOfWaitingUpdate(Boolean newStatusOfWaitingUpdate) {
        statusOfWaitingUpdate = newStatusOfWaitingUpdate;
    }

    public void setStatusOfWaitingRate(Boolean newStatusOfWaitingRate) {
        statusOfWaitingRate = newStatusOfWaitingRate;
    }
}
