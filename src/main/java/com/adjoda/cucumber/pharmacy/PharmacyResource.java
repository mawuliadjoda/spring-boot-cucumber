package com.adjoda.cucumber.pharmacy;

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
@RequestMapping(value = "/api/pharmacies", produces = MediaType.APPLICATION_JSON_VALUE)
public class PharmacyResource {

    private final PharmacyService pharmacyService;

    public PharmacyResource(final PharmacyService pharmacyService) {
        this.pharmacyService = pharmacyService;
    }

    @GetMapping
    public ResponseEntity<List<PharmacyDTO>> getAllPharmacies() {
        return ResponseEntity.ok(pharmacyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PharmacyDTO> getPharmacy(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(pharmacyService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createPharmacy(@RequestBody @Valid final PharmacyDTO pharmacyDTO) {
        final Long createdId = pharmacyService.create(pharmacyDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePharmacy(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final PharmacyDTO pharmacyDTO) {
        pharmacyService.update(id, pharmacyDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePharmacy(@PathVariable(name = "id") final Long id) {
        pharmacyService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
