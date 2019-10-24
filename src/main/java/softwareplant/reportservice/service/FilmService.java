package softwareplant.reportservice.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import softwareplant.reportservice.HttpsRestTemplate;
import softwareplant.reportservice.model.swapi.Film;

import java.util.Optional;

@Component
public class FilmService {

    @Cacheable("films")
    public Optional<Film> getById(String film_id) {
        final String uri = "https://swapi.co/api/films/" + film_id + "/";
        RestTemplate restTemplate = new HttpsRestTemplate();
        Optional<Film> film;
        try {
            film = Optional.ofNullable(restTemplate.getForObject(uri, Film.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
        film.ifPresent(f -> f.setFilm_id(film_id));
        return film;
    }
}
