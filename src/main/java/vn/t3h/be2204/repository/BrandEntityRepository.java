package vn.t3h.be2204.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.t3h.be2204.entities.BrandEntity;

public interface BrandEntityRepository extends JpaRepository<BrandEntity, Long> {
}
