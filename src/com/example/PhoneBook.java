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
        System.out.print("Do a search: ");
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

        // find longest name
        int longestName = 0;
        for (Person p : phoneBook.searchResult) {
            if (p.getName().length() > longestName)
                longestName = p.getName().length();
        }

        // format width
        String format = "%-" + longestName;

        if (longestName == 0) {
            System.exit(-1);
        }

        // print out details
        System.out.println();
        System.out.printf(format + "s" + " %s\n", "Name", "Phone");
        for (Person p : phoneBook.searchResult)
            System.out.printf(format + "s" + " %s\n", p.getName(), p.getPhone());
    }
}
