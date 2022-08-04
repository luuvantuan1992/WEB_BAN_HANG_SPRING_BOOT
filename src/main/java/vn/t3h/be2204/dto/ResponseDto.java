package vn.t3h.be2204.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    int code;// 1: thành công, 2: thất bại
    String message;
    Object data;

    public ResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
