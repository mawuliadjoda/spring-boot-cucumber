package com.adjoda.cucumber.pharmacy;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
}
