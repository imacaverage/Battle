package battle;

import javax.swing.*;
import java.awt.*;

/**
 * Класс "Битва"
 * Created by iMacAverage on 20.01.16.
 */
public class Battle extends JFrame {

    /**
     * Отобразить окно
     */
    public void display() {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(

                () -> {
                    setDefaultLookAndFeelDecorated(true);
                    Battle battle = new Battle();
                    battle.display();
                }

        );
    }

}
