package org.jpmc.turnstile.core.service;

public enum SingleCoinTurnstileState implements TurnstileState {
    LOCKED{
        @Override
        public void coin(AbstractTurnstileFSM turnstileFsm) {
            turnstileFsm.unlock();
            turnstileFsm.setState(UNLOCKED);
        }

        @Override
        public void pass(AbstractTurnstileFSM turnstileFsm) {
            turnstileFsm.alarm();
            turnstileFsm.setState(LOCKED);
        }
    },
    UNLOCKED{
        @Override
        public void coin(AbstractTurnstileFSM turnstileFsm) {
            turnstileFsm.thankYou();
            turnstileFsm.setState(UNLOCKED);
        }

        @Override
        public void pass(AbstractTurnstileFSM turnstileFsm) {
            turnstileFsm.lock();
            turnstileFsm.setState(LOCKED);
        }
    };

}
