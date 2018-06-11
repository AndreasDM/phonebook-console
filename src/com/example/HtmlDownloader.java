package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

class HtmlDownloader {
    private URL url;
    private String phoneBookSite;
    private String searchString;
    private String htmlPage;

    HtmlDownloader() {
        this.url = null;
        this.phoneBookSite = "https://www.gulesider.no/person/resultat/";
        this.searchString = "";
        this.htmlPage = "";
    }

    /**
     * This method returns the raw html text from the specified
     * search string
     *
     * @param searchString - the text used for searching the phone book.
     * @return raw html text
     */
    String get(String searchString) {
        this.searchString = formatSearchString(searchString);
        urlConcat(this.searchString);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                htmlPage += line;
            }
        }
        catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return htmlPage;
    }

    /**
     * replaces all blank spaces with '%20'
     * @param searchString input string
     * @return formatted string
     */
    private String formatSearchString(String searchString) {
        searchString = searchString.replaceAll("\\ ", "%20");
        return searchString;
    }

    /**
     * This function completes the target url, this is needed in order for
     * our search to work
     *
     * @param search the search string that is being added onto the url.
     */
    private void urlConcat(String search) {
        try {
            url = new URL(phoneBookSite + search);
        }
        catch (MalformedURLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
