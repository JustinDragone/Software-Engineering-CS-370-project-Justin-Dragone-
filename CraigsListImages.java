//import classes for the gui
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

//declare class that will implement a actionlistener for gui and display image 
public final class CraigsListImages implements ActionListener {
  //the image being taken in
    private BufferedImage image;     
    //the frame being viewed on screen 
    private JFrame frame;              
    //the filename being taken in
    private String filename;           
    
    // the picture in the filename is declared
    public CraigsListImages(String filename) {
        this.filename = filename;
        try {
            // sets up new File 
            File file = new File(filename);
            // if file is correct it reads the file
            if (file.isFile()) {
                image = ImageIO.read(file);
            }

            // read the file from the url 
            else {
                URL url = getClass().getResource(filename);
                if (url == null) { url = new URL(filename); }
                image = ImageIO.read(url);
            }
        }
        //if file can not open run runtimeexception and display could not open file
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + filename);
        }
    }
    
    //display the image from the file
    public CraigsListImages(File file) {
        try { image = ImageIO.read(file); }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + file);
        }
        if (image == null) {
            throw new RuntimeException("Invalid image file: " + file);
        }
    }
    
    //gui is set up for a jlabel
    public JLabel getJLabel() {
    	//no image is present
        if (image == null) { 
        	return null; 
        	}   
        ImageIcon icon = new ImageIcon(image);
        return new JLabel(icon);
    }
    
    //will display the image on the screen 
    public void show() {
    	//if there is no frame than create new jFrame
        if (frame == null) {
            frame = new JFrame();
            //creates the menu bar for saving the image to a location
            JMenuBar menuBar = new JMenuBar();
            JMenu menu = new JMenu("File");
            menuBar.add(menu);
            JMenuItem menuItem1 = new JMenuItem(" Save...   ");
            menuItem1.addActionListener(this);
            menuItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                     Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
            menu.add(menuItem1);
            frame.setJMenuBar(menuBar);
            frame.setContentPane(getJLabel());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle(filename);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
        }

        // draw image on screen
        frame.repaint();
    }
    
    // saves the picture  
    public void save(String name) {
        save(new File(name));
    }
    
    // saves the picture to a file 
    public void save(File file) {
        this.filename = file.getName();
        String suffix = filename.substring(filename.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        // the end must end in a .jpg or .png if not display error
        if (suffix.equals("jpg") || suffix.equals("png")) {
            try { ImageIO.write(image, suffix, file); }
            catch (IOException e) { e.printStackTrace(); }
        }
        // if it doesnt end in .jpg or .png then display error 
        else {
            System.out.println("Error: filename must end in .jpg or .png");
        }
    }
    
    // opens up a save box to save the image to a location on the computer
    // must use a .jpg or .png at the end of the name
    public void actionPerformed(ActionEvent e) {
        FileDialog chooser = new FileDialog(frame,
                             "Use a .png or .jpg extension", FileDialog.SAVE);
        chooser.setVisible(true);
    }
    
    //reads the picture from the command line and displays it
    // on the screen with the ability to save the image
    public static void main(String[] args) {
        CraigsListImages pic = new CraigsListImages(args[0]);
        pic.show();
    }//ends main

}// ends class
