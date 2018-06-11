package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class uses regex to extract desired data from
 * a HTML document and inserts it into a List<Person> object.
 */
public class HtmlExtractor {
    private Pattern namePattern;
    private Pattern phonePattern = null; // TODO do a regex pattern for all phone numbers
    private Matcher matcher;
    private List<Person> result;
    private String htmlDocument;

    public HtmlExtractor() {
        this("");
    }

    public HtmlExtractor(String htmlDocument) {
        this.namePattern = Pattern.
                compile("\">\\b([^Om\\ ][A-Za-zæøå]+\\ [A-Za-zæøå]+(\\ [A-Za-zæøå]+)?(\\ [A-Za-zæøå]+)?)\\b<\\/a>");
        this.matcher = namePattern.matcher(htmlDocument);
        this.result = new ArrayList<>();
    }

    public void setHtmlDocument(String htmlDocument) {
        this.htmlDocument = htmlDocument;
        this.matcher = namePattern.matcher(htmlDocument);
    }

    /**
     * This method extracts names from the html document
     * and adds it to an array.
     */
    private void extractName() {
        while (matcher.find()) {
            result.add(new Person(matcher.group(1), ""));
        }
    }

    public List<Person> run() {
        extractName();
        return result;
    }
}
