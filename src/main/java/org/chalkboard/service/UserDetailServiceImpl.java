package org.chalkboard.service;

import org.chalkboard.persistence.entity.UserEntity;
import org.chalkboard.persistence.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {


  @Autowired
  private UserEntityRepository userEntityRepository;


  @Override
  public UserDetails loadUserByUsername(String username) {
    UserEntity userEntity = userEntityRepository
            .findUserEntityByUseremail(username)
            .orElseThrow(() ->
                    new UsernameNotFoundException("The user you enter does not exist")
            );

    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
    return null;
  }
}
