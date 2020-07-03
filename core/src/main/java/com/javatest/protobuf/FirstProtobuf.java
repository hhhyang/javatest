
package com.javatest.protobuf;

public final class FirstProtobuf {
    private FirstProtobuf() {}
    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistryLite registry) {
    }

    public static void registerAllExtensions(
            com.google.protobuf.ExtensionRegistry registry) {
        registerAllExtensions(
                (com.google.protobuf.ExtensionRegistryLite) registry);
    }
    public interface testBufOrBuilder extends
            // @@protoc_insertion_point(interface_extends:protobuf.testBuf)
            com.google.protobuf.MessageOrBuilder {

        /**
         * <code>optional int32 ID = 1;</code>
         */
        int getID();

        /**
         * <code>optional string Url = 2;</code>
         */
        java.lang.String getUrl();
        /**
         * <code>optional string Url = 2;</code>
         */
        com.google.protobuf.ByteString
        getUrlBytes();
    }
    /**
     * Protobuf type {@code protobuf.testBuf}
     */
    public  static final class testBuf extends
            com.google.protobuf.GeneratedMessageV3 implements
            // @@protoc_insertion_point(message_implements:protobuf.testBuf)
            testBufOrBuilder {
        // Use testBuf.newBuilder() to construct.
        private testBuf(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
            super(builder);
        }
        private testBuf() {
            iD_ = 0;
            url_ = "";
        }

        @java.lang.Override
        public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
            return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
        }
        private testBuf(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            this();
            int mutable_bitField0_ = 0;
            try {
                boolean done = false;
                while (!done) {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                            break;
                        default: {
                            if (!input.skipField(tag)) {
                                done = true;
                            }
                            break;
                        }
                        case 8: {

                            iD_ = input.readInt32();
                            break;
                        }
                        case 18: {
                            java.lang.String s = input.readStringRequireUtf8();

                            url_ = s;
                            break;
                        }
                    }
                }
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                throw e.setUnfinishedMessage(this);
            } catch (java.io.IOException e) {
                throw new com.google.protobuf.InvalidProtocolBufferException(
                        e).setUnfinishedMessage(this);
            } finally {
                makeExtensionsImmutable();
            }
        }
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return com.javatest.protobuf.FirstProtobuf.internal_static_protobuf_testBuf_descriptor;
        }

        protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return com.javatest.protobuf.FirstProtobuf.internal_static_protobuf_testBuf_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            com.javatest.protobuf.FirstProtobuf.testBuf.class, com.javatest.protobuf.FirstProtobuf.testBuf.Builder.class);
        }

        public static final int ID_FIELD_NUMBER = 1;
        private int iD_;
        /**
         * <code>optional int32 ID = 1;</code>
         */
        public int getID() {
            return iD_;
        }

        public static final int URL_FIELD_NUMBER = 2;
        private volatile java.lang.Object url_;
        /**
         * <code>optional string Url = 2;</code>
         */
        public java.lang.String getUrl() {
            java.lang.Object ref = url_;
            if (ref instanceof java.lang.String) {
                return (java.lang.String) ref;
            } else {
                com.google.protobuf.ByteString bs =
                        (com.google.protobuf.ByteString) ref;
                java.lang.String s = bs.toStringUtf8();
                url_ = s;
                return s;
            }
        }
        /**
         * <code>optional string Url = 2;</code>
         */
        public com.google.protobuf.ByteString
        getUrlBytes() {
            java.lang.Object ref = url_;
            if (ref instanceof java.lang.String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                url_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        private byte memoizedIsInitialized = -1;
        public final boolean isInitialized() {
            byte isInitialized = memoizedIsInitialized;
            if (isInitialized == 1) return true;
            if (isInitialized == 0) return false;

            memoizedIsInitialized = 1;
            return true;
        }

        public void writeTo(com.google.protobuf.CodedOutputStream output)
                throws java.io.IOException {
            if (iD_ != 0) {
                output.writeInt32(1, iD_);
            }
            if (!getUrlBytes().isEmpty()) {
                com.google.protobuf.GeneratedMessageV3.writeString(output, 2, url_);
            }
        }

        public int getSerializedSize() {
            int size = memoizedSize;
            if (size != -1) return size;

            size = 0;
            if (iD_ != 0) {
                size += com.google.protobuf.CodedOutputStream
                        .computeInt32Size(1, iD_);
            }
            if (!getUrlBytes().isEmpty()) {
                size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, url_);
            }
            memoizedSize = size;
            return size;
        }

        private static final long serialVersionUID = 0L;
        @java.lang.Override
        public boolean equals(final java.lang.Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof com.javatest.protobuf.FirstProtobuf.testBuf)) {
                return super.equals(obj);
            }
            com.javatest.protobuf.FirstProtobuf.testBuf other = (com.javatest.protobuf.FirstProtobuf.testBuf) obj;

            boolean result = true;
            result = result && (getID()
                                        == other.getID());
            result = result && getUrl()
                    .equals(other.getUrl());
            return result;
        }

        @java.lang.Override
        public int hashCode() {
            if (memoizedHashCode != 0) {
                return memoizedHashCode;
            }
            int hash = 41;
            hash = (19 * hash) + getDescriptorForType().hashCode();
            hash = (37 * hash) + ID_FIELD_NUMBER;
            hash = (53 * hash) + getID();
            hash = (37 * hash) + URL_FIELD_NUMBER;
            hash = (53 * hash) + getUrl().hashCode();
            hash = (29 * hash) + unknownFields.hashCode();
            memoizedHashCode = hash;
            return hash;
        }

        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(
                com.google.protobuf.ByteString data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(
                com.google.protobuf.ByteString data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(byte[] data)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(
                byte[] data,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return PARSER.parseFrom(data, extensionRegistry);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseDelimitedFrom(java.io.InputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseDelimitedFrom(
                java.io.InputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(
                com.google.protobuf.CodedInputStream input)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input);
        }
        public static com.javatest.protobuf.FirstProtobuf.testBuf parseFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            return com.google.protobuf.GeneratedMessageV3
                    .parseWithIOException(PARSER, input, extensionRegistry);
        }

        public Builder newBuilderForType() { return newBuilder(); }
        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.toBuilder();
        }
        public static Builder newBuilder(com.javatest.protobuf.FirstProtobuf.testBuf prototype) {
            return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
        }
        public Builder toBuilder() {
            return this == DEFAULT_INSTANCE
                    ? new Builder() : new Builder().mergeFrom(this);
        }

        @java.lang.Override
        protected Builder newBuilderForType(
                com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
            Builder builder = new Builder(parent);
            return builder;
        }
        /**
         * Protobuf type {@code protobuf.testBuf}
         */
        public static final class Builder extends
                com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
                // @@protoc_insertion_point(builder_implements:protobuf.testBuf)
                com.javatest.protobuf.FirstProtobuf.testBufOrBuilder {
            public static final com.google.protobuf.Descriptors.Descriptor
            getDescriptor() {
                return com.javatest.protobuf.FirstProtobuf.internal_static_protobuf_testBuf_descriptor;
            }

            protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internalGetFieldAccessorTable() {
                return com.javatest.protobuf.FirstProtobuf.internal_static_protobuf_testBuf_fieldAccessorTable
                        .ensureFieldAccessorsInitialized(
                                com.javatest.protobuf.FirstProtobuf.testBuf.class, com.javatest.protobuf.FirstProtobuf.testBuf.Builder.class);
            }

            // Construct using com.javatest.protobuf.FirstProtobuf.testBuf.newBuilder()
            private Builder() {
                maybeForceBuilderInitialization();
            }

            private Builder(
                    com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
                super(parent);
                maybeForceBuilderInitialization();
            }
            private void maybeForceBuilderInitialization() {
                if (com.google.protobuf.GeneratedMessageV3
                        .alwaysUseFieldBuilders) {
                }
            }
            public Builder clear() {
                super.clear();
                iD_ = 0;

                url_ = "";

                return this;
            }

            public com.google.protobuf.Descriptors.Descriptor
            getDescriptorForType() {
                return com.javatest.protobuf.FirstProtobuf.internal_static_protobuf_testBuf_descriptor;
            }

            public com.javatest.protobuf.FirstProtobuf.testBuf getDefaultInstanceForType() {
                return com.javatest.protobuf.FirstProtobuf.testBuf.getDefaultInstance();
            }

            public com.javatest.protobuf.FirstProtobuf.testBuf build() {
                com.javatest.protobuf.FirstProtobuf.testBuf result = buildPartial();
                if (!result.isInitialized()) {
                    throw newUninitializedMessageException(result);
                }
                return result;
            }

            public com.javatest.protobuf.FirstProtobuf.testBuf buildPartial() {
                com.javatest.protobuf.FirstProtobuf.testBuf result = new com.javatest.protobuf.FirstProtobuf.testBuf(this);
                result.iD_ = iD_;
                result.url_ = url_;
                onBuilt();
                return result;
            }

            public Builder clone() {
                return (Builder) super.clone();
            }
            public Builder setField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.setField(field, value);
            }
            public Builder clearField(
                    com.google.protobuf.Descriptors.FieldDescriptor field) {
                return (Builder) super.clearField(field);
            }
            public Builder clearOneof(
                    com.google.protobuf.Descriptors.OneofDescriptor oneof) {
                return (Builder) super.clearOneof(oneof);
            }
            public Builder setRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    int index, Object value) {
                return (Builder) super.setRepeatedField(field, index, value);
            }
            public Builder addRepeatedField(
                    com.google.protobuf.Descriptors.FieldDescriptor field,
                    Object value) {
                return (Builder) super.addRepeatedField(field, value);
            }
            public Builder mergeFrom(com.google.protobuf.Message other) {
                if (other instanceof com.javatest.protobuf.FirstProtobuf.testBuf) {
                    return mergeFrom((com.javatest.protobuf.FirstProtobuf.testBuf)other);
                } else {
                    super.mergeFrom(other);
                    return this;
                }
            }

            public Builder mergeFrom(com.javatest.protobuf.FirstProtobuf.testBuf other) {
                if (other == com.javatest.protobuf.FirstProtobuf.testBuf.getDefaultInstance()) return this;
                if (other.getID() != 0) {
                    setID(other.getID());
                }
                if (!other.getUrl().isEmpty()) {
                    url_ = other.url_;
                    onChanged();
                }
                onChanged();
                return this;
            }

            public final boolean isInitialized() {
                return true;
            }

            public Builder mergeFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws java.io.IOException {
                com.javatest.protobuf.FirstProtobuf.testBuf parsedMessage = null;
                try {
                    parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
                } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                    parsedMessage = (com.javatest.protobuf.FirstProtobuf.testBuf) e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                } finally {
                    if (parsedMessage != null) {
                        mergeFrom(parsedMessage);
                    }
                }
                return this;
            }

            private int iD_ ;
            /**
             * <code>optional int32 ID = 1;</code>
             */
            public int getID() {
                return iD_;
            }
            /**
             * <code>optional int32 ID = 1;</code>
             */
            public Builder setID(int value) {

                iD_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>optional int32 ID = 1;</code>
             */
            public Builder clearID() {

                iD_ = 0;
                onChanged();
                return this;
            }

            private java.lang.Object url_ = "";
            /**
             * <code>optional string Url = 2;</code>
             */
            public java.lang.String getUrl() {
                java.lang.Object ref = url_;
                if (!(ref instanceof java.lang.String)) {
                    com.google.protobuf.ByteString bs =
                            (com.google.protobuf.ByteString) ref;
                    java.lang.String s = bs.toStringUtf8();
                    url_ = s;
                    return s;
                } else {
                    return (java.lang.String) ref;
                }
            }
            /**
             * <code>optional string Url = 2;</code>
             */
            public com.google.protobuf.ByteString
            getUrlBytes() {
                java.lang.Object ref = url_;
                if (ref instanceof String) {
                    com.google.protobuf.ByteString b =
                            com.google.protobuf.ByteString.copyFromUtf8(
                                    (java.lang.String) ref);
                    url_ = b;
                    return b;
                } else {
                    return (com.google.protobuf.ByteString) ref;
                }
            }
            /**
             * <code>optional string Url = 2;</code>
             */
            public Builder setUrl(
                    java.lang.String value) {
                if (value == null) {
                    throw new NullPointerException();
                }

                url_ = value;
                onChanged();
                return this;
            }
            /**
             * <code>optional string Url = 2;</code>
             */
            public Builder clearUrl() {

                url_ = getDefaultInstance().getUrl();
                onChanged();
                return this;
            }
            /**
             * <code>optional string Url = 2;</code>
             */
            public Builder setUrlBytes(
                    com.google.protobuf.ByteString value) {
                if (value == null) {
                    throw new NullPointerException();
                }
                checkByteStringIsUtf8(value);

                url_ = value;
                onChanged();
                return this;
            }
            public final Builder setUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return this;
            }

            public final Builder mergeUnknownFields(
                    final com.google.protobuf.UnknownFieldSet unknownFields) {
                return this;
            }


            // @@protoc_insertion_point(builder_scope:protobuf.testBuf)
        }

        // @@protoc_insertion_point(class_scope:protobuf.testBuf)
        private static final com.javatest.protobuf.FirstProtobuf.testBuf DEFAULT_INSTANCE;
        static {
            DEFAULT_INSTANCE = new com.javatest.protobuf.FirstProtobuf.testBuf();
        }

        public static com.javatest.protobuf.FirstProtobuf.testBuf getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        private static final com.google.protobuf.Parser<testBuf>
                PARSER = new com.google.protobuf.AbstractParser<testBuf>() {
            public testBuf parsePartialFrom(
                    com.google.protobuf.CodedInputStream input,
                    com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                    throws com.google.protobuf.InvalidProtocolBufferException {
                return new testBuf(input, extensionRegistry);
            }
        };

        public static com.google.protobuf.Parser<testBuf> parser() {
            return PARSER;
        }

        @java.lang.Override
        public com.google.protobuf.Parser<testBuf> getParserForType() {
            return PARSER;
        }

        public com.javatest.protobuf.FirstProtobuf.testBuf getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

    }

    private static final com.google.protobuf.Descriptors.Descriptor
            internal_static_protobuf_testBuf_descriptor;
    private static final
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
            internal_static_protobuf_testBuf_fieldAccessorTable;

    public static com.google.protobuf.Descriptors.FileDescriptor
    getDescriptor() {
        return descriptor;
    }
    private static  com.google.protobuf.Descriptors.FileDescriptor
            descriptor;
    static {
        java.lang.String[] descriptorData = {
                "\n\ntest.proto\022\010protobuf\"\"\n\007testBuf\022\n\n\002ID\030" +
                        "\001 \001(\005\022\013\n\003Url\030\002 \001(\tB#\n\022com.javatest.protobuf" +
                        "B\rFirstProtobufb\006proto3"
        };
        com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
                new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
                    public com.google.protobuf.ExtensionRegistry assignDescriptors(
                            com.google.protobuf.Descriptors.FileDescriptor root) {
                        descriptor = root;
                        return null;
                    }
                };
        com.google.protobuf.Descriptors.FileDescriptor
                .internalBuildGeneratedFileFrom(descriptorData,
                        new com.google.protobuf.Descriptors.FileDescriptor[] {
                        }, assigner);
        internal_static_protobuf_testBuf_descriptor =
                getDescriptor().getMessageTypes().get(0);
        internal_static_protobuf_testBuf_fieldAccessorTable = new
                com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
                internal_static_protobuf_testBuf_descriptor,
                new java.lang.String[] { "ID", "Url", });
    }

    // @@protoc_insertion_point(outer_class_scope)
}

