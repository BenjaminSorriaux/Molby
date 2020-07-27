package controller;

import entity.Level;
import entity.Reward;
import model.LevelDao;
import model.RewardDao;
import view.RewardPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RewardController {
    RewardPanel rewardPanel;

    ArrayList<Level> levels;

    public static void main(String[] args) {
        new RewardController();
    }

    public RewardController() {
        // Initialize admin panel
        this.rewardPanel = new RewardPanel(this);
        this.rewardPanel.setTitle("Reward Panel");
        this.rewardPanel.setSize(new Dimension(1280, 720));

        levels = LevelDao.getLevels();

        this.rewardPanel.setContentPane(new RewardPanel(this).$$$getRootComponent$$$());

        viewGetRewards(rewardPanel);

        this.rewardPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.rewardPanel.setVisible(true);
    }

    public void viewGetRewards(RewardPanel rewardPanel) {
        ArrayList<Reward> rewards = RewardDao.getRewards();
        if (!rewards.isEmpty())
        {
            int i = 0;

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridBagLayout());

            for (Reward reward : rewards) {
                i = i + 1;

                JLabel imageLabel = new JLabel();
                JTextField nameField = new JTextField(reward.getLabel());
                JTextField descriptionField = new JTextField(reward.getDescription());
                JTextField priceField = new JTextField(Integer.toString(reward.getCostXp()));
                JTextField nbAvailableField = new JTextField(Integer.toString(reward.getNbAvailable()));
                JTextField remainingField = new JTextField(Integer.toString(5));
                JButton editButton = new JButton("Edit");
                JButton removeButton = new JButton("Remove");

                // Insert image
                Image image = null;
                URL url = null;

                try {
                    url = new URL(reward.getSkin());
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
                gbc.gridx = 4;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(nbAvailableField, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 5;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                contentPanel.add(remainingField, gbc);

                // LEVEL PART
                JComboBox levelBox = new JComboBox();
                levels.forEach(level -> levelBox.addItem(level.getLabel()));
                String selectedLevel = levels.stream().filter((level -> level.getId() == reward.getIdLevel())).findFirst().orElseThrow().getLabel();
                levelBox.setSelectedItem(selectedLevel);
                gbc = new GridBagConstraints();
                gbc.gridx = 6;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                contentPanel.add(levelBox, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 7;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                contentPanel.add(editButton, gbc);

                gbc = new GridBagConstraints();
                gbc.gridx = 8;
                gbc.gridy = i;
                gbc.weightx = 1.0;
                contentPanel.add(removeButton, gbc);

                JLabel status = new JLabel();
                status.setHorizontalAlignment(JLabel.CENTER);
                gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 2;
                gbc.weightx = 1.0;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                rewardPanel.add(status, gbc);

                editButton.addActionListener(e -> {
                    int idLevel = levels.stream().filter((level -> level.getLabel().equals(levelBox.getSelectedItem()))).findFirst().orElseThrow().getId();

                    if (!nameField.getText().isEmpty() && !priceField.getText().isEmpty() && !descriptionField.getText().isEmpty() && !nbAvailableField.getText().isEmpty() && !remainingField.getText().isEmpty())
                    {
                        if (priceField.getText().matches("[0-9]+") && nbAvailableField.getText().matches("[0-9]+") && remainingField.getText().matches("[0-9]+"))
                        {
                            if (Integer.parseInt(nbAvailableField.getText()) >= Integer.parseInt(remainingField.getText()))
                            {
                                Reward reward1 = new Reward();
                                reward1.setId(reward.getId());
                                reward1.setLabel(nameField.getText());
                                reward1.setCostXp(Integer.parseInt(priceField.getText()));
                                reward1.setDescription(descriptionField.getText());
                                reward1.setNbAvailable(Integer.parseInt(nbAvailableField.getText()));
                                reward1.setIdLevel(idLevel);
                                reward1.setLevel(levelBox.getSelectedItem().toString());

                                viewUpdateReward(reward1);

                                status.setText("");
                            }
                            else
                            {
                                status.setText("Remaining rewards number can't be higher than the available one.");
                            }
                        }
                        else
                        {
                            status.setText("Enter correct values.");
                        }
                    }
                    else
                    {
                        status.setText("Some fields are empty.");
                    }

                });

                removeButton.addActionListener(e -> {
                    viewRemoveReward(reward);
                });
            }

            GridBagConstraints gbc;

            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            rewardPanel.add(contentPanel, gbc);

            JButton showAdd = new JButton("Add reward");
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            rewardPanel.add(showAdd, gbc);

            showAdd.addActionListener(e -> {
                showAddForm(rewardPanel, 1);
                showAdd.setVisible(false);
            });
        }
        else
        {
            rewardPanel.add(new JLabel("NO REWARD FOUND !"));
        }
    }

    public void viewCreateReward(Reward reward) {
        RewardDao.createReward( new Reward(reward.getLabel(), reward.getCostXp(), reward.getNbAvailable(), reward.getDescription(), reward.getIdLevel(), reward.getLevel(), reward.getSkin()));
    }

    public void viewRemoveReward(Reward reward)
    {
        RewardDao.removeReward(reward);
    }

    public void viewUpdateReward(Reward reward)
    {
        RewardDao.updateReward(reward);
    }

    public void showAddForm(RewardPanel rewardPanel, int posY)
    {
        GridBagConstraints gbc;

        JPanel contentPanel = new JPanel();

        JTextField labelField = new JTextField("Label");
        labelField.setColumns(20);
        JTextField costXpField = new JTextField("XP cost");
        costXpField.setColumns(20);
        JTextField nbAvailableField = new JTextField("Number available");
        costXpField.setColumns(20);
        JTextField descriptionField = new JTextField("Description");
        descriptionField.setColumns(20);
        JTextField skinField = new JTextField("Image URL");
        skinField.setColumns(20);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(labelField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(costXpField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(nbAvailableField, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(descriptionField, gbc);

        // LEVEL HERE
        JComboBox levelBox = new JComboBox();
        levels.forEach(level -> levelBox.addItem(level.getLabel()));

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(levelBox, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(skinField, gbc);

        JLabel statusLabel = new JLabel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = posY;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(statusLabel, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = posY;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rewardPanel.add(contentPanel, gbc);

        JButton confirmAdd = new JButton("Confirm");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = posY + 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rewardPanel.add(confirmAdd, gbc);

        int idLevel = levels.stream().filter((level -> level.getLabel().equals(levelBox.getSelectedItem()))).findFirst().orElseThrow().getId();

        confirmAdd.addActionListener(e -> {
            if (!labelField.getText().isEmpty() && !costXpField.getText().isEmpty() && !nbAvailableField.getText().isEmpty() && !descriptionField.getText().isEmpty() && !skinField.getText().isEmpty())
            {
                if (costXpField.getText().matches("[0-9]+") && nbAvailableField.getText().matches("[0-9]+"))
                {
                    Reward reward = new Reward();
                    reward.setLabel(labelField.getText());
                    reward.setCostXp(Integer.parseInt(costXpField.getText()));
                    reward.setNbAvailable(Integer.parseInt(nbAvailableField.getText()));
                    reward.setDescription(descriptionField.getText());
                    reward.setIdLevel(idLevel);
                    reward.setLevel(levelBox.getSelectedItem().toString());
                    reward.setSkin(skinField.getText());

                    viewCreateReward(reward);

                    statusLabel.setText("");
                }
                else
                {
                    statusLabel.setText("Insert correct values.");
                }
            }
            else
            {
                statusLabel.setText("Some fields are empty.");
            }

        });
    }

}
