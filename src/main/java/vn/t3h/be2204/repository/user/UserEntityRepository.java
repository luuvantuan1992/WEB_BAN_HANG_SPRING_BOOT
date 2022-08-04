package vn.t3h.be2204.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.t3h.be2204.entities.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByEmail(String email);

    boolean existsByEmail(String email);

    @Query(value = "select u from UserEntity u where u.fullName like %?1%")
    Page<UserEntity> findAll(String key, Pageable pageable);
    Page<UserEntity> findAll( Pageable pageable);
}
