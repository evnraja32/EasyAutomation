package practice.coding.leetcode;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LongestSubStringWithoutRepeating {

    @Test
    public void testMethod() {
        Assert.assertEquals(lengthOfLongestSubstring("tm1mzxxx"), 5);
        Assert.assertEquals(lengthOfLongestSubstring("tmmzuxt"), 5);
        Assert.assertEquals(lengthOfLongestSubstring("asjrgapa"), 6);
        Assert.assertEquals(lengthOfLongestSubstring("dvdf"), 3);
        Assert.assertEquals(lengthOfLongestSubstring("au"), 2);
        Assert.assertEquals(lengthOfLongestSubstring("a"), 1);
        Assert.assertEquals(lengthOfLongestSubstring(""), 0);
        Assert.assertEquals(lengthOfLongestSubstring("acbabcbb"), 3);
        Assert.assertEquals(lengthOfLongestSubstring("abcabcbb"), 3);
        Assert.assertEquals(lengthOfLongestSubstring("bbbbb"), 1);
        Assert.assertEquals(lengthOfLongestSubstring("pwwkew"), 3);
        Assert.assertEquals(lengthOfLongestSubstring("pw wke w"), 4);
    }

    public int lengthOfLongestSubstring(String s) {
        System.out.println(s);
        Set<Character> seen = new HashSet<>();
        int maxLen = 0, start = 0;

        for (int end = 0; end < s.length(); end++) {
            while (seen.contains(s.charAt(end))) {
                seen.remove(s.charAt(start));
                start++;
            }
            seen.add(s.charAt(end));
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;

    }

    public int lengthOfLongestSubstring_v1(String s) {
        System.out.println(s);
        int tempLength = 0;
        int longestStringLength = 0;

        HashSet<Character> charSet = new HashSet<>();

        int index = 0;
        int startIndex = index;
        while (index < s.length()) {

            if (startIndex == s.length()) {
                startIndex = ++index;
                continue;
            }


            if (charSet.contains(s.charAt(startIndex))) {
                System.out.println(index + " - " + startIndex + " - " + s.charAt(startIndex) + " => already exists resetting counts");
                System.out.println("Longest string length: " + longestStringLength);
                System.out.println(">>> Resetting counts");

//                charSet = new HashSet<>();
                charSet.remove(s.charAt(startIndex));
                tempLength = 0;
                startIndex = ++index;
                if (index >= s.length()) {
                    break;
                }
            }

            System.out.println(index + " - " + startIndex + " - " + s.charAt(startIndex) + " => not exists");

            charSet.add(s.charAt(startIndex));
            tempLength += 1;

            if (tempLength > longestStringLength) {
                longestStringLength = tempLength;
            }
            ++startIndex;
        }

        return longestStringLength;
    }
}
