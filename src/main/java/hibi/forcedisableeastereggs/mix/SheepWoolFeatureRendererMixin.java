package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.render.entity.feature.SheepWoolFeatureRenderer;

@Mixin(SheepWoolFeatureRenderer.class)
public class SheepWoolFeatureRendererMixin {
    @Redirect(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"
        )
    )
    boolean disableJeb(String that, Object _1) {
        return false;
    }
}
