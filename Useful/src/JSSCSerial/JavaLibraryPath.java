package JSSCSerial;
import java.util.logging.Logger;

public class JavaLibraryPath {
    static Logger logger = Logger.getLogger(JavaLibraryPath.class.getName());
    public static void main(String[] args) {
    	logger.info(System.getProperty("java.library.path"));
    }
}