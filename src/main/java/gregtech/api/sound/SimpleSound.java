package gregtech.api.sound;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class SimpleSound implements net.minecraft.client.audio.ISound {
    private final ResourceLocation mSoundResourceLocation;
    private final float mSoundStrength, mX, mY, mZ;
    private final boolean mRepeatable;

    public SimpleSound(
            ResourceLocation aSoundResourceLocation,
            float aSoundStrength,
            float aX,
            float aY,
            float aZ,
            boolean aRepeatable) {
        this.mSoundResourceLocation = aSoundResourceLocation;
        this.mSoundStrength = aSoundStrength;
        this.mX = aX;
        this.mY = aY;
        this.mZ = aZ;
        this.mRepeatable = aRepeatable;
    }

    @Override
    public ResourceLocation getPositionedSoundLocation() {
        return mSoundResourceLocation;
    }

    @Override
    public boolean canRepeat() {
        return mRepeatable;
    }

    @Override
    public int getRepeatDelay() {
        return 0;
    }

    @Override
    public float getVolume() {
        return mSoundStrength;
    }

    @Override
    public float getPitch() {
        return 1.0F;
    }

    @Override
    public float getXPosF() {
        return mX + 0.5F;
    }

    @Override
    public float getYPosF() {
        return mY + 0.5F;
    }

    @Override
    public float getZPosF() {
        return mZ + 0.5F;
    }

    @Override
    public AttenuationType getAttenuationType() {
        return AttenuationType.LINEAR;
    }
}
;
