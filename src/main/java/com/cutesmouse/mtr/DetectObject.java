package com.cutesmouse.mtr;

public class DetectObject {
    public int x;
    public int y;
    public int width;
    public int height;
    public DetectObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public boolean isHover(int mouseX, int mouseY) {
        return (x <= mouseX && x+width >= mouseX && y <= mouseY && y+height >= mouseY);
    }
    public int bottom() {
        return y+height;
    }
    public int right() {
        return x+width;
    }
}
