package vn.t3h.be2204.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.t3h.be2204.entities.ProductEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    Long id;
    @NotNull(message = "Giá bắt buộc phải điền")
    @Min(value = 0, message = "Giá ngỏ nhất là 0")
    Long price;
    @NotBlank(message = "Tên không được rỗng")
    String name;
    @NotNull(message = "Mô tả không được rỗng")
    String description;
    String image;

    Long categoryId;
    Long brandId;

//    List<String> images = new ArrayList<>();




    public ProductEntity convertToEntity() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(this.id);
        productEntity.setPrice(this.price);
        productEntity.setName(this.name);
        productEntity.setDescription(this.description);
//        productEntity.setImages(this.images);
        productEntity.setImage(this.image);
        productEntity.setCategoryId(this.categoryId);
        productEntity.setBrandId(this.brandId);

        return productEntity;
    }
}
