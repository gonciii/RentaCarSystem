package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    public static void setTheme() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean confirm(String str){
        String msg;
        if(str.equals("sure")){
            msg= "Do you want to delete this?";

        }else{
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null,msg,"Are you sure?",JOptionPane.YES_NO_OPTION)==0;
    }
    public static boolean isFieldEmpty(JTextField field){
        return field.getText().trim().isEmpty();
    }
    public static void showMessage(String str){
        String msg;
        String title = switch (str) {
            case "fill" -> {
                msg = "Please fill all mandatory fields.";
                yield "Alert!";
            }
            case "done" -> {
                msg = "Success";
                yield "Result";
            }
            case "notfound" -> {
                msg = "User not found.";
                yield "Not Found";
            }
            case "error" -> {
                msg = "Invalid operation.";
                yield "Error";
            }
            default -> {
                msg = str;
                yield "Message";
            }
        };
        JOptionPane.showMessageDialog(null,msg,title,JOptionPane.INFORMATION_MESSAGE);

    }
    public static boolean isFieldListEmpty(JTextField [] fieldList){
        for(JTextField field : fieldList){
            if(isFieldEmpty(field)) return true;
        }
        return false;
    }
    public static int getLocationPoint(String type, Dimension size){
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

}
