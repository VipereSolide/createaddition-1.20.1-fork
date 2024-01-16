package com.rose.gods_retribution.content.ponder;

import com.simibubi.create.foundation.ponder.SceneBuilder;
import com.simibubi.create.foundation.ponder.SceneBuildingUtil;
import net.minecraft.core.Direction;

public class PlasticMossPonderScene extends AbstractPonderScene
{
    @Override
    public void register(SceneBuilder scene, SceneBuildingUtil util)
    {
        scene.title("plastic_moss", "Block Of Plastic Moss");
        scene.configureBasePlate(0, 0, 3);
        scene.world.showSection(util.select.layer(0), Direction.UP);
    }
}
