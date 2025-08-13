package com.myapp.repository;

import com.myapp.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendsRepository extends JpaRepository<Friends,Long> {

}
