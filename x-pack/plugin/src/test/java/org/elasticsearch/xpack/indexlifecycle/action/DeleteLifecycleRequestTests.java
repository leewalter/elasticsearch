/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.indexlifecycle.action;

import org.elasticsearch.test.AbstractStreamableTestCase;
import org.elasticsearch.test.EqualsHashCodeTestUtils.MutateFunction;
import org.elasticsearch.xpack.indexlifecycle.action.DeleteLifecycleAction.Request;

public class DeleteLifecycleRequestTests extends AbstractStreamableTestCase<DeleteLifecycleAction.Request> {

    @Override
    protected Request createTestInstance() {
        return new Request(randomAlphaOfLengthBetween(1, 20));
    }

    @Override
    protected Request createBlankInstance() {
        return new Request();
    }

    @Override
    protected MutateFunction<Request> getMutateFunction() {
        return resp -> new Request(resp.getPolicyName() + randomAlphaOfLengthBetween(1, 10));
    }

}
