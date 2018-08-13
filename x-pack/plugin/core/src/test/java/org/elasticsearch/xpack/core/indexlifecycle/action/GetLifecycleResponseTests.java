/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.core.indexlifecycle.action;

import org.elasticsearch.common.io.stream.NamedWriteableRegistry;
import org.elasticsearch.test.AbstractStreamableTestCase;
import org.elasticsearch.xpack.core.indexlifecycle.DeleteAction;
import org.elasticsearch.xpack.core.indexlifecycle.LifecycleAction;
import org.elasticsearch.xpack.core.indexlifecycle.LifecyclePolicy;
import org.elasticsearch.xpack.core.indexlifecycle.LifecycleType;
import org.elasticsearch.xpack.core.indexlifecycle.TestLifecycleType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GetLifecycleResponseTests extends AbstractStreamableTestCase<GetIndexLifecyclePolicyResponse> {

    @Override
    protected GetIndexLifecyclePolicyResponse createTestInstance() {
        String randomPrefix = randomAlphaOfLength(5);
        List<LifecyclePolicy> policies = new ArrayList<>();
        for (int i = 0; i < randomIntBetween(0, 2); i++) {
            policies.add(new LifecyclePolicy(TestLifecycleType.INSTANCE, randomPrefix + i, Collections.emptyMap()));
        }
        return new GetIndexLifecyclePolicyResponse(policies);
    }

    @Override
    protected GetIndexLifecyclePolicyResponse createBlankInstance() {
        return new GetIndexLifecyclePolicyResponse();
    }

    protected NamedWriteableRegistry getNamedWriteableRegistry() {
        return new NamedWriteableRegistry(
            Arrays.asList(new NamedWriteableRegistry.Entry(LifecycleAction.class, DeleteAction.NAME, DeleteAction::new),
                        new NamedWriteableRegistry.Entry(LifecycleType.class, TestLifecycleType.TYPE, in -> TestLifecycleType.INSTANCE)));
    }

    @Override
    protected GetIndexLifecyclePolicyResponse mutateInstance(GetIndexLifecyclePolicyResponse response) {
        List<LifecyclePolicy> policies = new ArrayList<>(response.getPolicies());
        if (policies.size() > 0) {
            if (randomBoolean()) {
                policies.add(new LifecyclePolicy(TestLifecycleType.INSTANCE, randomAlphaOfLength(2), Collections.emptyMap()));
            } else {
                policies.remove(policies.size() - 1);
            }
        } else {
            policies.add(new LifecyclePolicy(TestLifecycleType.INSTANCE, randomAlphaOfLength(2), Collections.emptyMap()));
        }
        return new GetIndexLifecyclePolicyResponse(policies);
    }
}
