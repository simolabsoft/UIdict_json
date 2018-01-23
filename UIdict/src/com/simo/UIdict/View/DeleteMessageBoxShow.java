package com.simo.UIdict.View;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DeleteMessageBoxShow {
	@FXML
	 Label lblMessage; 
	private Stage deleteStage;
	private boolean okClicked = false;
	@FXML
	private void initialize()
	{
		
	}
	public void setMessage(String message)
	{
		lblMessage.setText(message);
	}
	public void setDeletStage(Stage _deleteStage)
	{
		this.deleteStage = _deleteStage;
		
	}
	public boolean isOkClicked()
	{
		return okClicked;
	}
	@FXML
	private void handleYes()
	{
		okClicked =true;
		deleteStage.close();
	}
	@FXML
	private void handleNo()
	{
		deleteStage.close();
	}
	public DeleteMessageBoxShow() {
		
	}
	
}
