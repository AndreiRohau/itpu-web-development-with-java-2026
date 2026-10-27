package com.arohau.optional.musicservice;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
/*
Music Service (Optional)

Description:
1. Create a MusicService class with a List attribute songs.
    The class should have an appropriate getter method for its attribute.

2. Create a Song class with the following attributes: title, artist and year.
    The class should have appropriate getter and setter methods for its attributes.

3. Add an init method with a @PostConstruct annotation to the MusicService class that adds 3 songs to the service list.

4. Add a destroy method with a @PreDestroy annotation to the MusicService class that
    writes a message to the console that MusicService is shutting down.

5. Use a Spring configuration class MusicServiceConfig to define and manage the MusicService bean.
 */
public class MusicServiceMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MusicServiceConfig.class);

        System.out.println("---------------------");
        System.out.println("assert that MusicService bean has a list with 3 songs");
        int size = ctx.getBean(MusicService.class).getSongs().size();
        System.out.println("size " + size);
        assert 3 == size;
        System.out.println("---------------------");

        ctx.registerShutdownHook();
        ctx.close();
    }
}
