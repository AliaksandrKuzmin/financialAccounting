package logic;

import logic.impl.LogicImpl;

public class LogicProvider {
    private static final Logic instance = new LogicImpl();
    public static Logic getInstance() { return instance; }
}

