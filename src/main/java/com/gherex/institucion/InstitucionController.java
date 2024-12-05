package com.gherex.institucion;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import javax.swing.*;
import java.sql.*;

public class InstitucionController {
    @FXML
    private Label estadoAccion;
    @FXML
    private TextField inputNombre;
    @FXML
    private TextField inputGrupo;
    @FXML
    private TextField inputBuscar;

    @FXML
    protected void btnRegistrar() {

        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_institución","root","");
            PreparedStatement pst = cn.prepareStatement("insert into alumnos values(?,?,?)");

            pst.setString(1,"0"); //columna 1
            pst.setString(2,inputNombre.getText().trim()); //columna 2; .trim() para quitar espacios de la cadena
            pst.setString(3,inputGrupo.getText().trim()); //columna 3
            pst.executeUpdate();

            inputNombre.setText("");
            inputGrupo.setText("");
        } catch (Exception e) {
            System.out.println("Error en btnRegistrar: " + e);
        }

        estadoAccion.setStyle("-fx-text-fill: green");
        estadoAccion.setText("Registro Exitoso");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> estadoAccion.setText(""));
        pause.play();
    }

    @FXML
    protected void btnModificar() {
        estadoAccion.setStyle("-fx-text-fill: yellow");
        estadoAccion.setText("Registro Modificado");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> estadoAccion.setText(""));
        pause.play();
    }

    @FXML
    protected void btnEliminar() {
        estadoAccion.setStyle("-fx-text-fill: red");
        estadoAccion.setText("Registro Eliminado");
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(e -> estadoAccion.setText(""));
        pause.play();
    }

    @FXML
    protected void btnBuscar() {
        try {
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_institución","root","");
            PreparedStatement pst = cn.prepareStatement("select * from alumnos where ID = ?");
            pst.setString(1,inputBuscar.getText().trim());

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                inputNombre.setText(rs.getString("NombreAlumno"));
                inputGrupo.setText(rs.getString("Grupo"));
            } else {
                JOptionPane.showMessageDialog(null,"Alumno no registrado.");
            }
        } catch (Exception e) {
            System.out.println("Error en btnBuscar: " + e);
        }
    }
}