package au.id.bjf.kaleidoscope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Models the colors used in the Kaleidoscope playing board.
 */
public enum KColor {

	BLACK('.'), BLUE('B'), RED('R'), YELLOW('Y');

	private static Map<Character, KColor> shortCodeMap;

	private char shortCode;

	KColor(char shortCode) {
		this.shortCode = shortCode;
	}

	public char getShortCode() {
		return shortCode;
	}

	public static KColor findFromShortCode(char c) {
		synchronized (KColor.class) {
			if (shortCodeMap == null) {
				shortCodeMap = new HashMap<Character, KColor>();
				for (KColor color : KColor.values()) {
					shortCodeMap.put(color.getShortCode(), color);
				}
			}
		}

		return shortCodeMap.get(c);
	}

	public static KColor[] findFromShortCode(CharSequence... strings) {
		List<KColor> results = new ArrayList<KColor>();
		for (CharSequence cs : strings) {
			if (cs != null) {
				for (int i = 0; i < cs.length(); ++i) {
					KColor color = findFromShortCode(cs.charAt(i));
					if (color == null)
						throw new IllegalArgumentException(
								"illegal color specified in argument");
					results.add(color);
				}
			}
		}
		return results.toArray(new KColor[] {});
	}

}
