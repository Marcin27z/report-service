package softwareplant.reportservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Report {

    @Id
    private Long report_id;

    private String query_criteria_character_phrase;

    private String query_criteria_planet_name;

    private String film_id;

    private String film_name;

    private String character_id;

    private String character_name;

    private String planet_id;

    private String planet_name;
}
