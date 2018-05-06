package Domain;

import Utility.Variables;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public class JumpingCharacter extends Character {

    double velocidadMovimiento;
    int velocidad;
    ArrayList<Image> sprite;

    public JumpingCharacter(int x, int y, int imgNum) throws FileNotFoundException {
        super(x, y, imgNum);
        this.velocidadMovimiento = 0.8;
        this.velocidad = (int) (Math.random() * 10 + 1);
        setSprite();
    }

    public void setSprite() throws FileNotFoundException {
        sprite = super.getSprite();
        for (int i = 0; i < 3; i++) {
            sprite.add(new Image(new FileInputStream("src/Assets/Jumping" + i + ".png")));
        }
    }

    @Override
    public void run() {
        //lo que hace este metodo es: si esta en 370 que disminuya el x para que se mueva en forma de que 
        //esta brincando y si esta en menos de 270 se aumenta para que se mueva en forma de caida 
        while (true) {
            try {
                super.setImage(this.sprite.get(2));
                super.setY(super.getY() - this.velocidadMovimiento);
                Thread.sleep(10);
                while (super.getY() <= 370) {
                    super.setImage(this.sprite.get(1));
                    super.setY(super.getY() - this.velocidadMovimiento);
                    Thread.sleep(10);
                    if (super.getY() < 270) {
                        super.setY(super.getY() + this.velocidadMovimiento);
                        Thread.sleep(10);
                        if (super.getY() > 270) {
                            super.setImage(this.sprite.get(2));
                            super.setY(super.getY() - 0.8);
                            while (super.getY() < 370) {
                                super.setY(super.getY() + this.velocidadMovimiento);
                                Thread.sleep(10);
                                if (super.getY()==370) {
                                    super.setImage(this.sprite.get(0));
                                    super.setY(370);
                                    Thread.sleep(10);
                                }
                            }
                        }
                    }
                }
                super.setY(super.getY() + this.velocidadMovimiento);
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(JumpingCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
