package Domain;

import Utility.Variables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class RunningCharacter extends Character {

    double velocidadMovimiento;
    int velocidad;

    public RunningCharacter(double x, double y, int imgNum) throws FileNotFoundException {
        super(x, y, imgNum);
        this.velocidadMovimiento = 0.8;
        this.velocidad = (int) (Math.random() * 10 + 1);
        setSprite();
    }

    public void setSprite() throws FileNotFoundException {
        ArrayList<Image> sprite = super.getSprite();
        for (int i = 0; i < 8; i++) {
            sprite.add(new Image(new FileInputStream("src/Assets/Running" + i + ".png")));
        }
        super.setSprite(sprite);
    }

    @Override
    //este metodo lo que hace es ir recorriendo un for con la cantidad de imagenes que debe cambiar y 
            //se va a aumentando el x para que se vaya moviendo como si estuviese caminando 
    public void run() {
        while (true) {
            for (int i = 0; i < 8; i++) {
                try {
                    ArrayList<Image> sprite = super.getSprite();
                    super.setImage(sprite.get(i));
                    Thread.sleep(40);
                    super.setX(super.getX()+ this.velocidadMovimiento);                                        
                    if (this.getX() > Variables.WIDTH) {
                        this.setX(0);
                        Thread.sleep(40);
                    }
                    Thread.sleep(70);
                } catch (InterruptedException ex) {
                    Logger.getLogger(RunningCharacter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
