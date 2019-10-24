package softwareplant.reportservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import softwareplant.reportservice.model.QueryCriteria;
import softwareplant.reportservice.model.Report;
import softwareplant.reportservice.service.ReportService;

import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportRestController {

    private ReportService reportService;

    @Autowired
    public ReportRestController(ReportService reportService) {
        this.reportService = reportService;
    }

    @RequestMapping(value = "/{report_id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity putReport(@PathVariable Long report_id, @RequestBody QueryCriteria queryCriteria) {
        Optional<Report> report = reportService.generateReport(report_id, queryCriteria);
        return report.map(r -> {
                    reportService.saveReport(r);
                    return new ResponseEntity(HttpStatus.OK);
                }
        ).orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @RequestMapping(value = "/{report_id}", method = RequestMethod.GET, produces = "application/json")
    public Report getReport(@PathVariable Long report_id) {
        return reportService.getReport(report_id).orElse(new Report());
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @RequestMapping(value = "/{report_id}", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity deleteReport(@PathVariable Long report_id) {
        return reportService.deleteReport(report_id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void deleteAllReports() {
        reportService.deleteAllReports();
    }
}
