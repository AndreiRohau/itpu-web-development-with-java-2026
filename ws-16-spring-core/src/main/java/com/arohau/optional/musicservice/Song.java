package com.arohau.optional.musicservice;

import java.util.Objects;

public class Song {
    private String title;
    private String artist;
    private String year;

    public Song() {
    }

    public Song(String title, String artist, String year) {
        this.title = title;
        this.artist = artist;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return Objects.equals(title, song.title) && Objects.equals(artist, song.artist) && Objects.equals(year, song.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist, year);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
