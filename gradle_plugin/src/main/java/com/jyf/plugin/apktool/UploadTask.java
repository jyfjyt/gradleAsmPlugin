package com.jyf.plugin.apktool;

import com.jyf.plugin.util.HttpUtil;
import com.jyf.plugin.util.PluginLog;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * @deprecated (not used now)
 */
@Deprecated
public class UploadTask extends DefaultTask {

    private String mOutputFile;

    public void setOutputFile(String o) {
        this.mOutputFile = o;
    }

    @TaskAction
    public void play() {
        PluginLog.println("==============> com.deepblue.executer.UploadTask Starting");
        PluginLog.println("==============> mOutputFile:" + mOutputFile);
        uploadFile(new File(mOutputFile));
    }

    private void uploadFile(File out) {
        PluginLog.println("uploadFile:"+out.getPath());
        String fileUrl = "https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png";
        String savePath = getProject().getRootDir().getPath() + File.separator + "readme";
        String saveName = "test.png";
        try {
            OkHttpClient client = HttpUtil.getHttpClient();
            Request request = new Request.Builder()
                    .url(fileUrl)
                    .build();
            Response response = null;
            response = client.newCall(request).execute();
            byte[] bytes = Objects.requireNonNull(response.body()).bytes();
            Path folderPath = Paths.get(savePath);
            boolean desk = Files.exists(folderPath);
            if (!desk) {
                Files.createDirectories(folderPath);
            }
            Path filePath = Paths.get(savePath + File.separator + saveName);
            PluginLog.println("download:" + filePath);
            Files.write(filePath, bytes, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
