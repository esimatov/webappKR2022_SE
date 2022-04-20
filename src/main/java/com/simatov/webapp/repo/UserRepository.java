package com.simatov.webapp.repo;

import com.simatov.webapp.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
