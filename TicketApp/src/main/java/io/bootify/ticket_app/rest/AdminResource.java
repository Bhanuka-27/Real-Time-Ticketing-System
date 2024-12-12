package io.bootify.ticket_app.rest;

import io.bootify.ticket_app.model.AdminDTO;
import io.bootify.ticket_app.service.AdminService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/admins", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminResource {

    private final AdminService adminService; // Service for handling admin-related operations

    // Constructor to inject the AdminService into this controller
    public AdminResource(final AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    public ResponseEntity<List<AdminDTO>> getAllAdmins() {
        return ResponseEntity.ok(adminService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDTO> getAdmin(@PathVariable(name = "id") final Long id) { // Calls the service to update the admin with the specified ID
        return ResponseEntity.ok(adminService.get(id)); // Returns the ID of the updated admin with a status of OK (200)
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createAdmin(@RequestBody @Valid final AdminDTO adminDTO) {
        final Long createdId = adminService.create(adminDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateAdmin(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final AdminDTO adminDTO) {
        adminService.update(id, adminDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteAdmin(@PathVariable(name = "id") final Long id) {
        adminService.delete(id); // Calls the service to delete the admin with the specified ID
        return ResponseEntity.noContent().build();
    }

}
