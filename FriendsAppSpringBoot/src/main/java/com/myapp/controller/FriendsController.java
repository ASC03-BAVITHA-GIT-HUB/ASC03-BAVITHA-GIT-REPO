package com.myapp.controller;

import com.myapp.entity.Friends;
import com.myapp.service.FriendsServiceSpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsServiceSpring friendsService;

    @PostMapping("/create")
    public Friends createFriend(@RequestBody Friends friend) {
        return friendsService.saveFriend(friend);
    }

    @GetMapping("/all")
    public List<Friends> getAllFriends() {
        return friendsService.getAllFriends();

    }

    @GetMapping("/{id}")
    public Friends getFriend(@PathVariable Long id) {
        return  friendsService.getFriendById(id);

    }

    @DeleteMapping("/{id}")
    public String deleteFriend(@PathVariable Long id) {
        friendsService.deleteById(id);
        return "Friend deleted successfully";
    }

}
