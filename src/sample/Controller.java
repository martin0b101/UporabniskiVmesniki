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


    public void openCB(ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);
        if (f != null){
            status.setText("Izbrana datoteka je: "+f.getAbsolutePath());
        }

    }

    public void clearCB(ActionEvent actionEvent) {
        message.setText("Sporočilo:");
        status.setText("Status");
        usedButtons.setText("");

    }

    public void exitCB(ActionEvent actionEvent) {
        System.exit(0); //zakljucim
    }

    public void izbira2(ActionEvent actionEvent) {
        MenuItem butttonPressed = (MenuItem) actionEvent.getSource(); //dobis tekst gumba pritisnjenega
        String pressed [] = butttonPressed.getText().split(" "); //splitas na dva dela izberes 2 del besedila
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + pressed[1]);
        else
            usedButtons.setText(pressed[1]);
    }

    public void izbira1(ActionEvent actionEvent) {
        MenuItem butttonPressed = (MenuItem) actionEvent.getSource(); //dobis tekst gumba pritisnjenega
        String pressed [] = butttonPressed.getText().split(" "); //splitas na dva dela izberes 2 del besedila
        if (usedButtons.getText() != "")
            usedButtons.setText(usedButtons.getText() + ", " + pressed[1]);
        else
            usedButtons.setText(pressed[1]);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //tabelo z kraji
        comboCitys.getItems().addAll(kraji); //doda elemente v comboBox
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20,1)); // 0 - 20
        spinner.valueProperty().addListener((obser, oldv, newV) ->{
            getComboCityValue((int) newV);
        });
        ToggleGroup radioButtonGroup = new ToggleGroup(); //grupiram radio gumbe da lahko zberes samo enega na enkrat
        addItem.setToggleGroup(radioButtonGroup);
        removeSelected.setToggleGroup(radioButtonGroup);
        removeFirst.setToggleGroup(radioButtonGroup);
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

            if (comboCitys.getValue() != null) {
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

    public void aboutAuthor(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Avtor");
        alert.setHeaderText("O avtorju");
        alert.setContentText("Naredil: Martin Vrbančič");
        alert.show();

    }
}
