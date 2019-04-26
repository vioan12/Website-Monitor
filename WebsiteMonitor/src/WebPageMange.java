import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebPageMange
{
    private String html;
    private URL url;
    public WebPageMange(URL url){
        this.url = url;
        this.html = "";
    }
    public void loadHtml() throws IOException {
        URLConnection spoof = url.openConnection();
        spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
        BufferedReader input = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
        html.replaceAll("[^a-zA-Z0-9 ]", "");
        String line = "";
        while ((line = input.readLine()) != null) {
            html = html.concat(line);
        }
    }
    public String extractContentHtml(String... args) throws NumberFormatException {
        Document doc;
        Elements elements;
        if (args.length >= 1) {
            doc = Jsoup.parse(html);
            elements = doc.select(args[0]);
            if (args.length > 1) {
                return elements.get(Integer.parseInt(args[1])).toString();
            }
            return elements.toString();
        }
        return getHtml();
    }
    public String getHtml() {
        return this.html;
    }
    public URL getUrl() {
        return this.url;
    }
}
