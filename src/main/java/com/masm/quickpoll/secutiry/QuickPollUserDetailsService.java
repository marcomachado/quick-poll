package com.masm.quickpoll.secutiry;

import com.masm.quickpoll.model.User;
import com.masm.quickpoll.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class QuickPollUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public QuickPollUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);

        if(!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("User with the " +
                    "username %s doesn't exist", username));
        }

        User currentUser = user.get();


        List<GrantedAuthority> authorities = new ArrayList<>();


        if(currentUser.isAdmin()) {
            authorities = AuthorityUtils.createAuthorityList("ROLE_ADMIN");
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(currentUser.getUsername());
        builder.password(currentUser.getPassword());
        builder.authorities(authorities);

        return builder.build();
    }


}
