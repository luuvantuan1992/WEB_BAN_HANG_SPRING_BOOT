package vn.t3h.be2204.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.t3h.be2204.entities.UserContactEntity;

public interface UserContactEntityRepository extends JpaRepository<UserContactEntity, Long> {

    void deleteAllByUserId(Long userId);
}
