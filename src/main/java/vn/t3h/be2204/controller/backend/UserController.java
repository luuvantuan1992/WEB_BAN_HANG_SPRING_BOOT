package vn.t3h.be2204.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.t3h.be2204.dto.ResponseDto;
import vn.t3h.be2204.dto.UserDto;
import vn.t3h.be2204.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("backend/user")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {

    //1: Tạo bảng USER trong csdl (FULL_NAME, EMAIL, PASSWORD)
    // và thêm 5 bản ghi vào cơ sở dữ liệu
    //2: Thêm thư viên jdbc bằng maven (pom) rồi kết nối
    // truy vấn tất cả bản ghi từ trong csdl
    // 3: Hiển thị danh sách user lên giao diên

    @Autowired
    UserService userService;

    public static String URL = "jdbc:mysql://localhost:3306/2204";
    public static String USER = "root";
    public static String PASS = "123456";

    @RequestMapping("list")
    public String list(Model model, @RequestParam(required = false) String searchKey,
                       @RequestParam(required = false, defaultValue = "1") Integer page,
                       @RequestParam(required = false, defaultValue = "5") Integer perpage) throws Exception{
        userService.list(page, perpage, searchKey, model);
        return "backend/user/user_list";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model, @PathVariable Long id) throws Exception{
        model.addAttribute("user", userService.delete(id));
        return "backend/user/user_detail";
    }

    @RequestMapping("rest-detail/{id}")
    @ResponseBody
    public UserDto getDetail( @PathVariable Long id) throws Exception{
        return userService.detail(id);
    }

    //1: Thêm thư viên vào maven
    //2: Thêm @valid và BindingResult và custom kiểu valid và nội dung message lỗi
    //3: chỉnh sửa dưới jsp

    @RequestMapping("create")
    public String create(Model model) throws Exception{
        UserDto dto = new UserDto();
        model.addAttribute("userDto", dto);
        return "backend/user/user_create";
    }

    @RequestMapping("update/{id}")
    public String update(Model model, @PathVariable Long id) throws Exception{
        UserDto dto = userService.detail(id);
        model.addAttribute("userDto", dto);
        return "backend/user/user_update";
    }
    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@Valid @ModelAttribute UserDto userDto,
                       BindingResult bindingResult,// ngay sau biến có @Valid, chứa kết quả sau khi valid
                       RedirectAttributes model) throws Exception{
        if (bindingResult.hasErrors()) {
            return "backend/user/user_create";
        }
        // lưu user vào csdl
        ResponseDto responseDto = userService.save(userDto);
        model.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/backend/user/list";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@ModelAttribute UserDto userDto,
                       RedirectAttributes model) throws Exception{
        // lưu user vào csdl
        ResponseDto responseDto = userService.save(userDto);
        model.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/backend/user/update/" + userDto.getId();
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes model) {
        ResponseDto responseDto = userService.delete(id);
        model.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/backend/user/list";
    }



}
