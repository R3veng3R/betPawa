package com.aj.pawatask.repositories;

import com.aj.pawatask.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
