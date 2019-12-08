package org.jpmc.turnstile.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jpmc.turnstile.core.service.TurnstileState;

@Getter
@Setter
@ToString
public class Turnstile {
    private String id;
    private String tenantId;
    private String tenantDescription;
    private Float locationLatitude;
    private Float locationLongitude;
    private TurnstileState state;
}
