import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;
import com.android.build.api.variant.VariantSelector;
import com.jyf.plugin.asmlogger.AsmLogTransform;
import com.jyf.plugin.util.PluginLog;

import org.gradle.api.Plugin;
import org.gradle.api.Project;


/**
 * @link https://developer.aliyun.com/article/996146#slide-14
 */
public class AsmLogPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        PluginLog.init(project);
        PluginLog.println(">>>>> Hello Using :" + getClass().getSimpleName());
        AndroidComponentsExtension<?, ?, ?> androidComponentsExtension = project.getExtensions().getByType(AndroidComponentsExtension.class);
        VariantSelector releaseVariantSelector=
                androidComponentsExtension.selector().all();
        /*
         * androidComponentsExtension.selector().withBuildType("release")
         */
        androidComponentsExtension.onVariants(releaseVariantSelector, variant -> {
            PluginLog.println("-------------------------variant");
            /*
             * InstrumentationScope.PROJECT=app
             * InstrumentationScope.ALL=app+library
             */
            variant.transformClassesWith(AsmLogTransform.class ,InstrumentationScope.ALL, none -> null);
            variant.setAsmFramesComputationMode(
                    FramesComputationMode.COPY_FRAMES
            );
        });
    }
}
