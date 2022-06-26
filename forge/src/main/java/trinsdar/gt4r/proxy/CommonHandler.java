package trinsdar.gt4r.proxy;

import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import trinsdar.gt4r.GT4Reimagined;
import trinsdar.gt4r.Ref;
import trinsdar.gt4r.datagen.GT4RRandomDropBonus;

import java.net.URL;
import java.util.List;
import java.util.Scanner;

import static trinsdar.gt4r.data.GT4RData.SupporterListGold;
import static trinsdar.gt4r.data.GT4RData.SupporterListSilver;

public class CommonHandler {

    public static void setup(FMLCommonSetupEvent e){
        e.enqueueWork(() -> {
            Registry.register(Registry.LOOT_FUNCTION_TYPE, new ResourceLocation(Ref.ID, "random_drop_bonus"), GT4RRandomDropBonus.RANDOM_DROP_BONUS);
        });
        new Thread(() -> {

            List<String>
                    tTextFile = downloadTextFile("updates.gregtech.mechaenetia.com/com/gregoriust/gregtech/supporterlist.txt", false);
            if (tTextFile != null && tTextFile.size() > 3) {
                SupporterListSilver.addAll(tTextFile);
            } else try {
                Scanner tScanner = new Scanner(GT4Reimagined.class.getResourceAsStream("/supporterlist.txt"));
                while (tScanner.hasNextLine()) SupporterListSilver.add(tScanner.nextLine().toLowerCase());
                tScanner.close();
                GT4Reimagined.LOGGER.warn("GT_DL_Thread: Failed downloading Silver Supporter List, using interal List!");
            } catch(Throwable exc) {exc.printStackTrace();}

            tTextFile = downloadTextFile("updates.gregtech.mechaenetia.com/com/gregoriust/gregtech/supporterlistgold.txt", false);
            if (tTextFile != null && tTextFile.size() > 3) {
                SupporterListGold.addAll(tTextFile);
            } else try {
                Scanner tScanner = new Scanner(GT4Reimagined.class.getResourceAsStream("/supporterlistgold.txt"));
                while (tScanner.hasNextLine()) SupporterListGold.add(tScanner.nextLine().toLowerCase());
                tScanner.close();
                GT4Reimagined.LOGGER.warn("GT_DL_Thread: Failed downloading Gold Supporter List, using interal List!");
            } catch(Throwable exc) {exc.printStackTrace();}

            SupporterListSilver.removeAll(SupporterListGold);

        }).start();
    }

    protected static List<String> downloadTextFile(String aURL, boolean aLowercase) {
        List<String> rList = NonNullList.create();
        try {
            Scanner tScanner = new Scanner(new URL(aURL.startsWith("http")?aURL:"https://"+aURL).openStream());
            while (tScanner.hasNextLine()) rList.add(aLowercase ? tScanner.nextLine().toLowerCase() : tScanner.nextLine());
            tScanner.close();
            for (String tLine : rList) if (tLine.contains("a href")) {
                GT4Reimagined.LOGGER.error("GT_DL_Thread: Your Internet Connection has Issues, you should probably go check that your ISP or Network don't do stupid Stuff.");
                return NonNullList.create();
            }
            return rList;
        } catch(Throwable f) {
            GT4Reimagined.LOGGER.error("GT_DL_Thread: Failed to Connect.");
        }
        return null;
    }
}
