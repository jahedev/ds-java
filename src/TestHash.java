public class TestHash {
    public static void main(String[] args) {
        Hash<String, Integer> map = new Hash<>(12);
        map.put("abc", 123);
        map.put("xyz", 321);

        System.out.printf("abc: %d\n", map.get("abc"));
        System.out.printf("xyz: %d\n", map.get("xyz"));
    }
}
