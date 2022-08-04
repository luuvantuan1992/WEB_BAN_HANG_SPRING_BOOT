package vn.t3h.be2204.utils;

import vn.t3h.be2204.controller.backend.UserController;
import vn.t3h.be2204.dto.ProductDto;
import vn.t3h.be2204.dto.UserDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbUtil {

    public static List<ProductDto> danhSachSanPham(){
        List<ProductDto> userDtoList = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(UserController.URL, UserController.USER, UserController.PASS);

            String queryString = "select * from product";
            PreparedStatement pst = connection.prepareStatement(queryString);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                Long price = resultSet.getLong("PRICE");
                String name = resultSet.getString("NAME");
                String description = resultSet.getString("DESCRIPTION");
                String image = resultSet.getString("IMAGE");
                Long brandId = resultSet.getLong("BRAND_ID");
                Long categoryId = resultSet.getLong("CATEGORY_ID");
                userDtoList.add(new ProductDto(id, price, name, description, image,brandId,categoryId));
            }
            connection.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  userDtoList;
    }
}
