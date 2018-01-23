package com.simo.UIdict.View;

import org.json.simple.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class EditWordController {
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
	public void setword(JSONObject _wordObject)
	{
		this.wordObject = _wordObject;
	}
	public void setSearchTxt(String word)
	{
		txtWord.setText(word);
	}
	public void setTradicionTxt(String word)
	{
		txtTraduction.setText(word);
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
	{   if(!txtTraduction.getText().isEmpty() )
		{
		String word = txtWord.getText();
		DictionaryOverViewController.getWords().getjsonreading().removeFromJson("Resources/dic.json", word);
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
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Traduction Entred");
	        alert.setContentText("Please fill the request information");
	        DialogPane dialogPane = alert.getDialogPane();
	        dialogPane.getStylesheets().add(
	           getClass().getResource("myDialogs.css").toExternalForm());
	       dialogPane.getStyleClass().add("myDialog");
	         alert.showAndWait();
	}
	}
public TextField getTxtSearch()
{
	return txtWord;
}
public TextArea getTradiction()
{
	return txtTraduction;
}
	@FXML
	private void handleCancel()
	{
		newStage.close();
	}
	
	
}
