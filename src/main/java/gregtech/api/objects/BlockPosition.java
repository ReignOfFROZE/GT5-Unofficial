package gregtech.api.objects;

import java.util.Objects;

public class BlockPosition {

    int mX, mY, mZ;

    public BlockPosition(int aX, int aY, int aZ) {
        this.mX = aX;
        this.mY = aY;
        this.mZ = aZ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockPosition that = (BlockPosition) o;
        return mX == that.mX && mY == that.mY && mZ == that.mZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mX, mY, mZ);
    }

    @Override
    public String toString() {
        return "BlockPosition{" + "mX=" + mX + ", mY=" + mY + ", mZ=" + mZ + '}';
    }
}
