package controller;

import entity.Level;
import model.LevelDao;
import view.LevelPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LevelController {

    LevelPanel levelPanel;

    ArrayList<Level> globalLevels;

    public static void main(String[] args) {
        new LevelController();
    }

    public LevelController() {
        // Initialize admin panel
        this.levelPanel = new LevelPanel(this);
        this.levelPanel.setTitle("Level Panel");
        this.levelPanel.setSize(new Dimension(1280, 720));

        this.levelPanel.setContentPane(new LevelPanel(this).$$$getRootComponent$$$());

        viewGetLevels(levelPanel);

        this.levelPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.levelPanel.setVisible(true);
    }

    public void viewGetLevels(LevelPanel levelPanel) {
        ArrayList<Level> levels = LevelDao.getLevels();
        if (!levels.isEmpty())
        {
            globalLevels = levels;
            int i = 0;

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridBagLayout());

            for (Level level : levels) {
                i = i + 1;

                JLabel imageLabel = new JLabel();
                JTextField nameField = new JTextField(level.getLabel());
                JTextField descriptionField = new JTextField(level.getDescription());
                JTextField priceField = new JTextField(Integer.toString(level.getCostXp()));
                JTextField requiredXpField = new JTextField(Integer.toString(level.getRequiredXp()));
                JButton editButton = new JButton("Edit");
                JButton removeButton = new JButton("Remove");

                // Insert image
                Image image = null;
                URL url = null;

                try {
                    url = new URL(level.getSkin());
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
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(imageLabel, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 1;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(nameField, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 2;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(descriptionField, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 3;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(priceField, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 3;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(priceField, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 4;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(requiredXpField, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 5;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                contentPanel.add(editButton, gbc);

                JLabel statusLabel = new JLabel();
                statusLabel.setHorizontalAlignment(JLabel.CENTER);
                gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                levelPanel.add(statusLabel, gbc);

                editButton.addActionListener(e -> {
                    if (!priceField.getText().isEmpty() && !descriptionField.getText().isEmpty() && !nameField.getText().isEmpty() && !requiredXpField.getText().isEmpty())
                    {
                        if (priceField.getText().matches("[0-9]+") && requiredXpField.getText().matches("[0-9]+"))
                        {
                            Level level1 = new Level();
                            level1.setId(level.getId());
                            level1.setCostXp(Integer.parseInt(priceField.getText()));
                            level1.setDescription(descriptionField.getText());
                            level1.setLabel(nameField.getText());
                            level1.setRequiredXp(Integer.parseInt(requiredXpField.getText()));

                            viewUpdateLevel(level1);
                        }
                        else
                        {
                            statusLabel.setText("Enter correct values.");
                        }
                    }
                    else
                    {
                        statusLabel.setText("Some fields are empty.");
                    }

                });

                gbc = new GridBagConstraints();
                gbc.gridx = 6;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                contentPanel.add(removeButton, gbc);

                removeButton.addActionListener(e -> {
                    viewRemoveLevel(level);
                });
            }

            GridBagConstraints gbc;
            gbc = new GridBagConstraints();

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            levelPanel.add(contentPanel, gbc);

            JButton showAdd = new JButton("Add level");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            levelPanel.add(showAdd, gbc);

            showAdd.addActionListener(e -> {
                showAddForm(levelPanel, 1);
                showAdd.setVisible(false);
            });
        }
        else
        {
            levelPanel.add(new JLabel("NO LEVEL FOUND !"));
        }

    }

    public void viewUpdateLevel(Level level)
    {
        LevelDao.updateLevel(level);
    }

    public void viewCreateLevel(Level level) {
        LevelDao.createLevel(level);
    }

    public void viewRemoveLevel(Level level)
    {
        LevelDao.removeLevel(level);
    }

    public void showAddForm(LevelPanel levelPanel, int posY)
    {
        GridBagConstraints gbc;

        JPanel contentPanel = new JPanel();

        JTextField labelField = new JTextField("Label");
        labelField.setColumns(20);
        JTextField costXpField = new JTextField("XP cost");
        costXpField.setColumns(20);
        JTextField nbAvailableField = new JTextField("XP required");
        costXpField.setColumns(20);
        JTextField descriptionField = new JTextField("Description");
        descriptionField.setColumns(20);
        JTextField skinField = new JTextField("Image URL");
        skinField.setColumns(20);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(labelField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(costXpField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(nbAvailableField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(descriptionField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(skinField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = posY;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        levelPanel.add(contentPanel, gbc);

        JLabel statusLabel = new JLabel();
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = posY + 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        levelPanel.add(statusLabel, gbc);

        JButton confirmAdd = new JButton("Confirm");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = posY + 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        levelPanel.add(confirmAdd, gbc);

        confirmAdd.addActionListener(e -> {
            if (!labelField.getText().isEmpty() && !costXpField.getText().isEmpty() && !nbAvailableField.getText().isEmpty() && !descriptionField.getText().isEmpty() && !skinField.getText().isEmpty())
            {
                if (costXpField.getText().matches("[0-9]+") && nbAvailableField.getText().matches("[0-9]+"))
                {
                    Level level = new Level();
                    level.setLabel(labelField.getText());
                    level.setCostXp(Integer.parseInt(costXpField.getText()));
                    level.setRequiredXp(Integer.parseInt(nbAvailableField.getText()));
                    level.setDescription(descriptionField.getText());
                    level.setSkin(skinField.getText());

                    viewCreateLevel(level);

                    statusLabel.setText("");
                }
                else
                {
                    statusLabel.setText("Enter correct values.");
                }
            }
            else
            {
                statusLabel.setText("Some fields are empty.");
            }

        });
    }
}
