package org.jpmc.turnstile.core.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.jpmc.turnstile.core.model.Turnstile;

@Slf4j
@Getter
@Setter
public class TurnstileFSM extends AbstractTurnstileFSM{
    private Turnstile turnstile;

    public TurnstileFSM(Turnstile turnstile) {
        this.turnstile = turnstile;
    }

    @Override
    protected TurnstileState getState() {
        return turnstile.getState();
    }

    @Override
    protected void setState(TurnstileState turnstileState) {
        turnstile.setState(turnstileState);

    }

    @Override
    protected void lock(){
        log.info("Turnstile [{}] is LOCKED now.", turnstile);
    }

    @Override
    protected void unlock(){
        log.info("Turnstile [{}] is UNLOCKED now.", turnstile);
    }

    @Override
    protected void thankYou(){
        log.info("Turnstile [{}] THANKING YOU for donation.", turnstile);
    }

    @Override
    protected void alarm(){
        log.info("Turnstile [{}] generating ALARM.", turnstile);
    }

}
