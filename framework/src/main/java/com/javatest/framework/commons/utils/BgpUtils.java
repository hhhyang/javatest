package com.javatest.framework.commons.utils;

import java.util.Arrays;


public interface BgpUtils {

    Long PUBLIC_AS_2_LOWBOUND = 1L;
    Long PUBLIC_AS_2_UPBOUND = 64511L;
    Long PUBLIC_AS_4_LOWBOUND = 65536L;
    Long PUBLIC_AS_4_UPBOUND = 41_9999_9999L;

    Long B1_ASN = 64512L; // B1
    Long B2_ASN = 38365L; // B2
    Long GI_ASN = 55967L; // GI

    Long[] OWN_PUBLIC_ASNS = new Long[]{B1_ASN, B2_ASN, GI_ASN};


    static boolean isPublicAs(final Long as) {

        Long unsignedAs = as & 0x0_FFFF_FFFFL;

        return (unsignedAs >= PUBLIC_AS_2_LOWBOUND && unsignedAs <= PUBLIC_AS_2_UPBOUND)
                || (unsignedAs >= PUBLIC_AS_4_LOWBOUND && unsignedAs <= PUBLIC_AS_4_UPBOUND);

    }

    static boolean isIspAs(final Long as) {

        if (isPublicAs(as)) {
            return Arrays.binarySearch(OWN_PUBLIC_ASNS, as) < 0;
        }

        return false;

    }

}
