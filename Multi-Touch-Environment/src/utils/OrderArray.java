package utils;

import java.util.ArrayList;
import uabc.edu.mx.container.Container;

public class OrderArray extends ArrayList<Container> {
    
    public OrderArray() {
    }
    
    @Override
    public boolean add(Container e) {
        return super.add(e); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean contains(Object o) {
        return super.contains(o); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Container set(int i, Container e) {
        return super.set(i, e); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean interchange(Container containerA, Container containerB) {
        int indexA = indexOf(containerA);
        int indexB = indexOf(containerB);
        if (indexA == -1 || indexB == -1) {
            return false;
        }
        Container a = get(indexA);
        a.setOrder(indexB+1);
        Container b = get(indexB);
        b.setOrder(indexA+1);
        set(indexA, b);
        set(indexB, a);
        
        return true;
        
        
        
        
    }
}