package softwareplant.reportservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softwareplant.reportservice.model.swapi.Planet;
import softwareplant.reportservice.service.PlanetService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PlanetServiceTests {

    @Autowired
    private PlanetService planetService;

    @Test
    void planetServiceTest() {
        Optional<Planet> planet = planetService.getPlanetById("3");
        assertTrue(planet.isPresent());
        assertEquals(new Planet("3", "Yavin IV"), planet.get());
    }
}
