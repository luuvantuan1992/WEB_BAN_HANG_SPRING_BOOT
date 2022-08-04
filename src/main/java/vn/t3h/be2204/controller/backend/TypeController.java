package vn.t3h.be2204.controller.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.t3h.be2204.dto.ResponseDto;
import vn.t3h.be2204.dto.TypeDto;
import vn.t3h.be2204.entities.BrandEntity;
import vn.t3h.be2204.service.TypeService;

import javax.validation.Valid;

@Controller
@RequestMapping("backend")
public class TypeController {
    @Autowired
    TypeService typeService;


    private void common(Model model, String path) {
        model.addAttribute("title_path",
                StringUtils.endsWithIgnoreCase(path, "brand") ? "nhãn hiệu" : " thể loại");
        model.addAttribute("path", path);
    }

    @RequestMapping("/{path}/create")
    public String create(@PathVariable String path, Model model) {
        common(model, path);
        model.addAttribute("dto", new BrandEntity());
        return "backend/type/create";
    }

    @RequestMapping("/{path}/list")
    public String list(Model model, @PathVariable String path) throws Exception {
        common(model, path);
        model.addAttribute("list", typeService.findAll(path));
        return "backend/type/list";
    }

    @RequestMapping("/{path}/update/{id}")
    public String update(Model model, @PathVariable Long id, @PathVariable String path) throws Exception {
        Object dto = typeService.detail(id, path);
        common(model, path);
        model.addAttribute("dto", dto);
        return "backend/type/update";
    }

    @RequestMapping(value = "/{path}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String save(@Valid @ModelAttribute TypeDto dto,
                       BindingResult bindingResult,// ngay sau biến có @Valid, chứa kết quả sau khi valid
                       RedirectAttributes model, @PathVariable String path) throws Exception {
        if (bindingResult.hasErrors()) {
            return "backend/type/create";
        }
        // lưu user vào csdl
        ResponseDto responseDto = typeService.save(dto, path);
        model.addFlashAttribute("message", responseDto.getMessage());
        return "redirect:/backend/" + path + "/list";
    }

    @GetMapping("/{path}/delete/{id}")
    public String deleteUser(@PathVariable Long id, @PathVariable String path, RedirectAttributes model) {
        typeService.delete(id, path);
        model.addFlashAttribute("message", "Xóa thành công");
        return "redirect:/backend/" + path + "/list";
    }
}
