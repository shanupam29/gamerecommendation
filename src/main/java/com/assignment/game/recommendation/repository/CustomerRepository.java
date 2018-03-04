package com.assignment.game.recommendation.repository;

import com.assignment.game.recommendation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data access layer to facilitate persistence of the entity object into
 * database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findGamesById(Long id);

}
