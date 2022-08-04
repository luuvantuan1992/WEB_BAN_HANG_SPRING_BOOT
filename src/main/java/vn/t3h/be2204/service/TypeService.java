package vn.t3h.be2204.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.t3h.be2204.dto.ResponseDto;
import vn.t3h.be2204.dto.TypeDto;
import vn.t3h.be2204.entities.BrandEntity;
import vn.t3h.be2204.entities.CategoryEntity;
import vn.t3h.be2204.repository.BrandEntityRepository;
import vn.t3h.be2204.repository.CategoryEntityRepository;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    CategoryEntityRepository categoryEntityRepository;

    @Autowired
    BrandEntityRepository brandEntityRepository;

    public static String BRAND_TYPE = "brand";
    public static String CATEGORY_TYPE = "category";

    public ResponseDto save(TypeDto o, String type) {
        if (BRAND_TYPE.equals(type))
            brandEntityRepository.save(o.convertToBrand());
        else
            categoryEntityRepository.save(o.convertToCategory());
        return new ResponseDto(1, o.getId() == null ? "Tạo mới thành công" : "Cập nhật thành công");
    }

    public TypeDto detail(Long id, String type) {
        TypeDto typeDto = new TypeDto();
        if (BRAND_TYPE.equals(type)){
            BeanUtils.copyProperties(brandEntityRepository.findById(id).get(), typeDto);
        }
        else {
            BeanUtils.copyProperties(categoryEntityRepository.findById(id).get(), typeDto);
        }

        return typeDto;
    }

    public List<?> findAll(String type) {
        if (BRAND_TYPE.equals(type))
            return brandEntityRepository.findAll();
        else
            return categoryEntityRepository.findAll();
    }

    public void delete(Long id, String type) {
        if (BRAND_TYPE.equals(type))
            brandEntityRepository.deleteById(id);
        else
            categoryEntityRepository.deleteById(id);
    }


}
