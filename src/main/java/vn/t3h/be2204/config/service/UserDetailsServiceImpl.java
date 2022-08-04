package vn.t3h.be2204.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.t3h.be2204.entities.UserEntity;
import vn.t3h.be2204.repository.user.UserEntityRepository;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserEntityRepository userEntityRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Lấy thông tin user trong database từ email
        // trả về cho thư viện security

        // UserEntity -> load từ database
        // Security -> UserDetails
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        httpServletRequest.setAttribute("username", username);
        UserEntity userEntity = userEntityRepository.findFirstByEmail(username);
        if (userEntity == null) {
            httpServletRequest.setAttribute("type", "1");
            throw new UsernameNotFoundException("Không tìm thấy tài khoản");

        }
        return userEntity;
    }
}
