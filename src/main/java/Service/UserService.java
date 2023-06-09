package Service;

import DBConnection.DBConnection;
import Model.Product;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {
    public void insertUser(User usr){
        String query = "insert into userinfo(name,email,address,dob,wishlist,cart,mostvisited,password,number,maxspent,primecount) values(?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setString(1,usr.getName());
            ps.setString(2,usr.getEmail());
            ps.setString(3,usr.getAddress());
            ps.setString(4,usr.getDob());
            ps.setString(5,usr.getWishlist());
            ps.setString(6,usr.getCart());
            ps.setString(7,usr.getMostvisited());
            ps.setString(8,usr.getPassword());
            ps.setString(9,usr.getPhnumber());
            ps.setInt(10,usr.getMaxspent());
            ps.setInt(11,usr.getPrimecount());
            
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Insert User Error : "+e);
        }
    }
    public User getSingleUserBy(String email, String password){
        User us = null;
        String query = "select * from userinfo where email=? and password=?";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setString(1,email);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                us = new User();
                us.setId(rs.getInt("id"));
                us.setName(rs.getString("name"));
                us.setEmail(rs.getString("email"));
                us.setPassword(rs.getString("password"));
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return us;
    }
    public List<User> getCustomerList(){
        List<User> userlist = new ArrayList<>();
        String query = "select * from userinfo";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User pr = new User();
                pr.setId(rs.getInt("id"));
                pr.setName(rs.getString("name"));
                pr.setEmail(rs.getString("email"));
                pr.setAddress(rs.getString("address"));
                pr.setPhnumber(rs.getString("number"));
                userlist.add(pr);
            }
        }
         catch(SQLException e){
            e.printStackTrace();
             System.out.println("Error: "+e);
        }
        return userlist;
    }
    public List<User> getCustomerListByLength(int len){
        List<User> userlist = new ArrayList<>();
        int start = ((len-1)*10)+1;
        int stop = len*10;
        String query = "select * from userinfo";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ResultSet rs = ps.executeQuery();
            int count=1;
            while(rs.next()){
                User pr = new User();
                pr.setId(rs.getInt("id"));
                pr.setName(rs.getString("name"));
                pr.setEmail(rs.getString("email"));
                pr.setAddress(rs.getString("address"));
                pr.setPhnumber(rs.getString("number"));
                if(count>=start && count<=stop){
                    userlist.add(pr);
                }
                count++;
            }
        }
         catch(SQLException e){
            e.printStackTrace();
             System.out.println("Error: "+e);
        }
        return userlist;
    }
    public int getPagination(){
        int lastId=1;
        String query = "select*from userinfo";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lastId++;
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return lastId;
    }
    public void insertCart(int uid,int pid,String size){
        String query = "insert into cart(uid,pid,size) values(?,?,?)";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setInt(1,uid);
            ps.setInt(2,pid);
            ps.setString(3,size);
            ps.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Insert User Error : "+e);
        }
    }
    public int cartCount(int id){
        int cartCount = 0;
        String query = "select*from cart where id=?";
        PreparedStatement ps = new DBConnection().getStatement(query);
        try{
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                cartCount++;
            }
        }
         catch(SQLException e){
            e.printStackTrace();
        }
        return cartCount;
    }
}
