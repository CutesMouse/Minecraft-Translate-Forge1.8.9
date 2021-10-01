package com.cutesmouse.mtr;

public class TranslateTask {

    private String source;
    private String translated;
    private long timing;

    public TranslateTask(String text) {
        this.source = text;
        this.timing = System.currentTimeMillis();
        translate();
    }

    public void translate() {
        if (!needTranslate()) return;
        Translater.queue(this);
    }

    public String getTranslatedText() {
        return translated;
    }

    public String getSource() {
        return source;
    }

    public String getSourceWithoutColor() {
        return source.replaceAll("\\u00a7.","");
    }

    public String getResult() {
        return toString();
    }

    public boolean needTranslate() {
        String noColor = getSourceWithoutColor();
        if (noColor.isEmpty()) return false;
        if (noColor.matches("\\d+|-\\d+|\\d+?.\\d*|-\\d+?.\\d*")) return false;
        if (noColor.contains("minecraft:")) return false;
        return true;
    }

    public boolean isSimiliar(TranslateTask task) {
        return task.getSourceWithoutColor().replaceAll("\\d","")
                .equals(getSourceWithoutColor().replaceAll("\\d",""));
    }

    public long getTiming() {
        return timing;
    }

    public void similarTranslate(TranslateTask task) {
        if (task.translated == null) {
            int wait = 10;
            while (wait > 0 && task.translated == null) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                wait--;
            }
            if (task.translated == null) {
                this.translated = "連線逾時!";
                return;
            }
        }
        if (isSimiliar(task)) {
            String[] sA = task.getTranslatedText().split("\\d+");
            String[] nb = source.split("[^0-9]+");
            StringBuilder sb = new StringBuilder();
            int s_offset = 0;
            int n_offset = 1;
            if (source.matches("^\\d+?.*")) {
                sb.append(nb[0]);
                s_offset = 1;
            }
            int i = 0;
            while (i + s_offset < sA.length || i + n_offset < nb.length) {
                if (i + s_offset < sA.length) sb.append(sA[i+s_offset]);
                if (i + n_offset < nb.length) sb.append(nb[i+n_offset]);
                i++;
            }
            translated = sb.toString();
        }
    }
    public void formalTranslate(String s) {
        this.translated = s;
    }

    @Override
    public String toString() {
        return translated == null ? source : translated;
    }
}
