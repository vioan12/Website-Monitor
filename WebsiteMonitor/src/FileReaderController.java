import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileReaderController implements IReaderController
{
    InputStream inputStream;
    ICustomInputStream customInputStream;
    private String fileWritePath;

    public FileReaderController(String fileWritePath) {
        this.fileWritePath = fileWritePath;
        customInputStream = new FileReadStream();
    }

    @Override public String read() throws IOException {
        String fileContent = "";
        inputStream = new FileInputStream(fileWritePath);
        fileContent = customInputStream.read(inputStream);
        closeInputStream(inputStream);
        return fileContent;
    }

    private void closeInputStream(InputStream inputStream) throws IOException {
        inputStream.close();
    }
}
