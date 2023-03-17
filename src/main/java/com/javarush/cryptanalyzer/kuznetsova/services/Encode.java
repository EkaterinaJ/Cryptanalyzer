package com.javarush.cryptanalyzer.kuznetsova.services;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static com.javarush.cryptanalyzer.kuznetsova.view.CryptanalyzerFrame.KEY;
import static com.javarush.cryptanalyzer.kuznetsova.constants.CryptoAlphabet.ALPHABET;

public class Encode {

    String encodedFile  = "src/main/resources/encoded";

    public StringBuilder encodingText (StringBuilder encodeString) {

        StringBuilder result = new StringBuilder();

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encodedFile))) {

            char encodedText;

            for (int i = 0; i < encodeString.length(); i++) {

                //encodeString.charAt(i) - перевожу полученную строку в символы
                int encodeStringToChar = encodeString.charAt(i);

                //indexOf - возвращает положение символа в ALPHABET или -1, если его там нет
                int symbolIndex = ALPHABET.indexOf(encodeStringToChar);
                boolean symbolsInAlphabet = (symbolIndex != -1);

                //проверяю, есть ли символы в строке ALPHABET
                if (symbolsInAlphabet) {
                    //charAt - возвращаю символ из ALPHABET, который расположен на месте (encodeStringToChar) + key)
                    //% ALPHABET.length() - чтобы вернуться в начало и пойти по новому кругу
                    encodedText = ALPHABET.charAt((symbolIndex + KEY) % ALPHABET.length());

                    result.append(encodedText);
                    bufferedWriter.write(String.valueOf(encodedText));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}