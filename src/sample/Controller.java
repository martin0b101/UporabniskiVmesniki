package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    public ComboBox<String> comboCitys;
    public RadioButton addItem;
    public RadioButton removeSelected;
    public Spinner spinner;
    public RadioButton removeFirst;
    public TextField inputMessage;
    public Label usedButtons;
    public CheckBox allowTwins;
    public Button buttonAction;
    public Label message; //sporocilo 
    public Label status;
    private ArrayList<String> kraji = new ArrayList();{{
        kraji.add("Kapele");
        kraji.add("Krško");
        kraji.add("Ljubljana");
    }}
    private String [] myName = {"M", "A", "R", "T", "I", "N"};
    private String[] myLastName = {"V", "R", "B", "A", "N", "Č", "I", "Č"};


    public void openCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null){
            status.setText("Izbrana datoteka je: "+f.getAbsolutePath());
        }

    }

    public void clearCB(ActionEvent actionEvent) {
        message.setText("");
        status.setText("");
        usedButtons.setText("");

    }

    public void exitCB(ActionEvent actionEvent) {
        System.exit(0); //zakljucim
    }

    public void izbira2(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myName[1]);
        else
            usedButtons.setText(myName[1]);
    }

    public void izbira1(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myName[0]);
        else
            usedButtons.setText(myName[0]);
    }

    public void izbira3(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myName[2]);
        else
            usedButtons.setText(myName[2]);
    }

    public void izbira4(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myName[3]);
        else
            usedButtons.setText(myName[3]);
    }

    public void izbira5(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myName[4]);
        else
            usedButtons.setText(myName[4]);
    }

    public void izbira6(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myName[5]);
        else
            usedButtons.setText(myName[5]);
    }


    public void izbira21(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[0]);
        else
            usedButtons.setText(myLastName[0]);
    }

    public void izbira22(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[1]);
        else
            usedButtons.setText(myLastName[1]);
    }

    public void izbira23(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[2]);
        else
            usedButtons.setText(myLastName[2]);

    }

    public void izbira24(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[3]);
        else
            usedButtons.setText(myLastName[3]);

    }

    public void izbira25(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[4]);
        else
            usedButtons.setText(myLastName[4]);

    }

    public void izbira26(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[5]);
        else
            usedButtons.setText(myLastName[5]);

    }

    public void izbira27(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[6]);
        else
            usedButtons.setText(myLastName[6]);
    }

    public void izbira28(ActionEvent actionEvent) {
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + myLastName[7]);
        else
            usedButtons.setText(myLastName[7]);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tabelo z kraji
        comboCitys.getItems().addAll(kraji); //doda elemente v comboBox
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20,1)); // 0 - 20
        spinner.valueProperty().addListener((obser, oldv, newV) ->{
            getComboCityValue((int) newV);
        });
    }


    private void getComboCityValue(int newValue){
        if (newValue < kraji.size())
            message.setText(kraji.get(newValue));
        else
            message.setText("Ni elementa");
    }

    private void addElementsInCombo(String element){ //metoda za dodajanje elementov c combo
        kraji.add(element);//dodam element v kraje
        //comboCitys.getItems().addAll(element);
    }


    public void addElement(ActionEvent actionEvent) {

        if (addItem.isSelected()) { //add item to combobox
            String newItem = inputMessage.getText().toString();
            if (inputMessage.getText().equals("")){
                message.setText("Napisi nekaj v polje");
            }
            else {
                if (!(allowTwins.isSelected()) && checkForDuplicates(newItem)) { //nesmem dodat duplikate
                    message.setText("Nemorem dodati duplikatov");
                } else {
                    kraji.add(inputMessage.getText().toString());
                    comboCitys.getItems().add(inputMessage.getText().toString());
                    message.setText("Dodajam");
                }
            }
        }
        else if (removeSelected.isSelected()){

            if () {
                String itemToRemove = comboCitys.getValue();
                int indexOfRemoved = kraji.indexOf(itemToRemove);
                kraji.remove(indexOfRemoved);
                comboCitys.getItems().remove(indexOfRemoved);
                message.setText("Odstranjujem izbranega");
            }
            else {
                message.setText("Ni elementa na izbranem mestu");
            }
        }
        else if (removeFirst.isSelected()){
            if (!kraji.isEmpty()) {
                comboCitys.getItems().remove(0);
                kraji.remove(0); //odstanim se v tabeli
                message.setText("Odstranjujem prvega");
            }
            else {
                message.setText("Ni elementa na izbranem mestu");
            }
        }
    }
    
    private boolean checkForDuplicates(String newElement){
        for (String kraj: kraji) {
            if (kraj.toLowerCase().equals(newElement.toLowerCase())){ //ce najedem kak element ko je isti ga vrnem true
                return true;
            }
        }
        return false;
        
    }
    
}
