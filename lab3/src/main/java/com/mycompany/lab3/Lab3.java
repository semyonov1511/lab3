
package com.mycompany.lab3;

import FileReaders.XMLFileReader;
import Interface.GUI;
import Interface.Reactor;
import java.util.ArrayList;

public class Lab3 {

    public static void main(String[] args) {
        ArrayList<Reactor> list = XMLFileReader.read("ReactorType.xml");
            GUI gui = new GUI();
            gui.setVisible(true);
    }
}
