import javax.swing.UIDefaults;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

final class ThaiFontSupport {
    private static final String THAI_SAMPLE = "ภาษาไทย";
    private static final String FONT_FAMILY = findThaiFontFamily();

    private ThaiFontSupport() {
    }

    static Font create(int style, int size) {
        return new Font(FONT_FAMILY, style, size);
    }

    static void configureSwingDefaults() {
        UIDefaults defaults = UIManager.getDefaults();
        List<Object[]> fontDefaults = new ArrayList<>();
        Enumeration<Object> keys = defaults.keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = defaults.get(key);
            if (value instanceof Font) {
                fontDefaults.add(new Object[]{key, value});
            }
        }

        for (Object[] entry : fontDefaults) {
            Font font = (Font) entry[1];
            defaults.put(entry[0], create(font.getStyle(), font.getSize()));
        }
    }

    private static String findThaiFontFamily() {
        String[] preferred = {"Leelawadee UI", "Tahoma", "Leelawadee", "Microsoft Sans Serif"};
        for (String family : preferred) {
            if (supportsThai(family)) {
                return family;
            }
        }
        for (String family : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (supportsThai(family)) {
                return family;
            }
        }
        return Font.DIALOG;
    }

    private static boolean supportsThai(String family) {
        return new Font(family, Font.PLAIN, 12).canDisplayUpTo(THAI_SAMPLE) < 0;
    }
}
