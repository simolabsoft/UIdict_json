package com.simo.UIdict.View;

import org.controlsfx.control.textfield.TextFields;
import org.json.simple.JSONObject;

import com.simo.UIdict.MainApp;
import com.simo.UIdict.Model.Words;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DictionaryOverViewController {
	private MainApp mainApp; 
	private static Words words;
 public DictionaryOverViewController() {
		
	}
@FXML
TextField txtSearch;
@FXML
TextArea txtTraduction;
@FXML
Button btnQuit;
@FXML
Button btnDelete;
@FXML
Label lblMessage;
@FXML
private void initialize()
{
settingTheDictionary();
}
public void setMainApp(MainApp _mainApp)
{
	
	this.mainApp = _mainApp;
	
}
@FXML
private void closeApp()
{
	mainApp.getPrimaryStage().close();
}
private void settingTheDictionary()
{
	words = new Words();
	TextFields.bindAutoCompletion(txtSearch,words.getMap().keySet());
	txtTraduction.setEditable(false);
}
@FXML
private void txtFieldEntred()
{
	
	txtTraduction.setText(words.getMap().get(txtSearch.getText()));
}
@FXML
private void deleteWord()
{
	boolean okClicked;
	String headerText = "Word Does Not Exist In The Dictionary !";
	String contentText = "Please Select An Existant Word.";
	if(words.getMap().containsKey(txtSearch.getText()) )
	{ okClicked = mainApp.showDeleteDialog("Are you sure to delete : " +txtSearch.getText().toUpperCase());
	  if(okClicked)
	  {
		  words.getjsonreading().removeFromJson("Resources/dic.json", txtSearch.getText());
		 txtSearch.setText("");
		 txtTraduction.setText("");
		  words = new Words();
		TextFields.bindAutoCompletion(txtSearch,words.getMap().keySet());
	
	  }
	}
	else
	{
		if(txtSearch.getText().isEmpty())
		{
			headerText = "No word Selected";
			contentText = "Please Enter A Word In the Text Fieled .";
			
		}
		 Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getPrimaryStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText(headerText);
	        alert.setContentText(contentText);
	        DialogPane dialogPane = alert.getDialogPane();
	        dialogPane.getStylesheets().add(
	           getClass().getResource("myDialogs.css").toExternalForm());
	       dialogPane.getStyleClass().add("myDialog");

	         alert.showAndWait();
	}
	
   
}
@FXML 
private void newWord()
{
	boolean okClicked;
	JSONObject wordObject = new JSONObject();
	okClicked=  mainApp.showNewDialog(wordObject);
	if(okClicked)
	{
		words.getjsonreading().writeToJsonFile("Resources/dic.json",wordObject);
		  words = new Words();
			TextFields.bindAutoCompletion(txtSearch,words.getMap().keySet());
	}
}
@FXML
private void editWord()
{   
	String headerText = "Word does not Exist";
	String contentText = "Please select a Word exist In The Dictionary.";
	if(words.getjsonreading().isWordExist("Resources/dic.json",txtSearch.getText())){
	boolean okClicked;
	JSONObject wordObject = new JSONObject();
	okClicked=  mainApp.showEditDialog(wordObject,txtSearch.getText(),txtTraduction.getText());
	if(okClicked)
	{
		words.getjsonreading().writeToJsonFile("Resources/dic.json",wordObject);
		  words = new Words();
		  txtSearch.setText("");
		  txtTraduction.setText("");
			TextFields.bindAutoCompletion(txtSearch,words.getMap().keySet());
			 
	}
	}
	else
	{
		if(txtSearch.getText().isEmpty())
		{
			headerText = "Enter a word To Be Edited";
			contentText = "Please choose a Word to Be Edited";
		}
		Alert alert = new Alert(AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Exist");
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(
           getClass().getResource("myDialogs.css").toExternalForm());
       dialogPane.getStyleClass().add("myDialog");

         alert.showAndWait();
	}
	
}
public static Words getWords()
{
	return words;
}
}


