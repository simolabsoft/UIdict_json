package com.simo.UIdict;
import java.io.IOException;

import org.json.simple.JSONObject;

import com.simo.UIdict.View.DeleteMessageBoxShow;
import com.simo.UIdict.View.DictionaryOverViewController;
import com.simo.UIdict.View.EditWordController;
import com.simo.UIdict.View.NewWordController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {
	
    private Stage primaryStage;
    private BorderPane rootLayout;
    public MainApp() {
		
	}
    
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("simoDict");
		this.primaryStage.getIcons().add(new Image("file:Resources/dic.png"));
		
		InitRootLayout();
		ShowDicionary();
		
	}

	private void ShowDicionary() {
	  try
	  {
		  FXMLLoader loader = new FXMLLoader();
		  loader.setLocation(MainApp.class.getResource("View/DictionaryView.fxml"));
	     AnchorPane dictionaryView  = (AnchorPane) loader.load();
		  rootLayout.setCenter(dictionaryView);

	        // Give the controller access to the main app.
	        DictionaryOverViewController controller = loader.getController();
	        controller.setMainApp(this);
	  }catch(IOException e)
	  {
		  e.printStackTrace();
	  }
		
	}

	private void InitRootLayout() {
		 try
		  {
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(MainApp.class.getResource("View/RootLayout.fxml"));
			 rootLayout = (BorderPane) loader.load();
			 Scene scene = new Scene(rootLayout);
			 primaryStage.setScene(scene);
			 primaryStage.setResizable(false);
			 primaryStage.show();
			  
		  }catch(IOException e)
		  {
			  e.printStackTrace();
		  }
	}
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}
	public boolean showDeleteDialog(String message) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/DeleteWordDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Delete");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.setResizable(false);

	        // Set the person into the controller.
	       DeleteMessageBoxShow controller = loader.getController();
	        controller.setDeletStage(dialogStage);
	        controller.setMessage(message);
	       

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean showNewDialog(JSONObject wordObject) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/NewWord.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("New Word");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.setResizable(false);
	        

	        
	        NewWordController controller = loader.getController();
	        controller.setNewStage(dialogStage);
	        controller.setword(wordObject);
	     
	       

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	public boolean showEditDialog(JSONObject wordObject,String word,String tradiction) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/EditWord.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Word");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        dialogStage.setResizable(false);
	        

	        
	       EditWordController controller = loader.getController();
	        controller.setNewStage(dialogStage);
	        controller.setSearchTxt(word);
	        controller.getTxtSearch().setEditable(false);
	        controller.setTradicionTxt(tradiction);
	        controller.setword(wordObject);
	     
	       

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
