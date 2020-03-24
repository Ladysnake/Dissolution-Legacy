package ladysnake.dissolution.common.config;

import net.minecraftforge.common.config.Config;
import org.apiguardian.api.API;

public class DissolutionConfig {

    public Client client = new Client();

    public Respawn respawn = new Respawn();

    public Ghost ghost = new Ghost();

    @Config.RequiresWorldRestart
    @Config.Comment("If set to anything other than \"default\", will force players' remnant status and prevent the dialogue from appearing")
    public DissolutionConfigManager.EnforcedSoulStrength forceRemnant = DissolutionConfigManager.EnforcedSoulStrength.DEFAULT;

    @Sync
    @Config.Comment("Because some people need dialogue to be explicit")
    public boolean technicianDialogue = false;

    @Config.Comment("If true, possessed entities will not emit any ambient sound.")
    public boolean cancelPossessingAmbientSounds = false;

    @Config.Comment("Make human flesh consumption add warp if Thaumcraft is installed")
    public boolean warpyFlesh = true;

    public class Client {

        @Config.Comment("Whether this mod should use shaders as an attempt to make things prettier")
        public boolean useShaders = true;

    }

    public static class Ghost {

        @Config.Comment("If set to true, dead players will be completely invisible for everything")
        public boolean invisibleGhosts = false;

//        @Sync
//        @Config.RangeDouble(min = 0D, max = 1D)
//        @Config.Comment("Any blocks having an average edge length below that value will let souls pass through")
//        public double maxThickness = 0.9;

        @RegExCheck("^/.+/$|^[\\w*]+:\\w+$")
        @Config.Comment("A list of block IDs that ectoplasm can interact with.\n" +
                "If the name begins and ends with a \'/\', it will be interpreted as a regular expression.")
        public String[] authorizedBlocks = new String[]{"minecraft:lever", "/minecraft:.*door/",
                "minecraft:wooden_button"};

        @RegExCheck("^/.+/$|^[\\w*]+:\\w+$")
        @Config.Comment(
                "A list of entities that souls cannot possess.\n" + 
                "This will only prevent future possession attempts, not cancel current ones.\n" + 
                "If the name begins and ends with a \'/\', it will be interpreted as a regular expression.")
        public String[] possessionBlacklist = new String[0];

        @RegExCheck("^/.+/$|^[\\w*]+:\\w+$")
        @Config.Comment(
                "A list of entities that souls can possess.\n" + 
                "This allows non-undead mobs and bosses to be possessed.\n" + 
                "If the name begins and ends with a \'/\', it will be interpreted as a regular expression.")
        @Config.RequiresMcRestart
        public String[] possessionWhitelist = new String[0];

        @Config.Comment("If set to false, incorporeal players won't be able to use the /dissolution stuck command to get back to their spawnpoint")
        public boolean allowStuckCommand = true;

        @Config.Comment("If set to true, will kill you when out of experience.")
        @Config.Name("Die when Out of Experience")
        public boolean dieFromExperience = false;

        @Config.Comment("Should incorporeal players get night vision?")
        @Config.Name("Incorporeal Night Vision")
        public DissolutionConfigManager.NightVisionState nightVision = DissolutionConfigManager.NightVisionState.ALWAYS;


    }

    /**
     * Only there to be used by unit tests
     */
    @API(status = API.Status.INTERNAL, consumers = "ladysnake.dissolution.common.config")
    static class TestGhost extends Ghost {

        @Sync
        @Config.Comment("Changes the way players fly as souls")
        public DissolutionConfigManager.FlightModes flightMode = DissolutionConfigManager.FlightModes.CUSTOM_FLIGHT;

    }

    public class Respawn {

        @Config.Comment("Whether players should respawn in a specific dimension when they die")
        @Config.LangKey("config.dissolution.respawnInNether")
        public boolean respawnInNether = false;

        @Config.Comment("If dimension respawn is on, the player will always respawn in this dimension")
        @Config.LangKey("config.dissolution.respawnDimension")
        public int respawnDimension = -1;

        @Config.Comment("Whether players should respawn instantly as souls without showing death screen (could mess with other mods)")
        @Config.LangKey("config.dissolution.skipDeathScreen")
        public boolean skipDeathScreen = true;

    }

}
