package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class uses regex to extract desired data from
 * a HTML document and inserts it into a List<Person> object.
 */
class HtmlExtractor {
    private Pattern namePattern;
    private Pattern phonePattern;
    private Matcher matcher;
    private List<Person> result;
    private String htmlDocument;

    HtmlExtractor() {
        this("");
    }

    HtmlExtractor(String htmlDocument) {
        this.namePattern = Pattern.
                compile("\">\\b([^Om\\ ][A-Za-zæøå\\-]+\\ [A-Za-zæøå\\-]+(\\ [A-Za-zæøå\\-]+)?(\\ [A-Za-zæøå\\-]+)?)\\b<\\/a>");
        this.phonePattern = Pattern.
                compile("\">([0-9]{3}\\ [0-9]{2}\\ [0-9]{3}|[0-9]{2}\\ [0-9]{2}\\ [0-9]{2}\\ [0-9]{2}\\ )<\\/span>");
        this.matcher = namePattern.matcher(htmlDocument);
        this.result = new ArrayList<>();
    }

    void setHtmlDocument(String htmlDocument) {
        this.htmlDocument = htmlDocument;
        this.matcher = namePattern.matcher(htmlDocument);
    }

    /**
     * This method extracts names from the html document
     * and adds it to an array.
     */
    private void extractName() {
        matcher = namePattern.matcher(this.htmlDocument);
        while (matcher.find()) {
            result.add(new Person(matcher.group(1), ""));
        }
    }

    /**
     * This method extracts phone numbers from the html
     * document and adds it to the person object.
     */
    private void extractPhone() {
        matcher = phonePattern.matcher(this.htmlDocument);
        int count = 0;
        while (matcher.find()) {
            if (count < result.size()) {
                result.get(count).setPhone(matcher.group(1));
                ++count;
            }
        }
    }

    List<Person> run() {
        extractName();
        extractPhone();
        return result;
    }
}
