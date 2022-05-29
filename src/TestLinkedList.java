public class TestLinkedList {
    public static void main(String[] args) {
        ListI<Integer> nums = new DoublyLinkedList<>();
        nums.addLast(10);
        nums.addLast(20);
        nums.addLast(30);
        nums.addLast(40);
        nums.addLast(50);

        System.out.println("-----");
        ((DoublyLinkedList<Integer>) nums).printListReverse();
        System.out.println("-----");

        nums.removeLast();
        nums.removeFirst();
        nums.remove(30);

        nums.printList();
        System.out.println("-----");

    }
}
