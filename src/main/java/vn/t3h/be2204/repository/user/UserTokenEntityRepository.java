package vn.t3h.be2204.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.t3h.be2204.entities.UserTokenEntity;

public interface UserTokenEntityRepository extends JpaRepository<UserTokenEntity, Long> {

    UserTokenEntity findFirstByToken(String token);
    void deleteAllByToken(String token);
}
