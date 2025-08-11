package com.myapp.service;

import com.myapp.entity.Friends;

import java.util.List;

public interface FriendsServiceSpring {
    Friends saveFriend(Friends friend);
    List<Friends> getAllFriends();
    Friends getFriendById(Long Id);
    void deleteById(Long Id);
}
