package org.gtreimagined.gt4r.reactor.components;

public enum InventoryDirection {

    Up,
    Down,
    Left,
    Right;

    public int offsetX(int x) {
        switch (this) {
            case Up:
            case Down: {
                return x;
            }
            case Left: {
                return x - 1;
            }
            case Right: {
                return x + 1;
            }
            default: {
                throw new AssertionError();
            }
        }
    }

    public int offsetY(int y) {
        switch (this) {
            case Up: {
                return y - 1;
            }
            case Down: {
                return y + 1;
            }
            case Left:
            case Right: {
                return y;
            }
            default: {
                throw new AssertionError();
            }
        }
    }
}
