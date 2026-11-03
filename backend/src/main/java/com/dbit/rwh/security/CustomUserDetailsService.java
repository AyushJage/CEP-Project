package com.dbit.rwh.security;

import com.dbit.rwh.entity.User;
import com.dbit.rwh.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with phone: " + phone));

        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        return new org.springframework.security.core.userdetails.User(
                user.getPhone(),
                user.getPasswordHash(),
                Collections.singletonList(authority)
        );
    }

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
