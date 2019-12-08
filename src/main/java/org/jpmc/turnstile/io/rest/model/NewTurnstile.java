package org.jpmc.turnstile.io.rest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewTurnstile {
    private String tenantDescription;
    private Float locationLatitude;
    private Float locationLongitude;
}
