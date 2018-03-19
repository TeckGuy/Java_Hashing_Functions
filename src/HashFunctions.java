import sun.applet.Main;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashFunctions {
    /**
     * Define components here
     */

    URL urllogoimage = Main.class.getResource("/image/logo.png");
    //setting ma default image icon to my frames
    Image iconimage = new ImageIcon(urllogoimage).getImage();


    JLabel labelHash, Title, results;
    JButton btn_Hash;
    JTextField textHash;
    JTextArea hashedResults;
    private JComboBox hashingAlgos;
    ScrollPane scrollPane;

    //panels
    JPanel pMain = new JPanel(new GridBagLayout());
    JPanel pFound = new JPanel(new GridBagLayout());
    JPanel pALL = new JPanel(new BorderLayout(0, 0));

    //frames
    JFrame UserFrame;

    String hashAlgo = null, plainText = null, generatedText = null;

    /**
     * Create Graphical user Interface
     */
    public void userInterFace() {
        Title = new JLabel("JAVA HASH FUNCTIONS");
        Title.setFont(new Font("Tahoma", Font.BOLD, 20));
        labelHash = new JLabel("Please Write Your Plain Text");
        labelHash.setFont(new Font("Tahoma", Font.BOLD, 15));
        results = new JLabel("CIPHER TEXT");
        results.setFont(new Font("Tahoma", Font.BOLD, 15));

        textHash = new JTextField(15);
        textHash.setFont(new Font("Tahoma", Font.PLAIN, 15));


        hashedResults = new JTextArea(5, 30);
        hashedResults.setEditable(false);
        hashedResults.setFont(new Font("Tahoma", Font.PLAIN, 15));
        hashedResults.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret) hashedResults.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane scrollpane = new JScrollPane();
        scrollpane.add(hashedResults);
        scrollpane.setViewportView(hashedResults);
        hashedResults.setWrapStyleWord(true);


        String[] hashingTpe = {"Choose Hashing Algorithm", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512"};
        hashingAlgos = new JComboBox(hashingTpe);
        hashingAlgos.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hashingAlgos.setFont(new Font("Tahoma", Font.PLAIN, 12));

        btn_Hash = new JButton("HASH PLAIN TEXT");
        btn_Hash.setBackground(Color.GREEN.darker());
        btn_Hash.setToolTipText("Reset Password");
        btn_Hash.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn_Hash.setFont(new Font("Tahoma", Font.BOLD, 15));

        hashingAlgos.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JComboBox<String> largment = (JComboBox<String>) event.getSource();
                hashAlgo = (String) largment.getSelectedItem();
                if (hashAlgo.equalsIgnoreCase("MD5")) {
                    //do something
                } else if (hashAlgo.equalsIgnoreCase("SHA-1")) {
                    //do something
                } else if (hashAlgo.equalsIgnoreCase("SHA-256")) {
                    //do something
                } else if (hashAlgo.equalsIgnoreCase("SHA-384")) {
                    //do something
                } else if (hashAlgo.equalsIgnoreCase("SHA-512")) {
                    //do something
                }
            }
        });


        /**
         * Define method to hash when button is clicked
         * */
        btn_Hash.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textHash.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Your Plain Text\nto hash", "Field Validation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (hashAlgo.equalsIgnoreCase("Choose Hashing Algorithm") || hashAlgo.equalsIgnoreCase(null)) {
                        JOptionPane.showMessageDialog(null, "Please Enter Choose Your\nHashing Algorithm.", "Validation", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        //get plain text
                        plainText = textHash.getText();
                        // Create MessageDigest instance for MD5
                        MessageDigest md = null;

                        try {
                            md = MessageDigest.getInstance(hashAlgo);
                        } catch (NoSuchAlgorithmException e1) {
                            e1.printStackTrace();
                        }

                        //Add password bytes to digest
                        md.update(plainText.getBytes());
                        //Get the hash's bytes
                        byte[] bytes = md.digest();
                        //This bytes[] has bytes in decimal format;
                        //Convert it to hexadecimal format
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < bytes.length; i++) {
                            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                        }
                        //Get complete hashed password in hex format
                        generatedText = sb.toString();

                        hashedResults.setText("");
                        hashedResults.setText(generatedText);
                    }
                }
            }
        });


        /**
         * Create JFrame and panel Here for user Interface
         * */

        //adding components to panels pMain
        GridBagConstraints v = new GridBagConstraints();
        v.anchor = GridBagConstraints.CENTER;
        v.insets = new Insets(0, 0, 20, 0);
        v.ipadx = 0;
        v.ipady = 0;
        v.gridx = 0;
        v.gridy = 0;
        pMain.add(Title, v);
        v.insets = new Insets(0, 0, 5, 0);
        v.gridy++;
        pMain.add(labelHash, v);
        v.gridy++;
        pMain.add(textHash, v);
        v.insets = new Insets(0, 0, 30, 0);
        v.gridy++;
        pMain.add(hashingAlgos, v);
        v.insets = new Insets(0, 0, 10, 0);
        //pMain.setBorder(new TitledBorder(""));

        //adding components to panels pFound
        v.anchor = GridBagConstraints.CENTER;
        v.insets = new Insets(0, 0, 0, 0);
        v.ipadx = 0;
        v.ipady = 0;
        v.gridx = 0;
        v.gridy = 0;
        pFound.add(results, v);
        v.gridy++;
        pFound.add(hashedResults, v);
        v.gridy++;
        pFound.add(btn_Hash, v);
        pFound.setBorder(new TitledBorder(""));

        //adding components to panels pALL
        pALL.add("North", pMain);
        pALL.add("Center", pFound);
        pALL.setBorder(new TitledBorder(""));


        //Set a Frame
        UserFrame = new JFrame("Hashing System");
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase", Color.blue);
        } catch (Exception c) {
        }
        UserFrame.setIconImage(iconimage);
        UserFrame.add(pALL);
        UserFrame.setVisible(true);
        UserFrame.setSize(800, 400);
        UserFrame.setLocationRelativeTo(null);
//        UserFrame.pack();
//        UserFrame.setResizable(false);
        UserFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

//    public static void main(String[]args){
//        HashFunctions hashFunctions = new HashFunctions();
//        hashFunctions.userInterFace();
//    }

}
