package vn.t3h.be2204.repository;

import vn.t3h.be2204.entities.ProductEntity;

import java.util.List;

public interface ProductEntityCustom {
    List<ProductEntity> findTop10();
}
