package softwareplant.reportservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import softwareplant.reportservice.model.QueryCriteria;
import softwareplant.reportservice.model.swapi.Film;
import softwareplant.reportservice.model.swapi.Person;
import softwareplant.reportservice.model.swapi.Planet;
import softwareplant.reportservice.service.PersonService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class PersonServiceTests {

    @Autowired
    private PersonService personService;

    @Test
    void test() {
        Optional<Person> person = personService.findPersonByCriteria(new QueryCriteria("Luke", "Tatooine"));
        assertTrue(person.isPresent());
        assertEquals(
                new Person("1", "Luke Skywalker",
                        new Film("2", "The Empire Strikes Back"),
                        new Planet("1", "Tatooine")),
                person.get());
    }

}
