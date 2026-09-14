package controller;

import java.util.Map;

public interface Command {
    String execute(Map<String, String> params);
}

