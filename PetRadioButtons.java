import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class PetRadioButtons extends JFrame {

    // These will store our pictures (we'll load them later)
    private JLabel pictureLabel;
    
    // Radio buttons - one for each pet
    private JRadioButton birdButton;
    private JRadioButton catButton;
    private JRadioButton dogButton;
    private JRadioButton rabbitButton;
    private JRadioButton pigButton;

    public PetRadioButtons() {
        // Basic window setup
        setTitle("Choose Your Pet");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Make things appear nicely
        setLayout(new FlowLayout());

        // Create the group so only one button can be selected at a time
        ButtonGroup petGroup = new ButtonGroup();

        // Create the radio buttons
        birdButton   = new JRadioButton("Bird", false);
        catButton    = new JRadioButton("Cat", false);
        dogButton    = new JRadioButton("Dog", false);
        rabbitButton = new JRadioButton("Rabbit", false);
        pigButton    = new JRadioButton("Pig", true);   // Pig starts selected

        // Add all buttons to the group (important!)
        petGroup.add(birdButton);
        petGroup.add(catButton);
        petGroup.add(dogButton);
        petGroup.add(rabbitButton);
        petGroup.add(pigButton);

        // Add buttons to the window
        add(birdButton);
        add(catButton);
        add(dogButton);
        add(rabbitButton);
        add(pigButton);

        // Area where the picture will appear
        pictureLabel = new JLabel();
        pictureLabel.setPreferredSize(new Dimension(280, 220));
        add(pictureLabel);

        // Listen to ALL radio buttons (very simple beginner way)
        birdButton.addActionListener(new PictureListener());
        catButton.addActionListener(new PictureListener());
        dogButton.addActionListener(new PictureListener());
        rabbitButton.addActionListener(new PictureListener());
        pigButton.addActionListener(new PictureListener());

        // Show pig picture when program starts
        showPigPicture();

        // Center the window on screen
        setLocationRelativeTo(null);
    }

    // Helper method to load and scale an image to fit the label (280x220)
    private ImageIcon loadScaledImage(String path) {
        try {
            // Read the full-size image
            BufferedImage fullImage = ImageIO.read(new File(path));
            
            // Get the label dimensions
            int labelWidth = 280;
            int labelHeight = 220;
            
            // Calculate the scaled dimensions while maintaining aspect ratio
            int originalWidth = fullImage.getWidth();
            int originalHeight = fullImage.getHeight();
            
            double widthRatio = (double) labelWidth / originalWidth;
            double heightRatio = (double) labelHeight / originalHeight;
            double ratio = Math.min(widthRatio, heightRatio);
            
            int scaledWidth = (int) (originalWidth * ratio);
            int scaledHeight = (int) (originalHeight * ratio);
            
            // Scale the image
            Image scaledImage = fullImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            System.err.println("Error loading image: " + path);
            e.printStackTrace();
            return null;
        }
    }

    // Very simple class that runs when any button is clicked
    class PictureListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (birdButton.isSelected()) {
                showBirdPicture();
            }
            else if (catButton.isSelected()) {
                showCatPicture();
            }
            else if (dogButton.isSelected()) {
                showDogPicture();
            }
            else if (rabbitButton.isSelected()) {
                showRabbitPicture();
            }
            else if (pigButton.isSelected()) {
                showPigPicture();
            }
        }
    }

    // These methods show different pictures
    private void showBirdPicture() {
        ImageIcon icon = loadScaledImage("c:/Users/ALEX/Documents/NetBeansProjects/ZU/bird.png");
        pictureLabel.setIcon(icon);
    }

    private void showCatPicture() {
        ImageIcon icon = loadScaledImage("c:/Users/ALEX/Documents/NetBeansProjects/ZU/cat.png");
        pictureLabel.setIcon(icon);
    }

    private void showDogPicture() {
        ImageIcon icon = loadScaledImage("c:/Users/ALEX/Documents/NetBeansProjects/ZU/dog.png");
        pictureLabel.setIcon(icon);
    }

    private void showRabbitPicture() {
        ImageIcon icon = loadScaledImage("c:/Users/ALEX/Documents/NetBeansProjects/ZU/rabbit.png");
        pictureLabel.setIcon(icon);
    }

    private void showPigPicture() {
        ImageIcon icon = loadScaledImage("c:/Users/ALEX/Documents/NetBeansProjects/ZU/pig.png");
        pictureLabel.setIcon(icon);
    }

    // MAIN METHOD - this starts the program
    public static void main(String[] args) {
        // Make the window appear
        PetRadioButtons window = new PetRadioButtons();
        window.setVisible(true);
    }
}