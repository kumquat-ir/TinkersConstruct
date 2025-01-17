package tconstruct.plugins.imc;

import java.util.Arrays;
import java.util.List;

import mantle.pulsar.pulse.Handler;
import mantle.pulsar.pulse.Pulse;
import tconstruct.TConstruct;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(TConstruct.modID)
@Pulse(
        id = "Tinkers AE2 Compatibility",
        description = "Tinkers Construct compatibility for Applied Energistics 2",
        modsRequired = "appliedenergistics2",
        forced = true)
public class TinkerAE2 {

    private static List<String> spatialIOLogics = Arrays.asList(
            "EssenceExtractorLogic",
            "GolemPedestalLogic", // TODO What happened to these?
            "MultiServantLogic"); // TODO Should Mantle handle this?

    private static List<String> spatialIOSmelteryLogics = Arrays.asList(
            "AdaptiveSmelteryLogic",
            "AqueductLogic",
            "CastingBasinLogic",
            "CastingChannelLogic",
            "CastingTableLogic",
            "FaucetLogic",
            "LavaTankLogic",
            "SmelteryDrainLogic",
            "SmelteryLogic",
            "TankAirLogic",
            "TowerFurnaceLogic");

    private static List<String> spatialIOToolLogics = Arrays.asList(
            "CraftingStationLogic",
            "FrypanLogic",
            "PartBuilderLogic",
            "PatternChestLogic",
            "StencilTableLogic",
            "ToolForgeLogic",
            "ToolStationLogic",
            "ToolBenchLogic",
            "MoldingTableLogic");

    @Handler
    public void init(FMLInitializationEvent event) {
        TConstruct.logger.info("AE2 detected. Registering for Spatial IO.");

        addtoSpatialWhitelist("tconstruct.blocks.logic.DryingRackLogic");
        addtoSpatialWhitelist("tconstruct.mechworks.logic.TileEntityLandmine");

        for (String s : spatialIOSmelteryLogics) {
            addtoSpatialWhitelist("tconstruct.smeltery.logic." + s);
        }

        for (String s : spatialIOToolLogics) {
            addtoSpatialWhitelist("tconstruct.tools.logic." + s);
        }
    }

    public void addtoSpatialWhitelist(String teClass) {
        FMLInterModComms.sendMessage("appliedenergistics2", "whitelist-spatial", teClass);
    }
}
