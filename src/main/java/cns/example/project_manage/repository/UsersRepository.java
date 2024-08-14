package cns.example.project_manage.repository;

import cns.example.project_manage.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    public Optional<Users> findByUserName(String userName);
//    public Users loadUserByUsername(String userName);
}
