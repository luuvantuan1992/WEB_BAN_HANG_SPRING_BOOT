package vn.t3h.be2204.controller;

import org.springframework.web.bind.annotation.*;

@RestController // = @Controller + @ResponseBody
public class HomeRestController {

    @RequestMapping("home-rest")
    public String homeRestController() {
        return "<h1 style=\"color:green\">Xin chào bạn!!!</h1>";
    }

    @RequestMapping("get-rest")
    public String get() {
        return "Đây là phương thức get";
    }

//    @RequestMapping(value = "post-rest", method = RequestMethod.POST)
    @PostMapping("post-rest")
    public String post() {
        return "Đây là phương thức post - tạo mới dữ liệu";
    }

    @PutMapping("put-rest")
    public String put() {
        return "Đây là phương thức put - sửa dữ liệu";
    }

    @DeleteMapping("delete-rest")
    public String delete() {
        return "Đây là phương thức delete - xóa dữ liệu";
    }
}
