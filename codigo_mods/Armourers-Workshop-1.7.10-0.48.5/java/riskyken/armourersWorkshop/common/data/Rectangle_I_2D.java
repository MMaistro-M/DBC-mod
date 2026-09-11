/*
 * Decompiled with CFR 0.152.
 */
package riskyken.armourersWorkshop.common.data;

public class Rectangle_I_2D {
    public int x;
    public int y;
    public int width;
    public int height;

    public Rectangle_I_2D(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void grow(int width, int height) {
        this.width += width;
        this.height += height;
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public boolean intersects(Rectangle_I_2D rectangle) {
        return this.x + this.width > rectangle.x & this.x < rectangle.x + rectangle.width && this.y + this.height > rectangle.y & this.y < rectangle.y + rectangle.height;
    }

    public boolean isInside(int x, int y) {
        return x >= this.x & x < this.x + this.width && y >= this.y & y < this.y + this.height;
    }

    protected Rectangle_I_2D clone() throws CloneNotSupportedException {
        return new Rectangle_I_2D(this.x, this.y, this.width, this.height);
    }

    public String toString() {
        return "Rectangle_I_2D [x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + "]";
    }
}

