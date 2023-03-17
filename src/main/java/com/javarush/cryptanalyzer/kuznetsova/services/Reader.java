package com.javarush.cryptanalyzer.kuznetsova.services;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    String myFile = "src/main/resources/input";
    String encodedFile = "src/main/resources/encoded";

    public StringBuilder readingMyFile () {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(myFile)))) {

            StringBuilder encodeString = new StringBuilder();
            //есть ли доступные символы
            while (bufferedReader.ready()) {
                encodeString.append((char) bufferedReader.read());
            }
            return encodeString;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StringBuilder readEncodedFile () {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(encodedFile)))) {

            StringBuilder myEncryptedFile = new StringBuilder();
            while (bufferedReader.ready()) {
                myEncryptedFile.append((char) bufferedReader.read());
            }
            return myEncryptedFile;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}