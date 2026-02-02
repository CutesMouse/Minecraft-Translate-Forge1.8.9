package com.cutesmouse.mtr.commands;

import com.cutesmouse.mtr.screen.MTRSettingScreen;
import com.cutesmouse.mtr.utils.DelayedTask;
import com.cutesmouse.mtr.settings.MTRConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

import java.util.ArrayList;
import java.util.List;

public class SettingCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "translate";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getCommandAliases() {
        ArrayList<String> aliase = new ArrayList<String>();
        aliase.add("tr");
        aliase.add("gt");
        aliase.add("mtr");
        return aliase;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Modify the settings of this mod";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        openScreen(new MTRSettingScreen());
//        if (args.length == 0) {
//            help(sender);
//            return;
//        }
//
//        if (args[0].equalsIgnoreCase("link")) {
//            // /tr link <link>
//            sender.addChatMessage(S("\u00A7a\u8ACB\u4F9D\u7167\u4EE5\u4E0B\u6307\u793A\u4F86\u8A2D\u5B9A\u7DB2\u5740:"));
//            sender.addChatMessage(S("\u00A761. \u5148\u81F3\u6B64\u7DB2\u9801 \u00A7e\u00A7l(\u9EDE\u64CA\u6587\u5B57)", "https://www.google.com/script/start/"));
//            sender.addChatMessage(S("\u00A762. \u9EDE\u64CA\u85CD\u8272\u6309\u9215 \"Start Scripting\""));
//            sender.addChatMessage(S("\u00A763. \u767B\u5165Google\u5E33\u865F\u5F8C \u9EDE\u64CA\u5DE6\u4E0A\u89D2 \"+\u65B0\u5C08\u6848\""));
//            sender.addChatMessage(S("\u00A764. \u76F4\u63A5\u8907\u88FD\u4EE5\u4E0B\u5167\u5BB9 \u00A7e\u00A7l(\u9EDE\u64CA\u6587\u5B57)", "https://docs.google.com/document/d/1-EBoaJ0uI3U-_yIrSutPJp9tddLnjPCUhjcIgxkW3-o/edit?usp=sharing"));
//            sender.addChatMessage(S("\u00A765. \u9EDE\u64CA\u5DE6\u4E0A\u89D2\u5DE5\u5177\u5217\u4E2D\u7684 \"\u767C\u5E03\""));
//            sender.addChatMessage(S("\u00A766. \u9EDE\u9078 \"\u90E8\u7F72\u70BA\u7DB2\u9801\u61C9\u7528\u7A0B\u5F0F\""));
//            sender.addChatMessage(S("\u00A767. \u66F4\u6539\u4E0B\u65B9 \"Who has access to the app:\" \u70BA \"Anyone, even anonymous\""));
//            sender.addChatMessage(S("\u00A768. \u9EDE\u64CA\u85CD\u8272Deploy\u6309\u9215 \u4E26\u8907\u88FD\u8A72\u7DB2\u5740 (\u8ACB\u6574\u4E32\uFF0C\u5305\u542Bhttp\u90E8\u5206\u5168\u90E8\u8907\u88FD)"));
//            sender.addChatMessage(S("\u00A769. \u8F38\u5165 /tr set \u4F86\u958B\u555F\u8A2D\u5B9A\u8996\u7A97!"));
//            sender.addChatMessage(S("\u00A77\u63D0\u9192\u60A8: \u5982\u679C\u9023\u7D50\u4E82\u8A2D\uFF0C\u6703\u7121\u6CD5\u9032\u884C\u7FFB\u8B6F"));
//            return;
//        }
//        help(sender);
    }

    private void help(ICommandSender sender) {
        sender.addChatMessage(S("\u00A76\u7528\u6CD5" + ": \u00a7f/tr <layout|source|target|set|link|reset>"));
    }

    private IChatComponent S(String s) {
        return new ChatComponentText(s);
    }

    private IChatComponent S(String s, String url) {
        ChatComponentText st = new ChatComponentText(s);
        st.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        return st;
    }

    private void openScreen(GuiScreen screen) {
        new DelayedTask(() -> Minecraft.getMinecraft().displayGuiScreen(screen));
    }
}
