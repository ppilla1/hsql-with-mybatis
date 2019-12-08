package org.jpmc.turnstile.core.service;

import org.jpmc.turnstile.core.model.Turnstile;

public abstract class AbstractTurnstileFSM {
    public void coin(){
        getState().coin(this);
    }
    public void pass(){
        getState().pass(this);
    }

    public abstract Turnstile getTurnstile();

    protected abstract TurnstileState getState();
    protected abstract void setState(TurnstileState turnstileState);
    protected abstract void lock();
    protected abstract void unlock();
    protected abstract void alarm();
    protected abstract void thankYou();
}
