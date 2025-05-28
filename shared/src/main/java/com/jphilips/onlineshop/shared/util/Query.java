package com.jphilips.onlineshop.shared.util;

public interface Query<I,O>{
    O execute(I input);
}