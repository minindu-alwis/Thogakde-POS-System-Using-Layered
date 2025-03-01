package Controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custome.LoginService;
import util.ServiceType;

import java.io.IOException;

public class LoginFormController {

    public TextField txtusername;
    public TextField txtpassword;

    private LoginService loginService= ServiceFactory.getInstance().getServiceType(ServiceType.LOGIN);

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {

        if (loginService.authenticateUser(txtusername.getText(),txtpassword.getText())){
            Stage stage=new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/thogakadeDashboard.fxml"))));
            stage.show();
        }else{
            new Alert(Alert.AlertType.INFORMATION, "Please Create Account and After Login...").show();
        }
    }



    public void btnSingUpOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/singup_form.fxml"))));
        stage.show();
    }


}
