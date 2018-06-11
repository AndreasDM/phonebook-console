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

        // Prompt user
        phoneBook.welcomeMessage();
        phoneBook.takeUserInput();

        // get html page
        String input = phoneBook.getUserInput();
        String htmlDocument = phoneBook.htmlDownloader.get(input);

        // filter out info from html document
        phoneBook.htmlExtractor.setHtmlDocument(htmlDocument);
        phoneBook.searchResult = phoneBook.htmlExtractor.run();

        // print out details
        for (Person p : phoneBook.searchResult)
            System.out.println(p.getName() + " " + p.getPhone());
    }
}
