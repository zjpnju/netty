/*
 * Copyright 2015 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.handler.codec.dns;

import io.netty.channel.AddressedEnvelope;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class DatagramDnsResponse extends DefaultDnsResponse
        implements AddressedEnvelope<DatagramDnsResponse, InetSocketAddress> {

    private final InetSocketAddress sender;
    private final InetSocketAddress recipient;

    public DatagramDnsResponse(InetSocketAddress sender, InetSocketAddress recipient, int id) {
        this(sender, recipient, id, DnsOpCode.QUERY, DnsResponseCode.NOERROR);
    }

    public DatagramDnsResponse(InetSocketAddress sender, InetSocketAddress recipient, int id, DnsOpCode opCode) {
        this(sender, recipient, id, opCode, DnsResponseCode.NOERROR);
    }

    public DatagramDnsResponse(
            InetSocketAddress sender, InetSocketAddress recipient,
            int id, DnsOpCode opCode, DnsResponseCode responseCode) {
        super(id, opCode, responseCode);
        this.sender = sender;
        this.recipient = recipient;
    }

    @Override
    public DatagramDnsResponse content() {
        return this;
    }

    @Override
    public InetSocketAddress sender() {
        return sender;
    }

    @Override
    public InetSocketAddress recipient() {
        return recipient;
    }

    @Override
    public DatagramDnsResponse setAuthoritativeAnswer(boolean authoritativeAnswer) {
        return (DatagramDnsResponse) super.setAuthoritativeAnswer(authoritativeAnswer);
    }

    @Override
    public DatagramDnsResponse setTruncated(boolean truncated) {
        return (DatagramDnsResponse) super.setTruncated(truncated);
    }

    @Override
    public DatagramDnsResponse setRecursionAvailable(boolean recursionAvailable) {
        return (DatagramDnsResponse) super.setRecursionAvailable(recursionAvailable);
    }

    @Override
    public DatagramDnsResponse setCode(DnsResponseCode code) {
        return (DatagramDnsResponse) super.setCode(code);
    }

    @Override
    public DatagramDnsResponse setId(int id) {
        return (DatagramDnsResponse) super.setId(id);
    }

    @Override
    public DatagramDnsResponse setOpCode(DnsOpCode opCode) {
        return (DatagramDnsResponse) super.setOpCode(opCode);
    }

    @Override
    public DatagramDnsResponse setRecursionDesired(boolean recursionDesired) {
        return (DatagramDnsResponse) super.setRecursionDesired(recursionDesired);
    }

    @Override
    public DatagramDnsResponse setZ(int z) {
        return (DatagramDnsResponse) super.setZ(z);
    }

    @Override
    public DatagramDnsResponse setQuestion(DnsQuestion question) {
        return (DatagramDnsResponse) super.setQuestion(question);
    }

    @Override
    public DatagramDnsResponse setAdditionalRecord(DnsRecord record) {
        return (DatagramDnsResponse) super.setAdditionalRecord(record);
    }

    @Override
    public DatagramDnsResponse touch() {
        return (DatagramDnsResponse) super.touch();
    }

    @Override
    public DatagramDnsResponse touch(Object hint) {
        return (DatagramDnsResponse) super.touch(hint);
    }

    @Override
    public DatagramDnsResponse retain() {
        return (DatagramDnsResponse) super.retain();
    }

    @Override
    public DatagramDnsResponse retain(int increment) {
        return (DatagramDnsResponse) super.retain(increment);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (!(obj instanceof AddressedEnvelope)) {
            return false;
        }

        @SuppressWarnings("unchecked")
        final AddressedEnvelope<?, SocketAddress> that = (AddressedEnvelope<?, SocketAddress>) obj;
        if (sender() == null) {
            if (that.sender() != null) {
                return false;
            }
        } else if (!sender().equals(that.sender())) {
            return false;
        }

        if (recipient() == null) {
            if (that.recipient() != null) {
                return false;
            }
        } else if (!recipient().equals(that.recipient())) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = super.hashCode();
        if (sender() != null) {
            hashCode = hashCode * 31 + sender().hashCode();
        }
        if (recipient() != null) {
            hashCode = hashCode * 31 + recipient().hashCode();
        }
        return hashCode;
    }
}