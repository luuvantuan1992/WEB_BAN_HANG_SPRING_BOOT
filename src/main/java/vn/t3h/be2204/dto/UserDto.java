package vn.t3h.be2204.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import vn.t3h.be2204.dto.validation.gmail.GmailIF;
import vn.t3h.be2204.dto.validation.password.PasswordAnotation;
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
@PasswordAnotation(message = "Mật khẩu không trùng khớp")
public class UserDto {
    private Long id;
    @NotBlank(message = "Họ tên không được rỗng")
    @NotNull // = null là lỗi, "" không lỗi
    @NotEmpty//= null là lỗi, "" là lỗi
    private String fullName;
    @Email(message = "Vui lòng nhập đúng dạng email")
    @NotBlank(message = "Email bắt buộc phải nhập")
    // email phải chứa đuôi là @gmail.com
    @GmailIF
    private String email;
    @NotBlank(message = "Mật khẩu bắt buộc phải nhập")
    private String password;
    @NotBlank(message = "Nhập lại password bắt buộc phải nhập")
    private String rePassword;

    private String role;

    Integer status;

    String address;

    List<String> phones = new ArrayList<>();

    public UserEntity convertToEntity(){
        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail(this.email);
//        userEntity.setFullName(this.fullName);
//        userEntity.setPassword(this.password);
//        userEntity.setId(this.id);
//        userEntity.setRole(this.role);
        BeanUtils.copyProperties(this, userEntity);
        return userEntity;
    }
}
