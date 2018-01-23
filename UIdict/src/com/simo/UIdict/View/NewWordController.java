package com.simo.UIdict.View;

import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class NewWordController {
	private Stage newStage;
	private boolean okClicked = false;
	private JSONObject wordObject;
	@FXML
	 TextField txtWord;
	@FXML
	TextArea txtTraduction;
	@FXML
	private void initialize()
	{
		
	}
	public void setNewStage(Stage _newStage)
	{
		this.newStage = _newStage;
		newStage.getIcons().add(new Image("file:Resources/dic.png"));
	
	}
	public boolean isOkClicked()
	{
		return okClicked;
	}
	@FXML
	private void handleOk()
	{   if(!txtWord.getText().isEmpty() && !txtTraduction.getText().isEmpty() )
		{
		   if(!isExist(txtWord.getText()))
		   {
		System.out.println(isExist(txtWord.getText()));
		String word = txtWord.getText();
		String traduction = txtTraduction.getText();
		wordObject.put("word", word);
		wordObject.put("traduction", traduction);
		okClicked =true;
		newStage.close();
		   }
		   else
		   {
			   Alert alert = new Alert(AlertType.WARNING);
		        alert.initOwner(newStage);
		        alert.setTitle("Exist Already");
		        alert.setHeaderText("\""+txtWord.getText()+"\"" + "  Is Already Exist");
		        alert.setContentText("Please Try With Another Word");
		        DialogPane dialogPane = alert.getDialogPane();
		        dialogPane.getStylesheets().add(
		           getClass().getResource("myDialogs.css").toExternalForm());
		       dialogPane.getStyleClass().add("myDialog");
		        
		         alert.showAndWait();
		   }
		}
	else
	{
		 Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(newStage);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Word Selected");
	        alert.setContentText("Please fill the request information");
	        DialogPane dialogPane = alert.getDialogPane();
	        dialogPane.getStylesheets().add(
	           getClass().getResource("myDialogs.css").toExternalForm());
	       dialogPane.getStyleClass().add("myDialog");
	         alert.showAndWait();
	}
	}
	@FXML
	private void handleCancel()
	{
		newStage.close();
	}
	public void setword(JSONObject _wordObject)
	{
		this.wordObject = _wordObject;
	}
	private boolean isExist(String word)
	{
		return DictionaryOverViewController.getWords().getjsonreading().isWordExist("Resources/dic.json", word);
	}

	public NewWordController() {
		// TODO Auto-generated constructor stub
	} 
			
	
}
