package org.jpmc.turnstile.service;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.jpmc.turnstile.core.model.Turnstile;
import org.jpmc.turnstile.core.service.AbstractTurnstileFSM;
import org.jpmc.turnstile.core.service.SingleCoinTurnstileState;

import org.jpmc.turnstile.core.service.TurnstileState;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

@Slf4j
@Setter
@ToString
public class TestTurnstileFSM extends AbstractTurnstileFSM {
    private StringBuilder actionSeq = new StringBuilder("");

    private Turnstile turnstile;

    @Override
    public Turnstile getTurnstile() {
        return turnstile;
    }

    @Override
    protected TurnstileState getState() {
        return this.turnstile.getState();
    }

    @Override
    protected void setState(TurnstileState turnstileState) {
        this.turnstile.setState(turnstileState);
    }

    @Override
    protected void lock() {
        actionSeq.append("L");
    }

    @Override
    protected void unlock() {
        actionSeq.append("U");
    }

    @Override
    protected void alarm() {
        actionSeq.append("A");
    }

    @Override
    protected void thankYou() {
        actionSeq.append("T");
    }

    @Before
    public void setup(){
        this.turnstile = new Turnstile();
        this.turnstile.setState(SingleCoinTurnstileState.LOCKED);
    }

    @After
    public void teardown(){
        this.turnstile = null;
    }

    @Test
    public void testCoinEventOnTurnstileLockedState(){
        log.info("{}", this);
        this.coin();
        log.info("{}", this);
        assertEquals(actionSeq.toString(), "U");
    }

    @Test
    public void testPassEventOnTurnstileLockedState(){
        log.info("{}", this);
        this.pass();
        log.info("{}", this);
        assertEquals(actionSeq.toString(), "A");
    }

    @Test
    public void testCoinEventOnTurnstileUnlockedState(){
        log.info("{}", this);
        this.coin();
        log.info("{}", this);

        this.coin();
        log.info("{}", this);

        assertEquals(actionSeq.toString(), "UT");
    }

    @Test
    public void testPassEventOnTrunstileUnlockedState(){
        log.info("{}", this);
        this.coin();
        log.info("{}", this);

        this.pass();
        log.info("{}", this);

        assertEquals(actionSeq.toString(), "UL");
    }

}
