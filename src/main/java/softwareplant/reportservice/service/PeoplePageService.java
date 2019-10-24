package softwareplant.reportservice.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import softwareplant.reportservice.HttpsRestTemplate;
import softwareplant.reportservice.model.swapi.PeoplePage;

import java.util.Optional;

@Component
public class PeoplePageService {

    @Cacheable("peoplePages")
    public Optional<PeoplePage> getPeoplePage(int pageNumber) {
        final String uri = "https://swapi.co/api/people/?page=" + pageNumber;
        HttpsRestTemplate restTemplate = new HttpsRestTemplate();
        try {
            return Optional.ofNullable(restTemplate.getForObject(uri, PeoplePage.class));
        } catch (RestClientException e) {
            return Optional.empty();
        }
    }
}
