package edu.wpi.cs3733.programname.commondata.proxy;

public class ProxyImage implements NodeImage {

    RealNodeImage realImage;
    String filepath;

    public ProxyImage(String filepath) {
        this.filepath = filepath;
    }

    public void display(){
        if (realImage == null) {
            this.realImage = new RealNodeImage(filepath);
        }
        realImage.display();
    }
}
