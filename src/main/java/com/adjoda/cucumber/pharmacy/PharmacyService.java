package com.adjoda.cucumber.pharmacy;

import com.adjoda.cucumber.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    public PharmacyService(final PharmacyRepository pharmacyRepository) {
        this.pharmacyRepository = pharmacyRepository;
    }

    public List<PharmacyDTO> findAll() {
        final List<Pharmacy> pharmacies = pharmacyRepository.findAll(Sort.by("id"));
        return pharmacies.stream()
                .map(pharmacy -> mapToDTO(pharmacy, new PharmacyDTO()))
                .toList();
    }

    public PharmacyDTO get(final Long id) {
        return pharmacyRepository.findById(id)
                .map(pharmacy -> mapToDTO(pharmacy, new PharmacyDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final PharmacyDTO pharmacyDTO) {
        final Pharmacy pharmacy = new Pharmacy();
        mapToEntity(pharmacyDTO, pharmacy);
        return pharmacyRepository.save(pharmacy).getId();
    }

    public void update(final Long id, final PharmacyDTO pharmacyDTO) {
        final Pharmacy pharmacy = pharmacyRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(pharmacyDTO, pharmacy);
        pharmacyRepository.save(pharmacy);
    }

    public void delete(final Long id) {
        pharmacyRepository.deleteById(id);
    }

    private PharmacyDTO mapToDTO(final Pharmacy pharmacy, final PharmacyDTO pharmacyDTO) {
        pharmacyDTO.setId(pharmacy.getId());
        pharmacyDTO.setName(pharmacy.getName());
        pharmacyDTO.setAddress(pharmacy.getAddress());
        pharmacyDTO.setLat(pharmacy.getLat());
        pharmacyDTO.setLng(pharmacy.getLng());
        return pharmacyDTO;
    }

    private Pharmacy mapToEntity(final PharmacyDTO pharmacyDTO, final Pharmacy pharmacy) {
        pharmacy.setName(pharmacyDTO.getName());
        pharmacy.setAddress(pharmacyDTO.getAddress());
        pharmacy.setLat(pharmacyDTO.getLat());
        pharmacy.setLng(pharmacyDTO.getLng());
        return pharmacy;
    }

}
