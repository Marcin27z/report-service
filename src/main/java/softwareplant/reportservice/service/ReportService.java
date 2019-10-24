package softwareplant.reportservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import softwareplant.reportservice.model.QueryCriteria;
import softwareplant.reportservice.model.Report;
import softwareplant.reportservice.repository.ReportRepository;

import java.util.Optional;

@Service
public class ReportService {

    private ReportRepository reportRepository;

    private PersonService personService;


    @Autowired
    public ReportService(ReportRepository reportRepository, PersonService personService) {
        this.reportRepository = reportRepository;
        this.personService = personService;
    }

    public Optional<Report> generateReport(Long report_id, QueryCriteria criteria) {
        return personService.findPersonByCriteria(criteria).map(person -> Report.builder()
                .report_id(report_id)
                .query_criteria_character_phrase(criteria.getQuery_criteria_character_phrase())
                .query_criteria_planet_name(criteria.getQuery_criteria_planet_name())
                .film_id(person.getFilmId())
                .film_name(person.getFilmName())
                .character_id(person.getPerson_id())
                .character_name(person.getName())
                .planet_id(person.getPlanetId())
                .planet_name(person.getPlanetName())
                .build());
    }

    public void saveReport(Report report) {
        reportRepository.save(report);
    }

    public void deleteAllReports() {
        reportRepository.deleteAll();
    }

    public boolean deleteReport(Long report_id) {
        try {
            reportRepository.deleteById(report_id);
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

    public Optional<Report> getReport(Long report_id) {
        return reportRepository.findById(report_id);
    }

    public Iterable<Report> getAllReports() {
        return reportRepository.findAll();
    }
}
