package org.jpmc.turnstile.core.service;

import lombok.extern.slf4j.Slf4j;
import org.jpmc.turnstile.core.model.Turnstile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
class SingleCoinTurnstileService implements TurnstileService{

    private final TurnstileStore turnstileStore;

    SingleCoinTurnstileService(TurnstileStore turnstileStore) {
        this.turnstileStore = turnstileStore;
    }

    @Override
    public String createTurnstile(Turnstile turnstile) {
        UUID id = UUID.randomUUID();
        turnstile.setId(id.toString());
        turnstile.setState(SingleCoinTurnstileState.LOCKED);
        turnstileStore.save(turnstile);
        return id.toString();
    }

    @Override
    public void coin(Turnstile turnstileFilter) {
        Turnstile turnstile = turnstileStore.fetchById(turnstileFilter);
        AbstractTurnstileFSM turnstileFSM = new TurnstileFSM(turnstile);
        turnstileFSM.coin();
        turnstile = turnstileFSM.getTurnstile();
        turnstileStore.save(turnstile);
    }

    @Override
    public void pass(Turnstile turnstileFilter) {
        Turnstile turnstile = turnstileStore.fetchById(turnstileFilter);
        AbstractTurnstileFSM turnstileFSM = new TurnstileFSM(turnstile);
        turnstileFSM.pass();
        turnstile = turnstileFSM.getTurnstile();
        turnstileStore.save(turnstile);
    }

    @Override
    public List<Turnstile> fetchAll() {
        return turnstileStore.fetchAll();
    }

    @Override
    public Turnstile fetchById(Turnstile turnstile) {
        Turnstile latestTurnstile = turnstileStore.fetchById(turnstile);
        return turnstileStore.fetchById(latestTurnstile);
    }
}
