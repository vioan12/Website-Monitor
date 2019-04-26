import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.*;


public class FileReadStream implements ICustomInputStream
{
    private FileInputStream fileInputStream;
    private InputStreamReader reader;

    @Override
    public String read(Object srcTarget) throws IOException {
        String fileContent = "";
        int data;
        fileInputStream = castDestTargetFileInputStream(srcTarget);
        reader = newInputStreamReader(fileInputStream);
        data = reader.read();
        while(data != -1) {
            fileContent = fileContent.concat(String.valueOf(data));
            data = reader.read();
        }
        closeReader(reader);
        return fileContent;
    }

    private void closeReader(InputStreamReader reader) throws IOException {
        reader.close();
    }

    private FileInputStream castDestTargetFileInputStream(Object destTarget){
        return (FileInputStream)destTarget;
    }

    private InputStreamReader newInputStreamReader(FileInputStream fileInputStream){
        return new InputStreamReader(fileInputStream);
    }
}
