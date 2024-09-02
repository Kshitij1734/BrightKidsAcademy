package com.brightkids_academy.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.brightkids_academy.entities.ImageAdmin;

@Repository
public interface ImageRepository extends JpaRepository<ImageAdmin, Long> {
}
