/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2017
 *
 * This project is licensed under GNU GENERAL PUBLIC LICENSE Version 3.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.item.crystal;

import hellfirepvp.astralsorcery.common.block.network.BlockCollectorCrystalBase;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.IMajorConstellation;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.data.research.ProgressionTier;
import hellfirepvp.astralsorcery.common.item.ItemGatedVisibility;
import hellfirepvp.astralsorcery.common.item.crystal.base.ItemTunedCrystalBase;
import hellfirepvp.astralsorcery.common.registry.RegistryItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: ItemTunedCelestialCrystal
 * Created by HellFirePvP
 * Date: 15.09.2016 / 19:49
 */
public class ItemTunedCelestialCrystal extends ItemTunedCrystalBase implements ItemGatedVisibility {

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        ItemStack stack;
        for (IWeakConstellation c : ConstellationRegistry.getWeakConstellations()) {
            stack = new ItemStack(this);
            CrystalProperties.applyCrystalProperties(stack, new CrystalProperties(CrystalProperties.MAX_SIZE_CELESTIAL, 100, 100));
            applyMainConstellation(stack, c);
            subItems.add(stack);
        }
    }

    @Override
    public ItemTunedCrystalBase getTunedItemVariant() {
        return this;
    }

    @Override
    public Color getHightlightColor(ItemStack stack) {
        return BlockCollectorCrystalBase.CollectorCrystalType.CELESTIAL_CRYSTAL.displayColor;
    }

    @Override
    public Color getCatalystColor(@Nonnull ItemStack stack) {
        return BlockCollectorCrystalBase.CollectorCrystalType.CELESTIAL_CRYSTAL.displayColor;
    }

    @Override
    public double collectionMultiplier(@Nonnull ItemStack stack) {
        return super.collectionMultiplier(stack) * 2;
    }

    @Override
    public double getShatterChanceMultiplier(@Nonnull ItemStack stack) {
        return super.getShatterChanceMultiplier(stack) * 2;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return RegistryItems.rarityCelestial;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean isSupposedToSeeInRender(ItemStack stack) {
        return getClientProgress().getTierReached().isThisLaterOrEqual(ProgressionTier.CONSTELLATION_CRAFT);
    }

}
