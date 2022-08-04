package vn.t3h.be2204.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.t3h.be2204.dto.ProductDto;
import vn.t3h.be2204.repository.ProductEntityRepository;
import vn.t3h.be2204.service.IImageService;
import vn.t3h.be2204.service.ProductService;
import vn.t3h.be2204.service.TypeService;
import vn.t3h.be2204.utils.FileUtils;

import javax.validation.Valid;

//PRODUCT: id, name, description, price, categoryId, brandId, image(ảnh đại diện)
//IMAGE: id, path, productId
//CATEGORY: id, name, description
//BRAND: id, name, description
//HASH_TAG: id, name, productId
@Controller
@RequestMapping("/backend/product")
public class ProductController {
    @Autowired
    IImageService imageService;

    @Autowired
    ProductService productService;

    @Autowired
    TypeService typeService;

    @RequestMapping("create")
    public String create(Model model) throws Exception{
        ProductDto dto = new ProductDto();
        model.addAttribute("productDto", dto);
        model.addAttribute("categories", typeService.findAll(TypeService.CATEGORY_TYPE));
        model.addAttribute("brands", typeService.findAll(TypeService.BRAND_TYPE));
        return "backend/product/create";
    }

    @PostMapping("upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) throws Exception{
        try {
            String fileName = imageService.save(file);

            String imageUrl = imageService.getImageUrl(fileName);

            return imageUrl;

        } catch (Exception e) {
        }

        return FileUtils.saveFile(file);
    }

    @RequestMapping(value = "save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@Valid @ModelAttribute ProductDto productDto,
                       BindingResult bindingResult,// ngay sau biến có @Valid, chứa kết quả sau khi valid
                       RedirectAttributes model) throws Exception{
        if (bindingResult.hasErrors()) {
            return "backend/product/create";
        }
        // lưu user vào csdl
        productService.save(productDto);
//        saveDb(productDto);
        model.addFlashAttribute("message", "Lưu sản phẩm thành công");
        return "redirect:/backend/product/create";

//        return "/WEB-INF/jsp/signin.jsp";
    }
    @Autowired
    ProductEntityRepository productEntityRepository;

}
