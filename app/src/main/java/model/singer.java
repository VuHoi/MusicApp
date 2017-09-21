package model;

/**
 * Created by Vu Khac Hoi on 9/21/2017.
 */

public class singer {
    String Name;
    String Avatar;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public singer(String name, String avatar) {

        Name = name;
        Avatar = avatar;
    }
}
