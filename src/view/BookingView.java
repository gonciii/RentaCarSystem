package view;

import business.BookManager;
import core.Helper;
import entity.Book;
import entity.Car;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookingView extends Layout {
    private JLabel lbl_car_info;
    private JLabel lbl_;
    private JTextField fld_cust_name;
    private JLabel fld_booking_idno;
    private JLabel lbl_phone_number;
    private JTextField fld_book_idno;
    private JTextField fld_book_mpno;
    private JLabel lbl_mail;
    private JTextField fld_mail;
    private JLabel lbl_book_strt_date;
    private JTextField fld_start_date;
    private JLabel lbl_fnsh_date;
    private JTextField fld_fnsh_date;
    private JLabel lbl_price;
    private JTextField fld_price;
    private JButton btn_book_res;
    private JPanel container;
    private Car car;
    private BookManager bookManager;
    private JTable table_reservations;
    private DefaultTableModel tableModel;


    public BookingView(Car selectedCar, String strt_date, String fnsh_date){
        tableModel = new DefaultTableModel();
        table_reservations = new JTable(tableModel);
        this.car = selectedCar;
        this.bookManager = new BookManager();
        this.add(container);
        guiInitialize(400,500);
        lbl_car_info.setText("Car: "+ this.car.getPlate() + " / " +
                this.car.getModel().getBrand().getName() + " / " +
                this.car.getModel().getName());
        this.fld_start_date.setText(strt_date);
        this.fld_fnsh_date.setText(fnsh_date);

        btn_book_res.addActionListener(e ->{
            JTextField [] checkFieldList = {
                    this.fld_cust_name,
                    this.fld_mail,
                    this.fld_price,
                    this.fld_book_mpno,
                    this.fld_book_idno,
                    this.fld_start_date,
                    this.fld_fnsh_date
            };
            if(Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMessage("fill");
            }
            else{
                Book book = new Book();
                book.setbCase("done");
                book.setCar_id(this.car.getId());
                book.setName(this.fld_cust_name.getText());
                book.setStrt_date(LocalDate.parse(fld_start_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                book.setFnsh_date(LocalDate.parse(fld_fnsh_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                book.setIdno(this.fld_book_idno.getText());
                book.setMpno(this.fld_book_mpno.getText());
                book.setMail(this.fld_mail.getText());
                book.setPrc(Integer.parseInt(this.fld_price.getText()));
                if(this.bookManager.save(book)){
                    Helper.showMessage("done");
                    dispose();


                }
                else{
                    Helper.showMessage("error");
                }
            }
         });

    }
}
