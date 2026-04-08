package com.mohammed.demo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// UserDetailsService - زي IUserStore في .NET
// Spring Security بيستخدمه عشان يجيب بيانات الـ User
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // دلوقتي بنستخدم User وهمي للتجربة
        // في الـ Real World هتجيب الـ User من الـ Database
        if (username.equals("mohammed")) {
            return User.builder()
                    .username("mohammed")
                    .password("{noop}1234") // {noop} معناها مش محتاج Encryption دلوقتي
                    .roles("ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found: " + username);
    }
}