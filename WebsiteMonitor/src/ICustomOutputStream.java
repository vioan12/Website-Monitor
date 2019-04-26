import java.io.IOException;

public interface ICustomOutputStream
{
    void writeString(String stringToWrite, Object destTarget);
    void appendString(String stringToAppend, Object destTarget);
}