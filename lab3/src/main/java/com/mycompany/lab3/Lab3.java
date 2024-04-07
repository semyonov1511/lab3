
package com.mycompany.lab3;

import Interface.FileReader;
import Interface.Reactor;
import java.util.ArrayList;

public class Lab3 {

    public static void main(String[] args) {
        ArrayList<Reactor> list = FileReader.parseXMLfile("ReactorType.xml");
    }
}
