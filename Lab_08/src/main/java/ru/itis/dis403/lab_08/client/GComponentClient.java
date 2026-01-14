package ru.itis.dis403.lab_08.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GComponentClient extends JComponent {

    private int x, y, bounds = 64;
    private boolean move_up, move_left;
    private int speed = 3;
    private int bYS = 300, bYC = 300;
    private Image image;
    private Socket socket;

    public GComponentClient() throws IOException {

        socket = new Socket("127.0.0.1", 50000);

        image = new ImageIcon("Lab_08/ball.png").getImage();

        setDoubleBuffered(true);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                bYC = Math.min(e.getY(), getHeight() - 200);
            }
        });

        new Thread(() -> {
            while (true) {
                try {
                    DataInputStream dis = new DataInputStream(socket.getInputStream());
                    bYS = dis.readInt();
                    x = dis.readInt();
                    y = dis.readInt();
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeInt(bYC);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                repaint();
            }
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println(bYS);
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(image,x,y,null);

        g2d.fillRect(10, bYS, 10, 200);
        g2d.fillRect(this.getWidth() - 20, bYC, 10, 200);

        if (x < 0) {
            g2d.setColor(Color.RED);
            g2d.fillRect(0, 0, 4, getHeight());
        }
        if (x > getWidth() - bounds) {
            g2d.setColor(Color.RED);
            g2d.fillRect(getWidth() - 4, 0, 4, getHeight());
        }
        g2d.setColor(Color.BLACK);

        g2d.dispose();

        Toolkit.getDefaultToolkit().sync();
    }
}