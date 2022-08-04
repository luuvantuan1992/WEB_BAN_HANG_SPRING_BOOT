package vn.t3h.be2204.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import vn.t3h.be2204.dto.validation.gmail.GmailIF;
import vn.t3h.be2204.entities.CategoryEntity;
import vn.t3h.be2204.entities.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data// tự động sinh ra các hàm getter setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    @NotBlank(message = "Họ tên không được rỗng")
    @NotNull // = null là lỗi, "" không lỗi
    @NotEmpty//= null là lỗi, "" là lỗi
    private String name;
    private String category;

    public CategoryEntity convertToEntity(){
        CategoryEntity categoryEntity = new CategoryEntity();
        BeanUtils.copyProperties(this, categoryEntity);
        return categoryEntity;
    }
}
