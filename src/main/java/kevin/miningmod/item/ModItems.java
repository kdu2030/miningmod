package kevin.miningmod.item;

import kevin.miningmod.MiningMod;
import kevin.miningmod.item.custom.SmartAxe;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {

    public static final Item SMART_IRON_AXE = registerItem("smart_iron_axe", new SmartAxe(ToolMaterials.IRON, 6.0f, -3.1f, new FabricItemSettings()));

    public static Item registerItem(String itemId, Item item){
        return Registry.register(Registries.ITEM, new Identifier(MiningMod.MOD_ID, itemId), item);
    }

    public static void addItemsToItemGroup(FabricItemGroupEntries entries, Item... items){
        for(Item item: items){
            entries.add(item);
        }
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> addItemsToItemGroup(entries, ModItems.SMART_IRON_AXE));
    }

}
