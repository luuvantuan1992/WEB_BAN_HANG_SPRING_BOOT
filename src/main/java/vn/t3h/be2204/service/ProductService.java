package vn.t3h.be2204.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.t3h.be2204.dto.ProductCartDto;
import vn.t3h.be2204.dto.ProductDto;
import vn.t3h.be2204.entities.ProductCartEntity;
import vn.t3h.be2204.entities.ProductEntity;
import vn.t3h.be2204.entities.UserEntity;
import vn.t3h.be2204.repository.ProductCartEntityRepository;
import vn.t3h.be2204.repository.ProductEntityRepository;

import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductEntityRepository productEntityRepository;

    @Autowired
    ProductCartEntityRepository productCartEntityRepository;

    public void save(ProductDto productDto) {
        ProductEntity productEntity = productDto.convertToEntity();
        productEntityRepository.save(productEntity);
    }

    public List<ProductEntity> findAll() {
        return productEntityRepository.findAll();
    }

    public ProductEntity detail(Long id) {
        return productEntityRepository.findById(id).orElse(new ProductEntity());
    }

    public List<ProductCartEntity> getListCart() {
        Long userId = ((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return productCartEntityRepository.findAllByUserId(userId);
    }


    public void cart(ProductCartDto dto) {
        Long userId = ((UserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ProductCartEntity productCartEntity = productCartEntityRepository.findFirstByProductIdAndUserId(dto.getProductId(), userId);
        if (productCartEntity != null) productCartEntity.setNumber(dto.getNumber());
        else {
            productCartEntity = new ProductCartEntity();
            productCartEntity.setUserId(userId);
            productCartEntity.setProductId(dto.getProductId());
            productCartEntity.setNumber(dto.getNumber());
        }
        productCartEntityRepository.save(productCartEntity);
    }
}
