package vn.t3h.be2204.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.t3h.be2204.dto.ProductCartDto;
import vn.t3h.be2204.dto.ResponseDto;
import vn.t3h.be2204.service.ProductService;

@Controller
@RequestMapping("product")
public class ProductCustomerController {
    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.detail(id));
        return "product";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResponseDto cart(@RequestBody ProductCartDto dto) throws Exception {
        productService.cart(dto);
        return new ResponseDto(1,"Thêm giỏ hàng thành công");
    }


    @GetMapping("/cart")
    public String checkout(Model model) {
        model.addAttribute("carts", productService.getListCart());
        return "cart";
    }
}
