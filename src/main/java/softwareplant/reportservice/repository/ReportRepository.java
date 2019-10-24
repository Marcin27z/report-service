package softwareplant.reportservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import softwareplant.reportservice.model.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {
}
