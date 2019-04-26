import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileWriterController implements IWriterController
{
    private OutputStream outputStream;
    private ICustomOutputStream customOutputStream;
    private String fileWritePath;

    public FileWriterController(String fileWritePath) {
        this.fileWritePath = fileWritePath;
        customOutputStream = new FileWriterStream();
    }

    @Override
    public void writeString(String stringToWrite) throws IOException {
        outputStream = new FileOutputStream(fileWritePath);
        customOutputStream.appendString(stringToWrite,outputStream);
        closeOutputStream(outputStream);
    }

    @Override
    public void appendStrings(String... stringsToAppend) throws IOException {
        if(stringsToAppend.length > 0){
            for(String string : stringsToAppend){
                appendString(string);
            }
        }
    }

    private void appendString(String stringToAppend) throws IOException {
        outputStream = new FileOutputStream(fileWritePath,true);
        customOutputStream.appendString(stringToAppend,outputStream);
        closeOutputStream(outputStream);
    }

    private void closeOutputStream(OutputStream outputStream) throws IOException {
        outputStream.close();
    }
}
