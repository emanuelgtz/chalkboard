package org.chalkboard.persistence.repository;

import org.apache.catalina.User;
import org.chalkboard.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {


  Optional<UserEntity> findUserEntityByUseremail(String useremail);

}
