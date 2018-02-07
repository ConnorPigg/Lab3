import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebScrape {
  /**
   * This is the main method for webscraping.
   *
   * @param unused unneeded command line args.
   *
   */
  public static void main(String[] unused) {
    String hamlet = "http://erdani.com/tdpl/hamlet.txt";
    String bls = "https://www.bls.gov/tus/charts/chart9.txt";
    String nws = "http://tgftp.nws.noaa.gov/data/raw/fz/fzus53.klot.srf.lot.txt";
    System.out.println("Counts: Hamlet" + countWords(hamlet) + " BLS "
        + countWords(bls) + " nws " + countWords(nws) );
    System.out.println("Count of prince in hamlet: "
        + countWord(hamlet, "prince"));
  }
  /**
   * This finds word count of a doc pointed by supplied url.
   * Word count defined as characters separated by spaces.
   *
   * @param url The url to get wc from.
   * @return Word cound defined as characters separated by whitespace.
   */
  public static int countWords(String url) {
    String content= urlToString(url);
    String[] words = content.split("\\s+");
    return words.length;
  }
  /**
   * Find count of given word from given url case independent.
   *
   * @param url the text to search from given url
   * @param word the word to look for
   * @return count of word occurences.
   */
  public static int countWord(String url, String word) {
    String content= urlToString(url);
    String[] words = content.split("\\s+");
    int count = 0;
    for(String someword : words) {
      if (someword.toLowerCase().contains(word.toLowerCase())) {
        count++;
      }
    }
    return count;
  }
  /**
   * Retrieve contents from a URL and return them as a string.
   *
   * @param url url to retrieve contents from
   * @return the contents from the url as a string, or an empty string on error
   */
  public static String urlToString(final String url) {
    Scanner urlScanner;
    try {
      urlScanner = new Scanner(new URL(url).openStream(), "UTF-8");
    } catch (IOException e) {
      return "";
    }
    String contents = urlScanner.useDelimiter("\\A").next();
    urlScanner.close();
    return contents;
  }
}
