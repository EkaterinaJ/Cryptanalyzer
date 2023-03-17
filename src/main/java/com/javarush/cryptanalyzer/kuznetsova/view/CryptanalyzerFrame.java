package com.javarush.cryptanalyzer.kuznetsova.view;

import com.javarush.cryptanalyzer.kuznetsova.services.Decode;
import com.javarush.cryptanalyzer.kuznetsova.services.Encode;
import com.javarush.cryptanalyzer.kuznetsova.services.Reader;

import javax.swing.*;
import java.awt.*;

public class CryptanalyzerFrame extends JFrame {
    public static int KEY;
    public JLabel labelStartWork, labelTextBeforeEncryption, labelTextAfterEncryption, labelTextDecryptionOptions, labelTextBeforeDecryption, labelTextAfterDecryption, infoBruteforce, infoEncryption;
    public JTextField textWaitingKey, textBeforeEncryption, textAfterEncryption, textBeforeDecryption, textAfterDecryption;
    public JButton buttonEncryption, buttonDecoding;
    public JRadioButton buttonMyText, buttonYourText, buttonCaesarCipher, buttonBruteForce;
    public ButtonGroup firstGroup, secondGroup;
//    public JScrollBar scrollBar;

    Reader reader = new Reader();
    Encode encode = new Encode();
    Decode decode = new Decode();

    public CryptanalyzerFrame() {
        initComponents();
        addActionButtonEncryption();
        addActionButtonDecoding();
        displayTheWindow();
    }

