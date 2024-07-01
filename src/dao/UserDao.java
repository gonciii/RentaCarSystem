package dao;

import core.DB;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;

    public UserDao() {
        this.con = DB.getInstance();
    }
    public ArrayList<User> findAll(){
        ArrayList <User> userList = new ArrayList<>();
        String sql = "Select * From public.user";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }
    public User findByLogin(String username, String password){
        User user = null;
        String query = "SELECT * FROM public.USER WHERE user_name = ? And user_password = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet resultSet = pr.executeQuery();
            if(resultSet.next()){
                user = this.match(resultSet);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User match(ResultSet rs){
        User user = new User();
        try {
            user.setUsername(rs.getString("user_name"));
            user.setPassword(rs.getString("user_password"));
            user.setRole(rs.getString("user_role"));
            user.setId(rs.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}
