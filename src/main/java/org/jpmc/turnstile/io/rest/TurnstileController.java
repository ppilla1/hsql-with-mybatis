package org.jpmc.turnstile.io.rest;

import lombok.extern.slf4j.Slf4j;
import org.jpmc.turnstile.core.model.Turnstile;
import org.jpmc.turnstile.core.service.TurnstileService;
import org.jpmc.turnstile.io.rest.model.NewTurnstile;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1_0/turnstile")
public class TurnstileController {

    private final TurnstileService turnstileService;

    public TurnstileController(TurnstileService turnstileService) {
        this.turnstileService = turnstileService;
    }

    @PostMapping({"/create"})
    String createTurnstile(@RequestHeader("tenantId") String tenantId, @RequestBody NewTurnstile newTurnstile){
        Turnstile turnstile = new Turnstile();
        turnstile.setTenantId(tenantId);
        turnstile.setTenantDescription(newTurnstile.getTenantDescription());
        turnstile.setLocationLatitude(newTurnstile.getLocationLatitude());
        turnstile.setLocationLongitude(newTurnstile.getLocationLongitude());

        return turnstileService.createTurnstile(turnstile);
    }

    @GetMapping({"/fetch-by-id/{id}"})
    Turnstile fetchById(@RequestHeader("tenantId") String tenantId, @PathVariable("id") String id){
        Turnstile turnstile = new Turnstile();
        turnstile.setId(id);
        turnstile.setTenantId(tenantId);
        return turnstileService.fetchById(turnstile);
    }

    @GetMapping({"/all"})
    List<Turnstile> fetchAll(){
        return turnstileService.fetchAll();
    }

    @GetMapping({"/{id}/put-coin"})
    void coin(@RequestHeader("tenantId") String tenantId, @PathVariable("id") String id){
        Turnstile turnstile = new Turnstile();
        turnstile.setId(id);
        turnstile.setTenantId(tenantId);
        turnstileService.coin(turnstile);
    }

    @GetMapping({"/{id}/passthrough"})
    void pass(@RequestHeader("tenantId") String tenantId, @PathVariable("id")String id){
        Turnstile turnstile = new Turnstile();
        turnstile.setId(id);
        turnstile.setTenantId(tenantId);
        turnstileService.pass(turnstile);
    }
}
