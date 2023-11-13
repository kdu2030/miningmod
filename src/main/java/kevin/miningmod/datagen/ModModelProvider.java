package kevin.miningmod.datagen;

import kevin.miningmod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class ModModelProvider extends FabricModelProvider {

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    public void registerItemModels(ItemModelGenerator itemModelGenerator, Model model, Item... items){
        for(Item item: items){
            itemModelGenerator.register(item, model);
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        registerItemModels(itemModelGenerator, Models.GENERATED, ModItems.SMART_IRON_AXE);
    }
}
