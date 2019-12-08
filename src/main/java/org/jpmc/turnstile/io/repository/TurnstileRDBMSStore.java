package org.jpmc.turnstile.io.repository;

import lombok.extern.slf4j.Slf4j;
import org.jpmc.turnstile.core.model.Turnstile;
import org.jpmc.turnstile.core.service.TurnstileStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TurnstileRDBMSStore implements TurnstileStore {

    private final TurnstileRepository turnstileRepository;

    public TurnstileRDBMSStore(TurnstileRepository turnstileRepository) {
        this.turnstileRepository = turnstileRepository;
    }

    @Override
    public void save(Turnstile turnstile) {
        Turnstile latestTurnstile = turnstileRepository.readById(turnstile);

        if (
                null !=  latestTurnstile
                && latestTurnstile.getId().equals(turnstile.getId())
                && latestTurnstile.getTenantId().equals(turnstile.getTenantId())){
            turnstileRepository.update(turnstile);
        }else{
            turnstileRepository.create(turnstile);
        }

    }

    @Override
    public Turnstile fetchById(Turnstile turnstile) {
        Turnstile latestTurnstile = turnstileRepository.readById(turnstile);
        return latestTurnstile;
    }

    @Override
    public List<Turnstile> fetchAll() {
        return turnstileRepository.readAll();
    }
}
