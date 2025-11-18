/** Functions for checking if a given string is an anagram. */
public class Anagram {
    public static void main(String args[]) {
        System.out.println(isAnagram("aabbcc","abcabc"));  
        System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); 
        System.out.println(isAnagram("Madam Curie","Radium came"));
        System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort"));

        System.out.println(preProcess("What? No way!!!"));
        System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");

        String str = "1234567";
        Boolean pass = true;
        for (int i = 0; i < 10; i++) {
            String randomAnagram = randomAnagram(str);
            System.out.println(randomAnagram);
            pass = pass && isAnagram(str, randomAnagram);
            if (!pass) break;
        }
        System.out.println(pass ? "test passed" : "test Failed");
    }  

    public static boolean isAnagram(String str1, String str2) {
        str1 = preProcess(str1);
        str2 = preProcess(str2);
		str1 = str1.replaceAll(" ", "");
		str2 = str2.replaceAll(" ", "");


        if (str1.length() != str2.length()) {
            return false;
        }

        int i = 0;

        while (str2.length() > 0) {
            char ch = str1.charAt(i);
            boolean found = false;

            for (int j = 0; j < str2.length(); j++) {
                if (ch == str2.charAt(j)) {
                    str2 = str2.substring(0, j) + str2.substring(j + 1);
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                return false;
            }

            i++;
        }
        return true;
    }

    // FIXED VERSION
    public static String preProcess(String str) {
		// Check for null or empty string
		if (str == null || str.length() == 0) {
			return "";
		}

		

		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				// uppercase to lowercase
				str = str.substring(0, i) + (char)(str.charAt(i) + 32) + str.substring(i + 1);
			}

			if (str.charAt(i) == '!' || str.charAt(i) == '?' || str.charAt(i) == '.' || str.charAt(i) == ',' || str.charAt(i) == ';' || str.charAt(i) == ':' || str.charAt(i) == ' ') {
				// remove punctuation
				str = str.substring(0, i) + str.substring(i + 1);
				i--; // adjust index since we removed a character
			}
		}
		return str;
	} 

    public static String randomAnagram(String str) {
        char[] chars = str.toCharArray();
        for (int i = chars.length - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }
        return new String(chars);
    }
}