package view;
import business.BookManager;
import business.BrandManager;
import business.CarManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.*;
import java.awt.Color;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JPanel pnl_brand;
    private JScrollPane scrl_brand;
    private JTable table_brand;
    private JPanel pnl_model;
    private JScrollPane scrl_model;
    private JTable table_model;
    private JComboBox cmb_brand;
    private JComboBox cmb_type;
    private JComboBox cmb_fuel;
    private JComboBox cmb_gear;
    private JLabel lbl_brand;
    private JLabel lbl_type;
    private JLabel lbl_fuel;
    private JLabel lbl_gear;
    private JButton btn_search;
    private JButton btn_clear;
    private JPanel pnl_car;
    private JScrollPane scrl_car;
    private JTable table_car;
    private JPanel pnl_booking;
    private JScrollPane scrl_booking;
    private JComboBox cmb_booking_type;
    private JComboBox cmb_booking_fuel;
    private JComboBox cmb_booking_gear;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_finish_date;
    private JTable tbl_booking;
    private JLabel lbl_strt_date;
    private JLabel lbl_end_date;
    private JLabel lbl_gear_type;
    private JLabel lbl_fuel_type;
    private JLabel fld_car_type;
    private JFormattedTextField frmt_str_date;
    private JFormattedTextField frmt_end_date;
    private JComboBox cmb_gear_type;
    private JComboBox cmb_fuel_type;
    private JComboBox cmb_car_type;
    private JButton btn_search_book;
    private JButton btn_booking_clear;
    private JPanel pnl_book;
    private JScrollPane scrl_book;
    private JTable table_reservations;
    private JComboBox cmb_search_plate;
    private JButton btn_res;
    private JTextField fld_plate_res;
    private JButton btn_clearsearch;
    private JButton btn_logout;
    private JLabel lbl_plate;
    private JButton btn_search_rent;
    private User user;
    private DefaultTableModel tmdl_brand = new DefaultTableModel();
    private DefaultTableModel tmdl_car = new DefaultTableModel();
    private DefaultTableModel tmdl_model = new DefaultTableModel();
    private DefaultTableModel tmdl_booking = new DefaultTableModel();
    private DefaultTableModel tmdl_reservations = new DefaultTableModel();
    private CarManager carManager;
    private BrandManager brandManager;
    private ModelManager modelManager;
    private BookManager bookManager;
    private JPopupMenu brandMenu ;
    private JPopupMenu modelMenu ;
    private JPopupMenu bookingMenu ;
    private JPopupMenu reservationMenu ;
    private JPopupMenu carMenu;
    private Object[] col_model;
    private Object[] col_car;
    private Object[] col_res_list;
    public AdminView(User user){
        this.brandManager = new BrandManager();
        this.modelManager = new ModelManager();
        this.bookManager= new BookManager();
        this.carManager = new CarManager();
        this.add(container);
        this.guiInitialize(800,300);
        this.user = user;
        if(this.user == null){
            dispose();
        }
        this.lbl_welcome.setText("Hoşgeldiniz: " + this.user.getUsername());
        loadComponent();
        loadBrandTable();
        loadBrandComponent();

        loadModelTable(null);
        loadModelComponent();
        loadModelFilter();

        loadCarTable(null);
        loadCarComponent();

        loadBookingTable(null);
        loadBookingComponent();
        loadBookingFilter();

        loadReservationTable(null);
        loadReservationComp();

        // RENK
        container.setBackground(Color.RED);


        btn_search_book.addActionListener(e-> {
            System.out.println(frmt_str_date.getText());
            System.out.println(frmt_end_date.getText());
                ArrayList<Car> carList = this.carManager.searchForBooking(
                        frmt_str_date.getText(),
                        frmt_end_date.getText(),
                        (Model.Type) cmb_car_type.getSelectedItem(),
                        (Model.Gear) cmb_gear.getSelectedItem(),
                        (Model.Fuel) cmb_fuel.getSelectedItem()
                );
                ArrayList<Object[]> bookingRow = this.carManager.getForTable(this.col_car.length,carList);

                loadBookingTable(bookingRow);

                });
        btn_clear.addActionListener(e->{
            loadBookingFilter();
        });



        this.table_brand.setComponentPopupMenu(brandMenu);
        this.table_model.setComponentPopupMenu(modelMenu);
        this.table_car.setComponentPopupMenu(carMenu);
        this.table_reservations.setComponentPopupMenu(reservationMenu);


        btn_booking_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadBookingFilter();
            }
         });
        btn_clearsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadReservationFilter();
            }
        });

        btn_res.addActionListener(e-> {
            ArrayList<Book> reservationList = this.bookManager.searchForBooking(
                    fld_plate_res.getText()
            );
            if(reservationList.isEmpty()){
                loadReservationTable(this.bookManager.getForTable(this.col_res_list.length,this.bookManager.findAll()));
            }
            else{
                ArrayList<Object[]> resRow = this.bookManager.getForTable(this.col_res_list.length,reservationList);

                loadReservationTable(resRow);
            }

        });

    }


    private void loadComponent() {
        this.btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
            }
        });
    }


    private void loadReservationTable(ArrayList<Object[]> bookList) {
        col_res_list = new Object[]{"ID","Name", "Brand", "Model", "Plate"};
        if(bookList == null){
            bookList = this.bookManager.getForTable(col_res_list.length,this.bookManager.findAll());
        }
        createTable(this.tmdl_reservations, table_reservations,col_res_list,bookList);


    }


    private void loadBookingTable(ArrayList<Object[]> carList) {
        this.col_car = new Object[]{"ID", "Brand", "Model", "Plate", "Color", "Km", "Year", "Type", "Fuel", "Gear"};
        createTable(this.tmdl_booking,tbl_booking,col_car,carList);

    }

    private void loadBookingFilter(){
        this.cmb_car_type.setModel(new DefaultComboBoxModel<>(Model.Type.values()));
        this.cmb_car_type.setSelectedItem(null);
        this.cmb_gear_type.setModel(new DefaultComboBoxModel<>(Model.Gear.values()));
        this.cmb_gear_type.setSelectedItem(null);
        this.cmb_fuel_type.setModel(new DefaultComboBoxModel<>(Model.Fuel.values()));
        this.cmb_fuel_type.setSelectedItem(null);

    }

    private void loadReservationFilter(){
        this.fld_plate_res.setText(null);
    }

    public void loadBookingComponent(){
        tableRowSelect(this.tbl_booking,bookingMenu);
        this.bookingMenu = new JPopupMenu();
        this.bookingMenu.add("Rent").addActionListener(e->{
            int selectCarId = this.getTableSelectedRow(this.tbl_booking,0);

            BookingView bookingView = new BookingView(this.carManager.getById(selectCarId),
                    this.frmt_str_date.getText(),this.frmt_end_date.getText());
            bookingView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBookingTable(null);
                    loadBookingFilter();
                    loadReservationTable(null);
                }
            });
        });
        this.tbl_booking.setComponentPopupMenu(bookingMenu);

        btn_search_book.addActionListener(e-> {
            ArrayList<Car> carList = this.carManager.searchForBooking(
                    frmt_str_date.getText(),
                    frmt_end_date.getText(),
                    (Model.Type) cmb_car_type.getSelectedItem(),
                    (Model.Gear) cmb_gear.getSelectedItem(),
                    (Model.Fuel) cmb_fuel.getSelectedItem()
            );
            ArrayList<Object[]> bookingRow = this.carManager.getForTable(this.col_car.length,carList);

            loadBookingTable(bookingRow);

        });
        btn_clear.addActionListener(e->{
            loadBookingFilter();
        });


    }

    public void loadCarComponent() {
        tableRowSelect(table_car,carMenu);
        this.carMenu = new JPopupMenu();
        this.carMenu.add("Create").addActionListener(e->{
            CarView carView = new CarView(new Car());
            carView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCarTable(null);
                    loadModelTable(null);
                    loadReservationTable(null);
                }

            });
        });
        this.carMenu.add("Update").addActionListener(e->{
            int selectedCarId = this.getTableSelectedRow(table_car,0);
            CarView carView = new CarView(this.carManager.getById(selectedCarId));
            carView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadCarTable(null);
                    loadModelTable(null);
                    loadReservationTable(null);
                }
            });
        });
        this.carMenu.add("Delete").addActionListener(e->{
            if(Helper.confirm("sure")){
                int selectedCarId = this.getTableSelectedRow(table_car,0);
                if(this.carManager.delete(selectedCarId)){
                    Helper.showMessage("done");
                    loadCarTable(null);
                }else{
                    Helper.showMessage("error");
                }

            }

        });
        this.table_car.setComponentPopupMenu(carMenu);
    }

    public void loadModelFilter() {
        this.cmb_type.setModel(new DefaultComboBoxModel<>(Model.Type.values()));
        this.cmb_type.setSelectedItem(null);
        this.cmb_gear.setModel(new DefaultComboBoxModel<>(Model.Gear.values()));
        this.cmb_gear.setSelectedItem(null);
        this.cmb_fuel.setModel(new DefaultComboBoxModel<>(Model.Fuel.values()));
        this.cmb_fuel.setSelectedItem(null);
        loadModelFilterBrand();

    }
    public void loadModelFilterBrand(){
        this.cmb_brand.removeAllItems();
        for(Brand obj : brandManager.findAll()){
            this.cmb_brand.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_brand.setSelectedItem(null);
    }
    public void loadModelTable(ArrayList<Object[]> modelList){
        this.col_model = new Object[]{"Model ID","Brand","Model Name","Type","Year","Fuel","Gear"};
        if(modelList == null){
            modelList = this.modelManager.getForTable(col_model.length,this.modelManager.findAll());
        }
        createTable(this.tmdl_model,this.table_model,col_model,modelList);
    }
    public void loadBrandTable(){

        Object [] col_brand = {"Brand ID","Brand Name"};
        ArrayList<Object[]> brandList = this.brandManager.getForTable(col_brand.length);
        this.tmdl_brand.setColumnIdentifiers(col_brand);
        this.createTable(this.tmdl_brand,this.table_brand,col_brand,brandList);
    }

    public void loadBrandComponent() {

        tableRowSelect(table_brand,brandMenu);

        this.brandMenu =new JPopupMenu();
        this.brandMenu.add("Create").addActionListener(e->{
            BrandView brandView = new BrandView(null);
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBrandTable();
                    loadModelTable(null);
                    loadModelFilterBrand();
                    loadCarTable(null);
                    loadBookingTable(null);
                    loadReservationTable(null);
                }
            });
        });
        this.brandMenu.add("Update").addActionListener(e->{
            int selectedBrandId = this.getTableSelectedRow(table_brand,0);
            BrandView brandView = new BrandView(this.brandManager.getById(selectedBrandId));
            brandView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadBrandTable();
                    loadModelTable(null);
                    loadModelFilterBrand();
                    loadCarTable(null);
                    loadReservationTable(null);
                }
            });
        });
        this.brandMenu.add("Delete").addActionListener(e->{
            if(Helper.confirm("sure")){
                int selectedBrandId = this.getTableSelectedRow(table_brand,0);
                System.out.println(selectedBrandId);
                if(this.brandManager.delete(selectedBrandId)){
                    Helper.showMessage("done");
                    loadBrandTable();
                    loadModelTable(null);
                    loadModelFilterBrand();
                    loadCarTable(null);
                }else{
                    Helper.showMessage("error");
                }

            }

        });
    }
    public void loadCarTable(ArrayList<Object[]> carList){
        col_car = new Object[]{"ID","Brand","Model","Plate","Color","Km","Year","Type","Fuel","Gear"};
        if(carList == null){
            carList = this.carManager.getForTable(col_car.length,this.carManager.findAll());
        }
        createTable(this.tmdl_car,this.table_car,col_car,carList);
    }

   public void loadModelComponent() {
       tableRowSelect(table_model,modelMenu);
       this.modelMenu =new JPopupMenu();
       this.modelMenu.add("Create").addActionListener(e->{
           ModelView modelView = new ModelView(new Model());
           modelView.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosed(WindowEvent e) {
                   loadModelTable(null);
               }
           });

       });
       this.modelMenu.add("Update").addActionListener(e->{
          int selectedModelId = this.getTableSelectedRow(table_model,0);
           ModelView modelView = new ModelView(this.modelManager.getById(selectedModelId));
           modelView.addWindowListener(new WindowAdapter() {
               @Override
               public void windowClosed(WindowEvent e) {
                   loadModelTable(null);
                   loadCarTable(null);
                   loadReservationTable(null);
               }
           });
       });
       this.modelMenu.add("Delete").addActionListener(e->{




       });

       this.btn_search.addActionListener(e->{
            ComboItem selectedBrand = (ComboItem) this.cmb_brand.getSelectedItem();
            int brandId =0;
            if(selectedBrand !=null){
                brandId = selectedBrand.getKey();
            }
            ArrayList<Model> modelListBySearch = this.modelManager.searchForTable(
                    brandId,
                    (Model.Fuel)cmb_fuel.getSelectedItem(),
                    (Model.Gear)cmb_gear.getSelectedItem(),
                    (Model.Type)cmb_type.getSelectedItem()
            );
            ArrayList<Object[]> modelRowListBySearch = this.modelManager.getForTable(this.col_model.length,modelListBySearch);
            loadModelTable(modelRowListBySearch);
       });
       this.btn_clear.addActionListener(e->{
           this.cmb_type.setSelectedItem(null);
           this.cmb_gear.setSelectedItem(null);
           this.cmb_fuel.setSelectedItem(null);
           this.cmb_type.setSelectedItem(null);
           this.cmb_brand.setSelectedItem(null);
           loadModelTable(null);
       });
   }


    private void createUIComponents() throws ParseException {
        this.frmt_str_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.frmt_str_date.setText("16/10/2024");
        this.frmt_end_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.frmt_end_date.setText("23/10/2024");

    }

    private void loadReservationComp(){
        tableRowSelect(table_reservations,reservationMenu);
        this.reservationMenu =new JPopupMenu();
        this.reservationMenu.add("Delete").addActionListener(e->{
            if(Helper.confirm("sure")){
                int selectedBookId = this.getTableSelectedRow(table_reservations,0);
                try {
                    if(this.bookManager.deleteById(selectedBookId)){
                        Helper.showMessage("done");
                        loadReservationTable(null);
                        loadModelTable(null);
                        loadCarTable(null);
                    }else{
                        Helper.showMessage("error");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }

        });
    }



}
