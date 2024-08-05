package org.chalkboard.service;

import org.chalkboard.persistence.entity.UserEntity;
import org.chalkboard.persistence.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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
            .findUserEntityByemail(username)
            .orElseThrow(() ->

                    new UsernameNotFoundException("The user requested does not exist")
            );

    List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

    userEntity.getRoles().forEach(role -> authorityList
            .add(
                    new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))
            ));

    userEntity.getRoles().stream()
            .flatMap(role -> role.getPermissionList().stream())
            .forEach(permission -> authorityList.add(
                    new SimpleGrantedAuthority(permission.getPermission())
            ));


    return new User(
            userEntity.getEmail(),
            userEntity.getPassword(),
            userEntity.isEnabled(),
            userEntity.isAccountNoExpired(),
            userEntity.isCredentialNoExpired(),
            userEntity.isAccountNoLocked(),
            authorityList
    );

  }

}
