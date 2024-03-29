/*
 * 

 * Copyright (C)  2013 Rosendo R. Sosa. .
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 */
package uabc.edu.mx.gui;

import common.integration.ServiceLocator;
import java.awt.Component;
import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragGestureRecognizer;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetContext;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import uabc.xmpp.entities.OfUser;
import utils.Configuration;

/**
 *
 * @author Rosendo R. Sosa.
 */
public class UserSelection extends javax.swing.JDialog {

    private DynamicTree dinamic1;
    private DynamicTree dinamic2;
    TreeDragSource ds;
    TreeDropTargetForLeaf dt;
    TreeDragSource ds1;
    TreeDropTarget dt1;

    /**
     * Creates new form UserSelection
     */
    public UserSelection(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        if(Configuration.dynamicTreeTables==null&&Configuration.dynamicTreeUsers==null){
        dinamic1 = new DynamicTree("Usuarios");
        dinamic2 = new DynamicTree("Mesas");
        dinamic1.tree.setName("tree1");
        dinamic2.tree.setName("tree2");
        dinamic2.tree.addMouseListener(new PopClickListener());
        ds = new TreeDragSource(dinamic1.tree, DnDConstants.ACTION_COPY_OR_MOVE);
        dt = new TreeDropTargetForLeaf(dinamic2.tree, dinamic2.rootNode);
        populate();
        
        }else{
        
        dinamic2=Configuration.dynamicTreeTables;
        dinamic1=Configuration.dynamicTreeUsers;
        dinamic1.tree.setName("tree1");
        dinamic2.tree.setName("tree2");
        dinamic2.tree.addMouseListener(new PopClickListener());
        ds = new TreeDragSource(dinamic1.tree, DnDConstants.ACTION_COPY_OR_MOVE);
        dt = new TreeDropTargetForLeaf(dinamic2.tree, dinamic2.rootNode);
        }

         dinamic1.tree.setCellRenderer(new MyRenderer(dinamic1));

        dinamic2.tree.setCellRenderer(new MyRenderer(dinamic2));

        jSplitPane1.setLeftComponent(dinamic1.tree);
        jSplitPane1.setRightComponent(dinamic2.tree);
       

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cancelBttn = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane1.setDividerLocation(230);
        getContentPane().add(jSplitPane1);

        jMenu1.setText("Opciones");

        cancelBttn.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        cancelBttn.setText("Guardar");
        cancelBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBttnActionPerformed(evt);
            }
        });
        jMenu1.add(cancelBttn);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Cancelar");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBttnActionPerformed
        int childs = dinamic2.rootNode.getChildCount();
        ArrayList[] listas = new ArrayList[childs];
        for (int i = 0; i < childs; i++) {
            listas[i] = new ArrayList();
        }
        for (int i = 0; i < childs; i++) {

            TreeNode node = dinamic2.rootNode.getChildAt(i);
            int users = node.getChildCount();
            if (users > 0) {

                for (int j = 0; j < users; j++) {
                    listas[i].add(node.getChildAt(j).toString());
                    System.out.println(node.getChildAt(j).toString());
                }

                if (!Configuration.configToLaunch.containsKey(node.toString())) {
                    Configuration.configToLaunch.put(node.toString(), listas[i]);
                } else {
                    Configuration.configToLaunch.remove(node.toString());
                    Configuration.configToLaunch.put(node.toString(), listas[i]);

                }
                  Configuration.sessionDataToSend.setTableMTID(node.toString()+"@edumat");

            }
        }
        Configuration.dynamicTreeTables=dinamic2;
        Configuration.dynamicTreeUsers=dinamic1;
        JOptionPane.showMessageDialog(this, "Configuración Guardada");
        dispose();

    }//GEN-LAST:event_cancelBttnActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserSelection.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserSelection dialog = new UserSelection(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem cancelBttn;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JSplitPane jSplitPane1;
    // End of variables declaration//GEN-END:variables

    private void populate() {
        ServiceLocator.getInstance().setTipo(uabc.xmpp.entities.OfUser.class);
        List<OfUser> users = ServiceLocator.getInstance().findAll();
        for (OfUser ofUser : users) {
            String name = ofUser.getUsername();
            if (name.startsWith("mesa")) {
                dinamic2.addObject(name);
            } else {
                dinamic1.addObject(name);
            }
        }

    }

    class PopClickListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger()) {
                int selRow = dinamic2.tree.getRowForLocation(e.getX(), e.getY());

                if (selRow != -1) {
                    TreePath currentSelection = dinamic2.tree.getSelectionPath();
                    if (currentSelection != null) {
                        DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) currentSelection.getLastPathComponent();
                        if (currentNode.getLevel() == 2) {
                            doPop(e);
                        }
                    }
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        private void doPop(MouseEvent e) {
            PopUpDemo menu = new PopUpDemo();
            menu.show(e.getComponent(), e.getX(), e.getY());

        }
    }

    class PopUpDemo extends JPopupMenu implements ActionListener {

        JMenuItem anItem;

        public PopUpDemo() {
            anItem = new JMenuItem("Remover");
            anItem.addActionListener(this);
            add(anItem);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            DefaultMutableTreeNode node = dinamic2.removeCurrentNode();
            dinamic1.addObject(dinamic1.rootNode, node.getUserObject());
        }
    }
}

