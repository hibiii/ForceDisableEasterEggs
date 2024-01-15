package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.render.entity.EntityRenderer;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {
    @Redirect(
        method = "renderLabelIfPresent",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/String;equals(Ljava/lang/Object;)Z"
        )
    )
    boolean disableDeadmouseOffset(String that, Object _1) {
        return false;
    }
}
