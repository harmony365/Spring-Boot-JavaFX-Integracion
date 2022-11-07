package com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.utiles;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.bytecode.javafx.spring.integration.SpringJavaFXIntegration.model.Digic;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import javafx.util.StringConverter;


/**
 *
 * @author JorgeLPR
 */
public class DigicConverter extends StringConverter<Digic> {

    ComboBox<Digic> combobox;

    public DigicConverter(ComboBox<Digic> combobox){
        this.combobox = combobox;
    }

    public DigicConverter(){
        this.combobox=null;
    }

    private void updateCell(){

        combobox.setCellFactory(new Callback<ListView<Digic>, ListCell<Digic>>() {
            @Override
            public ListCell<Digic> call(ListView<Digic> param) {

                ListCell cell = new ListCell<Digic>(){

                    @Override
                    public void updateItem(Digic item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            setText("");
                        }else{
                            setText(item.getValorMedioPago());
                        }
                    }

                };

                return cell;
            }
        });

    }

    @Override
    public String toString(Digic digic) {
        return digic == null ? null : digic.getValorMedioPago();
    }

    @Override
    public Digic fromString(String string) {
/*
        if(combobox != null){

            Digic digic = combobox.getValue();

            if(digic!=null){
                digic.setValorMedioPago();v(string);
                updateCell();
                return digic;
            }else{
                Digic newDigic = new Digic(combobox.getItems().size()+1, string, "", null);
                return newDigic;
            }

        }else{
            return null;
        }
*/
        return null;
    }

}
