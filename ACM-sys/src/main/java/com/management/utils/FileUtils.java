package com.management.utils;

import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author: lyz
 * @date: 2023-03-2023/3/15
 * @description: 文件上传与下载相关工具类
 */
public class FileUtils {

    private static final Logger logger = getLogger(com.management.utils.FileUtils.class);

    public static Boolean uploadFile(byte[] file, String filePath, String fileName) throws Exception{
        FileOutputStream out = null;
        try{
            File targetFile = new File(filePath);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            out = new FileOutputStream(filePath+fileName);
            out.write(file);
            out.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            out.close();
        }

    }
    public static String uploadImg(MultipartFile file, String urlName){
        String originalName = file.getOriginalFilename();
        String suffix = originalName.substring(originalName.lastIndexOf(".") + 1);
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = df1.format(new Date());
        String filePath = "";
        //保存到数据库的url的路径C:/pdf/tygfv.pdf
        http://ip:787/pdf/tygfv.pdf
        filePath = "C:/"+urlName+"/" + date + "/";
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "." + suffix;
        String fullPath = "";
        try {
            //执行保存操作
            Boolean writeFlag = uploadFile(file.getBytes(), filePath, fileName);
            if (writeFlag) {
                //保存到数据库的url
                fullPath = "http://10.10.0.98/download" + "/"+urlName+"/" + date + "/" + fileName;
                //保存到数据库
            }
        }catch (Exception e){
            return "error";
        }
        return fullPath;
    }

    public static void download(HttpServletResponse response, String filePath, String encode) {
        response.setContentType("text/html;charset=" + encode);
        String downLoadPath = filePath;
        File file = new File(downLoadPath);
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath)); BufferedOutputStream bos = new BufferedOutputStream(
                response.getOutputStream())) {
            long fileLength = file.length();
            String fileName = file.getName();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(encode), StandardCharsets.UTF_8));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 以流的方式下载
     *
     * @param path
     * @param response
     * @return
     */
    public HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(".".lastIndexOf(filename) + 1).toUpperCase();
            // 以流的形式下载文件。
            byte[] buffer;
            try (InputStream fis = new BufferedInputStream(new FileInputStream(path))) {
                buffer = new byte[fis.available()];
                fis.read(buffer);
            }
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            logger.error("{}", ex);
        }
        return response;
    }


    /**
     * 下载网络文件
     *
     * @throws IOException
     */
    public static void downLoadFile(HttpServletResponse response, String httpUrl) {
        ServletOutputStream out = null;
        try {
            //与服务器建立连接
            URL url = new URL(httpUrl);
            URLConnection conn = url.openConnection();
            InputStream inputStream = conn.getInputStream();
            try {
                //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
            } catch (Exception e){
                e.printStackTrace();
            }
            out = response.getOutputStream();
            // 读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    public void downLoadIsOnLine(String fileName, HttpServletRequest request, HttpServletResponse response, boolean isOnLine) throws IOException {
        String absolutePath = fileName;
        File f = new File(absolutePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        //解决中文乱码
        String userAgent = request.getHeader("user-agent").toLowerCase();
        fileName = f.getName();
        String downloadFileName;

        if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
            downloadFileName = URLEncoder.encode((fileName), "UTF-8");
        } else {
            downloadFileName = new String((fileName).getBytes(StandardCharsets.UTF_8), "iso-8859-1");
        }
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); // 非常重要
        // 在线打开方式
        if (isOnLine) {
            URL u = new URL("file:///" + absolutePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + downloadFileName);
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + downloadFileName);
        }

        try (BufferedInputStream br = new BufferedInputStream(new FileInputStream(f)); OutputStream out = response.getOutputStream()) {

            while ((len = br.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
    }
}
