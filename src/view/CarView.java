package view;

import business.CarManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.Car;
import entity.Model;

import javax.swing.*;
import javax.xml.catalog.Catalog;

public class CarView extends Layout{
    private JPanel container;
    private JLabel lbl_createcar;
    private JLabel lbl_model;
    private JComboBox cmb_model;
    private JLabel lbl_color;
    private JComboBox cmb_color;
    private JLabel lbl_km;
    private JTextField fld_km;
    private JLabel lbl_plate;
    private JTextField fld_plate;
    private JButton btn_save_car;
    private Car car;
    private CarManager carManager;
    private ModelManager modelManager;

    public JPanel getContainer() {
        return container;
    }

    public void setContainer(JPanel container) {
        this.container = container;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public void setModelManager(ModelManager modelManager) {
        this.modelManager = modelManager;
    }

    public CarManager getCarManager() {
        return carManager;
    }

    public void setCarManager(CarManager carManager) {
        this.carManager = carManager;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public JButton getBtn_save_car() {
        return btn_save_car;
    }

    public void setBtn_save_car(JButton btn_save_car) {
        this.btn_save_car = btn_save_car;
    }

    public JTextField getFld_plate() {
        return fld_plate;
    }

    public void setFld_plate(JTextField fld_plate) {
        this.fld_plate = fld_plate;
    }

    public JLabel getLbl_plate() {
        return lbl_plate;
    }

    public void setLbl_plate(JLabel lbl_plate) {
        this.lbl_plate = lbl_plate;
    }

    public JTextField getFld_km() {
        return fld_km;
    }

    public void setFld_km(JTextField fld_km) {
        this.fld_km = fld_km;
    }

    public JComboBox getCmb_color() {
        return cmb_color;
    }

    public void setCmb_color(JComboBox cmb_color) {
        this.cmb_color = cmb_color;
    }

    public JLabel getLbl_color() {
        return lbl_color;
    }

    public void setLbl_color(JLabel lbl_color) {
        this.lbl_color = lbl_color;
    }

    public JComboBox getCmb_model() {
        return cmb_model;
    }

    public void setCmb_model(JComboBox cmb_model) {
        this.cmb_model = cmb_model;
    }

    public JLabel getLbl_model() {
        return lbl_model;
    }

    public void setLbl_model(JLabel lbl_model) {
        this.lbl_model = lbl_model;
    }

    public JLabel getLbl_createcar() {
        return lbl_createcar;
    }

    public void setLbl_createcar(JLabel lbl_createcar) {
        this.lbl_createcar = lbl_createcar;
    }

    public JLabel getLbl_km() {
        return lbl_km;
    }

    public void setLbl_km(JLabel lbl_km) {
        this.lbl_km = lbl_km;
    }

    public CarView(Car car){
        this.car = car;
        this.carManager = new CarManager();
        this.modelManager = new ModelManager();
        this.add(container);
        this.guiInitialize(400,300);
        this.cmb_color.setModel(new DefaultComboBoxModel<>(Car.Color.values()));
        for(Model model : this.modelManager.findAll()){
            this.cmb_model.addItem(model.getComboItem());
        }
        if(this.car.getId() != 0){
            ComboItem selectedItem = car.getModel().getComboItem();
            this.cmb_model.getModel().setSelectedItem(selectedItem);
            this.cmb_color.getModel().setSelectedItem(car.getColor());
            this.fld_plate.setText(car.getPlate());
            this.fld_km.setText(Integer.toString(car.getKm()));


        }
        this.btn_save_car.addActionListener(e->{
            if(Helper.isFieldListEmpty(new JTextField[]{this.fld_km, this.fld_plate})){
                Helper.showMessage("fill");
            }
            else{
                boolean result;
                ComboItem selectedModel = (ComboItem) this.cmb_model.getSelectedItem();
                this.car.setModel_id(selectedModel.getKey());
                this.car.setColor((Car.Color) this.cmb_color.getSelectedItem());
                this.car.setPlate(this.fld_plate.getText());
                this.car.setKm(Integer.parseInt(this.fld_km.getText()));
                if(this.car.getId()!=0){
                    result = this.carManager.update(this.car);
                }
                else{
                    result = this.carManager.save(this.car);
                }
                if(result){
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
