package Interface;

import ReactorsRelated.Reactor;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class GUI extends javax.swing.JFrame {
    
    DefaultTreeModel model;
    Manager manager = new Manager();

    public GUI() {
        initComponents();
    }
    DefaultMutableTreeNode reactors = new DefaultMutableTreeNode("Reactors");

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        openFile = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ReactorsTree = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        openFile.setText("Open File");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        ReactorsTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(ReactorsTree);
        ReactorsTree.getAccessibleContext().setAccessibleParent(ReactorsTree);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openFile, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(openFile, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(223, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        int response = chooser.showOpenDialog(null);
        if (chooser.getSelectedFile() == null) {
            System.out.println("Choose a file!");
        } else {
            manager.setList(new File(chooser.getSelectedFile().getAbsolutePath()));
            for (Reactor reactor : manager.getList()) {
                DefaultMutableTreeNode concreteReactor = new DefaultMutableTreeNode(reactor.getType());
                for (String parameter : reactor.getParameters()){
                    concreteReactor.add(new DefaultMutableTreeNode(parameter));
                }
                reactors.add(concreteReactor);
                model = (DefaultTreeModel) ReactorsTree.getModel();
                model.setRoot(reactors);
                ReactorsTree.setModel(model);
            }
        }
    }//GEN-LAST:event_openFileActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree ReactorsTree;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton openFile;
    // End of variables declaration//GEN-END:variables
}
