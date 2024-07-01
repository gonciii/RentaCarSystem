package dao;

import core.DB;
import entity.Brand;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandDao {
    private  final Connection con;

    public BrandDao() {
        this.con = DB.getInstance();
    }
    public ArrayList<Brand> findAll(){
        ArrayList <Brand> brandList = new ArrayList<>();
        String sql = "Select * From public.brand";
        try{
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()){
                brandList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return brandList;
    }
    public Brand match(ResultSet rs){
        Brand brand = new Brand();
        try {

            brand.setId(rs.getInt("id"));
            brand.setName(rs.getString("brand_name"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return brand;
    }
    public Brand getById(int id){
        Brand obj = null;
        String query = "SELECT * FROM public.brand where id = ? Order by id ASC";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }
    public boolean save(Brand brand){
        String query = "INSERT INTO public.brand (brand_name) VALUES (?)";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,brand.getName());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Brand brand){
        String query = "UPDATE public.brand SET brand_name= ? WHERE id= ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,brand.getName());
            pr.setInt(2,brand.getId());

            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query ="DELETE FROM public.brand WHERE id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}