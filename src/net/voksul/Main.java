package net.voksul;

import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.ExceptionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Chris Bitler on 10/4/2015
 */

public class Main extends Applet implements Runnable, KeyListener {
    public static Main instance;
    Graphics graphics;
    List<Building> buildings;
    Shield shield;
    List<Nuke> nukes;
    List<Explosion> explosions;
    Image dbImage;
    Graphics dbg;
    int points = 0;
    boolean firstRun = false;
    Image bg;
    Image grass;


    public void init()
    {
        addKeyListener(this);
        setFocusable(true);
        requestFocus();
        URL url = getClass().getResource("bg.jpg");
        URL url2 = getClass().getResource("grass.png");
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        bg = toolkit.getImage(url);
        grass = toolkit.getImage(url2);
    }
    public void start()
    {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(Graphics g) {
        if(dbImage == null) {
            dbImage = createImage(this.getSize().width,this.getSize().height);
            dbg = dbImage.getGraphics();
        }
        dbg.setColor(getBackground());
        dbg.fillRect(0,0,this.getSize().width,this.getSize().height);
        dbg.setColor(getForeground());
        paint(dbg);
        g.drawImage(dbImage,0,0,this);
    }

    public void paint(Graphics g) {
        if (firstRun) {
            if (buildings.size() > 0) {
                g.setColor(Color.darkGray);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                for (int y = 0; y < this.getHeight() + 400; y += 160) {
                    for (int x = 0; x < this.getWidth() + 400; x += 160) {
                        g.drawImage(bg, x, y, null);
                    }
                }

                g.setColor(Color.YELLOW);
                Font font = new Font("Calibri", 0, 20);
                g.setFont(font);
                g.drawString("Points: " + points, 0, 20);
                Font font2 = new Font("Calibri", 0, 30);
                g.setColor(Color.RED);
                g.drawString("North Korea Nuke Simulator 2015", Main.instance.getSize().width / 2 - 150, Main.instance.getSize().height / 2);
                //g.setColor(Color.GREEN);
                //g.fillRect(0,this.getHeight()-30,this.getWidth(),30);
                for (int x = 0; x <= this.getWidth(); x += 10) {
                    g.drawImage(grass, x, this.getHeight() - 30, null);
                }
                for (Nuke nuke : nukes) {
                    nuke.draw(g);
                }
                shield.draw(g);
                for (Building building : buildings) {
                    building.draw(g);
                }
                Iterator<Explosion> explosIter = explosions.iterator();
                while (explosIter.hasNext()) {
                    Explosion explos = explosIter.next();
                    explos.draw(g);
                    if (explos.frame >= 1250) {
                        explosIter.remove();
                    }
                }
            } else {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, this.getWidth(), this.getHeight());
                Font font2 = new Font("Calibri", 0, 30);
                g.setFont(font2);
                g.setColor(Color.RED);
                g.drawString("You have lost. North Korea has won. North Korea Best Korea! Refresh to play again.", 0, 30);
                g.drawString("Your points: " + points, 0, 60);
            }
        }
    }

    @Override
    public void run() {
        instance = this;
        buildings = new ArrayList<>();
        shield = new Shield();
        explosions = new ArrayList<Explosion>();
        nukes = new ArrayList<>();
        //nukes.add(new Nuke(100,0,140));
        /*Building building = new Building();
        building.setX(200);
        building.setY(this.getHeight()-(building.getHeight()+30));
        buildings.add(building);*/
        Random r = new Random();
        for(int i = 0; i < 20; i++) {
            Building building = new Building();
            building.setX(r.nextInt(this.getWidth()));
            building.setY(this.getHeight() - (building.getHeight() + 30));
            buildings.add(building);
        }
        firstRun = true;
        while(true) {
            Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
            if(r.nextInt(250) == 12 && buildings.size() > 0) {
                nukes.add(new Nuke(r.nextInt(this.getWidth()),0,0));
            }
            List<Nuke> nukesRemoval = new ArrayList<>();
            List<Building> buildingRemoval = new ArrayList<>();
            for(Nuke nuke : nukes) {
                nuke.ypos += 2;
                for(Building building_ : buildings) {
                    if(nuke.getBoundingBox().collides(building_.getBoundingBox())) {
                        buildingRemoval.add(building_);
                        nukesRemoval.add(nuke);
                    }
                }
                if(nuke.getBoundingBox().collides(shield.getBoundingBox()) ||
                        nuke.ypos > Main.instance.getHeight()-60) {
                    if(!nukesRemoval.contains(nuke)) {
                        nukesRemoval.add(nuke);
                        if (nuke.getBoundingBox().collides(shield.getBoundingBox())) {
                            points += 100;
                        }
                    }
                }
            }
            for(Nuke nuke : nukesRemoval) {
                nukes.remove(nuke);
                explosions.add(new Explosion((int) nuke.xpos - nuke.width/2,(int) nuke.ypos));
            }
            for(Building build : buildingRemoval) {
                buildings.remove(build);
                if(buildings.size() == 0) {
                    //System.exit(0);
                    nukes.clear();
                }
            }
            nukesRemoval.clear();
            repaint();
            try {
                Thread.currentThread().sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            shield.x-=25;
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            shield.x+=25;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
