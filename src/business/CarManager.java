package business;

import core.Helper;
import dao.BookDao;
import dao.CarDao;
import entity.Book;
import entity.Car;
import entity.Model;
import view.AdminView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class CarManager {
    private final CarDao carDao;
    private final BookDao bookDao;
    public CarManager(){
        this.carDao = new CarDao();
        this.bookDao = new BookDao();
    }
    public Car getById (int id){
        return this.carDao.getById(id);
    }
    public ArrayList<Car> findAll(){
        return this.carDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Car> cars){
        ArrayList<Object[]> carList = new ArrayList<>();
        for(Car obj : cars){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getModel().getBrand().getName();
            rowObject[i++] = obj.getModel().getName();
            rowObject[i++] = obj.getPlate();
            rowObject[i++] = obj.getColor();
            rowObject[i++] = obj.getKm();
            rowObject[i++] = obj.getModel().getYear();
            rowObject[i++] = obj.getModel().getType();
            rowObject[i++] = obj.getModel().getFuel();
            rowObject[i++] = obj.getModel().getGear();
            carList.add(rowObject);

        }
        return carList;
    }

    public boolean save(Car car){
        if(this.getById(car.getId())!=null){
            Helper.showMessage("error");
            return false;
        }
        return this.carDao.save(car);
    }
    public boolean update(Car car){
        if(this.getById(car.getId())==null){
            Helper.showMessage(car.getId() + "ID not found.");
            return false;
        }
        return this.carDao.update(car);
    }
    public boolean delete(int id){
        if(this.getById(id)==null){
            Helper.showMessage(id+"ID Not Found.");
            return false;
        }
        return this.carDao.delete(id);
    }
    public ArrayList <Car> searchForBooking(String str_date, String fnsh_date, Model.Type type, Model.Gear gear,Model.Fuel fuel){
        String selectQuery = "SELECT * FROM public.car as c LEFT JOIN public.car_model as m";
        ArrayList<String> where = new ArrayList<>();
        ArrayList <String> joinWhere = new ArrayList<>();
        ArrayList<String> bookWhere = new ArrayList<>();
        joinWhere.add("c.car_model_id= m.id");
        str_date = LocalDate.parse(str_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        fnsh_date = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();


        if(fuel != null){
            where.add("m.model_fuel = '" + fuel.toString() + "'");
        }
        if(gear != null){
            where.add("m.model_gear = '" + gear.toString() + "'");
        }
        if(type != null){
            where.add("m.model_type = '" + type.toString() + "'");
        }

        String whereStr = String.join(" AND " , where);
        String joinStr = String.join(" AND " , joinWhere);

        if(joinStr.length()>0){
            selectQuery += " ON " + joinStr;
        }
        if(whereStr.length()>0){
            selectQuery += " WHERE " + whereStr;
        }

        ArrayList<Car> searchedCarList = this.carDao.selectByQuery(selectQuery);

        bookWhere.add("('" + str_date + "' between book_strt_date And book_fnsh_date)");
        bookWhere.add("('" + fnsh_date + "' between book_strt_date And book_fnsh_date)");
        bookWhere.add("(book_strt_date BETWEEN '" + str_date + "' AND '" + fnsh_date + "')");
        bookWhere.add("(book_fnsh_date BETWEEN '" + str_date + "' AND '" + fnsh_date + "')");
        String bookOrWhereStr = String.join(" OR ", bookWhere);
        String bookQuery = "SELECT * FROM public.book where " + bookOrWhereStr;

        ArrayList<Book> bookList = this.bookDao.selectByQuery(bookQuery);

        ArrayList<Integer> busyCarId = new ArrayList<>();
        for(Book book :bookList){
            System.out.println(book.getCar_id());
            busyCarId.add(book.getCar_id());
        }

        for(int i :busyCarId){
            System.out.println(i);
        }
        searchedCarList.removeIf(car->busyCarId.contains(car.getId()));

        return searchedCarList;

    }

}
