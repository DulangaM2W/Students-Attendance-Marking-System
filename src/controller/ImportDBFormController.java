package controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Files;

public class ImportDBFormController {

    public Button btnOK;
    public RadioButton rdoFirstTime;
    public Button btnBrowse;
    public TextField txtBrowse;
    public RadioButton rdoRestore;
    private SimpleObjectProperty<File> fileProperty;

    public void initialize(){
        txtBrowse.setEditable(false);
        rdoRestore.selectedProperty().addListener((observable, oldValue, newValue) -> {
            btnOK.setDisable(txtBrowse.getText().isEmpty() && newValue);
        });
    }

    public void btnBrowse_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Set BackUp Files");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("BackUp File","*.dep8backup"));
        File file = fileChooser.showOpenDialog(btnOK.getScene().getWindow());
        txtBrowse.setText(file!=null? file.getAbsolutePath():"");
        fileProperty.setValue(file);
    }

    public void btnOK_OnAction(ActionEvent actionEvent) {
        if (rdoFirstTime.isSelected()){
            fileProperty.setValue(null);
        }
        ((Stage)btnOK.getScene().getWindow()).close();
    }

    public void initFileProperty(SimpleObjectProperty fileProperty){

        this.fileProperty=fileProperty;
    }
}