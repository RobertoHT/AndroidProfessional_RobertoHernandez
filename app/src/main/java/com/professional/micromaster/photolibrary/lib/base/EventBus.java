package com.professional.micromaster.photolibrary.lib.base;

/**
 * Created by Roberto on 03/07/17.
 */

public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
