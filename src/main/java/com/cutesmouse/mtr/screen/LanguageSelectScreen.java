package com.cutesmouse.mtr.screen;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.resources.I18n;

import java.io.IOException;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LanguageSelectScreen extends GuiScreen {
    private GuiScreen parentScreen;
    private List list;
    private final String title;
    private final boolean auto;
    private final Supplier<LanguageManager.LanguageInfo> getCurrentSelected; // return current selected code
    private final Consumer<LanguageManager.LanguageInfo> setSelected; // set language procedure

    public LanguageSelectScreen(GuiScreen parent,
                                String title,
                                Supplier<LanguageManager.LanguageInfo> getCurrentSelected,
                                Consumer<LanguageManager.LanguageInfo> setSelected,
                                boolean auto) {
        this.parentScreen = parent;
        this.title = title;
        this.getCurrentSelected = getCurrentSelected;
        this.setSelected = setSelected;
        this.auto = auto;
    }

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(6, this.width / 2 - 100, this.height - 38, 200, 20, I18n.format("gui.done")));
        this.list = new List();
        this.list.registerScrollButtons(7, 8);
    }

    /**
     * Handles mouse input.
     */
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.list.handleMouseInput();
    }

    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.enabled) {
            switch (button.id) {
                case 6:
                    this.mc.displayGuiScreen(this.parentScreen);
                    break;
                default:
                    this.list.actionPerformed(button);
            }
        }
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.list.drawScreen(mouseX, mouseY, partialTicks);
        this.drawCenteredString(this.fontRendererObj, title, this.width / 2, 16, 16777215);
        this.drawCenteredString(this.fontRendererObj, I18n.format("mtr.text.option.language.warming"), this.width / 2, this.height - 56, 8421504);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    class List extends GuiSlot {
        private final java.util.List<String> langCodeList = Lists.newArrayList();
        private final Map<String, LanguageManager.LanguageInfo> languageMap = Maps.newHashMap();

        public List() {
            super(Minecraft.getMinecraft(), LanguageSelectScreen.this.width, LanguageSelectScreen.this.height, 32, LanguageSelectScreen.this.height - 65 + 4, 18);

            for (LanguageManager.LanguageInfo language : LanguageManager.getAllLanguages(auto)) {
                this.languageMap.put(language.code(), language);
                this.langCodeList.add(language.code());
            }
        }

        protected int getSize() {
            return this.langCodeList.size();
        }

        /**
         * The element in the slot that was clicked, boolean for whether it was double clicked or not
         */
        protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
            LanguageManager.LanguageInfo language = this.languageMap.get(this.langCodeList.get(slotIndex));
            setSelected.accept(language);
        }

        /**
         * Returns true if the element passed in is currently selected
         */
        protected boolean isSelected(int slotIndex) {
            return this.langCodeList.get(slotIndex).equals(getCurrentSelected.get().code());
        }

        /**
         * Return the height of the content being scrolled
         */
        protected int getContentHeight() {
            return this.getSize() * 18;
        }

        protected void drawBackground() {
            LanguageSelectScreen.this.drawDefaultBackground();
        }

        protected void drawSlot(int entryID, int p_180791_2_, int p_180791_3_, int p_180791_4_, int mouseXIn, int mouseYIn) {
            LanguageSelectScreen.this.fontRendererObj.setBidiFlag(true);
            LanguageSelectScreen.this.drawCenteredString(LanguageSelectScreen.this.fontRendererObj, this.languageMap.get(langCodeList.get(entryID)).toString(), this.width / 2, p_180791_3_ + 1, 16777215);
        }
    }
}