package com.rose.gods_retribution.content.block.labelling_machine;

import com.simibubi.create.content.kinetics.press.PressingBehaviour;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;

public class LabellingBehaviour extends PressingBehaviour
{
    public <T extends SmartBlockEntity & PressingBehaviourSpecifics> LabellingBehaviour(T be)
    {
        super(be);
    }
}
