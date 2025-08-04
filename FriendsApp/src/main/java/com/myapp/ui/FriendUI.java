package com.myapp.ui;

import com.myapp.model.Friend;
import com.myapp.service.FriendService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class FriendUI {
    private FriendService service = new FriendService();
    private Scanner sc = new Scanner(System.in);

    public void launch() {
        int choice;
        do {
            System.out.println("1. Add Friend");
            System.out.println("2. View all Friends");
            System.out.println("3. Update Friend");
            System.out.println("4. Delete Friend");
            System.out.println("5. Exit");
            System.out.println("Enter you choice: ");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    addFriendUI();
                    break;
                case 2:
                    viewFriendUI();
                    break;
                case 3:
                    updateFriendUI();
                    break;
                case 4:
                    deleteFriendUI();
                    break;
                default:
                    System.out.println("Invalid choice, try again.");

            }
        }
        while (choice != 5);
    }

    private void addFriendUI() {
        try {
            System.out.println("Name: ");
            String name = sc.nextLine();
            System.out.println("Email: ");
            String email = sc.nextLine();
            System.out.println("Phone: ");
            String phone = sc.nextLine();
            System.out.println("Gender: ");
            String gender = sc.nextLine();
            System.out.println("Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.println("Dob: ");
            Date dob =  Date.valueOf(sc.nextLine());
            System.out.println("City: ");
            String city = sc.nextLine();

            service.addFriend(name, email, phone, gender, age, dob, city);
        } catch (Exception exception) {
            System.out.println("Invalid input, please try again");
            exception.printStackTrace();
        }
    }

    private void viewFriendUI() {
        List<Friend> friends = service.getAllFriends();
        if (friends.isEmpty()) {
            System.out.println("No friends found");
        } else {
            System.out.println("\nFriend List");
            for (Friend f : friends) {
                System.out.println("ID: " + f.getId() + ", Name: " + f.getName() + ", Phone: " + f.getPhone() + ", Gender: " + f.getGender() + ", Age: " + f.getAge() + ", DOB: " + f.getDob() + ", City: " + f.getCity());
            }
        }
    }

    private void updateFriendUI() {
        System.out.println("Enter Friend ID to update: ");
        String id = sc.nextLine();
        System.out.println("New Name: ");
        String newName = sc.nextLine();
        System.out.println("New City: ");
        String newCity = sc.nextLine();

        service.updateFriend(id, newName, newCity);

    }

    private void deleteFriendUI() {
        System.out.println("Enter Friend ID to delete: ");
        String id = sc.nextLine();
        service.deleteFriend(id);
    }
}
