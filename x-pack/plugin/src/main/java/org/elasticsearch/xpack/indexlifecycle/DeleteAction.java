/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.xpack.indexlifecycle;

import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.logging.ESLoggerFactory;
import org.elasticsearch.common.xcontent.ObjectParser;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.index.Index;

import java.io.IOException;

public class DeleteAction extends LifecycleAction {
    public static final String NAME = "delete";

    private static final Logger logger = ESLoggerFactory.getLogger(DeleteAction.class);
    private static final ObjectParser<DeleteAction, Void> PARSER = new ObjectParser<>(NAME, DeleteAction::new);

    public static DeleteAction parse(XContentParser parser) {
        return PARSER.apply(parser, null);
    }

    public DeleteAction() {
    }

    public DeleteAction(StreamInput in) throws IOException {
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
    }

    @Override
    public String getWriteableName() {
        return NAME;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        builder.startObject();
        builder.endObject();
        return builder;
    }

    @Override
    protected void execute(Client client, Index index) {
        client.admin().indices().prepareDelete(index.getName()).execute(new ActionListener<DeleteIndexResponse>() {
            @Override
            public void onResponse(DeleteIndexResponse deleteIndexResponse) {
                logger.error(deleteIndexResponse);
            }

            @Override
            public void onFailure(Exception e) {
                logger.error(e);
            }
        });
    }

}
