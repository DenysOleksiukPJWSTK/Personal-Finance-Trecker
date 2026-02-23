package PFT.util;

import PFT.model.Transaction;

public interface Cloner {
    Transaction shallowClone();
    Transaction deepClone();
}
