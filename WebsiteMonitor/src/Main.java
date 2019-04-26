import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main
{
    private static String HTML1 = "";
    private static URL url;
    private static String HTML2 = "";

    private static Date date;

    static void url_atr() {
        try {
            url = new URL("http://fmi.unibuc.ro/ro/admitere_master/master_2019/");
        } catch (Exception exception) {
            date = new Date();
            System.err.println(date.toString() + " --> [url_atr] " + exception);
        }
    }

    static void send_mail() {
        GoogleMail mail = new GoogleMail();

        try {
            FileReader in = new FileReader("ChangedTheWebPage.txt");
            BufferedReader br = new BufferedReader(in);
            mail.Send(br.readLine(), br.readLine(), br.readLine(), "", "Master FMI", "S-a afisat Organizarea programelor de master. Va asteptam pe " + url.toString() + " pt mai multe detalii.");
            br.close();
            in.close();
        } catch (Exception exception) {
            date = new Date();
            System.err.println(date.toString() + " --> [send_mail] " + exception);
        }

    }

    static int html_compare() {
        url_atr();
        Thread thread = new Thread();

        try {
            URLConnection spoof;
            BufferedReader in;
            String strLine;
            Document doc;
            Elements element;
            do {
                date = new Date();
                System.out.println(date.toString() + " --> [html_compare] I did the comparison");
                thread.sleep(60000);
                spoof = url.openConnection();
                spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
                in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
                HTML1 = "";
                HTML1.replaceAll("[^a-zA-Z0-9 ]", "");
                strLine = "";
                while ((strLine = in.readLine()) != null) {
                    HTML1 += strLine;
                }
                doc = Jsoup.parse(HTML1);
                //element= doc.select("div#continut_standard");
                //element = doc.select("div#sites-canvas-main-content");
                element = doc.select("td");
                HTML1 = element.get(204).toString();
            } while(HTML1.equals(HTML2));

            return 1;
        } catch (Exception exception) {
            date = new Date();
            System.err.println(date.toString() + " --> [html_compare] " + exception);
            return 0;
        }
    }

    static int html_compare_second() {
        url_atr();
        Thread thread = new Thread();

        try {
            URLConnection spoof = url.openConnection();
            spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
            BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
            String strLine = "";
            HTML1 = "";
            HTML1.replaceAll("[^a-zA-Z0-9 ]", "");

            while ((strLine = in.readLine()) != null) {
                HTML1 += strLine;
            }
            Document doc = Jsoup.parse(HTML1);
            //Elements element = doc.select("div#continut_standard");
            //Elements element = doc.select("div#sites-canvas-main-content");
            Elements element = doc.select("td");
            HTML1 = element.get(204).toString();
            HTML2 = HTML1;
            return 1;
        } catch (Exception exception) {
            date = new Date();
            System.err.println(date.toString() + " --> [html_compare_second] " + exception);
            return 0;
        }
    }

    public static void main(String[] args) {

        /*
        int x;
        Thread thread = new Thread();
        try {
            do {
                thread.sleep(60000);
                HTML1 = "";
                HTML1.replaceAll("[^a-zA-Z0-9 ]", "");
                x = html_compare_second();
            } while(x != 1);

            date = new Date();
            System.out.println(date.toString() + " --> HTML2[" + url.toString() + "] load complete");

            do {
                thread.sleep(60000);
                HTML1 = "";
                HTML1.replaceAll("[^a-zA-Z0-9 ]", "");
                x = html_compare();
            } while(x != 1);

            send_mail();
        } catch (Exception exception) {
            date = new Date();
            System.err.println(date.toString() + " --> [main] " + exception);
        }
    */
        Thread compareThread = new CompareThread();
        compareThread.start();
        try {
            compareThread.join();
        } catch (Exception firstException) {
            System.out.println(firstException.toString());
        }
    }

}
