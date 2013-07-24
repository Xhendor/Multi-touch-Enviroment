package utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.JTextComponent;

/**
 *
 * @author emmanuelle
 */
public class MascarasText implements KeyListener {

    private int limite;
    private JTextComponent txt;
    private String datoTxt;
    private Pattern pattern;
    private Border wrongBorder = BorderFactory.createLineBorder(Color.RED);
    private Border defaultBorder;
    private boolean correct = false;
    private boolean obligatorio = false;
    private JTextArea txtArea;

    public MascarasText(JTextComponent txt, int limite, String regEx, boolean obligatorio) {
        this.defaultBorder = txt.getBorder();
        this.pattern = Pattern.compile(regEx);
        this.limite = limite;
        this.txt = txt;
        this.obligatorio = obligatorio;


    }

    public MascarasText(String datoTxt, String regEx, boolean obligatorio) {

        this.pattern = Pattern.compile(regEx);
        this.datoTxt = datoTxt;
        this.obligatorio = obligatorio;
        
    }

    public MascarasText() {
    }

    @Override
    public void keyTyped(KeyEvent ke) {

        if (txt.getText().length() == limite && ke.getKeyCode() != KeyEvent.VK_BACK_SPACE) {

            ke.consume();

        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //Si el campo es marcado como obligaorio
        if (obligatorio == true) {
            validateText(txt.getText());
            //Si el campo no es obligatorio, pero se introduce al menos un caracter
        } else if (obligatorio == false && txt.getText().length() != 0) {
            validateText(txt.getText());
        } else {
            //No valida, dado que el campo no será almacenado
        }
    }

    public int getLimite() {
        return limite;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    public void setTxt(JTextField txt) {
        this.txt = txt;
    }

    private void validateText(String cadena) {

        Matcher matcher = pattern.matcher(cadena);
        if (!matcher.matches()) {
            txt.setBorder(wrongBorder);
            correct = false;

        } else {

            txt.setBorder(defaultBorder);
            correct = true;

        }
    }

    private void validateText() {
       Matcher matcher = pattern.matcher(datoTxt);
        if (!matcher.matches()) {
            
            correct = false;

        } else {

            correct = true;

        }
    }
//     public boolean validateCampo(){
//         boolean campoValido;
//     if(obligatorio == true){
//        txt.getText();
//     
//     }
//     return campoValido;
//     }

    /**
     * @return the correct
     */
    public boolean isCorrect() {

        if (obligatorio == true) {
            validateText(txt.getText());
        } else if (obligatorio == false && txt.getText().length() != 0) {
            System.out.println("Ahora validar..." + txt.getName());
            validateText(txt.getText());
        } else {
            System.out.println("No validar... " + txt.getName());
            correct = true;
        }
        return correct;

    }

    /**
     * @param correct the correct to set
     */
    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    /**
     * @return the obligatorio
     */
    public boolean isObligatorio() {
        return obligatorio;
    }

    /**
     * @param obligatorio the obligatorio to set
     */
    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    /**
     * @param datoTxt the datoTxt to set
     */
    public void setDatoTxt(String datoTxt) {
        this.datoTxt = datoTxt;
    }

    /**
     * @return the correct
     */
    public boolean isCorrectDato() {

        if (obligatorio == true) {
            validateText();
        } else if (obligatorio == false && datoTxt.length() != 0) {
            System.out.println("Ahora validar:[" + datoTxt + "]");
            validateText();
        } else {
            System.out.println("No validar:" + datoTxt);
            correct = true;
        }
        return correct;

    }
}
