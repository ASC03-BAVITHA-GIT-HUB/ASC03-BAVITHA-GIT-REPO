package com.myapp.service;

import com.myapp.entity.Friends;
import com.myapp.repository.FriendsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsServiceSpring {

    @Autowired
    private FriendsRepository friendsRepository;

    @Override
    public Friends saveFriend(Friends friend) {
        return friendsRepository.save(friend);
    }

    @Override
    public List<Friends> getAllFriends() {
        return friendsRepository.findAll();
    }

    @Override
    public Friends getFriendById(Long Id) {
        return friendsRepository.findById(Id).orElse(null);
    }

    @Override
    public void deleteById(Long Id) {
        friendsRepository.deleteById(Id);

    }
}
