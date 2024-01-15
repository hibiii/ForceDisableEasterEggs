package hibi.forcedisableeastereggs.mix;

import java.util.Collection;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.google.common.collect.Lists;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(
        method = "parseBlockTag",
        at = @At("TAIL"),
        cancellable = true
    )
    private static void useErrorMessage(String _1, CallbackInfoReturnable<Collection<Text>> info) {
        info.setReturnValue(Lists.newArrayList(new Text[]{
            Text.literal("Invalid block tag").formatted(Formatting.field_1061)
        }));
    }
}
