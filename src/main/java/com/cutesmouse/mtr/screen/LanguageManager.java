package com.cutesmouse.mtr.screen;

import com.cutesmouse.mtr.settings.MTRSettings;
import net.minecraft.util.StatCollector;

import java.util.ArrayList;
import java.util.Arrays;

public class LanguageManager {
    public static ArrayList<LanguageInfo> getAllLanguages(boolean auto) {
        ArrayList<LanguageInfo> result = new ArrayList<>(Arrays.asList(
                new LanguageInfo("en", "English", "english"),
                new LanguageInfo("zh-TW", "中文 (台灣)", "Chinese Traditional (Taiwan)"),
                new LanguageInfo("zh-HK", "中文 (香港)", "Chinese (Hong Kong)"),
                new LanguageInfo("zh-CN", "中文 (中国)", "Chinese (China)"),
                new LanguageInfo("ja", "日本語", "Japanese"),
                new LanguageInfo("ko", "한국어", "Korean"),
                new LanguageInfo("af", "Afrikaans", "Afrikaans"),
                new LanguageInfo("sq", "Shqip", "Albanian"),
                new LanguageInfo("am", "አማርኛ", "Amharic"),
                new LanguageInfo("ar-SA", "العربية (المملكة العربية السعودية)", "Arabic (Saudi Arabia)"),
                new LanguageInfo("ar", "العربية", "Arabic"),
                new LanguageInfo("hy", "Հայերեն", "Armenian"),
                new LanguageInfo("az", "Azərbaycan dili", "Azerbaijani"),
                new LanguageInfo("eu", "Euskara", "Basque"),
                new LanguageInfo("be", "Беларуская", "Belarusian"),
                new LanguageInfo("bn-IN", "বাংলা (ভারত)", "Bengali (India)"),
                new LanguageInfo("bn", "বাংলা", "Bengali"),
                new LanguageInfo("bs-Cyrl", "босански (ћирилица)", "Bosnian (Cyrillic)"),
                new LanguageInfo("bs", "bosanski", "Bosnian"),
                new LanguageInfo("bg", "български", "Bulgarian"),
                new LanguageInfo("my", "မြန်မာ", "Burmese"),
                new LanguageInfo("ca", "Català", "Catalan"),
                new LanguageInfo("zh-Hans", "中文 (简体)", "Chinese (Simplified)"),
                new LanguageInfo("zh-Hant", "中文 (繁體)", "Chinese (Traditional)"),
                new LanguageInfo("zh", "中文", "Chinese"),
                new LanguageInfo("hr", "Hrvatski", "Croatian"),
                new LanguageInfo("cs", "Čeština", "Czech"),
                new LanguageInfo("da", "Dansk", "Danish"),
                new LanguageInfo("nl-BE", "Nederlands (België)", "Dutch (Belgium)"),
                new LanguageInfo("nl", "Nederlands", "Dutch"),
                new LanguageInfo("en-AU", "English (Australia)", "English (Australia)"),
                new LanguageInfo("en-CA", "English (Canada)", "English (Canada)"),
                new LanguageInfo("en-NZ", "English (New Zealand)", "English (New Zealand)"),
                new LanguageInfo("en-PH", "English (Philippines)", "English (Philippines)"),
                new LanguageInfo("en-ZA", "English (South Africa)", "English (South Africa)"),
                new LanguageInfo("en-GB", "English (United Kingdom)", "English (United Kingdom)"),
                new LanguageInfo("en-US", "English (United States)", "English (United States)"),
                new LanguageInfo("et", "Eesti", "Estonian"),
                new LanguageInfo("fil", "Filipino", "Filipino"),
                new LanguageInfo("fi", "Suomi", "Finnish"),
                new LanguageInfo("fr-CA", "Français (Canada)", "French (Canada)"),
                new LanguageInfo("fr-CH", "Français (Suisse)", "French (Switzerland)"),
                new LanguageInfo("fr", "Français", "French"),
                new LanguageInfo("fy", "Frysk", "Frisian"),
                new LanguageInfo("gl", "Galego", "Galician"),
                new LanguageInfo("ka", "ქართული", "Georgian"),
                new LanguageInfo("de", "Deutsch", "German"),
                new LanguageInfo("el", "Ελληνικά", "Greek"),
                new LanguageInfo("gn", "Avañe'ẽ", "Guarani"),
                new LanguageInfo("gu", "ગુજરાતી", "Gujarati"),
                new LanguageInfo("ha", "Hausa", "Hausa"),
                new LanguageInfo("he", "עברית", "Hebrew"),
                new LanguageInfo("iw", "עברית", "Hebrew"),
                new LanguageInfo("hi", "हिन्दी", "Hindi"),
                new LanguageInfo("hu", "Magyar", "Hungarian"),
                new LanguageInfo("is", "Íslenska", "Icelandic"),
                new LanguageInfo("ig", "Igbo", "Igbo"),
                new LanguageInfo("id", "Bahasa Indonesia", "Indonesian"),
                new LanguageInfo("ga", "Gaeilge", "Irish"),
                new LanguageInfo("it", "Italiano", "Italian"),
                new LanguageInfo("kn", "ಕನ್ನಡ", "Kannada"),
                new LanguageInfo("km", "ភាសាខ្មែរ", "Khmer"),
                new LanguageInfo("ky", "Кыргызча", "Kyrgyz"),
                new LanguageInfo("lo", "ລາວ", "Lao"),
                new LanguageInfo("lv", "Latviešu", "Latvian"),
                new LanguageInfo("ln", "Lingála", "Lingala"),
                new LanguageInfo("lt", "Lietuvių", "Lithuanian"),
                new LanguageInfo("lb", "Lëtzebuergesch", "Luxembourgish"),
                new LanguageInfo("mk", "македонски", "Macedonian"),
                new LanguageInfo("ms", "Bahasa Melayu", "Malay"),
                new LanguageInfo("ml", "മലയാളം", "Malayalam"),
                new LanguageInfo("mt", "Malti", "Maltese"),
                new LanguageInfo("mr", "मराठी", "Marathi"),
                new LanguageInfo("mn", "Монгол", "Mongolian"),
                new LanguageInfo("ne", "नेपाली", "Nepali"),
                new LanguageInfo("nb", "Norsk bokmål", "Norwegian Bokmal"),
                new LanguageInfo("no", "Norsk", "Norwegian"),
                new LanguageInfo("or", "ଓଡ଼ିଆ", "Odia"),
                new LanguageInfo("fa", "فارسی", "Persian"),
                new LanguageInfo("pl", "Polski", "Polish"),
                new LanguageInfo("pt-BR", "Português (Brasil)", "Portuguese (Brazil)"),
                new LanguageInfo("pt-PT", "Português (Portugal)", "Portuguese (Portugal)"),
                new LanguageInfo("pt", "Português", "Portuguese"),
                new LanguageInfo("pa-PK", "ਪੰਜਾਬੀ (ਪਾਕਿਸਤਾਨ)", "Punjabi (Pakistan)"),
                new LanguageInfo("pa", "ਪੰਜਾਬੀ", "Punjabi"),
                new LanguageInfo("ro", "Română", "Romanian"),
                new LanguageInfo("ru", "Русский", "Russian"),
                new LanguageInfo("gd", "Gàidhlig", "Scots Gaelic"),
                new LanguageInfo("sr", "Српски", "Serbian"),
                new LanguageInfo("sk", "Slovenčina", "Slovak"),
                new LanguageInfo("sl", "Slovenščina", "Slovenian"),
                new LanguageInfo("so", "Soomaali", "Somali"),
                new LanguageInfo("es-AR", "Español (Argentina)", "Spanish (Argentina)"),
                new LanguageInfo("es-CL", "Español (Chile)", "Spanish (Chile)"),
                new LanguageInfo("es-CO", "Español (Colombia)", "Spanish (Colombia)"),
                new LanguageInfo("es-CR", "Español (Costa Rica)", "Spanish (Costa Rica)"),
                new LanguageInfo("es-EC", "Español (Ecuador)", "Spanish (Ecuador)"),
                new LanguageInfo("es-SV", "Español (El Salvador)", "Spanish (El Salvador)"),
                new LanguageInfo("es-GT", "Español (Guatemala)", "Spanish (Guatemala)"),
                new LanguageInfo("es-HT", "Español (Haití)", "Spanish (Haiti)"),
                new LanguageInfo("es-HN", "Español (Honduras)", "Spanish (Honduras)"),
                new LanguageInfo("es-419", "Español (Latinoamérica)", "Spanish (Latin America)"),
                new LanguageInfo("es-MX", "Español (México)", "Spanish (Mexico)"),
                new LanguageInfo("es-NI", "Español (Nicaragua)", "Spanish (Nicaragua)"),
                new LanguageInfo("es-PA", "Español (Panamá)", "Spanish (Panama)"),
                new LanguageInfo("es-PY", "Español (Paraguay)", "Spanish (Paraguay)"),
                new LanguageInfo("es-PE", "Español (Perú)", "Spanish (Peru)"),
                new LanguageInfo("es-PR", "Español (Puerto Rico)", "Spanish (Puerto Rico)"),
                new LanguageInfo("es-ES", "Español (España)", "Spanish (Spain)"),
                new LanguageInfo("es-US", "Español (Estados Unidos)", "Spanish (United States)"),
                new LanguageInfo("es-UY", "Español (Uruguay)", "Spanish (Uruguay)"),
                new LanguageInfo("es-VE", "Español (Venezuela)", "Spanish (Venezuela)"),
                new LanguageInfo("es", "Español", "Spanish"),
                new LanguageInfo("sw", "Kiswahili", "Swahili"),
                new LanguageInfo("sv", "Svenska", "Swedish"),
                new LanguageInfo("tl", "Tagalog", "Tagalog"),
                new LanguageInfo("tg", "тоҷикӣ", "Tajik"),
                new LanguageInfo("ta", "தமிழ்", "Tamil"),
                new LanguageInfo("te", "తెలుగు", "Telugu"),
                new LanguageInfo("th", "ไทย", "Thai"),
                new LanguageInfo("tr", "Türkçe", "Turkish"),
                new LanguageInfo("uk", "Українська", "Ukrainian"),
                new LanguageInfo("ur", "اردو", "Urdu"),
                new LanguageInfo("uz", "Oʻzbekcha", "Uzbek"),
                new LanguageInfo("vi", "Tiếng Việt", "Vietnamese"),
                new LanguageInfo("cy", "Cymraeg", "Welsh"),
                new LanguageInfo("zu", "isiZulu", "Zulu")));
        if (auto) result.add(0, new LanguageInfo("auto", "mtr.text.language.auto", "auto"));
        return result;
    }

    public static LanguageInfo getSelected(boolean source) {
        String selected = source ? MTRSettings.getSourceLanguage() : MTRSettings.getTargetLanguage();
        LanguageInfo def = source ? new LanguageInfo("auto", "auto", "auto") :
                new LanguageInfo("zh-TW", "中文 (台灣)", "Chinese Traditional (Taiwan)");
        return getAllLanguages(source).stream().filter(info -> info.code.equals(selected)).findFirst().orElse(def);
    }

    public static class LanguageInfo {
        private final String code;
        private final String display;
        private final String identifier;

        public LanguageInfo(String code, String display, String identifier) {
            this.code = code;
            this.display = display;
            this.identifier = identifier;
        }

        public String code() {
            return code;
        }

        public String identifier() {
            return identifier;
        }

        public String display() {
            if (code.equals("auto")) return StatCollector.translateToLocal(display);
            else return display;
        }

        @Override
        public String toString() {
            return display() + " (" + code() + ")";
        }
    }
}
