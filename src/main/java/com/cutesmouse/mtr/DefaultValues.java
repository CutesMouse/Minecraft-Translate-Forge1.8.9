package com.cutesmouse.mtr;

public class DefaultValues {
    public static final int MAX_LENGTH = 140;
    public static final int MAX_LINE = 10;
    public static final int PER_LINE = 10;

    public static final int toggle_x = 350;
    public static final int toggle_z = 218;
    public static final int color_x = 350;
    public static final int color_z = 195;
    public static final int refresh_x = 350;
    public static final int refresh_z = 172;
    public static final int translateTextboard_x = 10;
    public static final int translateTextboard_z = 10;
    public static final int textboard_toggle_x = 350;
    public static final int textboard_toggle_z = 9;
    public static void Reset() {
        Config c = Config.Instance;
        c.setInt("layout","toggle_x",toggle_x);
        c.setInt("layout","toggle_z",toggle_z);
        c.setInt("layout","color_x",color_x);
        c.setInt("layout","color_z",color_z);
        c.setInt("layout","refresh_x",refresh_x);
        c.setInt("layout","refresh_z",refresh_z);
        c.setInt("layout","translateTextboard_x",translateTextboard_x);
        c.setInt("layout","translateTextboard_z",translateTextboard_z);
        c.setInt("layout","textboard_toggle_x",textboard_toggle_x);
        c.setInt("layout","textboard_toggle_z",textboard_toggle_z);

        c.setInt("option","textboard_maxLength",MAX_LENGTH);
        c.setInt("option","textboard_maxLine",MAX_LINE);
        c.setInt("option","textboard_perLine",PER_LINE);

        c.setString("source","lang","auto");
        c.setString("target","lang","zh-TW");
    }
}
