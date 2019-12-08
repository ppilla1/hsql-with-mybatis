package org.jpmc.turnstile.core.service;

import org.jpmc.turnstile.core.model.Turnstile;

import java.util.List;

public interface TurnstileStore {
    void save(Turnstile turnstile);
    Turnstile fetchById(Turnstile turnstile);
    List<Turnstile> fetchAll();
}
