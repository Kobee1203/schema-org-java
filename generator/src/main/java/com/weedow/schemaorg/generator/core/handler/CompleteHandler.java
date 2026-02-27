package com.weedow.schemaorg.generator.core.handler;

import java.time.Duration;

/**
 * Handler interface for code generation completion.
 */
public interface CompleteHandler {

    /**
     * Called when code generation completes successfully.
     *
     * @param elapsedTime the time taken for generation
     */
    void onComplete(Duration elapsedTime);
}
