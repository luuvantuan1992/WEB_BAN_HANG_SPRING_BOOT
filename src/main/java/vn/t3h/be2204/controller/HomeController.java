package vn.t3h.be2204.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.t3h.be2204.config.handler.LoginSuccessHandler;
import vn.t3h.be2204.dto.ResponseDto;
import vn.t3h.be2204.dto.UserDto;
import vn.t3h.be2204.service.ProductService;
import vn.t3h.be2204.service.UserService;
import vn.t3h.be2204.utils.DbUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller// đánh dấu nó là 1 controller
public class HomeController {

    @Autowired
    ProductService productService;

    @RequestMapping({"home", "", "/", "home/abc"})
    public String home(Model model) {
        model.addAttribute("list", productService.findAll());
        return "home";
    }

    @RequestMapping("home/{path}")
    public String homeModel(Model model, @PathVariable String path,
                            @RequestParam(required = false) String key) {
        model.addAttribute("list", productService.findAll());
        List<String> listStr = Arrays.asList(new String[]{"Nguyễn Văn A", "Trần Thị X", "Lên Văn D"});
        model.addAttribute("list", listStr);
        return "/WEB-INF/jsp/home.jsp";
    }

    @RequestMapping("login")
    public String login() {
        return "login";
    }

    @RequestMapping("forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public String dologin(@RequestBody Map<String, String> body, HttpServletRequest req, HttpServletResponse res) {
        String username = body.get("username");
        String password = body.get("password");

        // Validate kiểm tra và trẩ về mã lỗi
        // Lấy user từu database
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,
                    password);
            final List<GrantedAuthority> authorities = new ArrayList<>();
            User details = new User(username, password, authorities);
        token.setDetails(details);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(auth);
        try {
            loginSuccessHandler.onAuthenticationSuccess(req, res, auth);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "login";
    }

    @RequestMapping("loginFailed")
    public String login(Model model, @RequestParam Map<String, String> map) {
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry: map.entrySet()
                 ) {
                if (StringUtils.equals(entry.getKey(), "type")) {
                    if (StringUtils.equals(entry.getValue(), "1")) {
                        model.addAttribute("message", "Không tìm thấy tài khoản");
                    }
                } else if (StringUtils.equals(entry.getKey(), "username")){
                    model.addAttribute(entry.getKey(), entry.getValue());
                }

            }
        }
        return "login";
    }

    @RequestMapping("signin")
    public String signin() {
        return "signin";
    }

    @Autowired
    UserService userService;

    @RequestMapping(value = "signin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String savesignin(@Valid @ModelAttribute UserDto userDto,
                       BindingResult bindingResult,// ngay sau biến có @Valid, chứa kết quả sau khi valid
                       RedirectAttributes model) throws Exception{
        if (bindingResult.hasErrors()) {
            return "signin";
        }
        // lưu user vào csdl
        ResponseDto responseDto = userService.signin(userDto);
        model.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/login";
    }

    @RequestMapping("active-account/{token}")
    public String activeAccount(@PathVariable String token, RedirectAttributes model) {
        model.addFlashAttribute("message", userService.verifyToken(token));
        return "redirect:/login";
    }
}
