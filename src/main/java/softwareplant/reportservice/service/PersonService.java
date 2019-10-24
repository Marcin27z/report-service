package softwareplant.reportservice.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import softwareplant.reportservice.UrlUtils;
import softwareplant.reportservice.model.QueryCriteria;
import softwareplant.reportservice.model.swapi.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PersonService {

    private FilmService filmService;

    private PlanetService planetService;

    private PeoplePageService peoplePageService;

    @Autowired
    public PersonService(FilmService filmService, PlanetService planetService, PeoplePageService peoplePageService) {
        this.filmService = filmService;
        this.planetService = planetService;
        this.peoplePageService = peoplePageService;
    }

    @Cacheable("person")
    public Optional<Person> findPersonByCriteria(QueryCriteria queryCriteria) {
        if (queryCriteria.isInvalid()) {
            return Optional.empty();
        }
        int i = 1;
        Optional<PeoplePage> result;
        do {
            result = peoplePageService.getPeoplePage(i);
            Optional<Person> person = result.flatMap(page -> findPersonOnPage(page, queryCriteria));
            if (person.isPresent()) {
                return person;
            } else {
                ++i;
            }
        } while (result.map(PeoplePage::hasNext).orElse(false));
        return Optional.empty();
    }

    private Optional<Person> findPersonOnPage(PeoplePage peoplePage, QueryCriteria queryCriteria) {
        List<PersonDto> matchingPeople = peoplePage.getResults().stream()
                .filter(it -> it.getName().contains(queryCriteria.getQuery_criteria_character_phrase()))
                .collect(Collectors.toList());

        for (PersonDto matchingPerson : matchingPeople) {
            Optional<Person> person = planetService
                    .getPlanetById(UrlUtils.getIdFromUrl(matchingPerson.getHomeworld()))
                    .flatMap(homeworld -> homeworldMatchingCriteria(homeworld, queryCriteria))
                    .flatMap(homeworld -> getFilm(matchingPerson).map(film -> new PlanetFilmPair(homeworld, film)))
                    .flatMap(planetFilmPair -> splicePerson(planetFilmPair, matchingPerson));
            if (person.isPresent())
                return person;
        }
        return Optional.empty();
    }

    private Optional<Planet> homeworldMatchingCriteria(Planet homeworld, QueryCriteria queryCriteria) {
        return Optional.of(homeworld).filter(h -> h.getName().equals(queryCriteria.getQuery_criteria_planet_name()));
    }

    private Optional<Film> getFilm(PersonDto matchingPerson) {
        return filmService.getById(UrlUtils.getIdFromUrl(matchingPerson.getFilms().get(0)));
    }

    private Optional<Person> splicePerson(PlanetFilmPair planetFilmPair, PersonDto matchingPerson) {
        String personId = UrlUtils.getIdFromUrl(matchingPerson.getUrl());
        return Optional.of(new Person(personId, matchingPerson.getName(), planetFilmPair.getFilm(), planetFilmPair.getPlanet()));
    }
}

@Data
@AllArgsConstructor
class PlanetFilmPair {
    private Planet planet;
    private Film film;
}
