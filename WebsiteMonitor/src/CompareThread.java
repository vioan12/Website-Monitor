import java.net.URL;
import java.util.Date;

public class CompareThread extends Thread
{
    private WebPageMange baseWebPage, realTimeWebPage;
    private IReaderController fileReaderController;
    private IWriterController fileWriterController;
    private Date date;

    public CompareThread(){
        fileReaderController = new FileReaderController(Constants.GOOGLE_ACCOUNT_FILE_PATH);
        fileWriterController = new FileWriterController(Constants.LOG_FILE_PATH);
        try {
            baseWebPage = new WebPageMange(new URL(Constants.SITE_URL));
            realTimeWebPage = new WebPageMange(new URL(Constants.SITE_URL));
        } catch (Exception exception) {
            writeException(exception);
        }
    }
    public void run(){
        while (baseWebPage.getHtml().compareTo("")==0){
            try {
                baseWebPage.loadHtml();
            }catch (Exception exception){
                writeException(exception);
            }
        }
        writeStrings("baseWebPage load completed");
        sleep();
        while (realTimeWebPage.getHtml().compareTo("")==0){
            try {
                realTimeWebPage.loadHtml();
            }catch (Exception exception){
                writeException(exception);
            }
        }
        writeStrings("realTimeWebPage load completed");
        sleep();
        while (baseWebPage.getHtml().compareTo(realTimeWebPage.getHtml())==0) {
            try {
                realTimeWebPage.loadHtml();
            }catch (Exception exception){
                writeException(exception);
            }
            sleep();
        }
        writeStrings("S-a DAAAAAT");
    }

    private void writeException(Exception exception){
        date = new Date();
        try {
            fileWriterController.appendStrings(date.toString() + " --> " + exception.toString()+"\n");
        } catch (Exception secondException) {
            System.out.println(secondException.toString());
        }
    }

    private void writeStrings(String text){
        date = new Date();
        try {
            fileWriterController.appendStrings(date.toString() + " --> " + text +"\n");
        }catch(Exception secondException) {
            System.out.println(secondException.toString());
        }
    }

    private void sleep(){
        try {
            sleep(Constants.TIME_SLEEP_MILLISECONDS);
        } catch (Exception exception) {
            writeException(exception);
        }
    }
}
