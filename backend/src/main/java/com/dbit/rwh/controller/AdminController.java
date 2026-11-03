package com.dbit.rwh.controller;

import com.dbit.rwh.dto.AdminSummaryDto;
import com.dbit.rwh.dto.UserDto;
import com.dbit.rwh.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/dashboard/summary")
    public ResponseEntity<AdminSummaryDto> getSummary() {
        return ResponseEntity.ok(adminService.getSummary());
    }

    @GetMapping("/farmers")
    public ResponseEntity<Page<UserDto>> getFarmers(@RequestParam(required = false, defaultValue = "") String search,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(adminService.getFarmersPaginated(search, page, size));
    }

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

}
