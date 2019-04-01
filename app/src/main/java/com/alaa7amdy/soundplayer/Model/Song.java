package com.alaa7amdy.soundplayer.Model;

public class Song {
    private  String userName;
    private  String songName;
    private  String imageUrl;
    private  String songUrl;

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public Song(String userName, String songName, String imageUrl, String songUrl) {
        this.userName = userName;
        this.songName = songName;
        this.imageUrl = imageUrl;
        this.songUrl = songUrl;
    }
    public Song(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
