package controller;

import entity.Level;
import entity.User;
import model.LevelDao;
import model.UserDao;
import view.UserPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class UserController {
    UserPanel userPanel;

    ArrayList<Level> levels = LevelDao.getLevels();

    public static void main(String[] args) {
        new UserController();
    }

    public UserController() {
        this.userPanel = new UserPanel(this);
        this.userPanel.setTitle("User Panel");
        this.userPanel.setSize(new Dimension(1280, 720));

        this.userPanel.setContentPane(new UserPanel(this).$$$getRootComponent$$$());

        viewGetUsers(userPanel);

        this.userPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.userPanel.setVisible(true);
    }

    public void viewGetUsers(UserPanel userPanel){
        ArrayList<User> users = UserDao.getUsers();
        if (!users.isEmpty())
        {
            int posY = 0;

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridBagLayout());

            for (User user : users) {
                posY = posY + 1;

                String actualLevel = levels.stream().filter((level -> level.getId() == user.getIdActualLevel())).findFirst().orElseThrow().getLabel();

                JLabel imageLabel = new JLabel();
                JLabel nameLabel = new JLabel(user.getName());
                JLabel firstNameLabel = new JLabel(user.getFirstName());
                JLabel emailLabel = new JLabel(user.getEmail());
                JLabel groupLabel = new JLabel(user.getGroupName());
                JLabel promoLabel = new JLabel(user.getPromoName());
                JLabel actuaLevelLabel = new JLabel(actualLevel);
                JLabel experienceLabel = new JLabel(Integer.toString(user.getExperience()));
                JTextField expToEdit = new JTextField();
                JButton addButton = new JButton("Add experience");
                JButton removeButton = new JButton("Remove experience");

                // Insert image
                Image image = null;
                URL url = null;

                try {
                    url = new URL(user.getSkin());
                    image = ImageIO.read(url);
                    image = image.getScaledInstance(75, 75, image.SCALE_DEFAULT);
                } catch (MalformedURLException ex) {
                    System.out.println("Malformed URL");
                } catch (IOException iox) {
                    System.out.println("Can not load file");
                }

                if (image == null) throw new AssertionError();
                imageLabel.setIcon(new ImageIcon(image));

                GridBagConstraints gbc;

                gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(imageLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 1;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(nameLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 2;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(firstNameLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 3;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(emailLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 4;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(groupLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 5;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(promoLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 6;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(actuaLevelLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 7;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(experienceLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 8;
                gbc.gridy = posY;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(expToEdit, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 9;
                gbc.gridy = posY;
                contentPanel.add(addButton, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 10;
                gbc.gridy = posY;
                contentPanel.add(removeButton, gbc);

                JLabel statusLabel = new JLabel();
                statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
                gbc = new GridBagConstraints();
                gbc.gridy = posY + 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(statusLabel, gbc);

                removeButton.addActionListener(e -> {
                    if (expToEdit.getText().matches("[0-9]+"))
                    {
                        if (user.getExperience() >= Integer.parseInt(expToEdit.getText()))
                        {
                            user.setExperience(user.getExperience() - Integer.parseInt(expToEdit.getText()));
                            viewUpdateUserExp(user);
                            statusLabel.setText("");
                        }
                        else
                        {
                            statusLabel.setText("Can't remove more XP than the user have.");
                        }
                    }
                    else
                    {
                        statusLabel.setText("Enter correct values.");
                    }
                });

                addButton.addActionListener(e -> {
                    if (expToEdit.getText().matches("[0-9]+"))
                    {
                        user.setExperience(user.getExperience() + Integer.parseInt(expToEdit.getText()));
                        viewUpdateUserExp(user);
                        statusLabel.setText("");
                    }
                    else
                    {
                        statusLabel.setText("Enter correct values.");
                    }
                });

            }

            GridBagConstraints gbc;

            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            userPanel.add(contentPanel, gbc);
        }
        else
        {
            userPanel.add(new JLabel("NO USER FOUND !"));
        }
    }

    public void viewUpdateUserExp(User user)
    {
        UserDao.updateUserExp(user);
    }
}
