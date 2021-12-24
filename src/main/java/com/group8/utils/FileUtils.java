package com.group8.utils;

//import it.sauronsoftware.jave.Encoder;
//import it.sauronsoftware.jave.EncoderException;
//import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author acoffee
 * @create 2021-12-21 21:13
 */
public class FileUtils {
//    /**
//     * 获取视频文件的播放长度
//     * @param source
//     * @return 单位为毫秒
//     * @throws EncoderException
//     */
//    public static long getDuration(File source) throws EncoderException {
//        Encoder encoder = new Encoder();
//        MultimediaInfo m = encoder.getInfo(source);
//        return m.getDuration();
//    }

//    //根据文件路径去获取视频时长
//    public static long getDuration(String videoPath) throws EncoderException {
//        File source = new File(videoPath);
//        return getDuration(source);
//    }


//    private static ImmutableList<String> videoSuffixList = ImmutableList.of("mp4", "mov", "avi", "mkv", "m4v", "wmv",
//            "asf", "asx", "rm", "rmvb", "3gp", "dat", "flv", "vob");
    /**
     * 获取视频时长 * @param file 视频文件
     * @return 时长（秒）
     */ public static long getVideoDuration(File file){
        long duration = 0;

        try{
            MultimediaObject multimediaObject = new MultimediaObject(file);
            MultimediaInfo info = multimediaObject.getInfo();
            duration =  info.getDuration();
        } catch (Exception e){
            e.printStackTrace();
        }
        return duration;
    }

    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;
        }else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }


    //获取流文件
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除本地临时文件
     * @param file
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

}
