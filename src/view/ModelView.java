package view;

import business.BrandManager;
import business.ModelManager;
import core.ComboItem;
import core.Helper;
import entity.Brand;
import entity.Model;

import javax.swing.*;

public class ModelView extends Layout{
    private JPanel container;
    private JLabel lbl_heading;
    private JComboBox<ComboItem> cmb_brand;
    private JLabel lbl_modelname;
    private JTextField fld_modelname;
    private JLabel lbl_year;
    private JTextField fld_year;
    private JLabel lbl_type;
    private JComboBox <Model.Type> cmb_type;
    private JLabel lbl_fuel;
    private JComboBox <Model.Fuel> cmb_fueltype;
    private JLabel lbl_gear;
    private JComboBox<Model.Gear> cmb_geartype;
    private JButton btn_model_save;
    private Model model;
    private ModelManager modelManager;
    private BrandManager brandManager;

    public ModelView(Model model) {
        this.model = model;
        this.modelManager = new ModelManager();
        this.brandManager = new BrandManager();
        this.add(container);
        this.guiInitialize(300,400);
        for(Brand brand : this.brandManager.findAll()){
            this.cmb_brand.addItem(new ComboItem(brand.getId(), brand.getName()));
        }
        this.cmb_fueltype.setModel(new DefaultComboBoxModel<>(Model.Fuel.values()));
        this.cmb_geartype.setModel(new DefaultComboBoxModel<>(Model.Gear.values()));
        this.cmb_type.setModel(new DefaultComboBoxModel<>(Model.Type.values()));

        if(this.model.getId() !=0) {
            this.fld_year.setText(this.model.getYear());
            this.fld_modelname.setText(this.model.getName());
            this.cmb_fueltype.getModel().setSelectedItem(this.model.getFuel());
            this.cmb_geartype.getModel().setSelectedItem(this.model.getGear());
            ComboItem defaultBrand = new ComboItem(this.model.getBrand().getId(),this.model.getBrand().getName());
            this.cmb_brand.getModel().setSelectedItem(defaultBrand);
        }


        this.btn_model_save.addActionListener(e->{

            if(Helper.isFieldListEmpty(new JTextField[]{this.fld_modelname,this.fld_year})){
                Helper.showMessage("fill");
            }
            else{
                boolean result=false;
                ComboItem selectedBrand = (ComboItem) cmb_brand.getSelectedItem();
                    this.model.setYear(fld_year.getText());
                    this.model.setName(fld_modelname.getText());
                    this.model.setBrand_id(selectedBrand.getKey());
                    this.model.setType((Model.Type) cmb_type.getSelectedItem());
                    this.model.setFuel((Model.Fuel) cmb_fueltype.getSelectedItem());
                    this.model.setGear((Model.Gear) cmb_geartype.getSelectedItem());
                    if(this.model.getId() != 0){
                        result = this.modelManager.update(this.model);
                    }
                    else{
                        result = this.modelManager.save(this.model);
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