    private void displayTheWindow() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
        this.setSize(1350, 720);
        this.setLayout(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    void addActionButtonEncryption() {
        buttonEncryption.addActionListener(e -> {

            if (buttonMyText.isSelected()) {
                addActionButtonMyText();
            } else if (buttonYourText.isSelected()) {
                addActionButtonYourText();
            }
        });
    }

    void addActionButtonDecoding() {
        buttonDecoding.addActionListener(e -> {

            if (buttonMyText.isSelected() && buttonCaesarCipher.isSelected()) {
                addActionButtonCaesarCipher();
            } else if (buttonMyText.isSelected() && buttonBruteForce.isSelected()) {
                addActionButtonBruteForce();
            } else if (buttonYourText.isSelected() && buttonCaesarCipher.isSelected()) {
                addActionButtonCaesarCipher();
            } else if (buttonYourText.isSelected() && buttonBruteForce.isSelected()) {
                addActionButtonBruteForce();
            }
        });
    }

    void addActionButtonMyText() {
        buttonMyText.addActionListener(e -> {
            KEY = Integer.parseInt(textWaitingKey.getText());
            textBeforeEncryption.setText(String.valueOf(reader.readingMyFile()));
            textAfterEncryption.setText(String.valueOf(encode.encodingText(reader.readingMyFile())));
        });
    }

    void addActionButtonYourText() {
        buttonYourText.addActionListener(e -> {
            KEY = Integer.parseInt(textWaitingKey.getText());
//            String s = textBeforeEncryption.getText();
//                    YourText.append(s);
            textAfterEncryption.setText(String.valueOf(encode.encodingText(new StringBuilder(textBeforeEncryption.getText()))));
        });
    }

    void addActionButtonCaesarCipher() {
        buttonCaesarCipher.addActionListener(e -> {
            KEY = Integer.parseInt(textWaitingKey.getText());
            textBeforeDecryption.setText(String.valueOf(reader.readEncodedFile()));
            textAfterDecryption.setText(String.valueOf(decode.decodeFileCaesarCipher(reader.readEncodedFile())));
        });
    }

    void addActionButtonBruteForce() {
        buttonCaesarCipher.addActionListener(e -> {
            textBeforeDecryption.setText(String.valueOf(reader.readEncodedFile()));
            textAfterDecryption.setText(String.valueOf(decode.decodeBruteForceFile(reader.readEncodedFile())));
        });
    }


    private void initComponents() {
        this.setTitle("Cryptanalyzer");

        labelStartWork = new JLabel("Введите ключ(целое число) и выберите метод шифрования. Если хотите зашифровать ваш текст - запишите его в поле \"Текст для шифрования\".");
        labelStartWork.setBounds(20,10,1200,25);

        textWaitingKey = new JTextField();
        textWaitingKey.setBounds(20,45,420,25);

        buttonMyText = new JRadioButton("Зашифровать мой текст");
        buttonMyText.setBounds(20,80,200,25);
        buttonMyText.setBackground(Color.LIGHT_GRAY);

        buttonYourText = new JRadioButton("Зашифровать ваш текст");
        buttonYourText.setBounds(240,80,200,25);
        buttonYourText.setBackground(Color.LIGHT_GRAY);

        labelTextBeforeEncryption = new JLabel("Текст для шифрования:");
        labelTextBeforeEncryption.setBounds(20,115,600,25);

        textBeforeEncryption = new JTextField();
        textBeforeEncryption.setBounds(20,145,1300,50);


        labelTextAfterEncryption = new JLabel("Текст после шифрования:");
        labelTextAfterEncryption.setBounds(20,195,600,25);

        textAfterEncryption = new JTextField();
        textAfterEncryption.setBounds(20,225,1300,50);

        buttonEncryption = new JButton("Зашифровать");
        buttonEncryption.setBounds(20,290,200,25);
        buttonEncryption.setBackground(Color.LIGHT_GRAY);

        infoEncryption = new JLabel("Чтобы зашифровать надо нажать: выбрать, какой текст -> Зашифровать -> еще раз на тип текста нажать. Не успела исправить :'(");
        infoEncryption.setBounds(240,290,850,25);


        labelTextDecryptionOptions = new JLabel("Каким способом хотите расшифровать текст?");
        labelTextDecryptionOptions.setBounds(20,370,600,25);


        buttonCaesarCipher = new JRadioButton("Шифр цезаря");
        buttonCaesarCipher.setBounds(20,405,200,25);
        buttonCaesarCipher.setBackground(Color.LIGHT_GRAY);

        buttonBruteForce = new JRadioButton("Brute force");
        buttonBruteForce.setBounds(240,405,200,25);
        buttonBruteForce.setBackground(Color.LIGHT_GRAY);

        infoBruteforce = new JLabel("Brute force запукается не совсем очевидно, порядок такой: Brute force -> Расшифровать -> Шифр цезаря. Не успела и этот момент исправить");
        infoBruteforce.setBounds(450,405,850,25);


        labelTextBeforeDecryption = new JLabel("Текст для расшифрования:");
        labelTextBeforeDecryption.setBounds(20,445,600,25);

        textBeforeDecryption = new JTextField();
        textBeforeDecryption.setBounds(20,475,1300,50);


        labelTextAfterDecryption = new JLabel("Текст после расшифрования:");
        labelTextAfterDecryption.setBounds(20,540,600,25);

        textAfterDecryption = new JTextField();
        textAfterDecryption.setBounds(20,570,1300,50);


        buttonDecoding = new JButton("Расшифровать");
        buttonDecoding.setBounds(20,635,200,25);
        buttonDecoding.setBackground(Color.LIGHT_GRAY);



        firstGroup = new ButtonGroup();
        firstGroup.add(buttonMyText);
        firstGroup.add(buttonYourText);

        secondGroup = new ButtonGroup();
        secondGroup.add(buttonCaesarCipher);
        secondGroup.add(buttonBruteForce);

        this.add(labelStartWork);
        this.add(textWaitingKey);

        this.add(buttonMyText);
        this.add(buttonYourText);

        this.add(labelTextBeforeEncryption);
        this.add(labelTextAfterEncryption);
        this.add(textBeforeEncryption);
        this.add(textAfterEncryption);

        this.add(buttonEncryption);
        this.add(infoEncryption);

        this.add(labelTextDecryptionOptions);

        this.add(buttonCaesarCipher);
        this.add(buttonBruteForce);
        this.add(infoBruteforce);

        this.add(labelTextBeforeDecryption);
        this.add(labelTextAfterDecryption);
        this.add(textBeforeDecryption);
        this.add(textAfterDecryption);

        this.add(buttonDecoding);
    }
}

