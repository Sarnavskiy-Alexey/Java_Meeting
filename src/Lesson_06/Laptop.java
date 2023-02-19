package Lesson_06;

import java.util.Objects;

/* 1. Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java. */
public class Laptop {
    private String name;
    private int rom;
    private int hdd;
    private String os;
    private String color;


    public Laptop(String name, int rom, int hdd, String os, String color) {
        this.name = name;
        this.rom = rom;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }


    @Override
    public String toString() {
        return String.format("""
                        Название: %s
                        ПЗУ: %d
                        ОЗУ: %d
                        ОС: %s
                        Цвет: %s
                        -------------------
                        """,
                getName(), getRom(), getHdd(), getOs(), getColor());
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Laptop laptop = (Laptop) o;
        return name.equals(laptop.name) && rom == laptop.rom && hdd == laptop.hdd && os.equals(laptop.os) && color.equals(laptop.color);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, rom, hdd, os, color);
    }


    // getters
    public String getName() {
        return name;
    }

    public int getRom() {
        return rom;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}
