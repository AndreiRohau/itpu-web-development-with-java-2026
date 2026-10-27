package com.arohau.optional.musicservice;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

public class MusicService {
    private List<Song> songs = new ArrayList<>();

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @PostConstruct
    public void init() {
        songs.add(new Song("title-1", "artist-1", "2021"));
        songs.add(new Song("title-2", "artist-2", "2022"));
        songs.add(new Song("title-3", "artist-3", "2023"));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("MusicService is shutting down.");
    }
}
