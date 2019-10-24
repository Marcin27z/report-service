package softwareplant.reportservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryCriteria {

    private String query_criteria_character_phrase;

    private String query_criteria_planet_name;

    public boolean isInvalid() {
        return query_criteria_character_phrase == null
                || query_criteria_planet_name == null;
    }
}
