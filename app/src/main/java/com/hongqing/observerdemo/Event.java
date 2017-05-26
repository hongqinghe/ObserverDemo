package com.hongqing.observerdemo;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by gongtong on 17-5-26.
 */

public class Event {
    static final String keyString = "aaaa";
    private String key;

    public Event(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }


}
