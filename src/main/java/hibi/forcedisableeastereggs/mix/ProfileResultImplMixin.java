package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.profiler.ProfileResultImpl;

/*
 * Notice about implementation:
 * 
 * We are mixing into the class responsible for the reporting result from
 * profiling. While not as critical as the CrashReport mixin, it's still a very
 * good idea to minimize side effects as the profile reports can contain useful
 * data for developers, and we don't want to omit any of it. If any method call
 * should be removed, we should only remove it if the arguments are what we're
 * expecting. For a more detailed explanation, see @see CrashReportMixin.
 * 
 */
@Mixin(ProfileResultImpl.class)
public class ProfileResultImplMixin {
    @Redirect(
        method = "asString",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            ordinal = 1
        )
    )
    StringBuilder removeCommentPrefix(StringBuilder that, String arg) {
        // Implementation detail: we return the StringBuilder because Mojang chains .append(String) calls.
        if (arg.equals("// ")) {
            return that;
        } else {
            return that.append(arg);
        }
    }

    @Redirect(
        method = "asString",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            ordinal = 3
        )
    )
    StringBuilder removeExtraNewline(StringBuilder that, String arg) {
        // Implementation detail: we return the StringBuilder just in case Mojang chains .append(String) calls.
        if (arg.equals("\n\n")) {
            return that.append("\n");
        } else {
            return that.append(arg);
        }
    }
    
    @Inject(
        method = "generateWittyComment",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void disableComment(CallbackInfoReturnable<String> info) {
        info.setReturnValue("");
    }
}
