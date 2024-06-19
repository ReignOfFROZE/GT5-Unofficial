package gtPlusPlus.xmod.gregtech.api.metatileentity.implementations.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.logic.ProcessingLogic;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.util.GT_ExoticEnergyInputHelper;

public abstract class GregtechMeta_MultiBlockBase_ExoticCapable<T extends GregtechMeta_MultiBlockBase<T>>
    extends GregtechMeta_MultiBlockBase<T> {

    public GregtechMeta_MultiBlockBase_ExoticCapable(int aID, String aName, String aNameRegional) {
        super(aID, aName, aNameRegional);
    }

    public GregtechMeta_MultiBlockBase_ExoticCapable(String aName) {
        super(aName);
    }

    @Override
    public void addAdditionalTooltipInformation(ItemStack stack, List<String> tooltip) {
        super.addAdditionalTooltipInformation(stack, tooltip);
        tooltip.add(StatCollector.translateToLocal("gtPlusPlus.tooltip.multi_amp_enabled"));
    }

    @Override
    public List<GT_MetaTileEntity_Hatch> getExoticAndNormalEnergyHatchList() {
        List<GT_MetaTileEntity_Hatch> tHatches = new ArrayList<>();
        tHatches.addAll(mExoticEnergyHatches);
        tHatches.addAll(mEnergyHatches);
        return tHatches;
    }

    @Override
    protected void setProcessingLogicPower(ProcessingLogic logic) {
        logic.setAvailableVoltage(getMaxInputVoltage());
        logic.setAvailableAmperage(getMaxInputAmps());
        logic.setAmperageOC(true);
    }

    @Override
    public boolean addToMachineList(final IMetaTileEntity aMetaTileEntity, final int aBaseCasingIndex) {
        boolean exotic = false;
        if (aMetaTileEntity == null) return false;
        if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch hatch
            && GT_ExoticEnergyInputHelper.isExoticEnergyInput(aMetaTileEntity)) {
            hatch.updateTexture(aBaseCasingIndex);
            hatch.updateCraftingIcon(this.getMachineCraftingIcon());
            exotic = mExoticEnergyHatches.add(hatch);
        }
        return super.addToMachineList(aMetaTileEntity, aBaseCasingIndex) || exotic;
    }

}
