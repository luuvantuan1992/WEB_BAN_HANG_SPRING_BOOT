package vn.t3h.be2204.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.t3h.be2204.entities.ProductEntity;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Long> , ProductEntityCustom {

    List<ProductEntity> findAllByName(String name);
    ProductEntity findFirstByName(String name);
    List<ProductEntity> findByNameContaining(String name);

//    @Query(value = "select p.* from product_cart c " +
//            "join  product p on p.ID  = c.PRODUCT_ID " +
//            "GROUP BY  c.PRODUCT_ID DESC count(c.PRODUCT_ID) limit 10"
//            ,nativeQuery = true)
//    List<ProductEntity> findTop10();
}
