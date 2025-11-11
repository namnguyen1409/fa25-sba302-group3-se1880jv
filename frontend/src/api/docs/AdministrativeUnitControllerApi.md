# AdministrativeUnitControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAdministrativeUnits**](AdministrativeUnitControllerApi.md#getadministrativeunits) | **GET** /api/common/administrative-units |  |



## getAdministrativeUnits

> CustomApiResponseListAdministrativeUnitResponse getAdministrativeUnits(level, parentCode)



### Example

```ts
import {
  Configuration,
  AdministrativeUnitControllerApi,
} from '';
import type { GetAdministrativeUnitsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AdministrativeUnitControllerApi(config);

  const body = {
    // 'PROVINCE' | 'DISTRICT' | 'WARD'
    level: level_example,
    // string (optional)
    parentCode: parentCode_example,
  } satisfies GetAdministrativeUnitsRequest;

  try {
    const data = await api.getAdministrativeUnits(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **level** | `PROVINCE`, `DISTRICT`, `WARD` |  | [Defaults to `undefined`] [Enum: PROVINCE, DISTRICT, WARD] |
| **parentCode** | `string` |  | [Optional] [Defaults to `undefined`] |

### Return type

[**CustomApiResponseListAdministrativeUnitResponse**](CustomApiResponseListAdministrativeUnitResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

