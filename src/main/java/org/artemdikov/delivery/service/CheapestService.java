package org.artemdikov.delivery.service;


public interface CheapestService<T> {
    T getCheapest(String from, String to) throws Exception;
}
