package softwareplant.reportservice.model.swapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private String name;

    private String homeworld;

    private List<String> films;

    private String url;
}
