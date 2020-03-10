package org.taymyr.lagom.demo.impl;


import com.google.common.net.MediaType;
import com.lightbend.lagom.javadsl.api.transport.MessageProtocol;
import java.util.Optional;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class MessageProtocols {

    @JvmStatic
    @NotNull
    public static final MessageProtocol fromMediaType(@NotNull MediaType mediaType) {
        Intrinsics.checkParameterIsNotNull(mediaType, "mediaType");
        MessageProtocol var10000 = MessageProtocol.fromContentTypeHeader(Optional.of(mediaType.toString()));
        Intrinsics.checkExpressionValueIsNotNull(var10000, "MessageProtocol.fromCont…of(mediaType.toString()))");
        return var10000;
    }

}
