package tconstruct.plugins.ic2;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import tconstruct.TConstruct;
import tconstruct.library.TConstructRegistry;
import tconstruct.library.crafting.LiquidCasting;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(TConstruct.modID)
@Pulse(
        id = "Tinkers IC2 Compatibility",
        description = "Tinkers Construct compatibility for IndustrialCraft 2",
        modsRequired = "IC2",
        forced = true)
public class TinkerIC2 {

    private static final String IC2_UUM_FLUIDNAME = "ic2fluiduumatter";

    @Handler
    public void init(FMLInitializationEvent event) {
        TConstruct.logger.info("IC2 detected. Preparing for shenanigans.");

        Fluid fluidUUM = FluidRegistry.getFluid(IC2_UUM_FLUIDNAME);
        if (fluidUUM == null) return;

        FluidStack fluidStackBlock = new FluidStack(fluidUUM, 4500);
        LiquidCasting basinCasting = TConstructRegistry.getBasinCasting();

        // Block casting
        basinCasting.addCastingRecipe(
                new ItemStack(Blocks.diamond_block),
                fluidStackBlock,
                new ItemStack(Blocks.dirt),
                true,
                50);
    }
}
