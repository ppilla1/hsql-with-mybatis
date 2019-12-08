package org.jpmc.turnstile.core.service;

public interface TurnstileState {
    abstract void coin (AbstractTurnstileFSM turnstileFsm);
    abstract void pass (AbstractTurnstileFSM turnstileFsm);
}