package com.springsecurity.jpa;

import com.springsecurity.jpa.Repositories.UserRepository;
import com.springsecurity.jpa.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * this class implements UserDetailservice interface used to return
 * userdetails instance with the given username
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

         Optional<Users> user=userRepository.findByUserName(s);
         user.orElseThrow(() -> new UsernameNotFoundException("Not found"+s));
         return user.map(MyUserDetails::new).get();

    }
}
