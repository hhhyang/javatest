package com.javatest.general;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtnAssetRequest {

    /**
     * {"halo_cmd":"equip","pno":1,"psize":1000,"con":{"idc":"BB"}}
     */

    @SerializedName("halo_cmd")
    private String haloCmd;
    @SerializedName("pno")
    private Integer pno;
    @SerializedName("psize")
    private Integer psize;
    @SerializedName("con")
    private Map<String, Object> con;


    public static OtnAssetRequest of(String haloCmd) {

        OtnAssetRequest r = new OtnAssetRequest();
        r.setHaloCmd(haloCmd);
        return r;
    }

    public static OtnAssetRequest of(String haloCmd, String idcs) {

        OtnAssetRequest r = new OtnAssetRequest();
        r.setHaloCmd(haloCmd);
        r.setPno(1);
        r.setPsize(10000);

        Map<String, Object> con = new HashMap<>();
        con.put("idc", idcs);
        r.setCon(con);

        return r;
    }

}
