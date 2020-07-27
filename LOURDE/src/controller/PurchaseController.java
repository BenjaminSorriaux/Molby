package controller;

import entity.Purchase;
import model.PurchaseDao;
import view.PurchasePanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PurchaseController {
    PurchasePanel purchasePanel;

    ArrayList<Purchase> purchases;

    public static void main(String[] args) {
        new PurchaseController();
    }

    public PurchaseController() {
        this.purchasePanel = new PurchasePanel(this);
        this.purchasePanel.setTitle("Purchase Panel");
        this.purchasePanel.setSize(new Dimension(1280, 720));

        this.purchasePanel.setContentPane(new PurchasePanel(this).$$$getRootComponent$$$());

        viewGetPurchases(purchasePanel);

        this.purchasePanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.purchasePanel.setVisible(true);
    }

    public void viewUpdatePurchase(Purchase purchase){
        PurchaseDao.updatePurchase(purchase);
    }

    public void viewGetPurchases(PurchasePanel purchasePanel) {
        ArrayList<Purchase> purchases = PurchaseDao.getPurchases();

        if (!purchases.isEmpty()) {
            int posY = 0;

            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new GridBagLayout());

            for (Purchase purchase : purchases) {
                posY = posY + 1;

                if (purchase.getUseDate() == null && purchase.getValidationDate() == null) {

                    JLabel rewardNameLabel = new JLabel(purchase.getRewardName());
                    rewardNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel rewardPriceLabel = new JLabel(Integer.toString(purchase.getRewardPrice()));
                    rewardPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel rewardAvailableLabel = new JLabel(Integer.toString(purchase.getRewardAvailable()));
                    rewardAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel userNameLabel = new JLabel(purchase.getUserFirstName() + " " + purchase.getUserName());
                    userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel userPointsLabel = new JLabel(Integer.toString(purchase.getUserPoints()));
                    userPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel requestDateLabel = new JLabel(purchase.getRequestDate().toString());
                    requestDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel validationDateLabel = new JLabel("Not validated");
                    validationDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JLabel useDateLabel = new JLabel("Not used");
                    useDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    JButton approveButton = new JButton("Approve purchase");
                    JButton declineButton = new JButton("Decline purchase");

                    GridBagConstraints gbc;

                    gbc = new GridBagConstraints();
                    gbc.gridx = 0;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(rewardNameLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 1;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(rewardPriceLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 2;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(rewardAvailableLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 3;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(userNameLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 4;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(userPointsLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 5;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(requestDateLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 6;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(validationDateLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 7;
                    gbc.gridy = posY;
                    gbc.weightx = 1.0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    contentPanel.add(useDateLabel, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 8;
                    gbc.gridy = posY;
                    contentPanel.add(approveButton, gbc);

                    gbc = new GridBagConstraints();
                    gbc.gridx = 9;
                    gbc.gridy = posY;
                    contentPanel.add(declineButton, gbc);

                    approveButton.addActionListener(e ->
                    {
                        purchase.setValidationDate(new Timestamp(System.currentTimeMillis()));

                        purchase.setUserPoints(purchase.getUserPoints() - purchase.getRewardPrice());

                        viewUpdatePurchase(purchase);
                    });

                    declineButton.addActionListener(e ->
                    {

                    });
                }
                else {
                    if (purchase.getUseDate() == null) {
                        JLabel rewardNameLabel = new JLabel(purchase.getRewardName());
                        rewardNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel rewardPriceLabel = new JLabel(Integer.toString(purchase.getRewardPrice()));
                        rewardPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel rewardAvailableLabel = new JLabel(Integer.toString(purchase.getRewardAvailable()));
                        rewardAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel userNameLabel = new JLabel(purchase.getUserFirstName() + " " + purchase.getUserName());
                        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel userPointsLabel = new JLabel(Integer.toString(purchase.getUserPoints()));
                        userPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel requestDateLabel = new JLabel(purchase.getRequestDate().toString());
                        requestDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel validationDateLabel = new JLabel(purchase.getValidationDate().toString());
                        validationDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel useDateLabel = new JLabel("Not used");
                        useDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JButton approveButton = new JButton("Approve purchase");
                        approveButton.setEnabled(false);
                        JButton declineButton = new JButton("Decline purchase");
                        declineButton.setEnabled(false);

                        GridBagConstraints gbc;

                        gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(rewardNameLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 1;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(rewardPriceLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 2;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(rewardAvailableLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 3;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(userNameLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 4;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(userPointsLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 5;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(requestDateLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 6;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(validationDateLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 7;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(useDateLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 8;
                        gbc.gridy = posY;
                        contentPanel.add(approveButton, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 9;
                        gbc.gridy = posY;
                        contentPanel.add(declineButton, gbc);
                    }
                    else {
                        JLabel rewardNameLabel = new JLabel(purchase.getRewardName());
                        rewardNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel rewardPriceLabel = new JLabel(Integer.toString(purchase.getRewardPrice()));
                        rewardPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel rewardAvailableLabel = new JLabel(Integer.toString(purchase.getRewardAvailable()));
                        rewardAvailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel userNameLabel = new JLabel(purchase.getUserFirstName() + " " + purchase.getUserName());
                        userNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel userPointsLabel = new JLabel(Integer.toString(purchase.getUserPoints()));
                        userPointsLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel requestDateLabel = new JLabel(purchase.getRequestDate().toString());
                        requestDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel validationDateLabel = new JLabel(purchase.getValidationDate().toString());
                        validationDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JLabel useDateLabel = new JLabel(purchase.getUseDate().toString());
                        useDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        JButton approveButton = new JButton("Approve purchase");
                        approveButton.setEnabled(false);
                        JButton declineButton = new JButton("Decline purchase");
                        declineButton.setEnabled(false);

                        GridBagConstraints gbc;

                        gbc = new GridBagConstraints();
                        gbc.gridx = 0;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        contentPanel.add(rewardNameLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 1;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(rewardPriceLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 2;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(rewardAvailableLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 3;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        contentPanel.add(userNameLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 4;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(userPointsLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 5;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(requestDateLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 6;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(validationDateLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 7;
                        gbc.gridy = posY;
                        gbc.weightx = 1.0;
                        gbc.fill = GridBagConstraints.HORIZONTAL;
                        contentPanel.add(useDateLabel, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 8;
                        gbc.gridy = posY;
                        contentPanel.add(approveButton, gbc);

                        gbc = new GridBagConstraints();
                        gbc.gridx = 9;
                        gbc.gridy = posY;
                        contentPanel.add(declineButton, gbc);
                    }

                }

            }

            GridBagConstraints gbc;
            gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            purchasePanel.add(contentPanel, gbc);
        }
        else {
            System.out.println("No purchases found");
        }
    }
}
