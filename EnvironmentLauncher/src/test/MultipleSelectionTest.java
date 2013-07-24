package test;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MultipleSelectionTest extends JFrame {
 private JList lstColor, lstCopy;
 private JButton btnCopy;
 private final String arrColorName[] =
  { "Black","Blue","Cyan","Dark Gray","Gray","Green","Light Gray",
    "Magenta","Orange","Pink","Red","Yellow","White"
  };

 public MultipleSelectionTest() {
  super ("Multiple Selection List");  

  Container container = getContentPane();
  container.setLayout(new FlowLayout());

  lstColor = new JList (arrColorName);
  lstColor.setVisibleRowCount(5);
 lstColor.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
  container.add(new JScrollPane (lstColor));

  btnCopy = new JButton ("Copy >>>");  

  btnCopy.addActionListener(
   new ActionListener() {
   public void actionPerformed (ActionEvent e) {
   lstCopy.setListData(lstColor.getSelectedValues());
    }
   } //end of class
  );

  container.add(btnCopy);  

  lstCopy = new JList();
  lstCopy.setVisibleRowCount(5);
  lstCopy.setFixedCellHeight(15);
  lstCopy.setFixedCellWidth(100);
 lstCopy.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
  container.add(new JScrollPane(lstCopy))  ;

  setSize (400,300);
  setLocationRelativeTo(null);
  setVisible(true);
 }

 public static void main (String args[]) {
     MultipleSelectionTest test = new MultipleSelectionTest();
     test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
