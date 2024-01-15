package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.recipe.book.RecipeBookWidget;

@Mixin(RecipeBookWidget.class)
public class RecipeBookWidgetMixin {
    @Inject(
        method = "triggerPirateSpeakEasterEgg",
        at = @At("HEAD"),
        cancellable = true
    )
    private void disableExcitedze(String _1, CallbackInfo info) {
        info.cancel();
    }
}
