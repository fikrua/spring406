package springboot_401.demospringboot_401;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
    Long countByEmail(String email);
    long countByUsername(String username);

}
