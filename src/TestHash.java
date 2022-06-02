public class TestHash {
    public static void main(String[] args) {
        Hash<String, Integer> map = new Hash<>(12);
        map.put("abc", 123);
        map.put("xyz", 321);
        map.put("abcd", 311);
        map.put("absdfasdfcsd", 900);
        map.put("abcsbdfasdfd", 990);
        map.put("abfdgcd", 311);
        map.put("abvbccd", 311);
        map.put("aewrbcd", 311);
        map.put("abdasfcd", 311);
        map.put("abcvbhcd", 311);
        map.put("zcvzxcvabrtcd", 311);
        map.put("zxcvazbrtcd", 311);
        map.put("adtr345xcrtcd", 311);
        map.put("ab234rtcd", 311);
        map.put("ab5rtcd", 311);
        map.put("abdfg435345dfg3tcd", 311);
        map.put("abrtdf345h5cd", 311);
        map.put("abrtdf345h5cdX", 45354);
        map.remove("xyz");

        System.out.printf("abc: %d\n", map.get("abc"));
        System.out.printf("xyz: %d\n", map.get("xyz"));
        System.out.printf("abrtdf345h5cdX: %d\n", map.get("abrtdf345h5cdX"));
    }
}
