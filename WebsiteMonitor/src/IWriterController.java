import java.io.IOException;

public interface IWriterController
{
    void writeString(String stringToWrite) throws IOException;
    void appendStrings(String... stringsToAppend) throws IOException;
}
