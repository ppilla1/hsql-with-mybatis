package org.jpmc.turnstile.core.service;

import org.jpmc.turnstile.core.model.Turnstile;

import java.util.List;

public interface TurnstileService {
    String createTurnstile(Turnstile turnstile);
    void coin(Turnstile turnstile);
    void pass(Turnstile turnstile);
    List<Turnstile> fetchAll();
    Turnstile fetchById(Turnstile turnstile);
}
