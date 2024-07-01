package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class LoginView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JLabel lbl_welcome;
    private JLabel lbl_welcome2;
    private JPanel w_bottom;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JLabel lbl_password;
    private JButton btn_login;
    private  final UserManager userManager;

    public LoginView(){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitialize(400,400);


        container.setBackground(Color.red);
        lbl_welcome.setBackground(Color.RED);
        lbl_welcome.setForeground(Color.RED);
        lbl_username.setForeground(Color.RED);

        lbl_welcome2.setBackground(Color.RED);
        lbl_welcome2.setForeground(Color.RED);
        lbl_password.setForeground(Color.RED);
        btn_login.setForeground(Color.RED);

        btn_login.addActionListener(e -> {
            JTextField [] checkFieldList = {this.fld_username, this.fld_password};
            if(Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMessage("fill");
            }
            else{
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_password.getText());
                if(loginUser == null){
                    Helper.showMessage("notfound");
                }
                else{
                    AdminView adminView = new AdminView(loginUser);
                    dispose();
                }
            }



        });
    }















}
