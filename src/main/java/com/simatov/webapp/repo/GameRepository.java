package com.simatov.webapp.repo;

import com.simatov.webapp.models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Long> {
}
