package br.ifpr.jogo.model.graphicelement;

import javax.persistence.*;
import java.awt.Image;
import java.awt.Rectangle;

//MODEL
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GraphicElement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id_elemeto_grafico")
    private Integer idGraphicElement;

    @Column(name = "posicao_x")
    private int xPosition;
    @Column(name = "posicao_y")
    private int yPosition;
    @Column(name = "deslocamento_x")
    private int xDisplacement;
    @Column(name = "deslocamento_y")
    private int yDisplacement;
    @Transient
    private Image baseSprite;
    @Column(name = "visivel")
    private boolean visible;
    @Column(name = "largura_imagem")
    private int imageWidth;
    @Column(name = "altura_imagem")
    private int imageHeight;
    @Column(name = "direcao")
    private String direction;
    @Column(name = "velocidade")
    private int speed;
    @Column(name = "vida")
    private int hitPoints;

    public GraphicElement() {
        this.visible = true;
    }

    public Rectangle getRectangle() {
        return new Rectangle(xPosition, yPosition, imageWidth, imageHeight);
    }

    public int getXPosition() {
        return xPosition;
    }
    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getXDisplacement() {
        return xDisplacement;
    }

    public void setXDisplacement(int xDisplacement) {
        this.xDisplacement = xDisplacement;
    }

    public int getYDisplacement() {
        return yDisplacement;
    }

    public void setYDisplacement(int yDisplacement) {
        this.yDisplacement = yDisplacement;
    }

    public Image getBaseSprite() {
        return baseSprite;
    }

    public void setBaseSprite(Image baseSprite) {
        this.baseSprite = baseSprite;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getIdGraphicElement() {
        return idGraphicElement;
    }

    public void setIdGraphicElement(Integer idGraphicElement) {
        this.idGraphicElement = idGraphicElement;
    }
}
