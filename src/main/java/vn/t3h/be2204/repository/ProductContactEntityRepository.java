package vn.t3h.be2204.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.t3h.be2204.entities.ProductContactEntity;

public interface ProductContactEntityRepository extends JpaRepository<ProductContactEntity,Long> {

    void deleteAllByProductId(Long productId);
}
