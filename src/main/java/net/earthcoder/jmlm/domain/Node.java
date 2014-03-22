package net.earthcoder.jmlm.domain;

public class Node<E extends Human>  {

    protected E content;
    protected FeeController<E> feeController = new FeeController<E>();
    
    public E getContent() {
        return content;
    }
}