class TreeDragSource implements DragSourceListener, DragGestureListener {

    DragSource source;
    DragGestureRecognizer recognizer;
    TransferableTreeNode transferable;
    DefaultMutableTreeNode oldNode;
    JTree sourceTree;

    public TreeDragSource(JTree tree, int actions) {
        sourceTree = tree;
        source = new DragSource();
        recognizer = source.createDefaultDragGestureRecognizer(sourceTree,
                actions, this);
    }

    /*
     * Drag Gesture Handler
     */
    @Override
    public void dragGestureRecognized(DragGestureEvent dge) {
        TreePath path = sourceTree.getSelectionPath();
        if ((path == null) || (path.getPathCount() == 1)) {
            // We can't move the root node or an empty selection
            return;
        }
        oldNode = (DefaultMutableTreeNode) path.getLastPathComponent();
        transferable = new TransferableTreeNode(path);
        source.startDrag(dge, DragSource.DefaultMoveNoDrop, transferable, this);

        // If you support dropping the node anywhere, you should probably
        // start with a valid move cursor:
        //source.startDrag(dge, DragSource.DefaultMoveDrop, transferable,
        // this);
    }

    /*
     * Drag Event Handlers
     */
    @Override
    public void dragEnter(DragSourceDragEvent dsde) {
    }

    @Override
    public void dragExit(DragSourceEvent dse) {
    }

    @Override
    public void dragOver(DragSourceDragEvent dsde) {
    }

    @Override
    public void dropActionChanged(DragSourceDragEvent dsde) {
        System.out.println("Action: " + dsde.getDropAction());
        System.out.println("Target Action: " + dsde.getTargetActions());
        System.out.println("User Action: " + dsde.getUserAction());
    }

    @Override
    public void dragDropEnd(DragSourceDropEvent dsde) {
        /*
         * to support move or copy, we have to check which occurred:
         */
        System.out.println("Drop Action: " + dsde.getDropAction());
        if (dsde.getDropSuccess()
                && (dsde.getDropAction() == DnDConstants.ACTION_MOVE)) {
            ((DefaultTreeModel) sourceTree.getModel())
                    .removeNodeFromParent(oldNode);
        }

        /*
         * to support move only... if (dsde.getDropSuccess()) {
         * ((DefaultTreeModel)sourceTree.getModel()).removeNodeFromParent(oldNode); }
         */
    }
}

//TreeDropTarget.java
//A quick DropTarget that's looking for drops from draggable JTrees.
//
class TreeDropTarget implements DropTargetListener {

    DropTarget target;
    JTree targetTree;

    public TreeDropTarget(JTree tree) {
        targetTree = tree;
        target = new DropTarget(targetTree, this);
    }
    /*
     * Drop Event Handlers
     */
    boolean same2 = false;

    private TreeNode getNodeForEvent(DropTargetDragEvent dtde) {
        Point p = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        JTree tree = (JTree) dtc.getComponent();



        TreePath path = tree.getClosestPathForLocation(p.x, p.y);
        return (TreeNode) path.getLastPathComponent();
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getNodeForEvent(dtde);
        if (same2) {
            dtde.rejectDrag();

        } else if (node.isLeaf()) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(dtde.getDropAction());
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getNodeForEvent(dtde);
        if (same2) {
            dtde.rejectDrag();

        } else if (node.isLeaf()) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(dtde.getDropAction());
        }
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        Point pt = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        JTree tree = (JTree) dtc.getComponent();
        TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) parentpath
                .getLastPathComponent();
        if (parent.isLeaf()) {
            dtde.rejectDrop();
            return;
        }

        try {
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                if (tr.isDataFlavorSupported(flavors[i])) {
                    dtde.acceptDrop(dtde.getDropAction());
                    TreePath p = (TreePath) tr.getTransferData(flavors[i]);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) p
                            .getLastPathComponent();
                    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                    model.insertNodeInto(node, parent, 0);
                    dtde.dropComplete(true);
                    return;
                }
            }
            dtde.rejectDrop();
        } catch (Exception e) {
            dtde.rejectDrop();
        }
    }
// Then on your component(s)
}

//TreeDropTarget.java
//A quick DropTarget that's looking for drops from draggable JTrees.
//
class TreeDropTargetForLeaf implements DropTargetListener {

