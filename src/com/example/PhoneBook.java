package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    private List<Person> searchResult;
    private HtmlDownloader htmlDownloader;
    private HtmlExtractor htmlExtractor;
    private Scanner scanner;
    private String userInput;

    public PhoneBook() {
        searchResult = new ArrayList<>();
        htmlDownloader = new HtmlDownloader();
        htmlExtractor = new HtmlExtractor();
        scanner = new Scanner(System.in);
        userInput = "";
    }

    public List<Person> getResult() {
        // TODO
        return null;
    }

    public void search() {
        // TODO
    }

    private void welcomeMessage() {
        System.out.print("Search for person: ");
    }

    private void takeUserInput() {
        userInput = scanner.nextLine();
    }

    private String getUserInput() {
        return userInput;
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        // TODO: tell user what to do:
        phoneBook.welcomeMessage();

        // TODO: take users input
        phoneBook.takeUserInput();

        // TODO: download htlm document
        String input = phoneBook.getUserInput();
        String htmlDocument = phoneBook.htmlDownloader.get(input);

        // TODO: extract data from html document
        phoneBook.htmlExtractor.setHtmlDocument(htmlDocument);
        phoneBook.searchResult = phoneBook.htmlExtractor.run();

        // TODO: display results
        for (Person p : phoneBook.searchResult)
            System.out.println(p.getName() + " " + p.getPhone());
    }
}
