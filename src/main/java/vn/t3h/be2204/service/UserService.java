package vn.t3h.be2204.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import vn.t3h.be2204.config.language.MessageConfig;
import vn.t3h.be2204.dto.EmailDetails;
import vn.t3h.be2204.dto.ResponseDto;
import vn.t3h.be2204.dto.UserDto;
import vn.t3h.be2204.entities.UserContactEntity;
import vn.t3h.be2204.entities.UserEntity;
import vn.t3h.be2204.entities.UserTokenEntity;
import vn.t3h.be2204.repository.user.UserContactEntityRepository;
import vn.t3h.be2204.repository.user.UserEntityRepository;
import vn.t3h.be2204.repository.user.UserTokenEntityRepository;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserService {

    @Autowired
    UserEntityRepository userEntityRepository;

    @Autowired
    UserContactEntityRepository userContactEntityRepository;

    @Autowired
    MessageConfig messageConfig;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserTokenEntityRepository userTokenEntityRepository;

    @Autowired
    EmailServiceImpl emailService;

    public ResponseDto signin(UserDto userDto) {
        userDto.setRole("CUSTOMER");
        userDto.setStatus(0);
        ResponseDto responseDto = save(userDto);
        UserTokenEntity userTokenEntity = new UserTokenEntity();
        userTokenEntity.setUserId(userDto.getId());
        userTokenEntity.setToken(UUID.randomUUID().toString());
        userTokenEntityRepository.save(userTokenEntity);
        emailService.sendSimpleMail(new EmailDetails(userDto.getEmail(), "Vui lòng click vào đây để kích hoạt tài khoản: http://localhost:8080/active-account/" + userTokenEntity.getToken(), "Xác nhận tài khoản", null));
        return responseDto;
    }

    public String verifyToken(String token) {
        UserTokenEntity userTokenEntity = userTokenEntityRepository.findFirstByToken(token);
        if (userTokenEntity == null) return "Token không đúng";

        UserEntity userEntity = userEntityRepository.findById(userTokenEntity.getUserId()).get();
        if (userEntity == null) return "Tài khoản không tồn tại";
        String msg = "";
        if (userEntity.getStatus() == 1) msg = "Tài khoản đã được kích hoạt";
        else {
            userEntity.setStatus(1);
            userEntityRepository.save(userEntity);
            msg = "Kích hoạt tài khoản thành công";
        }

        userTokenEntityRepository.deleteAllByToken(token);
        return msg;
    }

    public ResponseDto save(UserDto userDto) {
        if (userDto == null)
            return new ResponseDto(2, messageConfig.getMessage("user.fail.save"));
        if (userDto.getId() != null)// nếu id khác null thì sẽ là cập nhật, id= null là thêm mới tài khoản
            return update(userDto);
        // Kiểm tra xem trùng email hay ko
        UserEntity userEntity = userEntityRepository.findFirstByEmail(userDto.getEmail());
        if /*(userEntity != null)*/ (userEntityRepository.existsByEmail(userDto.getEmail())) {
            return new ResponseDto(2, messageConfig.getMessage("user.fail.exist"));
        }
        // Bước 2: Lưu user vào bảng user
        UserEntity save = userDto.convertToEntity();
        save.setPassword(passwordEncoder.encode(userDto.getPassword()));
//        passwordEncoder.matches("123456", save.getPassword());
        save = userEntityRepository.save(save);
        // Bước 3 lấy thông user đã lưu, lưu vào bảng user_contact
        if (!CollectionUtils.isEmpty(userDto.getPhones())) {
            for (String phone : userDto.getPhones()
            ) {
//                if (userDto != null)
//                    throw new RuntimeException("Lỗi lưu user contact");
                UserContactEntity userContactEntity = new UserContactEntity();
                userContactEntity.setPhoneNumber(phone);
                userContactEntity.setUserId(save.getId());
                userContactEntityRepository.save(userContactEntity);
            }
        }

        userDto.setId(save.getId());
        return new ResponseDto(1, messageConfig.getMessage("user.success.save"));
    }

    public ResponseDto update(UserDto userDto) {
        if (userDto == null || userDto.getId() == null)
            return new ResponseDto(2, messageConfig.getMessage("user.update.save"));
        // cập nhật thông tin user
        UserEntity userEntity = userEntityRepository.findById(userDto.getId()).get();
        if (userEntity == null)
            new ResponseDto(2, messageConfig.getMessage("user.update.save"));
        userEntity.setFullName(userDto.getFullName());
        userEntity.setRole(userDto.getRole());
        userEntity.setStatus(userDto.getStatus());
        userEntityRepository.save(userEntity);

        // Xóa thông tin contact cũ
        userContactEntityRepository.deleteAllByUserId(userDto.getId());
        // Cập nhật thông tin contact mới
        if (!CollectionUtils.isEmpty(userDto.getPhones())) {
            for (String phone : userDto.getPhones()
            ) {
                UserContactEntity userContactEntity = new UserContactEntity();
                userContactEntity.setPhoneNumber(phone);
                userContactEntity.setUserId(userDto.getId());
                userContactEntityRepository.save(userContactEntity);
            }
        }
        return new ResponseDto(1, messageConfig.getMessage("user.success.update"));
    }

    public ResponseDto delete(Long id) {
        UserEntity userEntity = userEntityRepository.findById(id).get();
        if (userEntity == null)
            new ResponseDto(2, String.format(messageConfig.getMessage("user.delete.save"), userEntity.getFullName()));
        userEntityRepository.deleteById(id);

        userContactEntityRepository.deleteAllByUserId(id);
        return new ResponseDto(1, String.format(messageConfig.getMessage("user.success.delete"), userEntity.getFullName()));
    }

    public UserDto detail(Long id) {
        UserEntity userEntity = userEntityRepository.findById(id).get();
        UserDto dto = new UserDto();
        if (userEntity != null) {
            BeanUtils.copyProperties(userEntity, dto);
            if (!CollectionUtils.isEmpty(userEntity.getContacts())) {
                userEntity.getContacts().forEach(c -> {
                    dto.getPhones().add(c.getPhoneNumber());
                });
            }
        }
        return dto;
    }

    /**
     * @param page      là số trang
     * @param perpage   số phần tử trên 1 trang
     * @param searchKey ký tự người dùng tìm kiếm
     * @return
     */
    public void list(Integer page, Integer perpage, String searchKey, Model model) {
//        return userEntityRepository.findAll();// lấy tất cả các phần tử
        Page<UserEntity> pages = null;

        if (StringUtils.isBlank(searchKey))
            pages = userEntityRepository.findAll(PageRequest.of(page - 1, perpage));
        else {
            pages = userEntityRepository.findAll(searchKey, PageRequest.of(page - 1, perpage));
        }
        model.addAttribute("list", pages.getContent());
        model.addAttribute("page", page);
        model.addAttribute("searchKey", searchKey);
        model.addAttribute("perpage", perpage);
        model.addAttribute("total", pages.getTotalPages());

    }

}
