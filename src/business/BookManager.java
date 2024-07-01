package business;

import dao.BookDao;
import dao.CarDao;
import entity.Book;
import entity.Brand;
import entity.Car;
import entity.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BookManager {
    private final BookDao bookDao;

    public BookManager(){
        this.bookDao = new BookDao();
    }

  public boolean save(Book book){
        return this.bookDao.save(book);
  }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> bookrowList = new ArrayList<>();
        for(Book book : this.findAll()){
            Object[] rowObject = new Object[size];
            int i=0;
            rowObject[i++] = book.getName();
            rowObject[i++] = book.getId();
            rowObject[i++] = book.getCar().getModel().getBrand().getName();
            rowObject[i++] = book.getCar().getModel().getName();
            rowObject[i++] = book.getCar().getPlate();
            rowObject[i++] = book.getCar().getColor();
            rowObject[i++] = book.getCar().getKm();
            rowObject[i++] = book.getCar().getModel().getYear();
            rowObject[i++] = book.getCar().getModel().getType();
            rowObject[i++] = book.getCar().getModel().getFuel();
            rowObject[i++] = book.getCar().getModel().getGear();
            bookrowList.add(rowObject);
        }
        return bookrowList;
    }


    public ArrayList<Book> findAll(){
        return this.bookDao.findAll();
    }


    public boolean deleteById(int id) throws SQLException {
        return this.bookDao.deleteById(id);
    }

    public ArrayList <Book> searchForBooking(String car_plate){
        String selectQuery = "SELECT * FROM public.car as c LEFT JOIN public.book as b on c.id= b.book_car_id";
        ArrayList<String> where = new ArrayList<>();


        if(car_plate != null){
            System.out.println(car_plate);
            where.add("c.car_plate = '" + car_plate+ "'");
        }
        String whereClause = "";
        if(!where.isEmpty()){
            whereClause = " WHERE " + String.join(" AND ", where);
        }
        selectQuery = selectQuery + whereClause;
        System.out.println(selectQuery);
        return this.bookDao.selectByQuery(selectQuery);

    }
    public ArrayList<Object[]> getForTable(int size,ArrayList<Book> books){
        ArrayList<Object[]> bookList = new ArrayList<>();
        for(Book obj : books){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getCar().getModel().getBrand().getName();
            rowObject[i++] = obj.getCar().getModel().getName();
            rowObject[i++] = obj.getCar().getPlate();

            bookList.add(rowObject);

        }
        return bookList;
    }
}
