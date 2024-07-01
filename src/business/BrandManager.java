package business;

import core.Helper;
import dao.BrandDao;
import dao.UserDao;
import entity.Brand;
import entity.User;

import java.util.ArrayList;

public class BrandManager {
    private final BrandDao brandDao;

    public BrandManager() {
        this.brandDao = new BrandDao();
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> brandRowList = new ArrayList<>();
        for(Brand brand : this.findAll()){
            Object[] rowObject = new Object[size];
            int i=0;
            rowObject[i++] = brand.getId();
            rowObject[i++]=  brand.getName();
            brandRowList.add(rowObject);
        }
        return brandRowList;
    }
    public ArrayList<Brand> findAll(){
        return this.brandDao.findAll();
    }
    public boolean save (Brand brand){
        if(brand.getId() !=0){
            Helper.showMessage("error");
        }
        return this.brandDao.save(brand);
    }
    public Brand getById(int id){
        return this.brandDao.getById(id);
    }
    public boolean update(Brand b){
        if(this.getById(b.getId())==null){
            Helper.showMessage("notfound");
        }
        return this.brandDao.update(b);
    }
    public boolean delete(int id){
        if(this.getById(id)==null){
            Helper.showMessage(id+"ID is not found.");
            return false;
        }
        return this.brandDao.delete(id);
    }
}
