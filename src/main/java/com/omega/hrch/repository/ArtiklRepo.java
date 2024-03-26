package com.omega.hrch.repository;

import com.omega.hrch.model.Artikl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ArtiklRepo extends JpaRepository<Artikl,Long> {

}