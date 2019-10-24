package softwareplant.reportservice.model.swapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeoplePage {

    private String next;
    private List<PersonDto> results;

    public boolean hasNext() {
        return next != null;
    }
}
