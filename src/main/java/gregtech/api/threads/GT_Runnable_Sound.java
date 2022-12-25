package gregtech.api.threads;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.objects.BlockPosition;
import gregtech.api.sound.SimpleSound;
import gregtech.api.util.GT_Utility;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GT_Runnable_Sound implements Runnable {
    private final int mX, mY, mZ, mTimeUntilNextSound;
    private final World mWorld;
    private final ResourceLocation mSoundResourceLocation;
    private final float mSoundStrength, mSoundModulation;
    private boolean mRepeatable = false;
    private BlockPosition mBlockPos;

    public GT_Runnable_Sound(
            World aWorld,
            int aX,
            int aY,
            int aZ,
            int aTimeUntilNextSound,
            ResourceLocation aSoundResourceLocation,
            float aSoundStrength,
            float aSoundModulation) {
        mWorld = aWorld;
        mX = aX;
        mY = aY;
        mZ = aZ;
        mTimeUntilNextSound = aTimeUntilNextSound;
        mSoundResourceLocation = aSoundResourceLocation;
        mSoundStrength = aSoundStrength;
        mSoundModulation = aSoundModulation;
    }

    public GT_Runnable_Sound(
            World aWorld,
            int aX,
            int aY,
            int aZ,
            int aTimeUntilNextSound,
            ResourceLocation aSoundResourceLocation,
            float aSoundStrength,
            float aSoundModulation,
            boolean aRepeatable,
            BlockPosition aBlockPos) {
        this.mX = aX;
        this.mY = aY;
        this.mZ = aZ;
        this.mTimeUntilNextSound = aTimeUntilNextSound;
        this.mWorld = aWorld;
        this.mSoundResourceLocation = aSoundResourceLocation;
        this.mSoundStrength = aSoundStrength;
        this.mSoundModulation = aSoundModulation;
        this.mRepeatable = aRepeatable;
        this.mBlockPos = aBlockPos;
    }

    /**
     * @deprecated Use {@link #GT_Runnable_Sound(World, int, int, int, int, ResourceLocation, float, float)}
     */
    @Deprecated
    public GT_Runnable_Sound(
            World aWorld,
            int aX,
            int aY,
            int aZ,
            int aTimeUntilNextSound,
            String aSoundName,
            float aSoundStrength,
            float aSoundModulation) {
        this(
                aWorld,
                aX,
                aY,
                aZ,
                aTimeUntilNextSound,
                new ResourceLocation(aSoundName),
                aSoundStrength,
                aSoundModulation);
    }

    @Override
    public void run() {
        try {
            SimpleSound sound = mBlockPos != null ? GT_Utility.sPlayedSoundMap.get(mBlockPos) : null;
            if (sound == null || sound.getPositionedSoundLocation() != mSoundResourceLocation) {
                sound = new SimpleSound(
                        mSoundResourceLocation, mSoundStrength, mX + 0.5F, mY + 0.5F, mZ + 0.5F, mRepeatable);
                if (mRepeatable) GT_Utility.sPlayedSoundMap.put(mBlockPos, sound);
            }
            if (FMLClientHandler.instance().getClient().getSoundHandler().isSoundPlaying(sound)) return;
            Minecraft.getMinecraft().getSoundHandler().playSound(sound);
            //            FMLClientHandler.instance().getClient().getSoundHandler().playSound(sound);
        } catch (Throwable e) {
            /**/
        }
    }
}
