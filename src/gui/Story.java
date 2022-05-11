/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import Chatapp.App;
import Chatapp.User;

import java.awt.Color;
import java.awt.Image;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Owner
 */
public class Story extends javax.swing.JFrame {

    /**
     * Creates new form Story
     */
    public Story() {
        try {
            initComponents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws SQLException {

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setPreferredSize(new java.awt.Dimension(680, 530));
        setResizable(false);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(null);

        jList1.setBackground(new java.awt.Color(102, 102, 102));
        jList1.setFont(new java.awt.Font("Arial Black", 1, 20)); // NOI18N
        jList1.setForeground(new java.awt.Color(0, 0, 0));

        //ArrayList<User> contacts;
        DefaultListModel dlm=new DefaultListModel();
        App.loadContacts();
        //if(App.loggedUser.getContacts().size() != 0 || App.loggedUser.getContacts() !=null ){
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "password");
        Statement statement = connection.createStatement();
        ResultSet rst = statement.executeQuery("select * FROM contacts where adder_id = " + App.loggedUser.getId());
        ArrayList<Integer> contact_ids= new ArrayList<>();
        while(rst.next()) {
            int ids = rst.getInt("added_id");
            contact_ids.add(ids);
        }
        rst = statement.executeQuery("SELECT * FROM story");
        ArrayList<Integer> story_id = new ArrayList<>();
        while(rst.next()) {
            int ids = rst.getInt("user_id");
            story_id.add(ids);
        }
        ArrayList<Integer> story_ids= App.removeDuplicates(story_id);
        for (int contactId : contact_ids) {
            for(int storyId : story_ids){
                if(contactId == storyId){
                    rst = statement.executeQuery("SELECT * FROM contacts WHERE added_id = " + contactId);
                    rst.next();
                    dlm.addElement("" + rst.getString("name"));
                    break;
                }
            }
        }
        jList1.setModel(dlm);
//        jList1.setModel(new javax.swing.AbstractListModel<String>() {
//            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
//            public int getSize() { return strings.length; }
//            public String getElementAt(int i) { return strings[i]; }
//        });
        jList1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jList1ComponentShown(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(10, 110, 230, 370);

        jTextArea1.setBackground(new java.awt.Color(102, 102, 102));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(248, 110, 422, 180);

        jButton1.setBackground(new java.awt.Color(51, 51, 51));
        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(520, 440, 140, 32);

        jButton2.setBackground(new java.awt.Color(51, 51, 51));
        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Previous Story");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(247, 300, 140, 32);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/OIP.jpeg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 10, 90, 90);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Arabic Typesetting", 1, 40)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("rouf");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(120, 36, 130, 40);

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Add Your Story");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(270, 40, 150, 32);

        jButton4.setBackground(new java.awt.Color(51, 51, 51));
        jButton4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Next Story");
        getContentPane().add(jButton4);
        jButton4.setBounds(530, 300, 140, 32);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/background.jpg"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, -10, 700, 540);

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    jList1MouseClicked(evt);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1ComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jList1ComponentShown
        // TODO add your handling code here:
        jList1.setBackground(new Color(204, 204, 204));
        jTextArea1.setBackground(new Color(204, 204, 204));
    }//GEN-LAST:event_jList1ComponentShown

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        UserForm user = new UserForm();
        ImageIcon img = new ImageIcon("D:\\Work\\chatapp\\chat-icon.jpg");
        user.setIconImage(img.getImage());
        user.setTitle("i-chat");
        user.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        AddYourStory add = new AddYourStory();
        ImageIcon img = new ImageIcon("D:\\Work\\chatapp\\chat-icon.jpg");
        add.setIconImage(img.getImage());
        add.setTitle("i-chat");
        add.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        // TODO add your handling code here:
        setSize(695, 530);
        ImageIcon img = new ImageIcon("D:\\Downloads\\Levi Ackerman .jpg");
        Image image = img.getImage();
        Image modifiedImage = image.getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
        img = new ImageIcon(modifiedImage);
        jLabel1.setIcon(img);
    }//GEN-LAST:event_formComponentShown

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) throws SQLException {
        // TODO add your handling code here:
        JList list = (JList)evt.getSource();
        if (evt.getClickCount() == 1) {
            //print the name in console
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chatapp", "root", "password");
            Statement statement = connection.createStatement();
            String names = String.valueOf(list.getSelectedValue());
            System.out.println(names);
            ResultSet rst = statement.executeQuery("SELECT added_id FROM contacts WHERE name = '" +  names+"'");
            rst.next();
            int Id = Integer.parseInt(rst.getString("added_id"));
            User contact = new User(Id);
            App app = new App();
            app.loadStories(contact);
            contact.show_stories();


        }
    }

    /**
//     * @param args the command line arguments
     */
    public void showLog(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Story story = new Story();
                story.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
