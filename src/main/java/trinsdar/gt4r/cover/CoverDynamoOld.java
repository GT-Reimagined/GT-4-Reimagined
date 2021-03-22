package trinsdar.gt4r.cover;

import muramasa.antimatter.cover.CoverDynamo;
import trinsdar.gt4r.Ref;

public class CoverDynamoOld extends CoverDynamo {
    public CoverDynamoOld(String id) {
        super(id);
    }

    @Override
    public String getDomain() {
        return Ref.ID;
    }
}
