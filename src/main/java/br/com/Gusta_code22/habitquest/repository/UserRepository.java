package br.com.Gusta_code22.habitquest.repository;

import br.com.Gusta_code22.habitquest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
