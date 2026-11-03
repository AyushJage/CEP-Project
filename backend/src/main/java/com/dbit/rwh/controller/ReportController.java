package com.dbit.rwh.controller;

import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import com.dbit.rwh.service.ReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;
    private final UserRepository userRepo;

    @GetMapping("/yearly")
    public ResponseEntity<Map<String, Object>> getYearly(@AuthenticationPrincipal UserDetails principal,
                                                         @RequestParam(required = false) Integer year) {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        int y = (year != null) ? year : LocalDate.now().getYear();
        return ResponseEntity.ok(reportService.getYearlyReport(user, y));
    }

    @GetMapping("/export/csv")
    public ResponseEntity<byte[]> exportCsv(@AuthenticationPrincipal UserDetails principal,
                                            @RequestParam(required = false) Integer year) throws Exception {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        int y = (year != null) ? year : LocalDate.now().getYear();
        byte[] csv = reportService.exportCsv(user, y);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rwh-report-" + y + ".csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }

    @GetMapping("/export/pdf")
    public ResponseEntity<byte[]> exportPdf(@AuthenticationPrincipal UserDetails principal,
                                            @RequestParam(required = false) Integer year) throws Exception {
        User user = userRepo.findByPhone(principal.getUsername()).orElseThrow();
        int y = (year != null) ? year : LocalDate.now().getYear();
        byte[] pdf = reportService.exportPdf(user, y);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=rwh-report-" + y + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    public ReportController(ReportService reportService, UserRepository userRepo) {
        this.reportService = reportService;
        this.userRepo = userRepo;
    }

}
