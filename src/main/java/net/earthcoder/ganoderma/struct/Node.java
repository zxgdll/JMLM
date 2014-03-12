package net.earthcoder.ganoderma.struct;

import net.earthcoder.ganoderma.fee.FeeController;
import net.earthcoder.ganoderma.man.Human;

public class Node<E extends Human>  {

    protected E content;
    protected FeeController<E> feeController = new FeeController<E>();
    
    public E getContent() {
        return content;
    }
}
