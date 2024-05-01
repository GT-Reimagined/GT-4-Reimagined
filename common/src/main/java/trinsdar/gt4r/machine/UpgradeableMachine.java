package trinsdar.gt4r.machine;

import muramasa.antimatter.machine.types.BasicMachine;
import muramasa.antimatter.machine.types.BasicMultiMachine;
import trinsdar.gt4r.blockentity.single.BlockEntityUpgradeableMachine;
import trinsdar.gt4r.data.Textures;

public class UpgradeableMachine extends BasicMachine {
    public UpgradeableMachine(String domain, String id) {
        super(domain, id);
        baseTexture(Textures.BASE_HANDLER);
        setTile(BlockEntityUpgradeableMachine::new);
    }
}
