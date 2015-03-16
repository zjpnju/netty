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

import io.netty.util.internal.StringUtil;

import static io.netty.util.internal.ObjectUtil.checkNotNull;

public class AbstractDnsRecord implements DnsRecord {

    private final String name;
    private final DnsRecordType type;
    private final DnsRecordClass dnsClass;
    private final long timeToLive;

    protected AbstractDnsRecord(String name, DnsRecordType type, long timeToLive) {
        this(name, type, DnsRecordClass.IN, timeToLive);
    }

    protected AbstractDnsRecord(String name, DnsRecordType type, DnsRecordClass dnsClass, long timeToLive) {
        if (timeToLive < 0) {
            throw new IllegalArgumentException("timeToLive: " + timeToLive + " (expected: >= 0)");
        }

        this.name = checkNotNull(name, "name");
        this.type = checkNotNull(type, "type");
        this.dnsClass = checkNotNull(dnsClass, "dnsClass");
        this.timeToLive = timeToLive;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public DnsRecordType type() {
        return type;
    }

    @Override
    public DnsRecordClass dnsClass() {
        return dnsClass;
    }

    @Override
    public long timeToLive() {
        return timeToLive;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DnsRecord)) {
            return false;
        }

        final DnsRecord that = (DnsRecord) obj;
        return type().intValue() == that.type().intValue() &&
               dnsClass().intValue() == that.dnsClass().intValue() &&
               name().equals(that.name());
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 +
               type().intValue() * 31 +
               dnsClass().intValue();
    }

    @Override
    public String toString() {
        return new StringBuilder(64)
                .append(StringUtil.simpleClassName(this))
                .append('(')
                .append(name())
                .append(' ')
                .append(timeToLive())
                .append(' ')
                .append(dnsClass().name())
                .append(' ')
                .append(type().name())
                .append(')')
                .toString();
    }
}