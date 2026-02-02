package com.cutesmouse.mtr.screen;

import com.cutesmouse.mtr.settings.MTRSettings;
import net.minecraft.util.StatCollector;

public class DisplayUtils {
    public static String getMainSwitchDisplay() {
        if (MTRSettings.isActive())
            return StatCollector.translateToLocal("mtr.text.option.main") + ": " + StatCollector.translateToLocal("options.on");
        else
            return StatCollector.translateToLocal("mtr.text.option.main") + ": " + StatCollector.translateToLocal("options.off");
    }

    public static String getColorSwitchDisplay() {
        if (MTRSettings.isColorCodeEnabled())
            return StatCollector.translateToLocal("mtr.text.option.color.code") + ": " + StatCollector.translateToLocal("options.on");
        else
            return StatCollector.translateToLocal("mtr.text.option.color.code") + ": " + StatCollector.translateToLocal("options.off");
    }

    public static String getSourceLangDisplay() {
        return StatCollector.translateToLocal("mtr.text.option.source") + ": " + LanguageManager.getSelected(true).display();
    }


    public static String getTargetLangDisplay() {
        return StatCollector.translateToLocal("mtr.text.option.target") + ": " + LanguageManager.getSelected(false).display();
    }
}
