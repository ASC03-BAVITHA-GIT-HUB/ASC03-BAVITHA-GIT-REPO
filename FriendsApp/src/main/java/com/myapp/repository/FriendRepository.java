package com.myapp.repository;

import com.myapp.dal.DBConnection;
import com.myapp.model.Friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FriendRepository {

    //GET ALL FRIENDS
    public String getMaxFriendId() {
        String query = "SELECT MAX(id) FROM friends";
        try (Connection connection = DBConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet result = stmt.executeQuery(query)) {

            if (result.next()) return result.getString(1);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    // INSERT A NEW FRIEND
    public void insertFriend(Friend f) {
        String query = "INSERT INTO friends (id,name,email,phone,gender, age , dob , city)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, f.getId());
            preparedStatement.setString(2, f.getName());
            preparedStatement.setString(3, f.getEmail());
            preparedStatement.setString(4, f.getPhone());
            preparedStatement.setString(5, f.getGender());
            preparedStatement.setInt(6, f.getAge());
            preparedStatement.setDate(7, f.getDob());
            preparedStatement.setString(8, f.getCity());

            preparedStatement.executeUpdate();
            System.out.println("Friend inserted successfully");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    // READ: GET ALL FRIENDS

    public  List<Friend> getAllFriends() {
        List<Friend> list = new ArrayList<>();
        String query = "SELECT * FROM friends";
        try (Connection connection = DBConnection.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(query)) {

            while(result.next()) {
                Friend f = new Friend (
                        result.getString("id"),
                        result.getString("name"),
                        result.getString("email"),
                        result.getString("phone"),
                        result.getString("gender"),
                        result.getInt("age"),
                        result.getDate("dob"),
                        result.getString("city")
                );
                list.add(f);
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return list;
    }

    //UPDATE

    public void updateFriend(String id, String newName, String newCity) {
        String query = "UPDATE friends SET name = ? , city = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, newName);
                preparedStatement.setString(2,newCity);
                preparedStatement.setString(3,id);

                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Friend table updated successfully");
                } else {
                    System.out.println("Friend not found");
                }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    //DELETE: REMOVE A FRIEND BY ID

    public void deleteFriend(String id) {
        String query = "DELETE FROM friends WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,id);
            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("Friend deleted successfully");
            } else {
                System.out.println("Friend not found");
            }
        }
        catch( Exception exception) {
            exception.printStackTrace();
        }
    }
}
