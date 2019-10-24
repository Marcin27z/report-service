package softwareplant.reportservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softwareplant.reportservice.model.swapi.Film;
import softwareplant.reportservice.service.FilmService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class FilmServiceTests {

    @Autowired
    private FilmService filmService;

    @Test
    void filmServiceTest() {
        Optional<Film> film = filmService.getById("3");
        assertTrue(film.isPresent());
        assertEquals(new Film("3", "Return of the Jedi"), film.get());
    }

}
