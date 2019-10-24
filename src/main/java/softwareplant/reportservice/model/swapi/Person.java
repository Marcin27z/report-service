package softwareplant.reportservice.model.swapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String person_id;

    private String name;

    private Film film;

    private Planet planet;

    public String getFilmId() {
        return film.getFilm_id();
    }

    public String getFilmName() {
        return film.getTitle();
    }

    public String getPlanetId() {
        return planet.getPlanet_id();
    }

    public String getPlanetName() {
        return planet.getName();
    }
}
