package com.jphilips.onlineshop.shared.util;

public interface Command<I,O>{
    O execute(I input);
}
