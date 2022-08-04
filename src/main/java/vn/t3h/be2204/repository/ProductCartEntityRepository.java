package vn.t3h.be2204.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.t3h.be2204.entities.ProductCartEntity;
import vn.t3h.be2204.entities.ProductEntity;

import java.util.List;

public interface ProductCartEntityRepository extends JpaRepository<ProductCartEntity, Long> {

    List<ProductCartEntity> findAllByUserId(Long userId);

    ProductCartEntity findFirstByProductIdAndUserId(Long productId, Long userId);
}
