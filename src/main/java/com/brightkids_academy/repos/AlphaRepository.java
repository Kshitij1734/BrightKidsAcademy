package com.brightkids_academy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brightkids_academy.entities.AlphaAdmin; // Updated entity import

@Repository
public interface AlphaRepository extends JpaRepository<AlphaAdmin, Long> { // Updated interface and entity names
}
