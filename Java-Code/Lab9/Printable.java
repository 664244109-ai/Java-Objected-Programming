import java.io.IOException;
import java.nio.file.Path;
public interface Printable { void printToFile(Path path) throws IOException; }