    DropTarget target;
    JTree targetTree;
    DefaultMutableTreeNode rootNode;
    boolean same1 = false;

    public TreeDropTargetForLeaf(JTree tree, DefaultMutableTreeNode rootNode) {
        targetTree = tree;
        target = new DropTarget(targetTree, this);
        this.rootNode = rootNode;
    }

    /*
     * Drop Event Handlers
     */
    private TreeNode getNodeForEvent(DropTargetDragEvent dtde) {
        Point p = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        JTree tree = (JTree) dtc.getComponent();

        TreePath path = tree.getClosestPathForLocation(p.x, p.y);
        return (TreeNode) path.getLastPathComponent();
    }

    @Override
    public void dragEnter(DropTargetDragEvent dtde) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getNodeForEvent(dtde);
        System.out.println("NIVEL:" + node.getLevel());
        if (same1) {
            dtde.rejectDrag();

        } else if (node.getLevel() > 1) {
            dtde.rejectDrag();

        } else if (node == rootNode) {
            dtde.rejectDrag();
        } else {
            dtde.acceptDrag(dtde.getDropAction());
        }
    }

    @Override
    public void dragOver(DropTargetDragEvent dtde) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getNodeForEvent(dtde);
        if (same1) {
            dtde.rejectDrag();

        } else if (node.getLevel() > 1) {
            dtde.rejectDrag();

        } else if (node == rootNode) {
            dtde.rejectDrag();
        } else {
            // start by supporting move operations
            //dtde.acceptDrag(DnDConstants.ACTION_MOVE);
            dtde.acceptDrag(dtde.getDropAction());
        }
    }

    @Override
    public void dragExit(DropTargetEvent dte) {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent dtde) {
    }

    @Override
    public void drop(DropTargetDropEvent dtde) {
        Point pt = dtde.getLocation();
        DropTargetContext dtc = dtde.getDropTargetContext();
        JTree tree = (JTree) dtc.getComponent();
        TreePath parentpath = tree.getClosestPathForLocation(pt.x, pt.y);
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) parentpath
                .getLastPathComponent();
        if (parent == rootNode && parent.getLevel() != 1) {
            dtde.rejectDrop();
            return;
        }

        try {
            Transferable tr = dtde.getTransferable();
            DataFlavor[] flavors = tr.getTransferDataFlavors();
            for (int i = 0; i < flavors.length; i++) {
                if (tr.isDataFlavorSupported(flavors[i])) {
                    dtde.acceptDrop(dtde.getDropAction());
                    TreePath p = (TreePath) tr.getTransferData(flavors[i]);
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) p
                            .getLastPathComponent();
                    DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                    model.insertNodeInto(node, parent, 0);
                    dtde.dropComplete(true);
                    return;
                }
            }
            dtde.rejectDrop();
        } catch (Exception e) {
            e.printStackTrace();
            dtde.rejectDrop();
        }
    }
}

//TransferableTreeNode.java
//A Transferable TreePath to be used with asdfasdf & Drop applications.
//
class TransferableTreeNode implements Transferable {

    public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class,
            "Tree Path");
    DataFlavor flavors[] = {TREE_PATH_FLAVOR};
    TreePath path;

    public TransferableTreeNode(TreePath tp) {
        path = tp;
    }

    @Override
    public synchronized DataFlavor[] getTransferDataFlavors() {
        System.out.println(flavors.toString());
        return flavors;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return (flavor.getRepresentationClass() == TreePath.class);
    }

    @Override
    public synchronized Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return (Object) path;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}

class MyRenderer extends DefaultTreeCellRenderer {

    Icon tutorialIcon;
    DynamicTree dynamicTree;

    public MyRenderer(DynamicTree tree) {
        dynamicTree = tree;
    }

    @Override
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);

        DefaultMutableTreeNode node =
                (DefaultMutableTreeNode) value;

        if (dynamicTree.tree.getName().equalsIgnoreCase("tree1")) {

            if (leaf) {
                tutorialIcon = new ImageIcon(this.getClass().getResource("/res/usericon.png"));;
                setIcon(tutorialIcon);
            } else {
                tutorialIcon = new ImageIcon(this.getClass().getResource("/res/group.png"));;
                setIcon(tutorialIcon);
            }
        } else {
            if (leaf && (!node.isRoot()) && (node.getLevel() >= 2)) {
                tutorialIcon = new ImageIcon(this.getClass().getResource("/res/usericon.png"));
                setIcon(tutorialIcon);
            } else {
                if (node.isRoot()) {

                    tutorialIcon = new ImageIcon(this.getClass().getResource("/res/intranet.png"));
                    setIcon(tutorialIcon);
                } else {
                    tutorialIcon = new ImageIcon(this.getClass().getResource("/res/mticon.png"));
                    setIcon(tutorialIcon);

                }

            }

        }


        return this;
    }
}
