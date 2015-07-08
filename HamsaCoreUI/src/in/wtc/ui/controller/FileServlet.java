package in.wtc.ui.controller;
//package in.wtc.hcis.ui.controller;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.directwebremoting.dwrp.FileUpload;
//
//import in.wtc.hcis.ui.controller.Files;
//
//public class FileServlet extends HttpServlet
//{
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        doGet(request, response);
//    }
//    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
//    {
//        String type = request.getParameter("type");
//        Files files = (Files) request.getSession().getAttribute(FileUploader.ATTRIBUTE_UPLOADS);
//        FileUpload fileUpload;
//        if ("image".equals(type)) {
//            fileUpload = files.getImage();
//        } else if ("file".equals(type)) {
//            fileUpload = files.getFile();
//            String filename = fileUpload.getName();
//            int slashIndex = filename.replaceAll("\\\\", "/").lastIndexOf('/');
//            if (slashIndex > 0) {
//                filename = filename.substring(slashIndex + 1);
//            }
//            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
//        } else {
//            throw new IllegalStateException("Unknown file type " + type);
//        }
//        response.setContentType(fileUpload.getContentType());
//        response.setHeader("Expires", "Sun, 13 August 1978 12:00:00 GMT");
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//
//        InputStream in = fileUpload.getInputStream();
//        OutputStream out = response.getOutputStream();
//        byte[] bytes = new byte[1024];
//        int count = 0;
//        while ((count = in.read(bytes)) > 0) {
//            out.write(bytes, 0, count);
//        }
//        out.flush();
//    }
//}
