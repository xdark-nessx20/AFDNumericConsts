package view;

import model.Automaton;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import java.util.Objects;

public class Program {
    static {
        // Fondo VINOTINTO
        UIManager.put("OptionPane.background", Color.decode("#581845"));
        UIManager.put("Panel.background", Color.decode("#581845"));


        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Label.foreground", Color.WHITE);


        UIManager.put("Button.background", Color.WHITE);
        UIManager.put("Button.foreground", Color.decode("#581845"));
        UIManager.put("Button.select", Color.decode("#009EE8")); // AZUL pa cuando clickes
        UIManager.put("Button.focus", Color.decode("#009EE8"));

        // Fuente
        UIManager.put("Button.font", new Font("Montserrat", Font.ITALIC + Font.BOLD, 13));
        UIManager.put("OptionPane.messageFont", new Font("Montserrat", Font.ITALIC + Font.BOLD, 13));
    }

    public static void main(String[] args) {
        Automaton afd = new Automaton();
        Icon closeGif = new ImageIcon(Objects.requireNonNull(Program.class.getResource("/close.gif")));
        Icon helloGif = new ImageIcon(Objects.requireNonNull(Program.class.getResource("/hola.gif")));
        while(true){
            String num = (String) JOptionPane.showInputDialog(null,
                    "Type a numeric constant chain: \nDon't write if you wanna exit", "AFD Numeric Constants",
                    JOptionPane.PLAIN_MESSAGE, helloGif, null, null);
            if(num == null || num.isBlank()) break;

            String afdAnswer = afd.verify(num);
            processAnswer(afdAnswer);
        }
        JOptionPane.showMessageDialog(null, "Closing... Press OK", "AFD Numeric Constants",
                JOptionPane.PLAIN_MESSAGE, closeGif);
    }

    private static void processAnswer(String answer){
        // abrir el gif correcto
        String fileName = (answer.contains("valid")) ? "/bien.gif" : "/mal.gif";
        Icon gif = new ImageIcon(Objects.requireNonNull(Program.class.getResource(fileName)));

        JOptionPane.showMessageDialog(null, answer, "AFD Numeric Constants", JOptionPane.PLAIN_MESSAGE, gif);
    }
    /*
    Probar
    Bien:"2546", "24.", ".5", "314e-2", "-4.2e2", "+.25"
    Mal: "-.", "e2", ".", "-.+", "--4", "7.e", ".2e-1.5"
    */
}
