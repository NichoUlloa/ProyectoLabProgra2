package gui;

import controller.MarcaController;

import javax.swing.*;
import java.awt.event.ActionEvent;
public class VentanaRegistrarMarca extends Ventana{
    public static void main(String[] args) {
        VentanaRegistrarMarca ventana = new VentanaRegistrarMarca();
    }

    // componentes de la ventana atributos: nombreMarca, idMarca
    private JLabel textoMenu, textoNombreMarca, textoIDMarca;
    private JTextField campoNombreMarca, campoIDMarca;
    private JButton botonRegistrar, botonCancelar;

    public VentanaRegistrarMarca() {
        super("Registrar Marca", 500, 520);
        generarElementosVentana();
    }

    private void generarElementosVentana() {
        generarMensajeMenu();
        generarBotonRegistrar();
        generarBotonCancelar();
        generarCampoNombreMarca();
        generarCampoIDMarca();
    }

    private void generarMensajeMenu() {
        String textoBienvenida = "Registrar Marca";
        super.generarJLabelEncabezado(textoMenu, textoBienvenida, 200, 50);
    }

    private void generarBotonRegistrar() {
        String textoBoton = "Registrar Marca";
        botonRegistrar = super.generarBoton(textoBoton, 55, 400, 170, 20);
        botonRegistrar.addActionListener(this);
        this.add(botonRegistrar);
    }

    private void generarBotonCancelar() {
        String textoBotonCancelar = "Cancelar";
        botonCancelar = super.generarBoton(textoBotonCancelar, 275, 400, 170, 20);
        botonCancelar.addActionListener(this);
        this.add(botonCancelar);
    }

    private void generarCampoNombreMarca() {
        String textoNombreMarca = "Nombre:";
        super.generarJLabel(this.textoNombreMarca, textoNombreMarca, 20, 50, 150, 20);
        campoNombreMarca = super.generarJTextField(200, 50, 250, 20);
        this.add(campoNombreMarca);
    }

    private void generarCampoIDMarca() {
        String textoIDMarca = "ID:";
        super.generarJLabel(this.textoIDMarca, textoIDMarca, 20, 100, 150, 20);
        campoIDMarca = super.generarJTextField(200, 100, 250, 20);
        this.add(campoIDMarca);
    }

    // Método registrarMarca validando su existencia
    private boolean registrarMarca() throws ClassNotFoundException {
        if(this.campoIDMarca.getText().length()==0 || this.campoNombreMarca.getText().length()==0){
            return false;
        }
        else{
            return MarcaController.registrarMarca(this.campoNombreMarca.getText(), Integer.parseInt(this.campoIDMarca.getText()));
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.botonRegistrar) {
            try {
                if(registrarMarca()) {
                    JOptionPane.showMessageDialog(this,"Marca registrada correctamente");
                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
                    this.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(this,"Marca ya ingresada o datos incorrectos");
                }
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == this.botonCancelar){
            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
            this.dispose();
        }

    }

}

// EJEMPLO
//  private boolean registrarCarrera() throws ClassNotFoundException {
//        if(this.campoCodigoCarrera.getText().length()==0 || this.campoNombre.getText().length()==0 || this.campoSemestres.getText().length()==0){
//            return false;
//        }
//        else{
//            return CarreraController.añadirCarrera(this.campoNombre.getText(),this.campoCodigoCarrera.getText(),Integer.parseInt(this.campoSemestres.getText()));
//
//        }
//    }
//
//
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == this.botonRegistrar) {
//            try {
//                if(registrarCarrera()) {
//                    JOptionPane.showMessageDialog(this,"Carrera registrada correctamente");
//                    VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
//                    this.dispose();
//                }
//                else{
//                    JOptionPane.showMessageDialog(this,"Carrera ya ingresada o datos incorrectos");
//                }
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
//        if (e.getSource() == this.botonCancelar){
//            VentanaBienvenida ventanaBienvenida = new VentanaBienvenida();
//            this.dispose();
//        }
//
//    }

