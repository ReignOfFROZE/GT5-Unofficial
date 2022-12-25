package gregtech.api.net;

import com.google.common.io.ByteArrayDataInput;
import gregtech.api.objects.BlockPosition;
import gregtech.api.util.GT_Log;
import gregtech.api.util.GT_Utility;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;

public class GT_Packet_Sound extends GT_Packet_New {
    private int mX, mZ;
    private short mY;
    private String mSoundName;
    private float mSoundStrength, mSoundPitch;
    private boolean mRepeatable;

    public GT_Packet_Sound() {
        super(true);
    }

    public GT_Packet_Sound(
            String aSoundName, float aSoundStrength, float aSoundPitch, int aX, short aY, int aZ, boolean aRepeatable) {
        super(false);
        mX = aX;
        mY = aY;
        mZ = aZ;
        mSoundName = aSoundName;
        mSoundStrength = aSoundStrength;
        mSoundPitch = aSoundPitch;
        mRepeatable = aRepeatable;
    }

    @Override
    public void encode(ByteBuf aOut) {
        DataOutput tOut = new ByteBufOutputStream(aOut);
        try {
            tOut.writeUTF(mSoundName);
            tOut.writeFloat(mSoundStrength);
            tOut.writeFloat(mSoundPitch);
            tOut.writeInt(mX);
            tOut.writeShort(mY);
            tOut.writeInt(mZ);
        } catch (IOException e) {
            // this really shouldn't happen, but whatever
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public GT_Packet_New decode(ByteArrayDataInput aData) {
        return new GT_Packet_Sound(
                aData.readUTF(),
                aData.readFloat(),
                aData.readFloat(),
                aData.readInt(),
                aData.readShort(),
                aData.readInt(),
                aData.readBoolean());
    }

    @Override
    public void process(IBlockAccess aWorld) {
        GT_Utility.doSoundAtClient(
                new ResourceLocation(this.mSoundName),
                -1,
                0.7F,
                1.08F,
                mX,
                mY,
                mZ,
                mRepeatable,
                new BlockPosition(
                        MathHelper.floor_double(mX), MathHelper.floor_double(mY), MathHelper.floor_double(mZ)));
    }

    @Override
    public byte getPacketID() {
        return 1;
    }
}
