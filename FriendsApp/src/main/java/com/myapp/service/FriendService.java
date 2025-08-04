package com.myapp.service;

import com.myapp.model.Friend;
import com.myapp.repository.FriendRepository;

import java.sql.Date;
import java.util.List;

public class FriendService {
    private FriendRepository repo = new FriendRepository();

    public void addFriend(String name, String email, String phone, String gender, int age, Date dob, String city) {
        String maxId = repo.getMaxFriendId();
        String newId = generateNextId(maxId);

        Friend f = new Friend(newId, name, email, phone,gender, age, dob, city);
        repo.insertFriend(f);
    }

    public List<Friend> getAllFriends() {
        return repo.getAllFriends();
    }

    public void updateFriend(String id, String newName, String newCity) {
        repo.updateFriend(id, newName, newCity);
    }

    public String generateNextId(String maxId) {
        if (maxId == null) {
            return "F001";
        }

        int num = Integer.parseInt(maxId.substring(1)) + 1;
        return String.format("F%03d", num);
    }

    public void deleteFriend(String id) {
        repo.deleteFriend(id);
    }
}
