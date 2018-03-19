/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Welcome {

    //getting images
    URL url4 = Main.class.getResource("/image/tech.jpg");
    URL urllogo = Main.class.getResource("/image/logo.png");
    //setting images
    ImageIcon image4 = new ImageIcon(url4);

    //setting ma default image icon to my frames
    Image iconimage = new ImageIcon(urllogo).getImage();

    //getting components;
    JProgressBar current;
    JLabel splashimg, slogan, pwby;

    //setting panels
    JPanel panelbar = new JPanel(new GridBagLayout());

    //setting Frame
    JFrame Floadingbar;
    int num = 0;


    private void LoadingBar() {
        slogan = new JLabel("Hashing Power");
        slogan.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 10));
        slogan.setForeground(Color.BLACK);
        pwby = new JLabel("Powered By TecksolKE");
        pwby.setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 12));
        pwby.setForeground(Color.BLACK);
        splashimg = new JLabel(image4);

        //progressbar
        current = new JProgressBar(0, 2000);
        current.setBorder(null);
        current.setBackground(Color.lightGray);
        current.setForeground(Color.black);
        current.setValue(0);
        current.setPreferredSize(new Dimension(500, 6));
        current.setStringPainted(false);
        //current.setForeground(Color.blue.darker());

        //adding components to panels pMain
        GridBagConstraints v = new GridBagConstraints();
        v.anchor = GridBagConstraints.CENTER;
        v.insets = new Insets(0, 0, 0, 0);
        v.ipadx = 0;
        v.ipady = 0;
        v.gridx = 0;
        v.gridy = 0;
        panelbar.add(splashimg, v);
        v.insets = new Insets(0, 0, 0, 0);
        v.gridy++;
        v.anchor = GridBagConstraints.SOUTH;
        panelbar.add(current, v);
        v.anchor = GridBagConstraints.EAST;
        v.insets = new Insets(10, 0, 0, 10);
        v.gridy++;
        panelbar.add(slogan, v);
        v.insets = new Insets(0, 0, 2, 10);
        v.gridy++;
        panelbar.add(pwby, v);
        panelbar.setBackground(Color.lightGray);

        while (num < 2000) {
            current.setValue(num);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            num += 95;
            //System.out.println(num);
            if (num == 95) {
                //frame code
                Floadingbar = new JFrame("Java Hashing Program");
                try {
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    UIManager.put("nimbusBase", Color.blue);
                } catch (Exception c) {
                }
                Floadingbar.setUndecorated(true);
                Floadingbar.setIconImage(iconimage);
                Floadingbar.add(panelbar);
                Floadingbar.setVisible(true);
                Floadingbar.setSize(500, 382);
                Floadingbar.setLocationRelativeTo(null);
                Floadingbar.revalidate();
                Floadingbar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                Floadingbar.setBackground(Color.black);
                //end of frame code
            }
            if (num == 2090) {
                Floadingbar.setVisible(false);
                HashFunctions hashFunctions = new HashFunctions();
                hashFunctions.userInterFace();

            }
        }

    }

    public static void main(String[] args) {
        Welcome wl = new Welcome();
        wl.LoadingBar();
    }
}
