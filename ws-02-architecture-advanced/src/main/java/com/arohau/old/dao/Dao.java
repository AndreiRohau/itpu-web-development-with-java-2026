package com.arohau.old.dao;

import java.util.HashMap;
import java.util.Map;

public class Dao {

    private static final Dao dao = new Dao();

    public static Dao getInstance() {
        return dao;
    }

    // database stub
    private Map<String, String> engToBy = new HashMap<>();
    {
        engToBy.put("hi", "pryvitanne");
        engToBy.put("bye", "dapabachennia");
    }

    public String getTranslation(String message) {
        return engToBy.get(message);
    }
}
