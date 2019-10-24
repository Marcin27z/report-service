package softwareplant.reportservice.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import softwareplant.reportservice.HttpsRestTemplate;
import softwareplant.reportservice.model.swapi.Planet;

import java.util.Optional;

@Component
public class PlanetService {

    @Cacheable("planets")
    public Optional<Planet> getPlanetById(String planet_id) {
        final String uri = "https://swapi.co/api/planets/" + planet_id + "/";
        HttpsRestTemplate restTemplate = new HttpsRestTemplate();
        Optional<Planet> planet;
        try {
         planet = Optional.ofNullable(restTemplate.getForObject(uri, Planet.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
        planet.ifPresent(p -> p.setPlanet_id(planet_id));
        return planet;
    }
}
