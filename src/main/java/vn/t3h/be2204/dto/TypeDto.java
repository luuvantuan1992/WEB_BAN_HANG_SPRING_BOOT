package vn.t3h.be2204.dto;

import lombok.Data;
import org.springframework.beans.BeanUtils;
import vn.t3h.be2204.entities.BrandEntity;
import vn.t3h.be2204.entities.CategoryEntity;

@Data
public class TypeDto {
    private Long id;
    private String name;
    private String description;

    public BrandEntity convertToBrand(){
        BrandEntity brandEntity = new BrandEntity();
        BeanUtils.copyProperties(this, brandEntity);
        return brandEntity;
    }

    public CategoryEntity convertToCategory(){
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(this, categoryEntity);
        return categoryEntity;
    }
}
