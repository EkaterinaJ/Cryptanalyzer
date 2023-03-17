package com.javarush.cryptanalyzer.kuznetsova.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.javarush.cryptanalyzer.kuznetsova.view.CryptanalyzerFrame.KEY;
import static com.javarush.cryptanalyzer.kuznetsova.constants.CryptoAlphabet.ALPHABET;

public class Decode {

    public StringBuilder decodeFileCaesarCipher (StringBuilder myEncryptedFile) {

        StringBuilder finalText = new StringBuilder();

        char decodedText;

        for (int i = 0; i < myEncryptedFile.length(); i++) {

            int decodeStringToChar = myEncryptedFile.charAt(i);
            int symbolIndex = ALPHABET.indexOf(decodeStringToChar);
            boolean symbolsInAlphabet = (symbolIndex != -1);
            int symbolsShift = (symbolIndex - KEY);

            if (symbolsInAlphabet) {
                decodedText = ALPHABET.charAt(symbolsShift % ALPHABET.length());
                finalText.append(decodedText);
            }
        }
        return finalText;
    }



    public StringBuilder decodeBruteForceFile (StringBuilder encryptedFile) {

        StringBuilder decodeBruteForceFile = new StringBuilder();
        StringBuilder decodeFile = new StringBuilder();

        for (int i = 0; i < ALPHABET.length(); i++) {

            char decodedText;

            for (int j = 0; j < encryptedFile.length(); j++) {

                int encodeStringToChar = encryptedFile.charAt(j);
                int symbolIndex = ALPHABET.indexOf(encodeStringToChar);
                boolean symbolsInAlphabet = (symbolIndex != -1);

                if (symbolsInAlphabet) {
                    decodedText = ALPHABET.charAt((symbolIndex + i) % ALPHABET.length());
                    decodeBruteForceFile.append(decodedText);
                }
            }
        }
        decodeFile.append(regE(decodeBruteForceFile));
        return decodeFile;
    }

    public StringBuilder regE (StringBuilder decodeBruteForceFile) {

        StringBuilder regE = new StringBuilder();

        Pattern pattern = Pattern.compile("([А-ЯЁ]{1}[^\\.]+\\.+\\s+[А-ЯЁ]{1}[^\\.]+\\.+\\s+[А-ЯЁ]{1}[^\\.]+\\.+.+)");

        Matcher matcher = pattern.matcher(decodeBruteForceFile);

        while(matcher.find()) {
            regE.append(matcher.group());
        }
        return regE;
    }
}