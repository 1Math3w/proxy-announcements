package me.math3w.proxyannouncements.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    private static final Pattern hexColorPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    private Utils() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static TextComponent colorize(String message) {
        message = ChatColor.translateAlternateColorCodes('&', message);

        Matcher matcher = hexColorPattern.matcher(message);
        List<TextComponent> components = new ArrayList<>();
        int prevEnd = 0;

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();

            if (start > prevEnd) {
                String plainText = message.substring(prevEnd, start);
                TextComponent textComponent = new TextComponent(plainText);
                components.add(textComponent);
            }

            String colorCode = message.substring(start, end);
            int nextColorCodeIndex = getNextColorCodeIndex(message, end);
            TextComponent colorComponent = new TextComponent(message.substring(end, nextColorCodeIndex));
            colorComponent.setColor(ChatColor.of(colorCode));
            components.add(colorComponent);

            prevEnd = nextColorCodeIndex;
        }

        if (prevEnd < message.length()) {
            String remainingText = message.substring(prevEnd);
            TextComponent textComponent = new TextComponent(remainingText);
            components.add(textComponent);
        }

        TextComponent finalComponent = new TextComponent();
        for (TextComponent component : components) {
            finalComponent.addExtra(component);
        }

        return finalComponent;
    }

    private static int getNextColorCodeIndex(String message, int startIndex) {
        Matcher matcher = hexColorPattern.matcher(message);
        while (matcher.find(startIndex)) {
            if (matcher.start() != startIndex) {
                return matcher.start();
            }
        }
        return message.length();
    }
}
