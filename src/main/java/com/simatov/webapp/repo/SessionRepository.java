package com.simatov.webapp.repo;

import com.simatov.webapp.models.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {

}
