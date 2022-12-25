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
import net.minecraft.world.IBlockAccess;

public class GT_Packet_Stop_Sound extends GT_Packet_New {

    private String mSoundName;
    private double mX;
    private short mY;
    private double mZ;

    public GT_Packet_Stop_Sound(String mSoundName, double mX, short mY, double mZ) {
        super(false);
        this.mSoundName = mSoundName;
        this.mX = mX;
        this.mY = mY;
        this.mZ = mZ;
    }

    public GT_Packet_Stop_Sound(boolean aIsReference) {
        super(aIsReference);
    }

    @Override
    public byte getPacketID() {
        return 0;
    }

    @Override
    public void encode(ByteBuf aOut) {
        DataOutput tOut = new ByteBufOutputStream(aOut);
        try {
            tOut.writeUTF(mSoundName);
            tOut.writeInt(MathHelper.floor_double(mX));
            tOut.writeShort(mY);
            tOut.writeInt(MathHelper.floor_double(mZ));
        } catch (IOException e) {
            // this really shouldn't happen, but whatever
            e.printStackTrace(GT_Log.err);
        }
    }

    @Override
    public GT_Packet_New decode(ByteArrayDataInput aData) {
        return new GT_Packet_Stop_Sound(aData.readUTF(), aData.readInt(), aData.readShort(), aData.readInt());
    }

    @Override
    public void process(IBlockAccess aWorld) {
        GT_Utility.stopSoundAtClient(new BlockPosition(
                MathHelper.floor_double(mX), MathHelper.floor_double(mY), MathHelper.floor_double(mZ)));
    }
}
