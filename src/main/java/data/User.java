package data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    //    private String username;
//    private String photo;
//    private Long chatId;
    private Long id;
    @Column(name = "name")
    private String name;
    private int age;
    private Long photo;
    private Boolean statusOfWaitingPhoto;
    private Boolean statusOfWaitingName;

    public User() {

    }

    public User(boolean statusOfWaitingName, boolean statusOfWaitingPhoto) {
//        this.username = username;
//        this.chatId = chatId;
//        this.photo = photo;
        this.statusOfWaitingName = statusOfWaitingName;
        this.statusOfWaitingPhoto = statusOfWaitingPhoto;
    }

    public boolean getStatusOfPhoto() {
        return statusOfWaitingPhoto;
    }

    public boolean getStatusOfName() {
        return statusOfWaitingName;
    }

    public void setStatusOfPhoto(Boolean newStatusOfPhoto) {
        statusOfWaitingPhoto = newStatusOfPhoto;
    }

    public void setStatusOfName(Boolean newStatusOfName) {
        statusOfWaitingName = newStatusOfName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
