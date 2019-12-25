package com.max.serverMonitor.controller;

import com.alibaba.fastjson.JSONArray;
import com.max.serverMonitor.service.DeleteService;
import com.max.serverMonitor.service.FileTreeService;
import com.max.serverMonitor.service.FolderService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private FileTreeService fileTreeService;
    @Autowired
    private FolderService folderService;
    @Autowired
    private DeleteService deleteService;

    /**
     * @author Max
     * 文件树
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/fileTree")
    public void getFileTree(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getParameter("id");
        JSONArray json;
        if (path == null){
            // 获取根目录
            json = fileTreeService.getRoot();
        }else{
            // 获取该目录下的文件名
            json = fileTreeService.getFileDir(path);
        }
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().print(json);
    }

    /**
     * @author Max
     * 显示具体文件
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/folder")
    public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        String path;
        String filename;
        if(request.getParameter("path") != null)
        {
            path =  request.getParameter("path");
            session.setAttribute("path", path);
        }
        if(request.getParameter("filename") != null)
        {
            path = (String) session.getAttribute("path");
            filename = (String) request.getParameter("filename");
            path = path + File.separator + filename;

            session.setAttribute("path", path);
        }
        path = (String) session.getAttribute("path");

        response.getWriter().println(folderService.getFile(path));
    }
    /**
     * @author Max
     * 上传文件
     * @param session
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/upload")
    public void fileUpLoad(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String upLoadPath = (String) session.getAttribute("path");
        // 磁盘文件项工厂
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //设置缓冲区大小
        diskFileItemFactory.setSizeThreshold(1024*1024*10);
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        //设置单个文件大小限制
        fileUpload.setFileSizeMax(1024*1024*10);
        //设置所有文件总和大小限制
        fileUpload.setSizeMax(1024*1024*30);

        List<FileItem> fileItems = fileUpload.parseRequest(request);
        for (FileItem fileItem: fileItems){
            // 判断是否为文件表单项
            if (!fileItem.isFormField()){
                String fileName = fileItem.getName();
                fileItem.write(new File(upLoadPath,fileName));
            }
        }
        request.getRequestDispatcher("folder.jsp").forward(request, response);
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping("/download")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setContentType("application/octet-stream");
        String path = (String) session.getAttribute("path");
        String filename;
        if (request.getParameter("name") != null){
            filename = request.getParameter("name");
            session.setAttribute("filename", filename);
        }
        filename = (String) session.getAttribute("filename");
        File file = new File(path, filename);
        response.setContentType("application/octet-stream");
        if (file.exists()) {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            while ((inputStream.read(bytes)) > 0) {
                outputStream.write(bytes);
            }
            inputStream.close();
            outputStream.close();
        }
    }
    /**
     * @author Max
     * 删除文件
     */
    @RequestMapping("/delete")
    public void deleteFile(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        String path = (String) session.getAttribute("path");
        String filename = request.getParameter("name");
        // path =  path + File.separator + filename;
        File file = new File(path, filename);
        deleteService.delFile(file);
    }
    /**
     * @author Max
     * 显示图片
     */
    @RequestMapping("/img")
    public void showImage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        String filename = request.getParameter("filename");
        String path = (String) session.getAttribute("path");
        path =  path + File.separator + filename;

        BufferedImage image = ImageIO.read(new FileInputStream(path));
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.close();
    }
    /**
     * @author Max
     * 在线播放音乐
     */
    @RequestMapping("/music")
    public void turnOnTheMusic(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String path = (String) session.getAttribute("path");
        String filename = request.getParameter("filename");
        path = path + File.separator +filename;

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        while (inputStream.read(b) != -1){
            outputStream.write(b);
        }
        outputStream.close();
    }

    /**
     * @author Max
     * 在线播放视频
     */
    @RequestMapping("/video")
    public void turnOnTheVideo(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        String path = (String) session.getAttribute("path");
        String filename = request.getParameter("filename");
        path = path + File.separator + filename;
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(path));
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] b = new byte[1024];
        while (inputStream.read(b) != -1){
            outputStream.write(b);
        }
        outputStream.close();
    }
}
