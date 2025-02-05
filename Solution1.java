class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        int i,n=s1.length(),cnt=0;
        int[] freq1=new int[26];
        int[] freq2=new int[26];
        for(i=0;i<n;i++)
        {
            if(s1.charAt(i)!=s2.charAt(i))
                cnt++;
            freq1[s1.charAt(i)-'a']++;
            freq2[s2.charAt(i)-'a']++;
        }
        if(cnt!=0 && cnt>2)
            return false;
        for(i=0;i<26;i++)
        {
            if(freq1[i]!=freq2[i])
                return false;
        }
        return true;
    }
}