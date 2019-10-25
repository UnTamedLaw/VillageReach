package com.lawk.villagereach;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderModel {
    private static ArrayList<Order> orderArrayList;
    private static final String TAG = "Data:";


    public static void grabJson() {
        String dummydata = "{\"content\":[\n" +
                "  {\n" +
                "    \"id\":\"35e5059a-fd92-4078-a448-b00402b3fb5b\",\n" +
                "    \"externalId\":\"f152b9bb-c1e2-40b6-b381-28d739208662\",\n" +
                "    \"emergency\":false,\n" +
                "    \"facility\":\n" +
                "      {\n" +
                "        \"code\":\"HF01\",\n" +
                "        \"name\":\"Kankao Health Facility\",\n" +
                "        \"description\":null,\n" +
                "        \"active\":true,\n" +
                "        \"goLiveDate\":null,\n" +
                "        \"goDownDate\":null,\n" +
                "        \"comment\":null,\n" +
                "        \"enabled\":true,\n" +
                "        \"openLmisAccessible\":null,\n" +
                "        \"supportedPrograms\":null,\n" +
                "        \"geographicZone\":\n" +
                "        {\n" +
                "          \"code\":\"Malawi-Southern-Zomba\",\n" +
                "          \"name\":\"Zomba District\",\n" +
                "          \"level\":\n" +
                "          {\n" +
                "            \"code\":\"District\",\n" +
                "            \"name\":null,\n" +
                "            \"levelNumber\":3,\n" +
                "            \"id\":\"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "          },\n" +
                "          \"parent\":\n" +
                "          {\n" +
                "            \"code\":\"Malawi-Southern\",\n" +
                "            \"name\":\"Southern Region\",\n" +
                "            \"level\":\n" +
                "            {\n" +
                "              \"code\":\"Region\",\n" +
                "              \"name\":null,\"levelNumber\":2,\n" +
                "              \"id\":\"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "            },\n" +
                "            \"parent\":\n" +
                "            {\n" +
                "              \"code\":\"Malawi\",\n" +
                "              \"name\":\"Malawi\",\n" +
                "              \"level\":\n" +
                "              {\n" +
                "                \"code\":\"Country\",\n" +
                "                \"name\":null,\n" +
                "                \"levelNumber\":1,\n" +
                "                \"id\":\"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "              },\n" +
                "              \"parent\":null,\"id\":\"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "            },\n" +
                "            \"id\":\"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "          },\n" +
                "        \"id\":\"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\":null,\n" +
                "      \"type\":\n" +
                "      {\n" +
                "        \"code\":\"health_center\",\n" +
                "        \"name\":\"Health Center\",\n" +
                "        \"description\":null,\n" +
                "        \"displayOrder\":3,\n" +
                "        \"active\":true,\n" +
                "        \"id\":\"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\":\"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"processingPeriod\":\n" +
                "    {\n" +
                "      \"processingSchedule\":\n" +
                "      {\n" +
                "        \"code\":\"SCH001\",\n" +
                "        \"description\":null,\n" +
                "        \"modifiedDate\":null,\n" +
                "        \"name\":\"Monthly\",\n" +
                "        \"id\":\"9c15bd6e-3f6b-4b91-b53a-36c199d35eac\"\n" +
                "      },\n" +
                "      \"name\":\"Jan2017\",\n" +
                "      \"description\":null,\n" +
                "      \"startDate\":\"2017-01-01\",\n" +
                "      \"endDate\":\"2017-01-31\",\n" +
                "      \"id\":\"516ac930-0d28-49f5-a178-64764e22b236\"\n" +
                "    },\n" +
                "    \"createdDate\":\"2016-04-30T16:45:22Z\",\n" +
                "    \"createdBy\":\n" +
                "    {\n" +
                "      \"username\":\"administrator\",\n" +
                "      \"firstName\":\"Admin\",\n" +
                "      \"lastName\":\"Admin\",\n" +
                "      \"email\":null,\n" +
                "      \"verified\":false,\n" +
                "      \"active\":true,\n" +
                "      \"homeFacilityId\":\"e6799d64-d10d-4011-b8c2-0e4d4a3f65ce\",\n" +
                "      \"roleAssignments\":[],\n" +
                "      \"id\":\"a337ec45-31a0-4f2b-9b2e-a105c4b669bb\"\n" +
                "    },\n" +
                "    \"program\":\n" +
                "    {\n" +
                "      \"code\":\"PRG001\",\n" +
                "      \"name\":\"Family Planning\",\n" +
                "      \"description\":null,\n" +
                "      \"active\":true,\n" +
                "      \"periodsSkippable\":false,\n" +
                "      \"showNonFullSupplyTab\":true,\n" +
                "      \"supportLocallyFulfilled\":false,\n" +
                "      \"id\":\"dce17f2e-af3e-40ad-8e00-3496adef44c3\"\n" +
                "    },\n" +
                "    \"requestingFacility\" : {\n" +
                "      \"code\" : \"DH01\",\n" +
                "      \"name\" : \"Balaka District Hospital\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Balaka-Balaka\",\n" +
                "        \"name\" : \"Balaka\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"City\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 4,\n" +
                "          \"id\" : \"90e35999-a64f-4312-ba8f-bc13a1311c75\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern-Balaka\",\n" +
                "          \"name\" : \"Balaka District\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"District\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 3,\n" +
                "            \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi-Southern\",\n" +
                "            \"name\" : \"Southern Region\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Region\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 2,\n" +
                "              \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "            },\n" +
                "            \"parent\" : {\n" +
                "              \"code\" : \"Malawi\",\n" +
                "              \"name\" : \"Malawi\",\n" +
                "              \"level\" : {\n" +
                "                \"code\" : \"Country\",\n" +
                "                \"name\" : null,\n" +
                "                \"levelNumber\" : 1,\n" +
                "                \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "              },\n" +
                "              \"parent\" : null,\n" +
                "              \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "            },\n" +
                "            \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "          },\n" +
                "          \"id\" : \"50293982-602a-4046-ac29-d7f83a8a13e9\"\n" +
                "        },\n" +
                "        \"id\" : \"bf2b810b-cdbf-48b2-b569-149b3cf42387\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"dist_hosp\",\n" +
                "        \"name\" : \"District Hospital\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 4,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"663b1d34-cc17-4d60-9619-e553e45aa441\"\n" +
                "      },\n" +
                "      \"id\" : \"13037147-1769-4735-90a7-b9b310d128b8\"\n" +
                "    },\n" +
                "    \"receivingFacility\" : {\n" +
                "      \"code\" : \"DH01\",\n" +
                "      \"name\" : \"Balaka District Hospital\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Balaka-Balaka\",\n" +
                "        \"name\" : \"Balaka\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"City\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 4,\n" +
                "          \"id\" : \"90e35999-a64f-4312-ba8f-bc13a1311c75\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern-Balaka\",\n" +
                "          \"name\" : \"Balaka District\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"District\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 3,\n" +
                "            \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi-Southern\",\n" +
                "            \"name\" : \"Southern Region\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Region\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 2,\n" +
                "              \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "            },\n" +
                "            \"parent\" : {\n" +
                "              \"code\" : \"Malawi\",\n" +
                "              \"name\" : \"Malawi\",\n" +
                "              \"level\" : {\n" +
                "                \"code\" : \"Country\",\n" +
                "                \"name\" : null,\n" +
                "                \"levelNumber\" : 1,\n" +
                "                \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "              },\n" +
                "              \"parent\" : null,\n" +
                "              \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "            },\n" +
                "            \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "          },\n" +
                "          \"id\" : \"50293982-602a-4046-ac29-d7f83a8a13e9\"\n" +
                "        },\n" +
                "        \"id\" : \"bf2b810b-cdbf-48b2-b569-149b3cf42387\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"dist_hosp\",\n" +
                "        \"name\" : \"District Hospital\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 4,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"663b1d34-cc17-4d60-9619-e553e45aa441\"\n" +
                "      },\n" +
                "      \"id\" : \"13037147-1769-4735-90a7-b9b310d128b8\"\n" +
                "    },\n" +
                "    \"supplyingFacility\" : {\n" +
                "      \"code\" : \"WH02\",\n" +
                "      \"name\" : \"Balaka District Warehouse\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Balaka-Balaka\",\n" +
                "        \"name\" : \"Balaka\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"City\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 4,\n" +
                "          \"id\" : \"90e35999-a64f-4312-ba8f-bc13a1311c75\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern-Balaka\",\n" +
                "          \"name\" : \"Balaka District\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"District\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 3,\n" +
                "            \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi-Southern\",\n" +
                "            \"name\" : \"Southern Region\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Region\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 2,\n" +
                "              \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "            },\n" +
                "            \"parent\" : {\n" +
                "              \"code\" : \"Malawi\",\n" +
                "              \"name\" : \"Malawi\",\n" +
                "              \"level\" : {\n" +
                "                \"code\" : \"Country\",\n" +
                "                \"name\" : null,\n" +
                "                \"levelNumber\" : 1,\n" +
                "                \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "              },\n" +
                "              \"parent\" : null,\n" +
                "              \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "            },\n" +
                "            \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "          },\n" +
                "          \"id\" : \"50293982-602a-4046-ac29-d7f83a8a13e9\"\n" +
                "        },\n" +
                "        \"id\" : \"bf2b810b-cdbf-48b2-b569-149b3cf42387\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"warehouse\",\n" +
                "        \"name\" : \"Warehouse\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 1,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"e2faaa9e-4b2d-4212-bb60-fd62970b2113\"\n" +
                "      },\n" +
                "      \"id\" : \"9318b2ea-9ae0-42b2-a7e1-353683f54000\"\n" +
                "    },\n" +
                "    \"orderCode\" : \"ORDER-00000000-0000-0000-0000-000000000009R\",\n" +
                "    \"status\" : \"RECEIVED\",\n" +
                "    \"quotedCost\" : 100000.00,\n" +
                "    \"lastUpdater\" : {\n" +
                "      \"id\" : \"a337ec45-31a0-4f2b-9b2e-a105c4b669bb\",\n" +
                "      \"href\" : \"null/api/users/a337ec45-31a0-4f2b-9b2e-a105c4b669bb\"\n" +
                "    },\n" +
                "    \"lastUpdatedDate\" : \"2019-10-16T01:34:04.918Z\"\n" +
                "  }, \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    {\n" +
                "    \"id\" : \"cda300a2-c9a8-4d0a-b5f1-10570ad036d0\",\n" +
                "    \"externalId\" : \"17542db8-7912-4b9f-a922-0b111e1565c8\",\n" +
                "    \"emergency\" : false,\n" +
                "    \"facility\" : {\n" +
                "      \"code\" : \"HF01\",\n" +
                "      \"name\" : \"Kankao Health Facility\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Zomba\",\n" +
                "        \"name\" : \"Zomba District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern\",\n" +
                "          \"name\" : \"Southern Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "        },\n" +
                "        \"id\" : \"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"health_center\",\n" +
                "        \"name\" : \"Health Center\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 3,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\" : \"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"processingPeriod\" : {\n" +
                "      \"processingSchedule\" : {\n" +
                "        \"code\" : \"SCH002\",\n" +
                "        \"description\" : null,\n" +
                "        \"modifiedDate\" : null,\n" +
                "        \"name\" : \"Quarterly\",\n" +
                "        \"id\" : \"057921bd-1841-4748-8523-dbe5ebb58368\"\n" +
                "      },\n" +
                "      \"name\" : \"2017Q2\",\n" +
                "      \"description\" : null,\n" +
                "      \"startDate\" : \"2017-04-01\",\n" +
                "      \"endDate\" : \"2017-06-30\",\n" +
                "      \"id\" : \"c5012ddf-b145-4eee-9640-b2cbf4f2da85\"\n" +
                "    },\n" +
                "    \"createdDate\" : \"2019-09-21T03:48:04.637Z\",\n" +
                "    \"createdBy\" : {\n" +
                "      \"username\" : \"administrator\",\n" +
                "      \"firstName\" : \"Admin\",\n" +
                "      \"lastName\" : \"Admin\",\n" +
                "      \"email\" : null,\n" +
                "      \"verified\" : false,\n" +
                "      \"active\" : true,\n" +
                "      \"homeFacilityId\" : \"e6799d64-d10d-4011-b8c2-0e4d4a3f65ce\",\n" +
                "      \"roleAssignments\" : [ ],\n" +
                "      \"id\" : \"a337ec45-31a0-4f2b-9b2e-a105c4b669bb\"\n" +
                "    },\n" +
                "    \"program\" : {\n" +
                "      \"code\" : \"PRG002\",\n" +
                "      \"name\" : \"Essential Meds\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"periodsSkippable\" : false,\n" +
                "      \"showNonFullSupplyTab\" : true,\n" +
                "      \"supportLocallyFulfilled\" : false,\n" +
                "      \"id\" : \"10845cb9-d365-4aaa-badd-b4fa39c6a26a\"\n" +
                "    },\n" +
                "    \"requestingFacility\" : {\n" +
                "      \"code\" : \"HF01\",\n" +
                "      \"name\" : \"Kankao Health Facility\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Zomba\",\n" +
                "        \"name\" : \"Zomba District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern\",\n" +
                "          \"name\" : \"Southern Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "        },\n" +
                "        \"id\" : \"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"health_center\",\n" +
                "        \"name\" : \"Health Center\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 3,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\" : \"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"receivingFacility\" : {\n" +
                "      \"code\" : \"HF01\",\n" +
                "      \"name\" : \"Kankao Health Facility\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Zomba\",\n" +
                "        \"name\" : \"Zomba District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern\",\n" +
                "          \"name\" : \"Southern Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "        },\n" +
                "        \"id\" : \"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"health_center\",\n" +
                "        \"name\" : \"Health Center\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 3,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\" : \"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"supplyingFacility\" : {\n" +
                "      \"code\" : \"WH02\",\n" +
                "      \"name\" : \"Balaka District Warehouse\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Balaka-Balaka\",\n" +
                "        \"name\" : \"Balaka\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"City\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 4,\n" +
                "          \"id\" : \"90e35999-a64f-4312-ba8f-bc13a1311c75\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern-Balaka\",\n" +
                "          \"name\" : \"Balaka District\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"District\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 3,\n" +
                "            \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi-Southern\",\n" +
                "            \"name\" : \"Southern Region\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Region\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 2,\n" +
                "              \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "            },\n" +
                "            \"parent\" : {\n" +
                "              \"code\" : \"Malawi\",\n" +
                "              \"name\" : \"Malawi\",\n" +
                "              \"level\" : {\n" +
                "                \"code\" : \"Country\",\n" +
                "                \"name\" : null,\n" +
                "                \"levelNumber\" : 1,\n" +
                "                \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "              },\n" +
                "              \"parent\" : null,\n" +
                "              \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "            },\n" +
                "            \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "          },\n" +
                "          \"id\" : \"50293982-602a-4046-ac29-d7f83a8a13e9\"\n" +
                "        },\n" +
                "        \"id\" : \"bf2b810b-cdbf-48b2-b569-149b3cf42387\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"warehouse\",\n" +
                "        \"name\" : \"Warehouse\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 1,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"e2faaa9e-4b2d-4212-bb60-fd62970b2113\"\n" +
                "      },\n" +
                "      \"id\" : \"9318b2ea-9ae0-42b2-a7e1-353683f54000\"\n" +
                "    },\n" +
                "    \"orderCode\" : \"ORDER-1DPXNVYQR\",\n" +
                "    \"status\" : \"RECEIVED\",\n" +
                "    \"quotedCost\" : 0.00,\n" +
                "    \"lastUpdater\" : {\n" +
                "      \"id\" : \"a337ec45-31a0-4f2b-9b2e-a105c4b669bb\",\n" +
                "      \"href\" : \"null/api/users/a337ec45-31a0-4f2b-9b2e-a105c4b669bb\"\n" +
                "    },\n" +
                "    \"lastUpdatedDate\" : \"2019-10-10T08:42:27.863Z\"\n" +
                "  }, {\n" +
                "    \"id\" : \"08c9f2e4-d766-4fc9-8bb7-831b10b59242\",\n" +
                "    \"externalId\" : \"21d138bc-f2c2-4188-adf3-a3aae381abde\",\n" +
                "    \"emergency\" : false,\n" +
                "    \"facility\" : {\n" +
                "      \"code\" : \"HF01\",\n" +
                "      \"name\" : \"Kankao Health Facility\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Zomba\",\n" +
                "        \"name\" : \"Zomba District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern\",\n" +
                "          \"name\" : \"Southern Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "        },\n" +
                "        \"id\" : \"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"health_center\",\n" +
                "        \"name\" : \"Health Center\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 3,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\" : \"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"processingPeriod\" : {\n" +
                "      \"processingSchedule\" : {\n" +
                "        \"code\" : \"SCH002\",\n" +
                "        \"description\" : null,\n" +
                "        \"modifiedDate\" : null,\n" +
                "        \"name\" : \"Quarterly\",\n" +
                "        \"id\" : \"057921bd-1841-4748-8523-dbe5ebb58368\"\n" +
                "      },\n" +
                "      \"name\" : \"2017Q3\",\n" +
                "      \"description\" : null,\n" +
                "      \"startDate\" : \"2017-07-01\",\n" +
                "      \"endDate\" : \"2017-09-30\",\n" +
                "      \"id\" : \"65671374-66e2-4a11-a7dc-05b645c6d815\"\n" +
                "    },\n" +
                "    \"createdDate\" : \"2019-10-10T18:53:42.453Z\",\n" +
                "    \"createdBy\" : {\n" +
                "      \"username\" : \"administrator\",\n" +
                "      \"firstName\" : \"Admin\",\n" +
                "      \"lastName\" : \"Admin\",\n" +
                "      \"email\" : null,\n" +
                "      \"verified\" : false,\n" +
                "      \"active\" : true,\n" +
                "      \"homeFacilityId\" : \"e6799d64-d10d-4011-b8c2-0e4d4a3f65ce\",\n" +
                "      \"roleAssignments\" : [ ],\n" +
                "      \"id\" : \"a337ec45-31a0-4f2b-9b2e-a105c4b669bb\"\n" +
                "    },\n" +
                "    \"program\" : {\n" +
                "      \"code\" : \"PRG001\",\n" +
                "      \"name\" : \"Family Planning\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"periodsSkippable\" : false,\n" +
                "      \"showNonFullSupplyTab\" : true,\n" +
                "      \"supportLocallyFulfilled\" : false,\n" +
                "      \"id\" : \"dce17f2e-af3e-40ad-8e00-3496adef44c3\"\n" +
                "    },\n" +
                "    \"requestingFacility\" : {\n" +
                "      \"code\" : \"HF01\",\n" +
                "      \"name\" : \"Kankao Health Facility\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Zomba\",\n" +
                "        \"name\" : \"Zomba District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern\",\n" +
                "          \"name\" : \"Southern Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "        },\n" +
                "        \"id\" : \"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"health_center\",\n" +
                "        \"name\" : \"Health Center\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 3,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\" : \"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"receivingFacility\" : {\n" +
                "      \"code\" : \"HF01\",\n" +
                "      \"name\" : \"Kankao Health Facility\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Southern-Zomba\",\n" +
                "        \"name\" : \"Zomba District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Southern\",\n" +
                "          \"name\" : \"Southern Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"0bbd69c1-e20f-48f5-aae4-26dcd8aa7602\"\n" +
                "        },\n" +
                "        \"id\" : \"2c48a33f-a3c7-4540-be02-78d3d513f961\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"health_center\",\n" +
                "        \"name\" : \"Health Center\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 3,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"ac1d268b-ce10-455f-bf87-9c667da8f060\"\n" +
                "      },\n" +
                "      \"id\" : \"7938919f-6f61-4d1a-a4dc-923c31e9cd45\"\n" +
                "    },\n" +
                "    \"supplyingFacility\" : {\n" +
                "      \"code\" : \"WH01\",\n" +
                "      \"name\" : \"Ntcheu District Warehouse\",\n" +
                "      \"description\" : null,\n" +
                "      \"active\" : true,\n" +
                "      \"goLiveDate\" : null,\n" +
                "      \"goDownDate\" : null,\n" +
                "      \"comment\" : null,\n" +
                "      \"enabled\" : true,\n" +
                "      \"openLmisAccessible\" : null,\n" +
                "      \"supportedPrograms\" : null,\n" +
                "      \"geographicZone\" : {\n" +
                "        \"code\" : \"Malawi-Central-Ntcheu\",\n" +
                "        \"name\" : \"Ntcheu District\",\n" +
                "        \"level\" : {\n" +
                "          \"code\" : \"District\",\n" +
                "          \"name\" : null,\n" +
                "          \"levelNumber\" : 3,\n" +
                "          \"id\" : \"93c05138-4550-4461-9e8a-79d5f050c223\"\n" +
                "        },\n" +
                "        \"parent\" : {\n" +
                "          \"code\" : \"Malawi-Central\",\n" +
                "          \"name\" : \"Central Region\",\n" +
                "          \"level\" : {\n" +
                "            \"code\" : \"Region\",\n" +
                "            \"name\" : null,\n" +
                "            \"levelNumber\" : 2,\n" +
                "            \"id\" : \"9b497d87-cdd9-400e-bb04-fae0bf6a9491\"\n" +
                "          },\n" +
                "          \"parent\" : {\n" +
                "            \"code\" : \"Malawi\",\n" +
                "            \"name\" : \"Malawi\",\n" +
                "            \"level\" : {\n" +
                "              \"code\" : \"Country\",\n" +
                "              \"name\" : null,\n" +
                "              \"levelNumber\" : 1,\n" +
                "              \"id\" : \"6b78e6c6-292e-4733-bb9c-3d802ad61206\"\n" +
                "            },\n" +
                "            \"parent\" : null,\n" +
                "            \"id\" : \"4e471242-da63-436c-8157-ade3e615c848\"\n" +
                "          },\n" +
                "          \"id\" : \"58d51132-de7d-49f6-ba8d-fd2b5673c3ff\"\n" +
                "        },\n" +
                "        \"id\" : \"f754e928-cfb3-4ab0-af68-3a9703e39b52\"\n" +
                "      },\n" +
                "      \"operator\" : null,\n" +
                "      \"type\" : {\n" +
                "        \"code\" : \"warehouse\",\n" +
                "        \"name\" : \"Warehouse\",\n" +
                "        \"description\" : null,\n" +
                "        \"displayOrder\" : 1,\n" +
                "        \"active\" : true,\n" +
                "        \"id\" : \"e2faaa9e-4b2d-4212-bb60-fd62970b2113\"\n" +
                "      },\n" +
                "      \"id\" : \"19121381-9f3d-4e77-b9e5-d3f59fc1639e\"\n" +
                "    },\n" +
                "    \"orderCode\" : \"ORDER-202OBB8DR\",\n" +
                "    \"status\" : \"IN_ROUTE\",\n" +
                "    \"quotedCost\" : 0.00,\n" +
                "    \"lastUpdater\" : {\n" +
                "      \"id\" : \"a337ec45-31a0-4f2b-9b2e-a105c4b669bb\",\n" +
                "      \"href\" : \"null/api/users/a337ec45-31a0-4f2b-9b2e-a105c4b669bb\"\n" +
                "    },\n" +
                "    \"lastUpdatedDate\" : \"2019-10-10T18:53:42.418Z\"\n" +
                "  } ],\n" +
                "  \"last\" : true,\n" +
                "  \"totalElements\" : 8,\n" +
                "  \"totalPages\" : 1,\n" +
                "  \"sort\" : null,\n" +
                "  \"first\" : true,\n" +
                "  \"numberOfElements\" : 8,\n" +
                "  \"size\" : 2000,\n" +
                "  \"number\" : 0\n" +
                "} ";
        try{
            JSONObject jsonObject = new JSONObject(dummydata);
            JSONArray content = jsonObject.getJSONArray("content");

            for (int start = 0; start < content.length(); start++) {
                JSONObject contents = content.getJSONObject(start);

                String id = contents.getString("id");
                String orderCode = contents.getString("orderCode");
                String createdDate = contents.getString("createdDate");
                String status = contents.getString("status");
                boolean emergency = contents.getBoolean("emergency");

                JSONObject RFacility = contents.getJSONObject("requestingFacility");
                String requestingFacility = RFacility.getString("name");

                JSONObject SFacility = contents.getJSONObject("supplyingFacility");
                String supplyingFacility = SFacility.getString("name");

                JSONObject program = contents.getJSONObject("program");
                String programName = program.getString("name");

                JSONObject processingP = contents.getJSONObject("processingPeriod");
                String periodName = processingP.getString("name");
                orderArrayList = new ArrayList<>();
                orderArrayList.add(new Order(id, orderCode, status, requestingFacility, supplyingFacility, programName, periodName, createdDate, emergency ));
                //  Log.i(TAG, " id: " + id + " Order Code: " + orderCode + " status " + status + " Requesting Facility " +
                //          requestingFacility + " SupplyingFacility " + supplyingFacility + " Program Name " + programName +
                //          " Period " + periodName + " date: " + createdDate + " Emergency: " + emergency);

            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

