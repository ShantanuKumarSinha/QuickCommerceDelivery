package com.shann.quickcommerce.repositories;

import com.shann.quickcommerce.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PartnerRepository is a Spring Data JPA repository for managing Partner entities.
 * It provides methods to perform CRUD operations and custom queries on Partner data.
 */
@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    /**
     * Finds partners by their IDs.
     *
     * @param partnerIds the list of partner IDs to search for
     * @return a list of partners matching the provided IDs
     */
    @Query("SELECT p FROM Partner p WHERE p.id IN :partnerIds")
    List<Partner> findPartnersByIdIn(List<Long> partnerIds);
}
