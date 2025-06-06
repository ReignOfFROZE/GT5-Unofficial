package gtPlusPlus.xmod.gregtech.loaders;

import static gregtech.api.recipe.RecipeMaps.alloySmelterRecipes;

import java.util.HashSet;
import java.util.Set;

import gregtech.api.enums.GTValues;
import gregtech.api.enums.ItemList;
import gtPlusPlus.api.interfaces.RunnableWithInfo;
import gtPlusPlus.core.material.Material;
import gtPlusPlus.core.material.MaterialGenerator;

public class RecipeGenAlloySmelter extends RecipeGenBase {

    public static final Set<RunnableWithInfo<Material>> mRecipeGenMap = new HashSet<>();

    static {
        MaterialGenerator.mRecipeMapsToGenerate.add(mRecipeGenMap);
    }

    public RecipeGenAlloySmelter(final Material M) {
        this.toGenerate = M;
        mRecipeGenMap.add(this);
    }

    @Override
    public void run() {
        generateRecipes(this.toGenerate);
    }

    private void generateRecipes(final Material material) {
        final int tVoltageMultiplier = material.vVoltageMultiplier;
        final long duration = Math.max(material.getMass() * 2L, 1L);
        // Nuggets
        if (material.getIngot(1) != null && material.getNugget(1) != null) {
            GTValues.RA.stdBuilder()
                .itemInputs(material.getIngot(1), ItemList.Shape_Mold_Nugget.get(0))
                .itemOutputs(material.getNugget(9))
                .duration(duration)
                .eut(tVoltageMultiplier)
                .addTo(alloySmelterRecipes);
        }

        // Gears
        if (material.getIngot(1) != null && material.getGear(1) != null) {
            GTValues.RA.stdBuilder()
                .itemInputs(material.getIngot(8), ItemList.Shape_Mold_Gear.get(0))
                .itemOutputs(material.getGear(1))
                .duration(duration)
                .eut(tVoltageMultiplier)
                .addTo(alloySmelterRecipes);
        }

        // Ingot
        if (material.getIngot(1) != null && material.getNugget(1) != null) {
            GTValues.RA.stdBuilder()
                .itemInputs(material.getNugget(9), ItemList.Shape_Mold_Ingot.get(0))
                .itemOutputs(material.getIngot(1))
                .duration(duration)
                .eut(tVoltageMultiplier)
                .addTo(alloySmelterRecipes);
        }
    }
}
