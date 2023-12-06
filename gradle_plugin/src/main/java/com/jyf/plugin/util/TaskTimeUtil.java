package com.jyf.plugin.util;

import org.gradle.api.Project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class TaskTimeUtil {

    private final String mTag;

    public TaskTimeUtil(String mTag) {
        this.mTag = mTag;
    }


    private final SimpleDateFormat mSdf0 = new SimpleDateFormat("SSS", Locale.CHINA);
    private final SimpleDateFormat mSdf1 = new SimpleDateFormat("ss", Locale.CHINA);
    private final SimpleDateFormat mSdf2 = new SimpleDateFormat("mm", Locale.CHINA);

    private final Map<String, Long> mTimeStartCache = new HashMap<>();
    private final Map<String, Integer> mTimeDiffCache = new HashMap<>();

    public void projectStartInit(Project project) {
        PluginLog.println(">>>>>>>>>>>>>>>>>>> Using TaskTimeUtil");
        project.getTasks().forEach(
                it -> {
                    it.doFirst(df -> {
                        mTimeStartCache.put(df.getName(), System.currentTimeMillis());
                        PluginLog.println(mTag + " task:" + df.getName() + "start cal");
                    });
                    it.doLast(dl -> {
                        long time = mTimeStartCache.get(dl.getName());
                        long diff = System.currentTimeMillis() - time;
                        mTimeDiffCache.put(dl.getName(), (int) diff);
                        PluginLog.println(mTag + " task:" + dl.getName() + "--use:" + diff + " ms");
                    });
                }
        );
    }

    public void projectEndPrint() {
        int[] total = {0};
        List<Map.Entry<String, Integer>> list = new ArrayList<>(mTimeDiffCache.entrySet());
        list.sort(Map.Entry.comparingByValue());
        for (Map.Entry<String, Integer> entry : list) {
            PluginLog.println("---------------" + mTag + " " + entry.getKey() + " time:" + pt(entry.getValue()));
            total[0] = total[0] + entry.getValue();
        }
        PluginLog.println(">>>>>>>>>>>>>>>>>> " + mTag + " " + " time:" + pt(total[0]));
    }

    private String pt(long time) {
        if (time < 1000) {
            return mSdf0.format(time) + " ms";
        }
        if (time < 1000 * 60) {
            return mSdf1.format(time) + " s " + mSdf0.format(time) + " ms";
        }
        return mSdf2.format(time) + " m " + mSdf1.format(time) + " s " + mSdf0.format(time) + " ms";
    }
}
