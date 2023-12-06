import com.android.build.gradle.AppExtension;
import com.jyf.plugin.apktool.AppExtensionRunner;
import com.jyf.plugin.util.PluginLog;
import com.jyf.plugin.util.TaskTimeUtil;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ApkToolPlugin implements Plugin<Project> {

    private final TaskTimeUtil mTimeUtil = new TaskTimeUtil("afterEvaluate");


//    private UploadTask mUploadTask = null

    @Override
    public void apply(Project project) {
        PluginLog.println(">>>>> Hello Using :" + getClass().getSimpleName());

        runAppExtensionPre(project);

//        TaskProvider<UploadTask> uploadTaskProvider = project.getTasks().register("jyf", UploadTask.class, task -> {})
//        mUploadTask = uploadTaskProvider.get()
//        mUploadTask.setOutputFile(mReleaseOuputApkPath)
//        mUploadTask.setOutputFile("C:\\Users\\jinyf\\Desktop\\Airport_v6_1.3.0_release_202212301618.apk")
//        mUploadTask.dependsOn(task)
    }


    private void runAppExtensionPre(Project project) {
        project.afterEvaluate(it -> {
            stopLint(project);
//            PluginLog.pirntAllTask("project.afterEvaluate",project)
            AppExtension appExt = project.getExtensions().findByType(AppExtension.class);
            if (appExt!=null){
                runAppExtension(project,appExt);
            }else{
                PluginLog.println("!!! AppExtension not found !!!");
            }
        });
    }

    private void runAppExtension(Project it,AppExtension appExt) {
        if (appExt.getApplicationVariants().isEmpty()){
            PluginLog.println("!!! getApplicationVariants isEmpty");
            return;
        }
        mTimeUtil.projectStartInit(it);
        AppExtensionRunner runner=new AppExtensionRunner(it,appExt);
        runner.setCallback(mTimeUtil::projectEndPrint);
        runner.run();
    }


    private void stopLint(Project project) {
//        PluginLog.pirntAllTask("stopLint",project)
        project.getTasks().forEach(t->{
            if (t.getName().contains("lint")){
                PluginLog.println("!!! Task "+t.getName()+" Disabled");
                t.setEnabled(false);
            }
        });
    }


}