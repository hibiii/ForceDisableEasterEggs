package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.util.crash.CrashReport;

/*
 * Important notice about implementation:
 * 
 * We are mixing into the class responsible for the crash report. We ourselves
 * cannot crash here. This includes minimizing side effects. If something
 * returns something, then it *must* return something.
 * 
 * Additionally, if a method call should be removed, it should only be removed
 * if we are sure it's the one we want to remove. While At.ordinal alone is
 * enough for a single videogame implementation, ForceDisableEasterEggs has
 * been working on multiple Minecraft versions and the crash report may contain
 * crucial information for a mod developer, and we absolutely do not want to
 * omit that. What this means is that we must check what we are removing from
 * the crash report, and only act upon it if, and only if, it meets our
 * expectations.
 * 
 */
@Mixin(CrashReport.class)
public class CrashReportMixin {
    @Redirect(
        method = "asString",
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/StringBuilder;append(Ljava/lang/String;)Ljava/lang/StringBuilder;",
            ordinal = 1
        )
    )
    StringBuilder removeCommentPrefix(StringBuilder that, String arg) {
        // Implementation detail: we return the StringBuilder just in case Mojang chains .append(String) calls.
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
