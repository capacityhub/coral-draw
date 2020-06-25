package test.com.mxgraph;

import com.mxgraph.server.utils.ExportServlet;
import com.mxgraph.tool.layout.mxOrganicLayout;
import com.mxgraph.tool.swing.mxGraphComponent;
import com.mxgraph.tool.view.mxGraph;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;

import javax.swing.*;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class OrganicTest extends JFrame {
    static String xml = "<mxfile modified=\"2020-05-17T03:34:53.921Z\" host=\"urcs.feinno.com\" agent=\"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36\" etag=\"Jf_qsqkwz9mbYrOoMeo7\" version=\"10.9.5\" type=\"browser\"><diagram id=\"FO2v3a15tLzoUDuactzV\" name=\"Page-1\">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</diagram></mxfile>";
    public static void main(String[] args) throws Exception {
        ExportServlet exportServlet = new ExportServlet();
        ByteArrayOutputStream outputStream = exportServlet.handleRequest("111.png", xml);
        File file = new File("111.png");
        if (file.exists()){
            file.delete();
        }
        file.createNewFile();
        FileUtils.writeByteArrayToFile(file,  outputStream.toByteArray());
        System.out.println(new String(outputStream.toByteArray()));
    }
}