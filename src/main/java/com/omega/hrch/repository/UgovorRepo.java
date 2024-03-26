package com.omega.hrch.repository;

import com.omega.hrch.model.Ugovor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource
public interface UgovorRepo extends JpaRepository<Ugovor,Long> {
    List<Ugovor> findUgovorsByKupac(String kupac);
    List<Ugovor> findAllByStatusIs(String status);

}
