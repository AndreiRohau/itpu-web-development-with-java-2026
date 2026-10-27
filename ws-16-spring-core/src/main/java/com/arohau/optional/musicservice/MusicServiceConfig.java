package com.arohau.optional.musicservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MusicServiceConfig {
    @Bean
    public MusicService musicService() {
        return new MusicService();
    }
}
