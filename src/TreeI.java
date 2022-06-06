interface TreeI<E> {

    interface NodeI<E> {}

    enum TraversalOrder {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER;
    }

    NodeI<E> add(E obj);
    boolean contains(E obj);
    void remove(E obj);
    E min();
    E max();
    void traverse(TraversalOrder order);

}
