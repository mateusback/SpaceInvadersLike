package br.ifpr.jogo.model.graphicelement;

import br.ifpr.jogo.controller.CloudController;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

//MODEL
@Entity
@Table(name = "tb_nuvem")
public class Cloud extends GraphicElement {
    @Transient
    private CloudController cloudController;

    public Cloud(int xRandom, int yRandom){
        this.setCloudController(new CloudController(this));
        cloudController.load();
        super.setXPosition(xRandom);
        super.setYPosition(yRandom);

    }

    public Cloud(){
        this.setCloudController(new CloudController(this));
    }
    public CloudController getCloudController() {
        return cloudController;
    }

    public void setCloudController(CloudController cloudController) {
        this.cloudController = cloudController;
    }
}
