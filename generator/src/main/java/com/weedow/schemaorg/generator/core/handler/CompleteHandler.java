package com.weedow.schemaorg.generator.core.handler;

import java.time.Duration;

public interface CompleteHandler {

    void onComplete(Duration elapsedTime);
}
