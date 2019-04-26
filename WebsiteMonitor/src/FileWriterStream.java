import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWriterStream implements ICustomOutputStream
{
    private FileOutputStream outStream;
    private PrintWriter writer;

    public FileWriterStream(){
    }

    @Override
    public void writeString(String stringToWrite, Object destTarget) {
        outStream = castDestTargetToFileOutputStream(destTarget);
        writer = newPrintWriter(outStream);
        writer.write(stringToWrite);
        closeWriter(writer);
    }

    @Override
    public void appendString(String stringToAppend, Object destTarget) {
        outStream = castDestTargetToFileOutputStream(destTarget);
        writer = newPrintWriter(outStream);
        writer.append(stringToAppend);
        closeWriter(writer);
    }

    private void closeWriter(PrintWriter writer){
        writer.close();
    }

    private FileOutputStream castDestTargetToFileOutputStream(Object destTarget){
        return (FileOutputStream)destTarget;
    }

    private PrintWriter newPrintWriter(FileOutputStream fileOutputStream){
        return new PrintWriter(fileOutputStream);
    }
}