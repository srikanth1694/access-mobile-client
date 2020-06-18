/*
 *  This file is part of the IOTA Access distribution
 *  (https://github.com/iotaledger/access)
 *
 *  Copyright (c) 2020 IOTA Stiftung.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.iota.access.models

data class DelegationUser(
        val username: String,
        val publicId: String
) : PolicyAttributeList.Builder {

    override fun build(): PolicyAttributeList = PolicyAttributeComparable(
            PolicyAttributeSingle("public_id", "0x$publicId"),
            PolicyAttributeSingle("request.subject.type", "request.subject.value"),
            PolicyAttributeComparable.Operation.EQUAL
    )

    fun policyAttrList(obfuscate: Boolean) = PolicyAttributeComparable(
            PolicyAttributeSingle("public_id", if (obfuscate) "**********" else "0x$publicId"),
            PolicyAttributeSingle("request.subject.type", "request.subject.value"),
            PolicyAttributeComparable.Operation.EQUAL
    )

    override fun toString(): String = username
}
