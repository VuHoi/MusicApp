package model;

import java.io.Serializable;

/**
 * Created by Vu Khac Hoi on 9/20/2017.
 */

public class song implements Serializable{
    String Name;

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    String Duration;
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public song(String name, String duration, String path, String artist) {
        Name = name;
        Duration = duration;
        Path = path;
        Artist = artist;
    }

    public String getPath() {

        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }

    String Path;

    String Artist;


}
